package at.htlkaindorf.exa_206_pethome;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import at.htlkaindorf.exa_206_pethome.beans.Cat;
import at.htlkaindorf.exa_206_pethome.beans.Dog;
import at.htlkaindorf.exa_206_pethome.beans.Pet;
import at.htlkaindorf.exa_206_pethome.bl.PetAdapter;

public class PetListActivity extends AppCompatActivity {

    private PetAdapter pa;
    private RecyclerView recyclerView;
    private TextView tvType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);

        String type = getIntent().getStringExtra("Type");

        List<Pet> pets = (ArrayList<Pet>) getIntent().getSerializableExtra("Pets");

        Stream<Pet> filt;

        if(type.equals("Dog")) {
            filt = pets.stream().filter(p -> p instanceof Dog);
            //filt = filt.sorted((p1,p2) -> (((Dog)p1).compareTo(p2)));

            filt = filt.sorted(Comparator.comparing(d -> ((Dog)d).getSize()).thenComparing(d -> ((Dog)d).getDateOfBirth()));

        } else if(type.equals("Cat")) {
            filt = pets.stream().filter(p -> p instanceof Cat);
            filt = filt.sorted((p1,p2) -> (((Cat)p1).compareTo(p2)));
        } else
            filt = pets.stream();

        pets = filt.collect(Collectors.toList());

        pa = new PetAdapter(pets);

        recyclerView = findViewById(R.id.rvPets);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        recyclerView.setAdapter(pa);

        tvType = findViewById(R.id.tvType);
        tvType.setText(type + " list");

        findViewById(R.id.btBack).setOnClickListener(v -> finish());

    }

}
