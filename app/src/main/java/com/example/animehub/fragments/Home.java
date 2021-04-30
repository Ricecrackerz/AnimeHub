package com.example.animehub.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.animehub.EndlessRecyclerViewScrollListener;
import com.example.animehub.R;
import com.example.animehub.models.Anime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


import adapters.AnimeAdapter;
import okhttp3.Headers;


public class Home extends Fragment {
    public static final String ANIME_POPULARITYSORTED_URL = "https://kitsu.io/api/edge/anime?page%5Blimit%5D=20&page%5Boffset%5D=0&sort=popularityRank";
    public static final String TAG = "HomeFragment";
    public String nextUrl;
    public String firstUrl;
    public String lastUrl;
    AnimeAdapter animeAdapter;

    List<Anime> animes;
    EndlessRecyclerViewScrollListener scrollListener;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        RecyclerView rvAnimes= view.findViewById(R.id.rvAnimes);
        animes= new ArrayList<>();

        //create the adapter
        animeAdapter = new AnimeAdapter(getContext(), animes);
        // set the adapter on the recyclerview
        rvAnimes.setAdapter(animeAdapter);
        //Set a layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager((getContext()));
        rvAnimes.setLayoutManager(layoutManager);



        AsyncHttpClient client = new AsyncHttpClient();
        client.get(ANIME_POPULARITYSORTED_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG,"onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try{
                    JSONArray data = jsonObject.getJSONArray("data");
                    firstUrl = jsonObject.getJSONObject("links").getString("first");
                    nextUrl =  jsonObject.getJSONObject("links").getString("next");
                    lastUrl =  jsonObject.getJSONObject("links").getString("last");

                    Log.i(TAG,"data: " + data.toString() + "\n");
                    Log.i(TAG,"firstUrl: " + firstUrl + "\n");
                    Log.i(TAG,"nextUrl: " + nextUrl + "\n");
                    Log.i(TAG,"lastUrl: " + lastUrl + "\n");
                    animes.addAll(Anime.fromJsonArray(data));
                    animeAdapter.notifyDataSetChanged();
                    Log.i(TAG,"Animes: " + animes.size());

                    //this for loop is to check if it works
                    for(int j = 0; j < animes.size();j++){
                        Log.i(TAG,"Title: " + animes.get(j).getCanonicalTitle() + "\n");
                        Log.i(TAG,"ID: " + animes.get(j).getAnimeID() + "\n");
                    }//for
                } catch (JSONException e){
                    Log.e(TAG,"Hit json exception");
                }//catch
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {

            }
        });
        //Code for Infinite Pagination
        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.i(TAG,"onLoadMore: " + page);
                loadMoreData();
            }
        };
        //Adds the scroll listener to RecyclerView
        rvAnimes.addOnScrollListener(scrollListener);

    }//onViewCreated

    private void loadMoreData() {

        // 1. Send an API request to retrieve appropriate paginated data
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(nextUrl, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG,"onSuccess for loadMoreData");
                JSONObject jsonObject = json.jsonObject;

                try {
                    JSONArray data = jsonObject.getJSONArray("data");
                    // 2. Deserialize and construct new model objects from the API response
                    // 3. Append the new data objects to the existing set of items inside the array of items
                    animes.addAll(Anime.fromJsonArray(data));
                    animeAdapter.notifyDataSetChanged();

                    //TODO: Figure out some way to handle the case where we hit the end of the database
                    nextUrl = jsonObject.getJSONObject("links").getString("next");

                } catch (JSONException e) {
                    e.printStackTrace();
                }//catch
            }//onSuccess

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {

            }//onFailure
        });



    }


}