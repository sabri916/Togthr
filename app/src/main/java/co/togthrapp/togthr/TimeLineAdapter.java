package co.togthrapp.togthr;

/**
 * Created by sabri on 15/12/17.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
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
import co.togthrapp.togthr.DatabaseModel.PictureModel;
import co.togthrapp.togthr.DatabaseModel.VoteModel;

/**
 * Created by HP-HP on 05-12-2015.
 */
public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineViewHolder> {

    public List<BaseTimelineItem> getFeedList() {
        return mFeedList;
    }

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
        layoutId = R.layout.item_timeline_text;

        mContext = parent.getContext();
        mLayoutInflater = LayoutInflater.from(mContext);
        View view = mLayoutInflater.inflate(layoutId,parent,false);
        return new TimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(final TimeLineViewHolder holder, int position) {

        final BaseTimelineItem baseTimelineItem = mFeedList.get(position);
        //set tags
        holder.itemContainerLinearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Tags");

                final EditText input = new EditText(mContext);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tag = "#" + input.getText().toString();
                        ArrayList<String> tags = new ArrayList<>();
                        tags.add(tag);
                        baseTimelineItem.setTags(tags);
                        notifyDataSetChanged();
                    }
                });

                builder.show();
                return true;
            }
        });

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
        else if(mFeedList.get(position) instanceof VoteModel){

            VoteModel voteModel = (VoteModel) mFeedList.get(position);
            ArrayList<String> tags = (ArrayList<String>) mFeedList.get(position).getTags();
            if (tags != null) {
                StringBuilder sb = new StringBuilder("");
                for (String t : tags) {
                    sb.append(t + " ");
                }
                holder.tagsTextView.setText(sb.toString());
            }

            holder.voteLinearLayout.setVisibility(View.VISIBLE);
            holder.text.setText(voteModel.getVoteQuestion());

            String uid = voteModel.getAuthor();
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
        else{
            PictureModel pictureModel = (PictureModel) mFeedList.get(position);
            holder.imageView.setImageDrawable(Drawable.createFromPath(pictureModel.getPictureFile().getPath()));
            ArrayList<String> tags = (ArrayList<String>) mFeedList.get(position).getTags();
            if (tags != null) {
                StringBuilder sb = new StringBuilder("");
                for (String t : tags) {
                    sb.append(t + " ");
                }
                holder.tagsTextView.setText(sb.toString());
            }

            String uid = pictureModel.getAuthor();
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
