package co.togthrapp.togthr.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import co.togthrapp.togthr.GalleryAdapter;
import co.togthrapp.togthr.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment {

    RecyclerView mRecyclerView;

    public GalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ArrayList<Integer> ImageIdArrayList = new ArrayList<>();
        ImageIdArrayList.add(R.drawable.dream_oasis);
        ImageIdArrayList.add(R.drawable.dream_oasis);
        ImageIdArrayList.add(R.drawable.dream_oasis);
        ImageIdArrayList.add(R.drawable.dream_oasis);
        ImageIdArrayList.add(R.drawable.dream_oasis);
        ImageIdArrayList.add(R.drawable.dream_oasis);

        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        mRecyclerView = rootView.findViewById(R.id.rv_gallery);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecyclerView.setAdapter(new GalleryAdapter(getContext(), ImageIdArrayList));

        return rootView;
    }

}
