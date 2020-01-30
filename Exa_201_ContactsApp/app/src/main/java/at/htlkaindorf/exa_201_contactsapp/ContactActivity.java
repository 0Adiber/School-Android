package at.htlkaindorf.exa_201_contactsapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import at.htlkaindorf.exa_201_contactsapp.bl.Contact;

public class ContactActivity extends AppCompatActivity {

    private TextView tvName, tvLanguage, tvGender, tvPhone;
    private ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_details);

        Contact contact = getIntent().getParcelableExtra("contact");

        tvName = findViewById(R.id.tvName);
        tvLanguage = findViewById(R.id.tvLanguage);
        tvGender = findViewById(R.id.tvGender);
        tvPhone = findViewById(R.id.tvPhone);
        ivImage = findViewById(R.id.ivImage);

        tvName.setText(contact.getFirstname() + " " + contact.getLastname());
        tvLanguage.setText(contact.getLanguage().substring(0,1).toUpperCase() + contact.getLanguage().substring(1));
        tvGender.setText(""+contact.getGender());
        tvPhone.setText(contact.getPhoneNumber());
        Picasso.with(getApplicationContext()).load(contact.getPicture()).placeholder(R.drawable.ic_person_black_24dp).resize(200,200).into(ivImage);
    }
}
