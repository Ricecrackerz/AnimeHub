package com.example.animehub.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Anime {

    int animeID;
    String canonicalTitle;
    String enTitle;
    String synopsis;
    int averageRating;
    String originalPosterPath;
    String youTubeVideoId;

//empty constructor for parceler
    public Anime(){}

    public Anime(JSONObject jsonObject) throws JSONException{
        animeID =  jsonObject.getInt("id");
        averageRating = jsonObject.getJSONObject("attributes").getInt("averageRating");
        canonicalTitle = jsonObject.getJSONObject("attributes").getString("canonicalTitle");
        synopsis = jsonObject.getJSONObject("attributes").getString("synopsis");
        originalPosterPath = jsonObject.getJSONObject("attributes").getJSONObject("posterImage").getString("original");
        youTubeVideoId = jsonObject.getJSONObject("attributes").getString("youtubeVideoId");


    }//constructor

    public static List<Anime> fromJsonArray(JSONArray animeJsonArray) throws JSONException {
        List<Anime> animes= new ArrayList<>();
        for(int i=0; i < animeJsonArray.length(); i++){
            animes.add(new Anime(animeJsonArray.getJSONObject(i)));
        }
        return animes;

    }//fromJsonArray
    public int getAnimeID(){
        return animeID;
    }
    public String getCanonicalTitle(){
        return  canonicalTitle;
    }
    public String getSynopsis(){
        return  synopsis;
    }
    public int getAverageRating(){
        return averageRating;
    }
    public String getOriginalPosterPath(){
        //TODO: make this easier for glide to deal with
        return originalPosterPath;
    }
    public String getYouTubeVideoId(){
        return youTubeVideoId;
    }

}
