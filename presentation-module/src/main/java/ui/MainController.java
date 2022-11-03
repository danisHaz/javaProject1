package ui;

import ui.drawers.DrawerFactory;

import app.*;

import java.util.TreeSet;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MainController {

	public static TreeSet<String> moves = new TreeSet<String>() {{
		add("Rot");
		add("SymAxis");
		add("Shift");
	}};
	
	private final String fileName = "C:/Users/Danis/Documents/ToCode/java_semestr2/textFile.txt";
	private Main mInst;

	@FXML
    private Canvas canvas; 

	public static List<IShape> list = new ArrayList<>();
	public static List<String> types = new ArrayList<>();

	private void writeToFile() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(list.size());
			writer.write("\n");
			for (int i = 0; i < list.size(); i++) {
				writer.write(list.get(i).toString());
				writer.write("\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Error alert!");
 
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Error occured, check stack trace.");
 
        alert.showAndWait();
    }

	public void moveFigure(int pos, String type, double[] args) {
		try {
			switch (type) {
				case "Rot":
					list.get(pos).rot(args[0]);
					break;
				case "SymAxis":
					list.get(pos).symAxis((int)args[0]);
					break;
				case "Shift":
					list.get(pos).shift(new Point2D(args[0], args[1]));
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		gc.setStroke(Color.GREY);
		gc.strokeLine(200.0, 0.0, 200.0, 400.0);
		gc.strokeLine(0.0, 200.0, 400.0, 200.0);
		gc.setStroke(Color.BLACK);

		for (int i = 0; i < list.size(); i++) {
			try {
				if (i == pos) {
					gc.setStroke(Color.RED);
					DrawerFactory.create(list.get(i)).draw(list.get(i), gc);
					gc.setStroke(Color.BLACK);
				} else {
					DrawerFactory.create(list.get(i)).draw(list.get(i), gc);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void removeAll() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		list = new ArrayList<IShape>();

		gc.setStroke(Color.GREY);
		gc.strokeLine(200.0, 0.0, 200.0, 400.0);
		gc.strokeLine(0.0, 200.0, 400.0, 200.0);
		gc.setStroke(Color.BLACK);
	}

	// callback func
	public void countByPos(int pos, boolean type) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		gc.setStroke(Color.GREY);
		gc.strokeLine(200.0, 0.0, 200.0, 400.0);
		gc.strokeLine(0.0, 200.0, 400.0, 200.0);
		gc.setStroke(Color.BLACK);

		for (int i = 0; i < list.size(); i++) {
			try {
				if (i == pos) {
					gc.setStroke(Color.RED);
					DrawerFactory.create(list.get(i)).draw(list.get(i), gc);
					gc.setStroke(Color.BLACK);
				} else {
					DrawerFactory.create(list.get(i)).draw(list.get(i), gc);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		TextField field = new TextField();
		field.setLayoutX(450.0);
		field.setLayoutY(270.0);
		try {
			if (!type)
				field.setText(String.valueOf(list.get(pos).length()));
			else
				field.setText(String.valueOf(list.get(pos).square()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		mInst.getPane().getChildren().addAll(field);
	}

	// callback func
	public void setIfCross(IShape first, IShape second) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		gc.setStroke(Color.GREY);
		gc.strokeLine(200.0, 0.0, 200.0, 400.0);
		gc.strokeLine(0.0, 200.0, 400.0, 200.0);
		gc.setStroke(Color.BLACK);

		for (int i = 0; i < list.size(); i++) {
			try {
				if (first.toString().equals(list.get(i).toString())) {
					gc.setStroke(Color.RED);
					DrawerFactory.create(first).draw(first, gc);
					gc.setStroke(Color.BLACK);
				} else {
					DrawerFactory.create(list.get(i)).draw(list.get(i), gc);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (second.toString().equals(list.get(i).toString())) {
					gc.setStroke(Color.RED);
					DrawerFactory.create(second).draw(second, gc);
					gc.setStroke(Color.BLACK);
				} else {
					DrawerFactory.create(list.get(i)).draw(list.get(i), gc);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		TextField field = new TextField();
		field.setLayoutX(450.0);
		field.setLayoutY(270.0);
		try {
			if (first.cross(second))
				field.setText("Cross");
			else
				field.setText("Not Cross");
		} catch (Exception e) {
			e.printStackTrace();
		}

		mInst.getPane().getChildren().addAll(field);
	}

	// callback func
	public void removeFigureByPosition(int pos) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		list.remove(pos);
		gc.setStroke(Color.GREY);
		gc.strokeLine(200.0, 0.0, 200.0, 400.0);
		gc.strokeLine(0.0, 200.0, 400.0, 200.0);
		gc.setStroke(Color.BLACK);
		for (int i = 0; i < list.size(); i++) {
			try {
				DrawerFactory.create(list.get(i)).draw(list.get(i), gc);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}

	private void addToListAndDraw(IShape shape) {
		list.add(shape);
		try {
			DrawerFactory.create(shape).draw(shape, canvas.getGraphicsContext2D());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addCircle(double[] coords) throws Exception {
		double r = coords[2];
		Point2D point = new Point2D(coords[0], coords[1]);
		System.out.printf("ababsda %.2f, %.2f\n", coords[0], coords[1]);
		addToListAndDraw(new Circle(point, r));
	}

	public void addNGon(double[] coords) throws Exception {
		int n = coords.length / 2;
		Point2D[] points = new Point2D[n];
		for (int i = 0; i < coords.length; i += 2) {
			points[i / 2] = new Point2D(coords[i], coords[i + 1]);
		}

		addToListAndDraw(new NGon(points));
	}

	public void addQGon(double[] coords) throws Exception {
		Point2D[] points = new Point2D[4];
		for (int i = 0; i < coords.length; i += 2) {
			points[i / 2] = new Point2D(coords[i], coords[i + 1]);
		}
		addToListAndDraw(new QGon(points));	
	}

	public void addRectangle(double[] coords) throws Exception {
		Point2D[] points = new Point2D[4];
		for (int i = 0; i < coords.length; i += 2) {
			points[i / 2] = new Point2D(coords[i], coords[i + 1]);
		}
		addToListAndDraw(new Rectangle(points));
	}

	public void addTrapeze(double[] coords) throws Exception {
		Point2D[] points = new Point2D[4];
		for (int i = 0; i < coords.length; i += 2) {
			points[i / 2] = new Point2D(coords[i], coords[i + 1]);
		}
		addToListAndDraw(new Trapeze(points));
	}

	public void addPolyline(double[] coords) throws Exception {
		int n = coords.length / 2;
		Point2D[] points = new Point2D[n];
		for (int i = 0; i < coords.length; i += 2) {
			points[i / 2] = new Point2D(coords[i], coords[i + 1]);
		}
		addToListAndDraw(new Polyline(points));
	}

	public void addTGon(double[] coords) throws Exception {
		int n = 3;
		Point2D[] points = new Point2D[n];
		for (int i = 0; i < coords.length; i += 2) {
			points[i / 2] = new Point2D(coords[i], coords[i + 1]);
		}

		addToListAndDraw(new TGon(points));
	}

	public void addSegment(double[] coords) throws Exception {
		addToListAndDraw(new Segment(new Point2D(coords[0], coords[1]),
			new Point2D(coords[2], coords[3])));
	}

	public void addShape(String type, double[] coords) throws Exception {
		if (type.equals("Circle")) {
			addCircle(coords);
		} else if (type.equals("Segment")) {
			addSegment(coords);
		} else if (type.equals("TGon")) {
			addTGon(coords);
		} else if (type.equals("NGon")) {
			addNGon(coords);
		} else if (type.equals("QGon")) {
			addQGon(coords);
		} else if (type.equals("Trapeze")) {
			addTrapeze(coords);
		} else if (type.equals("Rectangle")) {
			addRectangle(coords);
		} else if (type.equals("Polyline")) {
			addPolyline(coords);
		}
	}
	 
	@FXML
	private void initialize() 
	{
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.GREY);
		gc.strokeLine(200.0, 0.0, 200.0, 400.0);
		gc.strokeLine(0.0, 200.0, 400.0, 200.0);
		gc.setStroke(Color.BLACK);
	}

	public MainController(Main mInst) { this.mInst = mInst; }
	
	@FXML
	private void addFigure() {
		FigureAddition addition = new FigureAddition();
		try {
			addition.start(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void saveToFile() {
		writeToFile();	
	}
	
	@FXML
	private void uploadFromFile() {
		// out of order
	}

	@FXML
	private void saveAsImg() {
		SnapshotParameters sp = new SnapshotParameters();
		sp.setFill(Color.TRANSPARENT);
		WritableImage wi = new WritableImage((int)(canvas.getHeight()), (int)(canvas.getWidth()));
		Image img = canvas.snapshot(null, wi);
		File file = new File("C:/Users/Danis/Documents/ToCode/java_semestr2/model.png");
		BufferedImage bufImg = SwingFXUtils.fromFXImage(img, null);
		try {
			ImageIO.write(bufImg, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void dragFigure() {
		DragFigure dragger = new DragFigure();
		try {
			dragger.start(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void removeFigure() {
		FigureRemoval removal = new FigureRemoval();
		try {
			removal.start(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void clear() {
		removeAll();
	}

	@FXML
	private void computeS() {
		Count cnt = new Count();
		try {
			cnt.start(this, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void computeP() {
		Count cnt = new Count();
		try {
			cnt.start(this, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void checkIfCross() {
		CrossChecking checking = new CrossChecking();
		try {
			checking.start(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}