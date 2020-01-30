package at.htlkaindorf.exa_206_pethome.bl;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import at.htlkaindorf.exa_206_pethome.MainActivity;
import at.htlkaindorf.exa_206_pethome.PetViewHolder;
import at.htlkaindorf.exa_206_pethome.R;
import at.htlkaindorf.exa_206_pethome.beans.Cat;
import at.htlkaindorf.exa_206_pethome.beans.Dog;
import at.htlkaindorf.exa_206_pethome.beans.Gender;
import at.htlkaindorf.exa_206_pethome.beans.Pet;

public class PetAdapter extends RecyclerView.Adapter<PetViewHolder>{

    private List<Pet> petList;

    public PetAdapter(List<Pet> pets) {
        petList = new LinkedList<>(pets);
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_item, parent, false);

        ImageView iv =view.findViewById(R.id.ivImage);
        ImageView ivGender = view.findViewById(R.id.ivGender);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvDate = view.findViewById(R.id.tvBirth);
        TextView tvAdd = view.findViewById(R.id.tvAdditional);

        return new PetViewHolder(view, tvName,tvDate,tvAdd,iv,ivGender);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        Pet pet = petList.get(position);

        holder.getTvName().setText(pet.getName());
        holder.getTvDate().setText(pet.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        holder.getIvGender().setImageResource(pet.getGender().equals(Gender.MALE) ? R.drawable.male : R.drawable.female);
        holder.getTvAdditional().setText(pet instanceof Dog ? "Size: " + ((Dog)pet).getSize() : "Color: " + ((Cat)pet).getColor());

        if(pet instanceof Cat)
            Picasso.with(MainActivity.mainActivity.getApplicationContext()).load(((Cat)pet).getPictureUri().toString()).placeholder(R.drawable.ic_launcher_background).resize(200,200).into(holder.getIvImage());
        else
            holder.getIvImage().setImageResource(R.drawable.dog);
    }

    @Override
    public int getItemCount() {
        return petList.size();
    }
}
