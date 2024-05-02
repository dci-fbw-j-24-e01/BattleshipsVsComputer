package dci.j24e1.group1.battleshipvscomputer;

import java.util.ArrayList;
import java.util.Random;

public class Ships {
    private final int fieldSize = 10;
    private Ship[][] ships;

    public Ships() {
        firstFill();
        randomFieldFill();
    }

    public Ship[][] getShips() {
        return ships;
    }

    private void firstFill() {
        ships = new Ship[fieldSize][fieldSize];
        for (int i = 0; i < ships.length; i++) {
            for (int j = 0; j < ships[i].length; j++) {
                ships[i][j] = new Ship(9, i, j);
            }
        }
    }

    private void randomFieldFill() {

        for (int i = 4; i > 0; i--) {
            for (int j = 1; j <= 5 - i; j++) {
                createShip(i, j);
            }
        }
        finishFill();
    }

    private void finishFill() {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if(ships[i][j].getId() == 9) {
                    ships[i][j].setId(0);
                }
            }
        }
    }

    private void createShip(int length, int number) {
        Random random = new Random();
        while (true) {
            int x = random.nextInt(0, fieldSize);
            int y = random.nextInt(0, fieldSize);
            if (createShipField(length, number, x, y)) {
                break;
            }
        }
    }

    private boolean createShipField(int length,int number, int x, int y) {
        Random random = new Random();
        ArrayList<Ship> Ships = new ArrayList<>();
        int state = random.nextInt(1,5);
        if (state == 1) {
            if (x + length < fieldSize) {
                for (int i = 0; i < length; i++) {
                    if (ships[x + i][y].getId() != 9) {
                        return false;
                    }
                    Ships.add(ships[x + i][y]);
                }
            }
        } else if (state == 2) {
            if (y + length < fieldSize) {
                for (int i = 0; i < length; i++) {
                    if (ships[x][y + i].getId() != 9) {
                        return false;
                    }
                    Ships.add(ships[x][y + i]);
                }
            }
        } else if(state == 3){
            if (x - length >= 0) {
                for (int i = 0; i < length; i++) {
                    if (ships[x - i][y].getId() != 9) {
                        return false;
                    }
                    Ships.add(ships[x - i][y]);
                }
            }
        } else {
            if (y - length >= 0) {
                for (int i = 0; i < length; i++) {
                    if (ships[x][y - i].getId() != 9) {
                        return false;
                    }
                    Ships.add(ships[x][y - i]);
                }
            }
        }
        if (Ships.isEmpty()) {
            return false;
        }
        addShipsInField(Ships, length, number);
        fillAroundShip(Ships);
        return true;
    }

    private void addShipsInField(ArrayList<Ship> Ships, int length, int number) {
        for (int i = 0; i < length; i++) {
            ships[Ships.get(i).getX()][Ships.get(i).getY()].setId(length * 10 + number);
        }
    }

    private void fillAroundShip(ArrayList<Ship> Ships) {
        ArrayList<Ship> ShipsAroundShip = getShipsAroundShip(Ships, this.getShips());
        for (Ship Ship : ShipsAroundShip) {
            ships[Ship.getX()][Ship.getY()].setId(0);
        }
    }

    public static ArrayList<Ship> getShipsAroundShip(ArrayList<Ship> shipShips, Ship[][] currentShips) {
        ArrayList<Ship> newShips = new ArrayList<>();
        for (Ship Ship : shipShips) {
            int x = Ship.getX();
            int y = Ship.getY();
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
                        newShips.add(new Ship(i, j));
                    }
                }
            }
        }
        return newShips;
    }
}
