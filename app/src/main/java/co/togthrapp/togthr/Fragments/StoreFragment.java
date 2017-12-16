package co.togthrapp.togthr.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import co.togthrapp.togthr.DatabaseModel.StoreModel;
import co.togthrapp.togthr.R;
import co.togthrapp.togthr.StoreAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private StoreAdapter mStoreAdapter;

    public StoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_store, container, false);

        mRecyclerView = rootView.findViewById(R.id.rv_store);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                        false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        //Offers ArrayList
        ArrayList<StoreModel> offerArrayList = new ArrayList<>();
        offerArrayList.add(new StoreModel("Dream Oasis","5 tickes for 5 Rials!",R.drawable.dream_oasis));
        offerArrayList.add(new StoreModel("Dream Oasis2","52 tickes for 5 Rials!",R.drawable.dream_oasis));

        mStoreAdapter = new StoreAdapter(getContext(),offerArrayList,getActivity());
        mRecyclerView.setAdapter(mStoreAdapter);

        return rootView;
    }

}
