package at.htlkaindorf.minesweeper.bl;

import android.widget.Button;

public class Field {

    private boolean mine;
    private boolean discovered;
    private boolean flagged;
    private int minesAround;
    private int id;

    public Field(boolean mine, boolean discovered, int minesAround, int id) {
        this.mine = mine;
        this.discovered = discovered;
        this.minesAround = minesAround;
        this.id = id;
        this.flagged = false;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }

    public int getMinesAround() {
        return minesAround;
    }

    public void setMinesAround(int minesAround) {
        this.minesAround = minesAround;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

}
