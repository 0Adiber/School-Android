package at.htlkaindorf.exa_206_pethome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import at.htlkaindorf.exa_206_pethome.beans.Gender;
import at.htlkaindorf.exa_206_pethome.beans.Pet;
import at.htlkaindorf.exa_206_pethome.bl.PetAdapter;
import at.htlkaindorf.exa_206_pethome.io.IO_Access;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static MainActivity mainActivity;

    private List<Pet> pets;

    private ImageButton btDogs, btCats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;

        pets = IO_Access.loadPets();

        btDogs = findViewById(R.id.btDogs);
        btCats = findViewById(R.id.btCats);

        btDogs.setOnClickListener(v -> {
            Intent in = new Intent(getApplicationContext(), PetListActivity.class);
            in.putExtra("Type", "Dog");
            in.putExtra("Pets", new ArrayList<>(pets));
            startActivity(in);
        });

        btCats.setOnClickListener(v -> {
            Intent in = new Intent(getApplicationContext(), PetListActivity.class);
            in.putExtra("Type", "Cat");
            in.putExtra("Pets", new ArrayList<>(pets));
            startActivity(in);
        });

    }

}
