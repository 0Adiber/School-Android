package at.htlkaindorf.exa_205_game2048;

public enum ColorScheme {

    C0(0, 0xffe8dfc1, 0x00000000),
    C2(2,0xfff7e1bc, 0xff000000),
    C4(4,0xffdbd1ad,0xff000000),
    C8(8,0xfffcc16d,0xffffffff),
    C16(16,0xfff7a22a,0xffffffff),
    C32(32,0xfff7792a,0xffffffff),
    C64(64, 0xfff7612a,0xffffffff),
    C128(128,0xfffcd883,0xffffffff),
    C256(256,0xffedaf1c,0xffffffff),
    C512(512,0xffedaf1c,0xffffffff),
    C1024(1024,0xffedaf1c,0xffffffff),
    C2048(2048,0xffedaf1c,0xffffffff);

    ColorScheme(int value, int backgroundColor, int fontColor) {
        this.value = value;
        this.backgroundColor = backgroundColor;
        this.fontColor = fontColor;
    }

    private int value;
    private int backgroundColor;
    private int fontColor;

    public int getValue() {
        return this.value;
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public int getFontColor() {
        return this.fontColor;
    }
}
