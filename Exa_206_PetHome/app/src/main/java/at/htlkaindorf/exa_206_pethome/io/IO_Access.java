package at.htlkaindorf.exa_206_pethome.io;

import android.content.res.AssetManager;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import at.htlkaindorf.exa_206_pethome.MainActivity;
import at.htlkaindorf.exa_206_pethome.beans.Cat;
import at.htlkaindorf.exa_206_pethome.beans.CatColor;
import at.htlkaindorf.exa_206_pethome.beans.Dog;
import at.htlkaindorf.exa_206_pethome.beans.Gender;
import at.htlkaindorf.exa_206_pethome.beans.Pet;
import at.htlkaindorf.exa_206_pethome.beans.Size;

public class IO_Access {

    public static List<Pet> loadPets() {
        List<Pet> pets = new LinkedList<>();
        AssetManager am = MainActivity.mainActivity.getAssets();

        try {
            InputStream in = am.open("pets.csv");
            pets = new BufferedReader(new InputStreamReader(in))
                    .lines()
                    .skip(1)
                    .map(IO_Access::apply)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            Log.e("Main", e.toString());
        }

        return pets;
    }

    private static Pet apply(String l) {
        String[] parts = l.split(",");
        //pet_type,name,gender,birthdate,size,color,avatar
        Gender g = (parts[2].equalsIgnoreCase("male") ? Gender.MALE : Gender.FEMALE);
        LocalDate dof = LocalDate.of(Integer.parseInt(parts[3].split("/")[2]), Integer.parseInt(parts[3].split("/")[0]), Integer.parseInt(parts[3].split("/")[1]));
        //dog
        if (parts[0].equalsIgnoreCase("dog")) {
            Size s = (parts[4].equalsIgnoreCase("s") ? Size.SMALL : parts[4].equalsIgnoreCase("m") ? Size.MEDIUM : Size.LARGE);
            return new Dog(parts[1], dof, g, s);
        }
        //cat
        Uri u = Uri.parse(parts[6]);

        return new Cat(parts[1], dof, g, CatColor.valueOf(parts[5].toUpperCase()), u);

    }
}
