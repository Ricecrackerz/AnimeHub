package com.example.animehub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.animehub.models.Anime;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.parceler.Parcels;

import okhttp3.Headers;

public class AnimeDetailedPage extends YouTubeBaseActivity {
    TextView animeTitle;
    TextView animeOverview;
    RatingBar animeRatingBar;
    TextView  animeRatingScore;
    int ratingAjusted=10;
    YouTubePlayerView youTubePlayerView;
    public static final String YOUTUBE_API_KEY= "AIzaSyBh8rS5UMSZP9mZVRnRgaiTmyCrx4NozYA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_detailed_page);

        animeTitle= findViewById(R.id.animeTitle);
        animeOverview= findViewById(R.id.animeOverview);
        animeRatingBar= findViewById(R.id.animeRatingBar);
        youTubePlayerView= findViewById(R.id.player);
        animeRatingScore= findViewById(R.id.animeRatingScore);
        String title= getIntent().getStringExtra("title");
        Anime anime= Parcels.unwrap(getIntent().getParcelableExtra("anime"));


        animeTitle.setText(anime.getCanonicalTitle());
        animeOverview.setText(anime.getSynopsis());
        animeRatingBar.setRating((float)anime.getAverageRating()/ratingAjusted);

        // Getting and formatting the rating score
        double adjustedRatingScore= anime.getAverageRating()/ratingAjusted;
        String roundedScore= String.format("%.2f", adjustedRatingScore);
        String displayScore= roundedScore.concat(" /10");
        animeRatingScore.setText(displayScore);




        // For when use clicks on youtube video
        youTubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d("AnimeDetailedPage", "onInitializationSuccess");
                youTubePlayer.cueVideo(anime.getYouTubeVideoId());
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("AnimeDetailedPage", "onInitializationFailure");

            }
        });


    }
}