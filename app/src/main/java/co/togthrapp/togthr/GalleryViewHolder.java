package co.togthrapp.togthr;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by sabri on 16/12/17.
 */

class GalleryViewHolder extends RecyclerView.ViewHolder {

    ImageView photoImageView;

    public GalleryViewHolder(View view, int viewType) {
        super(view);
        photoImageView = view.findViewById(R.id.iv_photo);
    }
}
