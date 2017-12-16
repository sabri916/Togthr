package co.togthrapp.togthr;

/**
 * Created by sabri on 15/12/17.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.togthrapp.togthr.DatabaseModel.BaseTimelineItem;
import co.togthrapp.togthr.DatabaseModel.ChatModel;

/**
 * Created by HP-HP on 05-12-2015.
 */
public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineViewHolder> {

    private List<BaseTimelineItem> mFeedList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public TimeLineAdapter(List<BaseTimelineItem> feedList) {
        mFeedList = feedList;
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int position = viewType;
        int layoutId = 0;
        if(mFeedList.get(position) instanceof ChatModel){
            layoutId = R.layout.item_timeline_text;
        }
        mContext = parent.getContext();
        mLayoutInflater = LayoutInflater.from(mContext);
        View view = mLayoutInflater.inflate(layoutId,parent,false);
        return new TimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(final TimeLineViewHolder holder, int position) {

        if(mFeedList.get(position) instanceof ChatModel) {
            ChatModel chatModel = (ChatModel) mFeedList.get(position);
            String message = chatModel.getText();
            holder.text.setText(message);
            ArrayList<String> tags = (ArrayList<String>) mFeedList.get(position).getTags();
            if (tags != null) {
                StringBuilder sb = new StringBuilder("");
                for (String t : tags) {
                    sb.append(t + " ");
                }
                holder.tagsTextView.setText(sb.toString());
            }

            String uid = chatModel.getAuthor();
            Log.i("uid",uid);

            StorageReference storageReference =
                    FirebaseStorage.getInstance().
                            getReference("profile_photo/" + uid + "/profile.png");


            File localFile = null;
            try {
                localFile = File.createTempFile("images", "png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            final File finalLocalFile = localFile;
            storageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Picasso.with(mContext)
                                    .load(finalLocalFile)
                                    .into(new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                            holder.timelineView.setMarker(new BitmapDrawable(mContext.getResources(), bitmap));
                                        }

                                        @Override
                                        public void onBitmapFailed(Drawable errorDrawable) {
                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {
                                        }
                                    });
                        }
                    });
        }
    }

    @Override
    public int getItemCount() {
        return (mFeedList!=null? mFeedList.size():0);
    }

}
