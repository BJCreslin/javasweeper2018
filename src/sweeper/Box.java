package sweeper;

public enum Box {
    ZERO,
    num1,
    num2,
    num3,
    num4,
    num5,
    num6,
    num7,
    num8,
    BOMB,
    OPENED,
    CLOSED,
    FLAGED,
    BOMBED,
    NOBOMB;
    public Object image;

    public Box nextNumberBomb() {
        return Box.values()[this.ordinal() + 1];
    }
}
