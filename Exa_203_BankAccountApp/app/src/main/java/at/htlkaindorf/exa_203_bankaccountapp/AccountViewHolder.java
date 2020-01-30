package at.htlkaindorf.exa_203_bankaccountapp;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import at.htlkaindorf.exa_203_bankaccountapp.beans.Account;

public class AccountViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivType;
    private TextView tvType, tvIvan, tvBalance,tvAvailable;
    private Account account;
    private String[] ibans;

    public AccountViewHolder(@NonNull View itemView, TextView tvType, TextView tvIvan, TextView tvBalance, TextView tvAvailable, ImageView ivType, String[] ibans) {
        super(itemView);
        this.tvType = tvType;
        this.tvIvan = tvIvan;
        this.tvBalance = tvBalance;
        this.tvAvailable = tvAvailable;
        this.ivType = ivType;
        this.ibans = ibans;

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent trans = new Intent(itemView.getContext(), TransferActivity.class);
                trans.putExtra("account", account);
                trans.putExtra("ibans", ibans);
                ((Activity)itemView.getContext()).startActivityForResult(trans, 99);

                return false;
            }
        });

    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public TextView getTvType() {
        return tvType;
    }

    public void setTvType(TextView tvType) {
        this.tvType = tvType;
    }

    public TextView getTvIvan() {
        return tvIvan;
    }

    public void setTvIvan(TextView tvIvan) {
        this.tvIvan = tvIvan;
    }

    public TextView getTvBalance() {
        return tvBalance;
    }

    public void setTvBalance(TextView tvBalance) {
        this.tvBalance = tvBalance;
    }

    public TextView getTvAvailable() {
        return tvAvailable;
    }

    public void setTvAvailable(TextView tvAvailable) {
        this.tvAvailable = tvAvailable;
    }

    public ImageView getIvType() {
        return ivType;
    }

    public void setIvType(ImageView ivType) {
        this.ivType = ivType;
    }
}
