package com.zoray.savori.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.zoray.savori.R;
import com.zoray.savori.data.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amandayin.
 */

public class SearchRecyclerViewAdapter extends  RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder>{

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(context).inflate(
                R.layout.item_row, parent, false
        );
        return new ViewHolder(row);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Item item = items.get(position);
        holder.tvName.setText(item.getName());
        holder.cbBought.setChecked(item.isBought());
        holder.cbBought.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,
                        "Item " + position,
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private Context context;
    private List<Item> items;
    public SearchRecyclerViewAdapter(Context context){
        this.context = context;
        items = new ArrayList<Item>();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvName;
        private final CheckBox cbBought;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            cbBought = (CheckBox) itemView.findViewById(R.id.cbBought);
        }
    }
}
