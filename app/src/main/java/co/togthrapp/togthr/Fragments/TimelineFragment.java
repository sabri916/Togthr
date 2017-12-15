package co.togthrapp.togthr.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import co.togthrapp.togthr.ProfileActivity;
import co.togthrapp.togthr.R;
import co.togthrapp.togthr.TimeLineAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimelineFragment extends Fragment {


    public TimelineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Get data from Firebse

        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_timeline, container, false);

        try {
            setUpTopBar(fragmentView);
        } catch (IOException e) {
            e.printStackTrace();
        }

        RecyclerView mRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.rv_timeline);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                        false));
        mRecyclerView.setHasFixedSize(true);

        ArrayList<String> chatterArrayList = new ArrayList<>();
        chatterArrayList.add("Yellow");
        chatterArrayList.add("Blue");
        chatterArrayList.add("Green");
        chatterArrayList.add("Red");

        //set adapter
        TimeLineAdapter mTimeLineAdapter = new TimeLineAdapter(chatterArrayList);
        mRecyclerView.setAdapter(mTimeLineAdapter);
        return fragmentView;
    }

    private void setUpTopBar(View view) throws IOException {
        ///////////////Profile icon
        final ImageView profileImageView = (ImageView) view.findViewById(R.id.iv_icon_profile);
        String uid = FirebaseAuth.getInstance().getUid();
        final File localFile = File.createTempFile("profile","png");
        StorageReference storageReference =
                FirebaseStorage.getInstance().getReference("profile_photo/" + uid + "/profile.png");
        storageReference.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Picasso.with(getContext()).load(localFile)
                                .into(profileImageView);
                    }
                });
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

}
