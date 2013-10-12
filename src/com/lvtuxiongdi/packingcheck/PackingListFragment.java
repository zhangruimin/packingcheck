package com.lvtuxiongdi.packingcheck;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

public final class PackingListFragment extends Fragment {

    public static PackingListFragment newInstance(String content) {
        PackingListFragment fragment = new PackingListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.layout_list, container, false);
        GridLayout viewById = (GridLayout)layout.findViewById(R.id.GridLayout1);
        for(int i=0;i<12;i++){
            TextView text = new TextView(getActivity());
            text.setGravity(Gravity.CENTER);
            text.setText("a");
            text.setTextSize(20 * getResources().getDisplayMetrics().density);
            text.setPadding(20, 20, 20, 20);
            viewById.addView(text);
        }


        return layout;
    }
}
