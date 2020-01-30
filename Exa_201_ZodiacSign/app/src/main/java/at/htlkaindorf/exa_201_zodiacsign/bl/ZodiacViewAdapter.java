package at.htlkaindorf.exa_201_zodiacsign.bl;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import at.htlkaindorf.exa_201_zodiacsign.R;
import at.htlkaindorf.exa_201_zodiacsign.ZodiacViewHolder;

public class ZodiacViewAdapter extends RecyclerView.Adapter<ZodiacViewHolder> {

    private List<ZodiacSign> zodiacSigns = new LinkedList<>();

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MMMM");

    public ZodiacViewAdapter(Resources res, String pkg) {
        String[] names = "Wassermann,Fische,Widder,Stier,Zwilling,Krebs,Löwe,Jungfrau,Waage,Skorpion,Schütze,Steinbock".split(",");
        String[] dates = "21.1,20.2,21.3,21.4,21.5,22.6,23.7,24.8,24.9,24.10,23.11,22.12".split(",");
        String[] symbols = "aquarius,pisces,aries,taurus,gemini,cancer,leo,virgo,libra,scorpius,sagittarius,capricornus".split(",");

        for(int i = 0; i<names.length; i++) {
            int id = res.getIdentifier(symbols[i], "drawable",pkg);
            int day = Integer.parseInt(dates[i].split("\\.")[0]);
            int month = Integer.parseInt(dates[i].split("\\.")[1]);
            zodiacSigns.add(new ZodiacSign(names[i],MonthDay.of(month,day),id));
        }

        zodiacSigns.sort(Comparator.comparing(ZodiacSign::getStartDate));
    }

    @NonNull
    @Override
    public ZodiacViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.zodiac_item, parent,false);

        ImageView iv = view.findViewById(R.id.ivZodiac);
        TextView tv = view.findViewById(R.id.tvDesc);

        ZodiacViewHolder zh = new ZodiacViewHolder(view, iv,tv);

        return zh;
    }

    @Override
    public void onBindViewHolder(@NonNull ZodiacViewHolder holder, int position) {
        ZodiacSign zodiac = zodiacSigns.get(position);
        ZodiacSign z2 = zodiacSigns.get((position+1)%zodiacSigns.size());
        holder.getIvZodiac().setImageResource(zodiac.getDrawableId());
        String date = zodiac.getStartDate().format(dtf) + " bis " + z2.getStartDate().withDayOfMonth(z2.getStartDate().getDayOfMonth()-1).format(dtf);
        holder.getTvDesc().setText(zodiac.getName() + "\n" + date);
    }

    @Override
    public int getItemCount() {
        return zodiacSigns.size();
    }
}
