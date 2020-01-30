package at.htlkaindorf.exa_201_contactsapp;

import android.content.Intent;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;
import at.htlkaindorf.exa_201_contactsapp.bl.Contact;
import at.htlkaindorf.exa_201_contactsapp.bl.ContactAdapter;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivPicture;
    private TextView tvName;
    private ContactAdapter ca;
    private int position;

    public ContactViewHolder(@NonNull View itemView, ImageView ivPicture, TextView tvName, ContactAdapter ca) {
        super(itemView);
        this.ivPicture = ivPicture;
        this.tvName = tvName;
        this.ca = ca;

        itemView.setOnClickListener(new MyOnClick());

        MyGestureListener mg1 = new MyGestureListener();
        GestureDetectorCompat mg = new GestureDetectorCompat(ca.m.getApplicationContext(), mg1);

        itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mg.onTouchEvent(event);
            }
        });

    }

    public ImageView getIvPicture() {
        return ivPicture;
    }

    public void setIvPicture(ImageView ivPicture) {
        this.ivPicture = ivPicture;
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    class MyOnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Contact contact =  ca.getContact(position);

            Intent intent = new Intent(ca.m.getBaseContext(), ContactActivity.class);
            intent.putExtra("contact", contact);
            ca.m.startActivity(intent);
        }
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private final int MIN_DIST = 60;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float deltaX = e1.getX() - e2.getX();
            float deltaXAbs = Math.abs(deltaX);

            Log.d("Main", deltaXAbs+"");

            if(deltaXAbs > MIN_DIST && deltaX < 0) {
                ca.removeContact(position);
            }

            return true;
        }
    }

}
