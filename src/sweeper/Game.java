package sweeper;

public class Game {
    private Bomb bomb;
    private Flag flag;
    private GameState state;

    public GameState getState() {
        return state;
    }

    public void start() {
        bomb.start();
        flag.start();
    }


    public Game(int cols, int rows, int bombsNumber) {
        Ranges.setSize(new Coord(cols, rows));
        bomb = new Bomb(bombsNumber);
        flag = new Flag();
        state = GameState.PLAYED;

    }

    public Box getBox(Coord coord) {
        if (flag.get(coord) == Box.OPENED) {
            return bomb.get(coord);
        }
        return flag.get(coord);
    }

    public void pressLeftButton(Coord coord) {
        if (gameover()) return;
        openBox(coord);
        checkWinner();
    }

    private void checkWinner() {
        if (state == GameState.PLAYED) {
            if (flag.getCountOfClosedBoxes() == bomb.getTotalBombs()) {
                state = GameState.WINNER;
            }
        }
    }

    void setOpenedToClosedBoxesAroundNumber(Coord coord) {
        if (flag.get(coord) != Box.BOMB) {
            if (flag.getCountOfFlageBoxesAround(coord) == bomb.get(coord).getNumber()) {
                for (Coord around : Ranges.getCoordsAround(coord)) {
                    if (flag.get(around) == Box.CLOSED) {
                        openBox(around);
                    }
                }
            }

        }
    }

    private void openBox(Coord coord) {
        switch (flag.get(coord)) {
            case OPENED:
                setOpenedToClosedBoxesAroundNumber(coord);
                return;
            case FLAGED:
                return;
            case CLOSED:
                switch (bomb.get(coord)) {
                    case ZERO:
                        openBaxesAround(coord);
                        return;
                    case BOMB:
                        openBombs(coord);
                        return;
                    default:
                        flag.setOpenedToBox(coord);
                        return;
                }
        }
    }

    private void openBombs(Coord coordBombed) {
        state = GameState.BOMBED;
        flag.setBombedToBox(coordBombed);
        for (Coord coord : Ranges.getAllCoords()) {
            if (bomb.get(coord) == Box.BOMB) {
                flag.setOpenedToClosedBox(coord);
            } else {
                flag.setNobombToFlagedSafeBox(coord);
            }
        }
    }

    private void openBaxesAround(Coord coord) {
        flag.setOpenedToBox(coord);
        for (Coord around : Ranges.getCoordsAround(coord)) {
            openBox(around);
        }
    }

    public void pressRightButton(Coord coord) {
        if (gameover()) return;
        flag.toggleFlagedToBox(coord);
    }

    private boolean gameover() {
        if (state == GameState.PLAYED) return false;
        start();
        return true;
    }


}
