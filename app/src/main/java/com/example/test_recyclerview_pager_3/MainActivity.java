package com.example.test_recyclerview_pager_3;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Product> pagerList = new ArrayList();
    private ArrayList<Product> movieList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.initializeData();

        RecyclerView rv = findViewById(R.id.rv);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutManager);

        Log.d("logcheck","" + pagerList.size() + " " + movieList.size());
        ProductCardAdapter mCardAdapter = new ProductCardAdapter(getSupportFragmentManager(), pagerList, movieList);
        rv.setAdapter(mCardAdapter);
    }

    public void initializeData()
    {

        pagerList.add(new Product(R.drawable.movie1, "어벤져스"));
        pagerList.add(new Product(R.drawable.movie2, "미션임파서블"));
        pagerList.add(new Product(R.drawable.movie3, "아저씨"));

        movieList.add(new Product(R.drawable.movie4, "범죄도시"));
        movieList.add(new Product(R.drawable.movie5, "공공의적"));
        movieList.add(new Product(R.drawable.movie6, "맨인블랙"));

        movieList.add(new Product(R.drawable.movie7, "고질라"));
        movieList.add(new Product(R.drawable.movie8, "캡틴마블"));
        movieList.add(new Product(R.drawable.movie9, "아이언맨"));
    }


}
