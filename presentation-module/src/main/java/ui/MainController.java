package ui;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import app.Circle;
import app.IShape;
import app.NGon;
import app.Point2D;
import app.Polyline;
import app.QGon;
import app.Rectangle;
import app.Segment;
import app.TGon;
import app.Trapeze;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import ui.drawers.DrawerFactory;

public class MainController {
	
	private final String fileName = "./src/main/java/ui/figures.txt";
	private final String pngFilePath = "./src/main/java/ui/snapshot.png";
	private Main mInst;

	private final int answerLayoutX = 450;
	private final int answerLayoutY = 305;
	private TextField answer = null;

	@FXML
    private Canvas canvas;

	public static List<IShape> list = new ArrayList<>();
	public static List<String> types = new ArrayList<>();

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

		clearCanvasAndDrawAll(canvas.getGraphicsContext2D(), new IShape[] {list.get(pos)});
	}

	// callback func
	public void countByPos(int pos, boolean type) {
		clearCanvasAndDrawAll(canvas.getGraphicsContext2D(), new IShape[] {list.get(pos)});

		answer = new TextField();
		answer.setLayoutX(answerLayoutX);
		answer.setLayoutY(answerLayoutY);
		double res = 0.0;
		try {
			if (!type)
				res = list.get(pos).length();
			else
				res = list.get(pos).square();
		} catch (Exception e) {
			e.printStackTrace();
		}

		answer.setText(String.valueOf(res));
		mInst.getPane().getChildren().addAll(answer);
	}

	// callback func
	public void setIfCross(IShape first, IShape second) {
		clearCanvasAndDrawAll(canvas.getGraphicsContext2D(), new IShape[] {first, second});

		answer = new TextField();
		answer.setLayoutX(answerLayoutX);
		answer.setLayoutY(answerLayoutY);
		try {
			if (first.cross(second))
				answer.setText("Cross");
			else
				answer.setText("Not Cross");
		} catch (Exception e) {
			e.printStackTrace();
		}

		mInst.getPane().getChildren().addAll(answer);
	}

	// callback func
	public void removeFigureByPosition(int pos) {
		list.remove(pos);
		clearCanvasAndDrawAll(canvas.getGraphicsContext2D(), null);
	}

	public void addCircle(double[] coords) throws Exception {
		double r = coords[2];
		Point2D point = new Point2D(coords[0], coords[1]);
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
		clearCanvas(canvas.getGraphicsContext2D());
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
		File file = new File("./src/main/java/ui/figures.txt");
		FileInputStream fstream = null;
		try {
			fstream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		try {
			String line;
			while ((line = br.readLine()) != null) {
				IShape restoredShape = IfigureFactory.create(line);
				addToListAndDraw(restoredShape);
			}
		} catch (Exception e) {
			showAlert();
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {

		}
	}

	@FXML
	private void saveAsImg() {
		SnapshotParameters sp = new SnapshotParameters();
		sp.setFill(Color.TRANSPARENT);
		WritableImage wi = new WritableImage((int)(canvas.getWidth()), (int)(canvas.getHeight()));
		Image img = canvas.snapshot(null, wi);
		File file = new File(pngFilePath);
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

	private void clearCanvas(GraphicsContext gc) {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setStroke(Color.GREY);
		gc.strokeLine(200.0, 0.0, 200.0, 400.0);
		gc.strokeLine(0.0, 200.0, 400.0, 200.0);
		gc.setStroke(Color.BLACK);
	}

	private boolean findIn(IShape shape, IShape[] shapeList) {
		if (shapeList == null || shape == null)
			return false;

		for (int i = 0; i < shapeList.length; i++) {
			if (shape.toString().equals(shapeList[i].toString()))
				return true;
		}

		return false;
	}

	private void clearCanvasAndDrawAll(GraphicsContext gc, IShape[] redList) {
		clearCanvas(gc);

		for (int i = 0; i < list.size(); i++) {
			try {
				if (findIn(list.get(i), redList)) {
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

	private void writeToFile() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			String endOf = "\n";
			for (int i = 0; i < list.size(); i++) {
				if (i == list.size() - 1)
					endOf = "";
				writer.write(list.get(i).toString() + endOf);
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
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

	private void removeAll() {
		list = new ArrayList<IShape>();
		clearCanvas(canvas.getGraphicsContext2D());
	}
}