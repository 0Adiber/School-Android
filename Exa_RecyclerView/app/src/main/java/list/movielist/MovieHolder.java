package list.movielist;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieHolder extends RecyclerView.ViewHolder {

    private TextView tvTitle,tvDuration,tvReleased;

    public MovieHolder(@NonNull View itemView, TextView tvTitle, TextView tvDuration, TextView tvReleased) {
        super(itemView);
        this.tvTitle = tvTitle;
        this.tvDuration = tvDuration;
        this.tvReleased = tvReleased;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    public TextView getTvDuration() {
        return tvDuration;
    }

    public void setTvDuration(TextView tvDuration) {
        this.tvDuration = tvDuration;
    }

    public TextView getTvReleased() {
        return tvReleased;
    }

    public void setTvReleased(TextView tvReleased) {
        this.tvReleased = tvReleased;
    }
}
