package co.togthrapp.togthr.Fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import co.togthrapp.togthr.DatabaseModel.BaseTimelineItem;
import co.togthrapp.togthr.DatabaseModel.ChatModel;
import co.togthrapp.togthr.DatabaseModel.PictureModel;
import co.togthrapp.togthr.ProfileActivity;
import co.togthrapp.togthr.R;
import co.togthrapp.togthr.TimeLineAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimelineFragment extends Fragment {

    private static final int REQUEST_CODE_PICK_IMAGE = 1234;
    private EditText chatEditText;
    private ImageView sendImageView;
    private RecyclerView mRecyclerView;
    private TimeLineAdapter mTimeLineAdapter;

    public TimelineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Get data from Firebse

        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_timeline, container, false);

        setUpTextBox(fragmentView);

        mRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.rv_timeline);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                        false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        //read from database

        final ArrayList<BaseTimelineItem> chatterArrayList = new ArrayList<>();
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("timeline");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String type = (String) dataSnapshot.child("type").getValue();
                if(type.equals("text")){
                    ChatModel chatModel = dataSnapshot.getValue(ChatModel.class);
                    chatterArrayList.add(chatModel);
                    mRecyclerView.scrollToPosition(chatterArrayList.size()-1);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //set adapter
        mTimeLineAdapter = new TimeLineAdapter(chatterArrayList);
        mRecyclerView.setAdapter(mTimeLineAdapter);
        mRecyclerView.scrollToPosition(chatterArrayList.size()-1);
        return fragmentView;
    }

    private void setUpTextBox(View view) {
        chatEditText = view.findViewById(R.id.et_chat);
        chatEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                storeTextInDatabase(chatEditText.getText().toString());
                chatEditText.setText("");
                return true;
            }
        });

        sendImageView = view.findViewById(R.id.iv_send_media);
        sendImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("send_media","clicked");
                String[] list = {"Picture", "Vote"};
                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setItems(list, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position) {
                                if(position == 0){
                                    Intent intent = new Intent();
                                    intent.setType("image/*");
                                    intent.setAction(Intent.ACTION_GET_CONTENT);
                                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE_PICK_IMAGE);
                                }
                            }
                        }).create();
                alertDialog.show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                return;
            }
            InputStream inputStream = null;
            try {
                inputStream = getContext().getContentResolver().openInputStream(data.getData());
                File imageFile = inputStreamToFile(inputStream);
                mTimeLineAdapter.getFeedList().add(new PictureModel())


            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }

    private File inputStreamToFile(InputStream inputStream) throws Exception{
        File localFile = File.createTempFile("images", "png");
        OutputStream outputStream = new FileOutputStream(localFile);
        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }
        return localFile;
    }

    private void storeTextInDatabase(String message) {
        String uid = FirebaseAuth.getInstance().getUid();
        if(message == null){
            message = "*silence*";
        }
        ChatModel chatModel = new ChatModel(uid,
                "text",
                message,
                new ArrayList<String>());

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("timeline");
        myRef.push().setValue(chatModel);
    }
}
