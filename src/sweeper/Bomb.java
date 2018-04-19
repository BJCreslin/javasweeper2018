package sweeper;

class Bomb {

    Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
        fixBombsCount();
    }

    public int getTotalBombs() {
        return totalBombs;
    }

    private int totalBombs;
    private Matrix bombMap;

    void start() {
        bombMap = new Matrix(Box.ZERO);
        for (int i = 0; i < totalBombs; i++) {
            placeBomb();
        }
    }

    private void fixBombsCount() {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (totalBombs > maxBombs) totalBombs = maxBombs;
    }

    private void placeBomb() {
        //for (int i = 0; i < totalBombs; i++) {
        while (true) {
            Coord coord = Ranges.getRandomCoord();
            if (Box.BOMB == bombMap.get(coord)) continue;
            bombMap.set(coord, Box.BOMB);
            incNumberAroundBobm(coord);
            break;
        }
    }

    Box get(Coord coord) {
        return bombMap.get(coord);
    }

    private void incNumberAroundBobm(Coord coord) {
        for (Coord around : Ranges.getCoordsAround(coord)) {
            if (Box.BOMB != bombMap.get(around))
                bombMap.set(around, bombMap.get(around).nextNumberBomb());
        }
    }
}
