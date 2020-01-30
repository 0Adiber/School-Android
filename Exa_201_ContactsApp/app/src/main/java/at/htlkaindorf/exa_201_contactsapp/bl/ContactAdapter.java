package at.htlkaindorf.exa_201_contactsapp.bl;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import at.htlkaindorf.exa_201_contactsapp.ContactViewHolder;
import at.htlkaindorf.exa_201_contactsapp.MainActivity;
import at.htlkaindorf.exa_201_contactsapp.R;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    private List<Contact> contacts;
    private List<Contact> safed;
    public static MainActivity m;

    public ContactAdapter(MainActivity m) {

        this.m = m;

        AssetManager am = m.getAssets();

        try {
            InputStream in = am.open("contact_data.csv");
            contacts = new BufferedReader(new InputStreamReader(in))
                    .lines()
                    .skip(1)
                    .map(Contact::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            Log.e("Main", e.toString());
        }

        contacts.sort(Comparator.comparing(Contact::getFirstname).thenComparing(Contact::getLastname));

        safed = new LinkedList<>(contacts);

    }


    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item,parent,false);

        ImageView iv = view.findViewById(R.id.ivPicture);
        TextView tv = view.findViewById(R.id.tvName);

        ContactViewHolder cv = new ContactViewHolder(view, iv,tv, this);

        return cv;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);

        Picasso.with(m.getApplicationContext()).load(contact.getPicture().toString()).placeholder(R.drawable.ic_person_black_24dp).resize(200,200).into(holder.getIvPicture());

        holder.getTvName().setText(contact.getFirstname() + ", "+contact.getLastname());
        holder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void filterContacts(String filter) {
        contacts = new LinkedList<>(safed);
        if(!filter.isEmpty()) {
            contacts.removeIf(contact -> !(contact.getFirstname().toLowerCase().startsWith(filter.toLowerCase()) || contact.getLastname().toLowerCase().startsWith(filter.toLowerCase()))); //aka predicate
        }
        notifyDataSetChanged();
    }

    public Contact getContact(int position) {
        return contacts.get(position);
    }

    public void removeContact(int position) {
        Contact contact = contacts.get(position);
        contacts.remove(position);
        safed.remove(contact);
        notifyDataSetChanged();
    }

}
