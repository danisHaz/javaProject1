package ui;

import app.*;

import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class CountController {
    private Count mInst;
    private boolean type;

    public CountController(Count mInst, boolean type) {
        this.mInst = mInst;
        this.type = type;
    }

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private void count() {
        mInst.getMainController();
        List<IShape> list = MainController.list;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).toString().equals((String) comboBox.getValue())) {
                mInst.onDestroy();
                mInst.getMainController().countByPos(i, type);
                return;
            }
        }
    }

    @FXML
    private void cancel() {
        mInst.onDestroy();
    }

    public void setDataToCombo() {
        mInst.getMainController();
        List<IShape> list = MainController.list;
        for (int i = 0; i < list.size(); i++) {
            comboBox.getItems().add(list.get(i).toString());
        }

    }
}