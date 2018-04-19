package sweeper;

public class Game {
    private Bomb bomb;
    private Flag flag;

    public void start() {
        bomb.start();
        flag.start();
    }

    public Game(int cols, int rows, int bombsNumber) {
        Ranges.setSize(new Coord(cols, rows));
        bomb = new Bomb(bombsNumber);
        flag = new Flag();
    }

    public Box getBox(Coord coord) {
        if (flag.get(coord)==Box.OPENED){
            return bomb.get(coord);
        }
        return flag.get(coord);
    }

    public void pressLeftButton(Coord coord) {
        flag.setOpenedToBox(coord);
    }

    public void pressRightButton(Coord coord) {
        flag.toggleFlagedToBox(coord);
    }
}
