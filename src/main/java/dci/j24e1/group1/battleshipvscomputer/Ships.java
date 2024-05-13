package dci.j24e1.group1.battleshipvscomputer;

import java.util.ArrayList;
import java.util.Random;

public class Ships {
    private final int FIELD_SIZE = 10;
    private Ship[][] ships;

    public Ships() {
        fillFieldRandomly();
    }

    public Ship[][] getShips() {
        return ships;
    }

    private void firstFill() {
        ships = new Ship[FIELD_SIZE][FIELD_SIZE];
        for (int i = 0; i < ships.length; i++) {
            for (int j = 0; j < ships[i].length; j++) {
                ships[i][j] = new Ship(9, i, j);
            }
        }
    }

    private void fillFieldRandomly() {
        firstFill();
        int maxCellCount= 4;
        int minCellCount = 1;
        for (int i = maxCellCount; i > 0; i--) {
            for (int j = minCellCount; j <= maxCellCount - i + 1; j++) {
                createShip(i, j);
            }
        }
        finishFill();
    }

    private void finishFill() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (ships[i][j].getId() == 9) {
                    ships[i][j].setId(0);
                }
            }
        }
    }

    private void createShip(int length, int id) {
        Random random = new Random();
        while (true) {
            int x = random.nextInt(0, FIELD_SIZE);
            int y = random.nextInt(0, FIELD_SIZE);
            ArrayList<Ship> possibleCoordinates = getPossibleCoordinates(x, y, length, id);
            if (possibleCoordinates != null) {
                ArrayList<Ship> coordinates = getFinalShipCoordinates(possibleCoordinates, length);
                addShipsOnField(coordinates);
                fillAroundShip(coordinates);
                break;
            }
        }
    }

    private ArrayList<Ship> getPossibleCoordinates(int x, int y, int length, int id) {
        Random random = new Random();
        boolean isHorizontal = random.nextBoolean();
        ArrayList<Ship> possibleCoordinates = new ArrayList<>();
        int start, finish;
        int newID = length * 10 + id;
        if (isHorizontal) {
            start = y - length + 1;
            finish = y + length - 1;
            for (int i = start; i <= finish; i++) {
                if (i < 0 || i >= FIELD_SIZE) {
                    continue;
                }
                if (ships[x][i].getId() == 9) {
                    possibleCoordinates.add(new Ship(newID, x, i));
                }
            }
        }
        if (possibleCoordinates.isEmpty() || possibleCoordinates.size() < length) {
            possibleCoordinates = new ArrayList<>();
            start = x - length + 1;
            finish = x + length - 1;
            for (int i = start; i <= finish; i++) {
                if (i < 0 || i >= FIELD_SIZE) {
                    continue;
                }
                if (ships[i][y].getId() == 9) {
                    possibleCoordinates.add(new Ship(newID, i, y));
                }
            }
        }
        if (possibleCoordinates.size() < length) {
            return null;
        }
        return possibleCoordinates;
    }

    private ArrayList<Ship> getFinalShipCoordinates(ArrayList<Ship> possibleCoordinates, int length) {
        ArrayList<Ship> coordinates = new ArrayList<>();
        for (int i = 0; i <= possibleCoordinates.size() - length; i++) {
            Random random = new Random();
            boolean isNext = random.nextBoolean();
            if (possibleCoordinates.size() - i == length || !isNext) {
                for (int j = 0; j < length; j++) {
                    coordinates.add(possibleCoordinates.get(j));
                }
                break;
            }
        }
        return coordinates;
    }

    private void addShipsOnField(ArrayList<Ship> finishedShip) {
        for (Ship ship : finishedShip) {
            int x = ship.getX();
            int y = ship.getY();
            ships[x][y] = ship;
        }
    }

    private void fillAroundShip(ArrayList<Ship> shipCoordinates) {
        ArrayList<Ship> coordinatesAroundShip = getCoordinatesAroundShip(shipCoordinates, this.getShips());
        for (Ship ship : coordinatesAroundShip) {
            ships[ship.getX()][ship.getY()].setId(0);
        }
    }

    public static ArrayList<Ship> getCoordinatesAroundShip(ArrayList<Ship> shipCoordinates, Ship[][] currentShips) {
        ArrayList<Ship> coordinates = new ArrayList<>();
        for (Ship ship : shipCoordinates) {
            int x = ship.getX();
            int y = ship.getY();
            int xStart = x - 1;
            int xFinish = x + 1;
            int yStart = y - 1;
            int yFinish = y + 1;
            if (x - 1 < 0) {
                xStart = x;
            } else if (x + 1 > 9) {
                xFinish = x;
            }
            if (y - 1 < 0) {
                yStart = y;
            } else if (y + 1 > 9) {
                yFinish = y;
            }
            for (int i = xStart; i <= xFinish; i++) {
                for (int j = yStart; j <= yFinish; j++) {
                    if (currentShips[i][j].getId() == 9 || currentShips[i][j].getId() == 0) {
                        coordinates.add(new Ship(i, j));
                    }
                }
            }
        }
        return coordinates;
    }
}
