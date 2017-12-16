package co.togthrapp.togthr;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sabri on 16/12/17.
 */

class StoreViewHolder extends RecyclerView.ViewHolder {

    ImageView headerImage;
    TextView storeTitle;
    TextView offerDescription;
    Button addToPostButton;

    public StoreViewHolder(View itemView) {
        super(itemView);

        headerImage = itemView.findViewById(R.id.iv_offer_head);
        storeTitle = itemView.findViewById(R.id.tv_offer_title);
        offerDescription = itemView.findViewById(R.id.tv_offer_description);
        addToPostButton = itemView.findViewById(R.id.btn_share_offer);
    }
}
