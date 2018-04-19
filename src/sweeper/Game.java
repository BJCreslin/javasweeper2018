package sweeper;

public class Game {
    private Bomb bomb;

    public void start() {
        bomb.start();}

    public Game( int cols, int rows, int bombsNumber){
            Ranges.setSize(new Coord(cols, rows));
            bomb = new Bomb(bombsNumber);
        }

        public Box getBox (Coord coord){
            return bomb.get(coord);
        }

    }
