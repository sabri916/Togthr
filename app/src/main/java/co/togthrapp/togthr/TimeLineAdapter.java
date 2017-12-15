package co.togthrapp.togthr;

/**
 * Created by sabri on 15/12/17.
 */

import android.content.Context;
import android.view.LayoutInflater;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.github.vipulasri.timelineview.TimelineView;

import java.util.List;

import co.togthrapp.togthr.DatabaseModel.BaseTimelineItem;

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
        mContext = parent.getContext();
        mLayoutInflater = LayoutInflater.from(mContext);
        View view = mLayoutInflater.inflate(R.layout.item_timeline,parent,false);

        return new TimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {

        //String timeLineModel = mFeedList.get(position);
        //holder.text.setText(timeLineModel);
    }

    @Override
    public int getItemCount() {
        return (mFeedList!=null? mFeedList.size():0);
    }

}
