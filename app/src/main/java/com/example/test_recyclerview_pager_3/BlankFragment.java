package com.example.test_recyclerview_pager_3;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class BlankFragment extends Fragment {
    private String title;
    private int resourceId;

    public BlankFragment() {
    }

    public static BlankFragment newInstance(Product item) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString("title", item.getTitle());
        args.putInt("resourceId",item.getResourceID());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString("title");
            resourceId = getArguments().getInt("resourceId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.image_type, container, false);
        TextView tv = v.findViewById(R.id.textView);
        tv.setText(title);
        ImageView iv = v.findViewById(R.id.imageView);
        iv.setImageResource(resourceId);

        return v;
    }

}
