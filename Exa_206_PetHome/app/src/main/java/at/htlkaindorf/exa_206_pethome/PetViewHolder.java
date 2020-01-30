package at.htlkaindorf.exa_206_pethome;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PetViewHolder extends RecyclerView.ViewHolder {

    private TextView tvName, tvDate, tvAdditional;
    private ImageView ivImage, ivGender;

    public PetViewHolder(@NonNull View itemView, TextView tvName, TextView tvDate, TextView tvAdditional, ImageView ivImage, ImageView ivGender) {
        super(itemView);
        this.tvName = tvName;
        this.tvDate = tvDate;
        this.tvAdditional = tvAdditional;
        this.ivImage = ivImage;
        this.ivGender = ivGender;
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public TextView getTvDate() {
        return tvDate;
    }

    public void setTvDate(TextView tvDate) {
        this.tvDate = tvDate;
    }

    public TextView getTvAdditional() {
        return tvAdditional;
    }

    public void setTvAdditional(TextView tvAdditional) {
        this.tvAdditional = tvAdditional;
    }

    public ImageView getIvImage() {
        return ivImage;
    }

    public void setIvImage(ImageView ivImage) {
        this.ivImage = ivImage;
    }

    public ImageView getIvGender() {
        return ivGender;
    }

    public void setIvGender(ImageView ivGender) {
        this.ivGender = ivGender;
    }
}
