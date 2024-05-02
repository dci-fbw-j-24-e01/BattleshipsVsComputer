package dci.j24e1.group1.battleshipvscomputer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class BattleFieldsController {
    @FXML
    public Label topLabel;

    @FXML
    public Button startButton;

    @FXML
    public Button randomButton;

    @FXML
    private VBox computerBox;

    @FXML
    private VBox  userBox;
    private Field userField;
    private  Field computerField;

    @FXML
    protected void start() {
        randomButton.setDisable(true);
        startButton.setDisable(true);
    }
    @FXML
    protected void random() {
        userField = new Field(userBox, new Ships().getShips(), true, topLabel);
        computerField = new Field(computerBox, new Ships().getShips(), false, topLabel);
        GameAction.setUserField(userField);
        GameAction.setComputerField(computerField);
    }

//    @FXML
  //  private void initialize() {
       // Ships computerShips = new Ships();
        //Ships userShips = new Ships();
       // Field computerField = new Field(computerBox, computerShips.getShips(), false, topLabel);
        //Field userField = new Field(userBox, userShips.getShips(), true, topLabel);
      //  GameAction.setUserField(userField);
      //  GameAction.setComputerField(computerField);

//        for (int i = 0; i < computerShips.getShips().length; i++) {
//            for (int j = 0; j < computerShips.getShips()[i].length; j++) {
//                System.out.print(computerShips.getShips()[i][j].getId() + " ");
//                if (computerShips.getShips()[i][j].getId() == 0) {
//                    System.out.print(" ");
//                }
//            }
//            System.out.println();
//        }
 //   }

}