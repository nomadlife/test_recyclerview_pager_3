package com.example.test_recyclerview_pager_3;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.HashMap;


public class ProductCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Product> pagerList;
    private ArrayList<Product> movieList;
    private FragmentManager fragmentManager;

    HashMap<Integer, Integer> mViewPagerState = new HashMap<>();

    public ProductCardAdapter(FragmentManager fragmentManager, ArrayList<Product> data1, ArrayList<Product> data2) {
        this.pagerList = data1;
        this.movieList = data2;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        if (i==0) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, viewGroup, false);
            return new ViewHolder(v);
        } else {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row2, viewGroup, false);
            return new ViewHolder2(v);
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int i) {

        if (i==0){
            BannerPagerAdapter bannerPagerAdapter = new BannerPagerAdapter(fragmentManager, i);
            viewHolder.vp.setAdapter(bannerPagerAdapter);
            viewHolder.vp.setId(i+1);

            if (mViewPagerState.containsKey(i)) {
                viewHolder.vp.setCurrentItem(mViewPagerState.get(i));
            }
        } else {

            BlankFragment.newInstance(movieList.get(i));

            if (mViewPagerState.containsKey(i)) {
                viewHolder.frame.setCurrentItem(mViewPagerState.get(i));
            }
        }


    }

    @Override
    public int getItemCount() {
        return movieList.size()+1;
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        mViewPagerState.put(holder.getAdapterPosition(), holder.vp.getCurrentItem());
        super.onViewRecycled(holder);
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewPager vp;

        public ViewHolder(View itemView) {
            super(itemView);
            vp = itemView.findViewById(R.id.vp);
        }
    }

    public static class ViewHolder2 extends RecyclerView.ViewHolder {

        public FrameLayout frameLayout;

        public ViewHolder2(View itemView) {
            super(itemView);
            frameLayout = itemView.findViewById(R.id.frame);
        }
    }

    public class BannerPagerAdapter extends FragmentPagerAdapter {

        public BannerPagerAdapter(FragmentManager fm, int i) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return BlankFragment.newInstance(pagerList.get(position));
        }

        @Override
        public int getCount() {
            return pagerList.size();
        }
    }

}
