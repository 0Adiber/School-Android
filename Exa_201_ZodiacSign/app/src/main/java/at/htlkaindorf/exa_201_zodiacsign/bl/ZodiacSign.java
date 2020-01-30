package at.htlkaindorf.exa_201_zodiacsign.bl;

import java.time.MonthDay;

public class ZodiacSign {

    private String name;
    private MonthDay startDate;
    private int drawableId;

    public ZodiacSign(String name, MonthDay startDate, int drawableId) {
        this.name = name;
        this.startDate = startDate;
        this.drawableId = drawableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MonthDay getStartDate() {
        return startDate;
    }

    public void setStartDate(MonthDay startDate) {
        this.startDate = startDate;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }
}
