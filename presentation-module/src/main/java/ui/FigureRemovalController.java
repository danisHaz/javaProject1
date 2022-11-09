package ui;

import app.*;

import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
public class FigureRemovalController {
    private FigureRemoval mInst;

	public FigureRemovalController(FigureRemoval inst) { mInst = inst; }

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private void removeCurrent() {
        mInst.getMainController();
        List<IShape> curList = MainController.list;
        String obj = (String) comboBox.getValue();
        for (int i = 0; i < curList.size(); i++) {
            if (curList.get(i).toString().equals(obj)) {
                mInst.getMainController().removeFigureByPosition(i);
                mInst.onDestroy();
                return;
            }
        }
    }

    @FXML
    private void cancel() {
        mInst.onDestroy();
    }

    public void setDataToCombo() {
        comboBox.getItems().clear();
        mInst.getMainController();
        List<IShape> curList = MainController.list;
		for (int i = 0; i < curList.size(); i++) {
            comboBox.getItems().add(curList.get(i).toString());
        }
    }
}