package at.htlkaindorf.exa_201_zodiacsign;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ZodiacViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivZodiac;
    private TextView tvDesc;

    public ZodiacViewHolder(@NonNull View itemView, ImageView ivZodiac, TextView tvDesc) {
        super(itemView);
        this.ivZodiac = ivZodiac;
        this.tvDesc = tvDesc;

        itemView.setOnClickListener(v -> {
            String url = MainActivity.fotze.getString(R.string.wikipedia_url, tvDesc.getText().toString().split("\n")[0]);
            Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(url));
            MainActivity.fotze.startActivity(viewIntent);
        });

    }

    public ImageView getIvZodiac() {
        return ivZodiac;
    }

    public void setIvZodiac(ImageView ivZodiac) {
        this.ivZodiac = ivZodiac;
    }

    public TextView getTvDesc() {
        return tvDesc;
    }

    public void setTvDesc(TextView tvDesc) {
        this.tvDesc = tvDesc;
    }

}
