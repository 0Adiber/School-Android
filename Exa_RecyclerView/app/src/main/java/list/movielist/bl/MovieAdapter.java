package list.movielist.bl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import list.movielist.MovieHolder;
import list.movielist.R;

public class MovieAdapter extends RecyclerView.Adapter<MovieHolder>{

    private List<Movie> movieList = Arrays.asList(
            new Movie("Herr der Ringe", YearMonth.of(2003,3),228),
            new Movie("Star Wars", YearMonth.of(1980,9),166),
            new Movie("Spectre", YearMonth.of(2015,11),144));

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false);

        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvDuration = view.findViewById(R.id.tvDuration);
        TextView tvReleased = view.findViewById(R.id.tvReleased);

        MovieHolder movieHolder = new MovieHolder(view,tvTitle,tvDuration,tvReleased);

        return movieHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.getTvTitle().setText(movie.getName());
        holder.getTvReleased().setText(movie.getReleasedString());
        holder.getTvDuration().setText(movie.getDurationString());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
