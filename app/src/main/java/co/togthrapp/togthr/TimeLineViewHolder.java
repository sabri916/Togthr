package co.togthrapp.togthr;

/**
 * Created by sabri on 15/12/17.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;

class TimeLineViewHolder extends RecyclerView.ViewHolder {

    private final TimelineView mTimelineView;
    protected TextView text;
    protected TextView tagsTextView;

    public TimeLineViewHolder(View view, int viewType) {
        super(view);

        mTimelineView = (TimelineView) itemView.findViewById(R.id.time_marker);
        mTimelineView.initLine(viewType);
        text = (TextView) itemView.findViewById(R.id.item_timeline_text);
        tagsTextView = itemView.findViewById(R.id.tv_tags);
    }
}
