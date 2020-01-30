package at.htlkaindorf.exa_203_bankaccountapp.bl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import at.htlkaindorf.exa_203_bankaccountapp.AccountViewHolder;
import at.htlkaindorf.exa_203_bankaccountapp.IO_Access;
import at.htlkaindorf.exa_203_bankaccountapp.R;
import at.htlkaindorf.exa_203_bankaccountapp.beans.Account;
import at.htlkaindorf.exa_203_bankaccountapp.beans.GiroAccount;
import at.htlkaindorf.exa_203_bankaccountapp.beans.StudentAccount;

public class AccountAdapter extends RecyclerView.Adapter<AccountViewHolder> {

    private List<Account> accounts;
    private List<Account> filtered;
    private String[] ibans;

    public AccountAdapter() {
        accounts = IO_Access.loadAccounts();
        filtered = new LinkedList<>(accounts);

        ibans = new String[accounts.size()];
        int cc = 0;
        for(Account a : accounts) {
            ibans[cc++] = a.getIvan();
        }
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_item,parent,false);

        ImageView iv = view.findViewById(R.id.ivType);
        TextView tvType = view.findViewById(R.id.tvType);
        TextView tvIvan = view.findViewById(R.id.tvIvan);
        TextView tvBalance = view.findViewById(R.id.tvBalance);
        TextView tvAvailable = view.findViewById(R.id.tvAvailable);

        AccountViewHolder av = new AccountViewHolder(view,tvType,tvIvan,tvBalance,tvAvailable,iv, ibans);

        return av;
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        Account acc = filtered.get(position);

        holder.setAccount(acc);

        holder.getTvBalance().setText(convMoney(acc.getBalance()));

        holder.getTvBalance().setTextColor((acc.getBalance() > 0 ? 0xff30b032 : 0xffe32030));

        holder.getTvIvan().setText("IBAN: " + acc.getIvan());

        if(acc instanceof StudentAccount) {
            holder.getTvType().setText("Student-Account");
            holder.getIvType().setImageResource(R.drawable.ic_school_black_24dp);
            holder.getTvAvailable().setText(convMoney(acc.getBalance()));
        } else {
            holder.getTvType().setText("Giro-Account");
            GiroAccount g = (GiroAccount)acc;
            holder.getTvAvailable().setText(convMoney(acc.getBalance()+((GiroAccount) acc).getOverdraft()));
        }

    }

    @Override
    public int getItemCount() {
        return filtered.size();
    }

    public void filterAccounts(String type) {
        Stream<Account> filt;

        if(type.equalsIgnoreCase("student"))
            filt = accounts.stream().filter(a -> a instanceof StudentAccount);
        else if(type.equalsIgnoreCase("giro"))
            filt = accounts.stream().filter(a -> a instanceof GiroAccount);
        else
            filt = accounts.stream();

        filtered = filt.collect(Collectors.toList());
        notifyDataSetChanged();
    }

    private String convMoney(double money) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        String formatted = format.format(money).replace(".", " ");
        return "€ " + formatted.subSequence(0,formatted.length()-1);
    }

    public void transferMoney(Account fromAccount, String toIban, double amount) {
        boolean valid = false;
        for(String b : ibans) {
            if(b.equals(toIban))
                valid=true;

        }

        if(valid==false) return;

        accounts.forEach(a -> {
            if(a.getIvan().equals(fromAccount.getIvan()))
                a.setBalance(a.getBalance()-amount);
            if(a.getIvan().equals(toIban))
                a.setBalance(a.getBalance()+amount);
        });
        filterAccounts("none");
    }

}
