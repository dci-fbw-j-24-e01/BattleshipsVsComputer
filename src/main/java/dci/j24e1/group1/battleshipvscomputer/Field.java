package dci.j24e1.group1.battleshipvscomputer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Field {
    @FXML
    private Label counterLabel;
    private Ship[][] ships;
    private Button[][] buttons = new Button[10][10];
    private VBox vbox;
    private ArrayList<Ship> hittedShips = new ArrayList<>();
    private int points = 0;
    private int counter = 2;

    public Field(VBox vbox, Ship[][] ships, Label counterLabel, Button startButton, Button randomButton) {
        this.vbox = vbox;
        this.ships = ships;
        this.counterLabel = counterLabel;
        createField();
    }


    private void createField() {
        for (int i = 0; i < 10; i++) {
            HBox hBox = new HBox();

            for (int j = 0; j < 10; j++) {
                Button button = new Button();
                button.setMinHeight(30);
                button.setMinWidth(30);
                button.getProperties().put("x", i);
                button.getProperties().put("y", j);
                hBox.getChildren().add(button);
                buttons[i][j] = button;
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        Button button = (Button) event.getSource();
                        int x = (int) button.getProperties().get("x");
                        int y = (int) button.getProperties().get("y");
                        button.setDisable(true);

                        if (ships[x][y].getId() == 0) {
                            button.setStyle("-fx-background-color: #81D8D0");
                        }
                        if (ships[x][y].getId() > 0) {

                            button.setStyle("-fx-background-color: #826D8C");
                            points += ships[x][y].getId() / 10;
                            System.out.println(ships[x][y].getId());
                            System.out.println("Points " + points);

                            hittedShips.add(ships[x][y]);
                            ArrayList<Ship> hittedShip = isDead(ships[x][y]);
                            if (hittedShip != null) {
                                ArrayList<Ship> waterAroundDeadShip = Ships.getShipsAroundShip(hittedShip, ships);
                                for (Ship Ship : waterAroundDeadShip) {
                                    buttons[Ship.getX()][Ship.getY()].setStyle("-fx-background-color: #81D8D0");
                                    buttons[Ship.getX()][Ship.getY()].setDisable(true);
                                }
                            }
                        }

                        if (event.getSource() == button) {
                            counter -= 1;
                            if (counter == 0 && points < 50) {
                                blockAllButtons();
                            }

                            lost();
                        }
                    }
                });
            }
            vbox.getChildren().add(hBox);
        }
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

    private void blockAllButtons() {
        for (Button[] button : buttons) {
            for (Button value : button) {
                value.setDisable(true);
            }
        }
    }

    private void lost(){
        if (counter > 0 && points == 50) {
            blockAllButtons();
        }
    }
}
