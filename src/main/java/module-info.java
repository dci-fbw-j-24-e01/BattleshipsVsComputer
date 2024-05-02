module dci.j24e1.group1.battleshipvscomputer {
    requires javafx.controls;
    requires javafx.fxml;


    opens dci.j24e1.group1.battleshipvscomputer to javafx.fxml;
    exports dci.j24e1.group1.battleshipvscomputer;
}