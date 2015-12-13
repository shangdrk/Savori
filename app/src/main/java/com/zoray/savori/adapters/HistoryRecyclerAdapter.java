package com.zoray.savori.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zoray.savori.R;
import com.zoray.savori.data.Transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class HistoryRecyclerAdapter
        extends RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Transaction> transactions;

    public HistoryRecyclerAdapter(Context context) {
        this.context = context;
        this.transactions = new ArrayList<>();
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
        Transaction row = transactions.get(position);

        holder.title.setText(row.getFoodTitle());
        holder.desc.setText(convertToDesc(row));

        Bitmap food = BitmapFactory.decodeByteArray(row.getFoodImage(), 0,
                row.getFoodImage().length);
        holder.ivFood.setImageBitmap(food);

        Bitmap seller = BitmapFactory.decodeByteArray(row.getSellerImage(), 0,
                row.getSellerImage().length);
        holder.ivSeller.setImageBitmap(seller);

        holder.rowFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: IMPLEMENT
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public void update(List<Transaction> newList) {
        transactions = newList;
        notifyDataSetChanged();
    }

    private String convertToDesc(Transaction row) {
        String time = dateToTime(row.getOrderTime());
        return String.format(context.getString(R.string.order_desc_placeholder),
                row.getPrice(), time);
    }

    private String dateToTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, HH:mm");
        sdf.setTimeZone(TimeZone.getDefault());

        return sdf.format(date);
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
