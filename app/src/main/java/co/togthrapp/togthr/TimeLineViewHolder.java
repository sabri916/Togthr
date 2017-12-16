package co.togthrapp.togthr;

/**
 * Created by sabri on 15/12/17.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;

class TimeLineViewHolder extends RecyclerView.ViewHolder {


    protected TextView text;
    protected TextView tagsTextView;
    protected TimelineView timelineView;
    protected ImageView imageView;
    protected LinearLayout voteLinearLayout;
    protected LinearLayout itemContainerLinearLayout;


    public TimeLineViewHolder(View view, int viewType) {
        super(view);

        timelineView = (TimelineView) itemView.findViewById(R.id.time_marker);
        timelineView.initLine(viewType);
        tagsTextView = itemView.findViewById(R.id.tv_tags);

        text = (TextView) itemView.findViewById(R.id.item_timeline_text);

        imageView = itemView.findViewById(R.id.iv_timeline_picture);

        voteLinearLayout = itemView.findViewById(R.id.ll_vote_container);

        itemContainerLinearLayout = itemView.findViewById(R.id.item_container);

    }
}
