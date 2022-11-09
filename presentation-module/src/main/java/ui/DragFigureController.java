package ui;

import app.*;

import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.ComboBox;

public class DragFigureController {
	private DragFigure mInst;
	private List<TextField> list = new ArrayList<>();
	private Spinner<Integer> spinner;
	private double initX = 5.0;
	private double initY = 100.0;
	private double currentX = initX;
	private double currentY = initY;
	private final double deltaX = 160.0;
	private final double deltaY = 30.0;
	private boolean isLeft = true;

	@FXML
	private ComboBox<String> comboBox;

	@FXML
	private ComboBox<String> drags;

	public DragFigureController(DragFigure controller) { mInst = controller; }
	
	public void setDataToCombo() {
		comboBox.getItems().clear();
		mInst.getMainController();
		List<IShape> curList = MainController.list;
		for (int i = 0; i < curList.size(); i++)
			comboBox.getItems().add(curList.get(i).toString());

		drags.getItems().clear();
		currentX = initX;
		currentY = initY;

		drags.getItems().addAll(
			"Rot",
			"SymAxis",
			"Shift"
		);

		drags.setOnAction((event) -> {
			DragFigureController.this.addAttrs((String) drags.getValue());
		});
	}

	@FXML
	private void dragCurrent() {
		String figure = (String) comboBox.getValue();
		String type = (String) drags.getValue();

		if (figure == "" || type == ""
			|| figure == null || type == null) {
			mInst.getMainController().showAlert();
			return;
		}

		mInst.getMainController();
		List<IShape> curList = MainController.list;
		for (int i = 0; i < curList.size(); i++) {
			if (curList.get(i).toString().equals(figure)) {
				double[] args;
				if (type == "SymAxis") {
					args = new double[1];
					args[0] = (double) ((Integer) spinner.getValue()).intValue();
				} else
					args = new double[list.size()];
				for (int j = 0; j < list.size(); j++) {
					args[j] = Double.parseDouble(list.get(j).getText());
				}

				mInst.getMainController().moveFigure(i, type, args);
				mInst.onDestroy();
				return;
			}
		}

	}

	@FXML
	private void cancel() {
		mInst.onDestroy();
	}

	private void removeSpinner() {
		if (spinner != null)
			mInst.getPane().getChildren().remove(spinner);
	}

	private void addSpinner() {
		spinner = new Spinner<Integer>();
		SpinnerValueFactory<Integer> valueFactory =
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1, 0);

		spinner.setValueFactory(valueFactory);

		spinner.setLayoutX(initX);
		spinner.setLayoutY(initY);

		mInst.getPane().getChildren().addAll(spinner);
	}

	private void addAttrs(String value) {
		for (int i = 0; i < list.size(); i++) {
			mInst.getPane().getChildren().remove(list.get(i));
		}

		list = new ArrayList<>();
		removeSpinner();
		isLeft = true;
		
		currentX = initX;
		currentY = initY;

		if (value == "Rot") {
			addNFields(1);
		} else if (value == "SymAxis") {
			addSpinner();
		} else {
			addNFields(2);
		}
	}

	private void setCoords(TextField field) {
		field.setLayoutX(currentX);
		field.setLayoutY(currentY);
		if (isLeft) {
			currentX += deltaX;
			isLeft = false;
		} else {
			currentX -= deltaX;
			currentY += deltaY;
			isLeft = true;
		}
	}

	private void addField(TextField field) {
		list.add(field);
		mInst.getPane().getChildren().addAll(field);
	}

	private void addNFields(int n) {
		for (int i = 0; i < n; i++) {
			TextField field = new TextField();
			field.setText("0");
			setCoords(field);
			addField(field);
		}
	}
}