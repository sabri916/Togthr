package co.togthrapp.togthr;

/**
 * Created by sabri on 15/12/17.
 */

import android.content.Context;
import android.view.LayoutInflater;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;

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
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {

        if(mFeedList.get(position) instanceof ChatModel) {
            String message = ((ChatModel) mFeedList.get(position)).getText();
            holder.text.setText(message);
            ArrayList<String> tags = (ArrayList<String>) mFeedList.get(position).getTags();
            if (tags != null){
                StringBuilder sb = new StringBuilder("");
                for (String t : tags) {
                    sb.append(t + " ");
                    holder.tagsTextView.setText(sb.toString());
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return (mFeedList!=null? mFeedList.size():0);
    }

}
