package com.zoray.savori.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zoray.savori.R;
import com.zoray.savori.data.HistoryRow;

import java.util.ArrayList;
import java.util.List;

public class HistoryRecyclerAdapter
        extends RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<HistoryRow> historyRows;

    public HistoryRecyclerAdapter(Context context) {
        this.context = context;
        this.historyRows = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View anItem = LayoutInflater.from(context).inflate(
                R.layout.list_row_history, parent, false
        );
        return new ViewHolder(anItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HistoryRow row = historyRows.get(position);

        // TODO: IMPLEMENTATION
    }

    @Override
    public int getItemCount() {
        return 0;
                //historyRows.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView desc;
        private ImageView ivFood;
        private ImageView ivSeller;
        private RelativeLayout rowFrame;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.history_row_title);
            desc = (TextView) itemView.findViewById(R.id.history_row_desc);
            ivFood = (ImageView) itemView.findViewById(R.id.history_row_food);
            ivSeller = (ImageView) itemView.findViewById(R.id.history_row_seller);
            rowFrame = (RelativeLayout) itemView.findViewById(R.id.history_row_frame);
        }
    }
}
