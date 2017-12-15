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
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_timeline, container, false);

        //Profile icon
        ImageView profileImageView = (ImageView) fragmentView.findViewById(R.id.iv_icon_profile);
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
            }
        });

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

}
