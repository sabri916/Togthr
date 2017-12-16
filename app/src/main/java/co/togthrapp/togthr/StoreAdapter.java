package co.togthrapp.togthr;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import co.togthrapp.togthr.DatabaseModel.StoreModel;
import co.togthrapp.togthr.Fragments.TimelineFragment;

/**
 * Created by sabri on 16/12/17.
 */

public class StoreAdapter extends RecyclerView.Adapter<StoreViewHolder> {

    private Context mContext;
    private ArrayList<StoreModel> storeArrayList;
    private MainActivity activity;

    public StoreAdapter(Context mContext, ArrayList<StoreModel> storeArrayList, FragmentActivity activity) {
        this.mContext = mContext;
        this.storeArrayList = storeArrayList;
        this.activity = (MainActivity) activity;
    }

    @Override
    public StoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_store,parent,false);
        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StoreViewHolder holder, int position) {
        final StoreModel storeModel = storeArrayList.get(position);
        holder.headerImage.setImageResource(storeModel.getImageId());
        holder.storeTitle.setText(storeModel.getTitle());
        holder.offerDescription.setText(storeModel.getOfferText());
        holder.addToPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.postOffer(storeModel.getOfferText(),new TimelineFragment());
            }
        });
    }

    @Override
    public int getItemCount() {
        return storeArrayList.size();
    }
}
