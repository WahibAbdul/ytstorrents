package codeclobber.com.ytsbrowser.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import codeclobber.com.ytsbrowser.R;
import codeclobber.com.ytsbrowser.activities.MovieDetailActivity;
import codeclobber.com.ytsbrowser.models.Movie;

/**
 * Created by wahib on 2/12/17.
 */

public class RVMoviesAdapter extends RecyclerView.Adapter<RVMoviesAdapter.ViewHolder> {

    private Context mContext;
    private List<Movie> mMovieList = new ArrayList<>();
    private boolean isListCard = false;

    public RVMoviesAdapter(Context context, List<Movie> movieList) {
        this.mContext = context;
        this.mMovieList = movieList;
    }

    public RVMoviesAdapter(Context context, List<Movie> movieList, boolean isListCard) {
        this.mContext = context;
        this.mMovieList = movieList;
        this.isListCard = isListCard;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(
                !isListCard ? R.layout.row_movie_card : R.layout.row_search_card,
                parent,
                false);
        return new ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);
        holder.title.setText(movie.getTitle());
        if (isListCard) {
            StringBuilder builder = new StringBuilder("");
            for (String s : movie.getGenres()) {
                builder.append(s);
                builder.append(" / ");
            }
            if (builder.length() > 1)
                holder.genre.setText(builder.substring(0, builder.length() - 2));
        } else {
            holder.genre.setText(movie.getGenres().size() > 0 ? movie.getGenres().get(0) : "None");
        }


        holder.year.setText(String.format("%d", movie.getYear()));
        Double rating = movie.getRating();
        holder.rating.setText(String.format("%s", rating));
        holder.title.setText(movie.getTitle());
        holder.title.setText(movie.getTitle());

        Glide.with(mContext)
                .load(movie.getMediumCoverImage())
                .into(holder.coverImage);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, rating, genre, year;
        ImageView coverImage;

        ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            genre = (TextView) itemView.findViewById(R.id.tv_genre);
            year = (TextView) itemView.findViewById(R.id.tv_year);
            rating = (TextView) itemView.findViewById(R.id.tv_rating);

            coverImage = (ImageView) itemView.findViewById(R.id.img_coverImage);

            itemView.findViewById(R.id.backView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, MovieDetailActivity.class);
                    Movie movie = mMovieList.get(getAdapterPosition());
                    intent.putExtra("object", movie);
                    mContext.startActivity(intent);
                }
            });

        }
    }
}
