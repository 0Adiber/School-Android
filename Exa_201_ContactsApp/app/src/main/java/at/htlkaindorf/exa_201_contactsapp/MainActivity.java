package at.htlkaindorf.exa_201_contactsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import at.htlkaindorf.exa_201_contactsapp.bl.ContactAdapter;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SearchView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvContacts);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));

        ContactAdapter ca = new ContactAdapter(this);

        recyclerView.setAdapter(ca);

        SearchView sv = findViewById(R.id.svSearch);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ca.filterContacts(newText);
                return false;
            }
        });

    }
}
