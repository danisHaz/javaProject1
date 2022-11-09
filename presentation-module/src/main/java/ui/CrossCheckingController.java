package ui;

import app.*;

import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class CrossCheckingController {
	private CrossChecking mInst;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private ComboBox<String> firstFigure;

    @FXML
    private ComboBox<String> secondFigure;

    public CrossCheckingController(CrossChecking inst) { mInst = inst; }

    public void setDataToCombo() {
        comboBox.getItems().clear();
        comboBox.getItems().addAll(
            "Circle",
			"Segment",
			"TGon",
			"NGon",
			"QGon",
			"Trapeze",
			"Rectangle",
			"Polyline"
        );

        comboBox.setOnAction((event) -> {
            CrossCheckingController.this.addAttrs((String) comboBox.getValue());
		});
    }

    @FXML
    private void check() {
        String firstOne = (String) firstFigure.getValue();
        String secondOne = (String) secondFigure.getValue();

        if (firstOne == null || secondOne == null
            || firstOne == "" || secondOne == "") {
            mInst.getMainController().showAlert();
            return;
        }

        mInst.getMainController();
        List<IShape> curList = MainController.list;
        IShape fInstance = null;
        IShape sInstance = null;
        for (int i = 0; i < curList.size(); i++) {
            if (fInstance == null && curList.get(i).toString().equals(firstOne)) {
                fInstance = curList.get(i);
            }

            if (sInstance == null && curList.get(i).toString().equals(secondOne)) {
                sInstance = curList.get(i);
            }
        }

        mInst.getMainController().setIfCross(fInstance, sInstance);
        mInst.onDestroy();
    }

    @FXML
    private void cancel() {
        mInst.onDestroy();
    }

    private void addAttrs(String type) {
        firstFigure.getItems().clear();
        secondFigure.getItems().clear();
        mInst.getMainController();
        List<IShape> curList = MainController.list;
        for (int i = 0; i < curList.size(); i++) {
            if (curList.get(i).getType().equals(type)) {
                firstFigure.getItems().add(curList.get(i).toString());
                secondFigure.getItems().add(curList.get(i).toString());
            }
        }
    }
}