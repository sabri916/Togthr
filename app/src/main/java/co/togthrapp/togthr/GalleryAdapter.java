package co.togthrapp.togthr;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by sabri on 16/12/17.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryViewHolder> {

    Context context;
    ArrayList imageIdArrayList;

    public GalleryAdapter(Context context, ArrayList<Integer> imageIdArrayList) {
        this.context = context;
        this.imageIdArrayList = imageIdArrayList;
    }


    @Override
    public GalleryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.gallery_item;

        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutId,parent,false);
        return new GalleryViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(GalleryViewHolder holder, int position) {
        holder.photoImageView.setImageResource((Integer) imageIdArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return imageIdArrayList.size();
    }
}
