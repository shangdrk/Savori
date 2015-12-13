package com.zoray.savori.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.zoray.savori.MainActivity;
import com.zoray.savori.R;
import com.zoray.savori.ResultActivity;
import com.zoray.savori.adapters.SearchRecyclerViewAdapter;
import com.zoray.savori.data.Dish;
import com.zoray.savori.data.SearchResult;
import com.zoray.savori.data.Transaction;

import java.util.Date;

public class FragmentResultDetails extends Fragment {

    public final static String TAG = "FRAGMENT_RESULT_DETAILS";
    private String resultId;
    private RelativeLayout layoutContent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_result_details, container, false);

        resultId = ((ResultActivity) getActivity()).getResultId();

        layoutContent = (RelativeLayout) rootView.findViewById(R.id.layoutContent_Detail);

        final TextView tvDishName = (TextView) rootView.findViewById(R.id.dishNameDetail);
        final TextView tvDishPrice = (TextView) rootView.findViewById(R.id.dishPriceDetail);
        final ImageView ivDish = (ImageView) rootView.findViewById(R.id.ivDishImageDetail);
        final TextView tvChefName = (TextView) rootView.findViewById(R.id.tvChefNameDetail);
        final ImageView ivChef = (ImageView) rootView.findViewById((R.id.ivChefDetail));

        final ParseUser user = ParseUser.getCurrentUser();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Dish");
        query.getInBackground(resultId, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                Dish dish = new Dish();
                if (e == null) {
                    String dishName = ((ResultActivity) getActivity()).toTitleCase(object.getString("dishName"));
                    String dishPrice = object.getString("price");
                    String parseId = object.getObjectId();
                    ParseFile dishImage = object.getParseFile("picture");
                    ParseUser chef = (ParseUser) object.get("chef");
                    byte[] imageBytes = new byte[0];
                    try {
                        imageBytes = dishImage.getData();
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }

                    dish.setDishName(dishName);
                    dish.setPrice(dishPrice);
                    dish.setParseID(parseId);
                    dish.setChef(chef);
                    if (imageBytes != null) {
                        dish.setDishImage(imageBytes);
                    }
                } else {
                    // something went wrong
                }

                tvDishName.setText(dish.getDishName());
                ParseUser chef = null;
                try {
                    chef = dish.getChef().fetchIfNeeded();
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                if (chef != null) {
                    tvChefName.setText(chef.getString("firstName") + " " + chef.getString("lastName"));
                }
                tvDishPrice.setText(dish.getPrice() + " Ft");
                Bitmap dishImageBmp = BitmapFactory.decodeByteArray(dish.getDishImage(), 0,
                        dish.getDishImage().length);
                ivDish.setImageBitmap(dishImageBmp);

                byte[] chefImageBytes = new byte[0];
                try {
                    chefImageBytes = chef.getParseFile("picture").getData();
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                if (chefImageBytes != null) {
                    Bitmap chefImageBmp = BitmapFactory.decodeByteArray(chefImageBytes, 0, chefImageBytes.length);
                    ivChef.setImageBitmap(chefImageBmp);
                }
            }
        });

        final TextView tvOrder = (TextView) rootView.findViewById(R.id.tvOrder);
        tvOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ParseUser user2 = ParseUser.getCurrentUser();
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Dish");
                query.getInBackground(resultId, new GetCallback<ParseObject>() {
                            @Override
                            public void done(ParseObject object, ParseException e) {
                                if (e == null) {
                                    //TODO: actually place the order!
                                    Transaction newOrder = new Transaction();
                                    newOrder.put("price", Integer.parseInt(object.getString("price")));
                                    newOrder.put("buyer", user2);
                /*ParseUser chef = null;
                try{
                    chef = dish.getChef().fetchIfNeeded();
                }catch (ParseException ex){
                    ex.printStackTrace();
                }
                if (chef!= null){
                    newOrder.put("seller", chef);
                }*/
                                    newOrder.put("seller", object.get("chef"));
                                    newOrder.put("food", object);
                                    newOrder.put("orderTime", new Date());
                                    newOrder.put("isFinished", false);
                                    newOrder.saveInBackground(new SaveCallback() {
                                        public void done(ParseException e) {
                                            if (e == null) {
                                                showSnackBarMessage("Your order has been placed!");
                                            } else {
                                                Log.d("mylog", e.getMessage());
                                                showSnackBarMessage("Sorry, this item is not available for now.");

                                            }
                                        }
                                    });

                                }
                            }
                        }
                );
            }
        });
        return rootView;
    }


    private void showSnackBarMessage(String message) {
        Snackbar.make(layoutContent,
                message,
                Snackbar.LENGTH_LONG
        ).setAction("Got it", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }).show();
    }
}
