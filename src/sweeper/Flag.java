package sweeper;

class Flag {
    private Matrix flagMap;
    private int countOfClosedBoxes;

    void start() {
        flagMap = new Matrix(Box.CLOSED);
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
        //  flagMap.set(new Coord(4, 4), Box.OPENED);
    }

    Box get(Coord coord) {
        return flagMap.get(coord);
    }

    public void setOpenedToBox(Coord openedToBox) {
        flagMap.set(openedToBox, Box.OPENED);
        countOfClosedBoxes--;
    }

    public void setFlagedToBox(Coord coord) {
        flagMap.set(coord, Box.FLAGED);
    }

    public void toggleFlagedToBox(Coord coord) {
        switch (flagMap.get(coord)) {
            case CLOSED:
                setFlagedToBox(coord);
                break;
            case FLAGED:
                setClosedToBox(coord);
                break;
        }
    }

    private void setClosedToBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED);
    }

    int getCountOfClosedBoxes() {
        return countOfClosedBoxes;
    }

    void setBombedToBox(Coord coordBombed) {
        flagMap.set(coordBombed, Box.BOMBED);


    }

    void setOpenedToClosedBox(Coord coord) {
        if (flagMap.get(coord) == Box.CLOSED) {
            flagMap.set(coord, Box.OPENED);
        }
    }

    void setNobombToFlagedSafeBox(Coord coord) {
        if (flagMap.get(coord) == Box.FLAGED) {
            flagMap.set(coord, Box.NOBOMB);
        }
    }



     int getCountOfFlageBoxesAround(Coord coord) {
        int count = 00;
        for (Coord around : Ranges.getCoordsAround(coord)) {
            if (flagMap.get(around) == Box.FLAGED) {
                count++;
            }
        }
        return count;
    }

}
