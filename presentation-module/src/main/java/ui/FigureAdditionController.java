package ui;

import java.util.*;
import java.lang.Exception;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.ComboBox;

public class FigureAdditionController {
	private FigureAddition mInst;
	private Spinner<Integer> spinner;
	private ArrayList<TextField> coords = new ArrayList<TextField>();
	private double currentX = 5.0;
	private double currentY = 35.0;
	private final double deltaX = 160.0;
	private final double deltaY = 30.0;
	private boolean isLeft = true;

	@FXML
	private ComboBox<String> comboBox;

	public FigureAdditionController(FigureAddition inst) { mInst = inst; }

	public void addAttrs(String type) {
		if (type.equals("Circle")) {
			addCircle();
		} else if (type.equals("Segment")) {
			addSegment();
		} else if (type.equals("TGon")) {
			addTGon();
		} else if (type.equals("NGon")) {
			addNGon();
		} else if (type.equals("QGon")) {
			addQGon();
		} else if (type.equals("Trapeze")) {
			addTrapeze();
		} else if (type.equals("Rectangle")) {
			addRectangle();
		} else if (type.equals("Polyline")) {
			addPolyline();
		}
	}

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
			if (comboBox.getValue() instanceof String)
				FigureAdditionController.this.addAttrs((String) comboBox.getValue());
		});
	}

	@FXML
	private void addCurrent() {
		String type = (String) comboBox.getValue();
		
		double[] c = new double[coords.size()];
		for (int i = 0; i < coords.size(); i++) {
			try {
				c[i] = Double.parseDouble(coords.get(i).getText());
			} catch (Exception e) {
				mInst.getMainController().showAlert();
				e.printStackTrace();
				return;
			}
		}
		try {
			mInst.getMainController().addShape(type, c);
		} catch (Exception e) {
			mInst.getMainController().showAlert();
			e.printStackTrace();
			return;
		}
		cancel();
	}

	@FXML
	private void cancel() {
		removeSpinner();
		clearCoords();
		mInst.onDestroy();
	}

	private void removeSpinner() {
		if (spinner != null)
			mInst.getPane().getChildren().remove(spinner);
	}

	private void addSpinner() {
		spinner = new Spinner<Integer>();
		SpinnerValueFactory<Integer> valueFactory =
				new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);

		spinner.setValueFactory(valueFactory);
		spinner.valueProperty().addListener((obs, oldValue, newValue) -> {
			clearCoords();
			addNFields(newValue);
		});

		spinner.setLayoutX(450.0);
		spinner.setLayoutY(5.0);

		mInst.getPane().getChildren().addAll(spinner);
	}

	private void clearCoords() {
		for (int i = 0; i < coords.size(); i++) {
			if (coords.get(i) != null)
				mInst.getPane().getChildren().remove(coords.get(i));
		}

		coords = new ArrayList<TextField>();
		currentX = 5.0;
		currentY = 35.0;
		isLeft = true;
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
		coords.add(field);
		mInst.getPane().getChildren().addAll(field);
	}

	private void addNFields(int n) {
		for (int i = 0; i < 2 * n; i++) {
			TextField field = new TextField();
			field.setText("0");
			setCoords(field);
			addField(field);
		}
	}

	private void addCircle() {
		removeSpinner();
		clearCoords();
		addNFields(1);
		TextField field = new TextField();
		field.setText("0");
		setCoords(field);
		addField(field);
	}

	private void addSegment() {
		removeSpinner();
		clearCoords();
		addNFields(2);
	}

	private void addTGon() {
		removeSpinner();
		clearCoords();
		addNFields(3);
	}

	private void addNGon() {
		removeSpinner();
		clearCoords();
		addSpinner();
		addNFields(spinner.getValue());
	}

	private void addQGon() {
		removeSpinner();
		clearCoords();
		addNFields(4);
	}

	private void addTrapeze() {
		removeSpinner();
		addQGon();
	}

	private void addRectangle() {
		removeSpinner();
		addQGon();
	}

	private void addPolyline() {
		removeSpinner();
		clearCoords();
		addSpinner();
		addNFields(spinner.getValue());
	}
}