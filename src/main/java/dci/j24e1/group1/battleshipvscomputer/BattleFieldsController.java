package dci.j24e1.group1.battleshipvscomputer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class BattleFieldsController {
    @FXML
    public Label counterLabel;

    @FXML
    public Label messageText;
    @FXML
    private VBox computerBox;


    @FXML
    private void initialize() {
        Ships computerShips = new Ships();
        Field field = new Field(computerBox, computerShips.getShips(), counterLabel );

        for (int i = 0; i < computerShips.getShips().length; i++) {
            for (int j = 0; j < computerShips.getShips()[i].length; j++) {
                System.out.print(computerShips.getShips()[i][j].getId() + " ");
                if (computerShips.getShips()[i][j].getId() == 0) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

}