package at.htlkaindorf.exa_201_zodiacsign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import at.htlkaindorf.exa_201_zodiacsign.bl.ZodiacSign;
import at.htlkaindorf.exa_201_zodiacsign.bl.ZodiacViewAdapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public static MainActivity fotze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvZodiac);

        recyclerView = findViewById(R.id.rvZodiac);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        recyclerView.setAdapter(new ZodiacViewAdapter(getResources(), this.getPackageName()));
        fotze = this;
    }

}
