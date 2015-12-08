package com.zoray.savori.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.zoray.savori.R;
import com.zoray.savori.fragments.FragmentAccount;
import com.zoray.savori.fragments.FragmentDefault;
import com.zoray.savori.fragments.FragmentHistory;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public MainFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FragmentDefault.getInstance();
            case 1:
                return FragmentHistory.getInstance();
            case 2:
                return FragmentAccount.getInstance();
            default:
                return FragmentDefault.getInstance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    private int[] imageResId = {
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher
    };

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable icon = ContextCompat.getDrawable(context, imageResId[position]);
        icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
        SpannableString ss = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(icon, ImageSpan.ALIGN_BOTTOM);
        ss.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return ss;
    }
}
