package com.example.animehub.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.animehub.R;
import com.example.animehub.models.Anime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.annotation.Nullable;

import okhttp3.Headers;


public class Home extends Fragment {
    public static final String ANIME_POPULARITYSORTED_URL = "https://kitsu.io/api/edge/anime?sort=popularityRank";
    public static final String TAG = "HomeFragment";

    List<Anime> animes;

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
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(ANIME_POPULARITYSORTED_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG,"onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try{
                    JSONArray data = jsonObject.getJSONArray("data");
                    Log.i(TAG,"data: " + data.toString());
                    animes = Anime.fromJsonArray(data);
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

    }//onViewCreated


}