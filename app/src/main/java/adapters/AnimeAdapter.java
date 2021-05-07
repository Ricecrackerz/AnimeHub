package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.animehub.AnimeDetailedPage;
import com.example.animehub.R;
import com.example.animehub.fragments.Home;
import com.example.animehub.models.Anime;

import org.parceler.Parcels;

import java.util.List;


public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.ViewHolder> {


    // leave empty for now
    Context context;
    List<Anime> animes;

    public AnimeAdapter( Context context, List<Anime> animes) {
        this.context =context;
        this.animes = animes;
    }

    // inflating a layout from xml and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View animeView = LayoutInflater.from(context).inflate(R.layout.item_anime, parent, false);
        return new ViewHolder(animeView);
    }

    // populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // get the anime passed in position
        Anime anime = animes.get(position);
        // bind anime data into viewHolder
        holder.bind(anime);

    }

    // return the total count of items in the list
    @Override
    public int getItemCount() {

        return animes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout animeContainer;
        TextView animeTitle;
        TextView animeOverview;
        ImageView animePoster;
        RatingBar animeRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            animeTitle= itemView.findViewById(R.id.animeTitle);
            animeOverview= itemView.findViewById(R.id.animeOverview);
            animePoster= itemView.findViewById(R.id.animePoster);
            animeRating = itemView.findViewById(R.id.animeRating);
            animeContainer = itemView.findViewById(R.id.animeContainer);
        }

        public void bind(Anime anime) {
            animeTitle.setText(anime.getCanonicalTitle());
            animeOverview.setText(anime.getSynopsis());
            Glide.with(context).load(anime.getOriginalPosterPath()).into(animePoster);
            animeRating.setRating((float) anime.getAverageRating()/10);

            //1. Register click listener on whole row
            animeContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //2. Navigate to a new activity on tap
                   Intent i= new Intent(context, AnimeDetailedPage.class);
                   i.putExtra("anime", Parcels.wrap(anime));
                   context.startActivity(i);
                }
            });
        }
    }
    }





