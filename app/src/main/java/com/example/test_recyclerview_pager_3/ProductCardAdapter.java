package com.example.test_recyclerview_pager_3;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
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

    public static final int VIEW_TYPE_A = 0;
    public static final int VIEW_TYPE_B = 1;


    HashMap<Integer, Integer> mViewPagerState = new HashMap<>();

    public ProductCardAdapter(FragmentManager fragmentManager, ArrayList<Product> data1, ArrayList<Product> data2) {
        this.pagerList = data1;
        this.movieList = data2;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        Log.d("logcheck - ","onCreateViewHolder - viewtype :" + viewType);

        if (viewType==0) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, viewGroup, false);
            return new ViewHolder(v);
        } else {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_type2, viewGroup, false);
            return new ViewHolder2(v);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_A;
        } else {
            return VIEW_TYPE_B;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        Log.d("logcheck - ","onBindViewHolder - " + i + "th view");

        if (i==0){
            BannerPagerAdapter bannerPagerAdapter = new BannerPagerAdapter(fragmentManager, i);
            ((ViewHolder)viewHolder).vp.setAdapter(bannerPagerAdapter);
            ((ViewHolder)viewHolder).vp.setId(i+1);

            ((ViewHolder)viewHolder).vp.setClipToPadding(false);
//            int margin = 80;
            ((ViewHolder)viewHolder).vp.setPadding(80, 10, 80, 10);
            ((ViewHolder)viewHolder).vp.setPageMargin(40);

            if (mViewPagerState.containsKey(i)) {
                ((ViewHolder)viewHolder).vp.setCurrentItem(mViewPagerState.get(i));
            }
        } else {

            ((ViewHolder2)viewHolder).textView.setText(movieList.get(i-1).getTitle());
            ((ViewHolder2)viewHolder).imageView.setImageResource(movieList.get(i-1).getResourceID());

        }

    }



    @Override
    public int getItemCount() {
        return movieList.size()+1;
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {

        Log.d("logchceck","recycle item - " + holder.getAdapterPosition());

        if (holder.getAdapterPosition() == 0){
            mViewPagerState.put(holder.getAdapterPosition(), ((ViewHolder)holder).vp.getCurrentItem());

        }
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

        public TextView textView;
        public ImageView imageView;

        public ViewHolder2(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
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
