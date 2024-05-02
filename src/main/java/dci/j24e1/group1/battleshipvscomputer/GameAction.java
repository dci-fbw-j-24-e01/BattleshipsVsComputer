package dci.j24e1.group1.battleshipvscomputer;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.Random;

public class GameAction {
    private Ship[][] ships;
    private Button[][] buttons;
    private ArrayList<Ship> hittedShips;
    private static ArrayList<Ship> computerHits = new ArrayList<>();
    private static Field computerField;
    private static Field userField;
    private  static int counter = 0;

    public static Field getComputerField() {
        return computerField;
    }

    public static Field getUserField() {
        return userField;
    }

    public static void setUserField(Field userField) {
        GameAction.userField = userField;
    }

    public static void setComputerField(Field computerField) {
        GameAction.computerField = computerField;
    }

    public GameAction() {

    }
    private void initialize(boolean isUser) {
        if (isUser) {
            this.ships = computerField.getShips();
            this.buttons = computerField.getButtons();
            this.hittedShips = computerField.getHittedShips();
        } else {
            this.ships = userField.getShips();
            this.buttons = userField.getButtons();
            this.hittedShips = userField.getHittedShips();
        }
    }

    public boolean userAction(ActionEvent event) {
        initialize(true);

        Button button = (Button) event.getSource();
        int x = (int) button.getProperties().get("x");
        int y = (int) button.getProperties().get("y");
        button.setDisable(true);

        if (ships[x][y].getId() == 0) {
            button.setStyle("-fx-background-color: #81D8D0");
        }
        if (ships[x][y].getId() > 0) {

            button.setStyle("-fx-background-color: #826D8C");

            hittedShips.add(ships[x][y]);
            ArrayList<Ship> hittedShip = isDead(ships[x][y]);
            if (hittedShip != null) {
                ArrayList<Ship> waterAroundDeadShip = Ships.getShipsAroundShip(hittedShip, ships);
                for (Ship Ship : waterAroundDeadShip) {
                    buttons[Ship.getX()][Ship.getY()].setStyle("-fx-background-color: #81D8D0");
                    buttons[Ship.getX()][Ship.getY()].setDisable(true);
                }
            }
            return true;
        }
        return false;
    }

    public boolean computerAction() {
        initialize(false);
        Random random = new Random();
        int x, y;
        while (true) {
            x = random.nextInt(0, 10);
            y = random.nextInt(0, 10);

            if (!isExist(x, y)) {
                computerHits.add(new Ship(x, y));
                break;
            }
        }
        if (ships[x][y].getId() == 0) {
            buttons[x][y].setStyle("-fx-background-color: #81D8D0");
        }
        if (ships[x][y].getId() > 0) {
            buttons[x][y].setStyle("-fx-background-color: #826D8C");
            hittedShips.add(ships[x][y]);
            ArrayList<Ship> hittedShip = isDead(ships[x][y]);
            if (hittedShip != null) {
                ArrayList<Ship> waterAroundDeadShip = Ships.getShipsAroundShip(hittedShip, ships);
                computerHits.addAll(waterAroundDeadShip);
                for (Ship Ship : waterAroundDeadShip) {
                    buttons[Ship.getX()][Ship.getY()].setStyle("-fx-background-color: #81D8D0");
                    buttons[Ship.getX()][Ship.getY()].setDisable(true);
                }
            }
            return true;
        }
        return false;
    }

    private ArrayList<Ship> isDead(Ship current) {
        ArrayList<Ship> hittedShip = new ArrayList<>();
        int length = current.getId() / 10;
        for (Ship ship : hittedShips) {
            if (ship.getId() == current.getId()) {
                hittedShip.add(ship);
            }
        }
        if (hittedShip.size() == length) {
            return hittedShip;
        }
        return null;
    }

    private boolean isExist(int x, int y) {
        for (int i = 0; i < computerHits.size(); i++) {
            if (computerHits.get(i).getX() == x && computerHits.get(i).getY() == y) {
                return true;
            }
        }
        return false;
    }
}