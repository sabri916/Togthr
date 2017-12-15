package co.togthrapp.togthr.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import co.togthrapp.togthr.DatabaseModel.BaseTimelineItem;
import co.togthrapp.togthr.DatabaseModel.ChatModel;
import co.togthrapp.togthr.ProfileActivity;
import co.togthrapp.togthr.R;
import co.togthrapp.togthr.TimeLineAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimelineFragment extends Fragment {

    private EditText chatEditText;
    private ImageView sendImageView;
    private RecyclerView mRecyclerView;

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
        TimeLineAdapter mTimeLineAdapter = new TimeLineAdapter(chatterArrayList);
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
