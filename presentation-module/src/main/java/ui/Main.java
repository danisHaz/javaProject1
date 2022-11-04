package ui;

import java.io.FileInputStream;
import java.io.IOException;
 
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
 
public class Main extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	private Pane root;

	public Pane getPane() { return root; }

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		String fxmlDocPath = "./src/main/java/ui/Main.fxml";
		FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
		loader.setController(new MainController(this));
		root = (Pane) loader.load(fxmlStream);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Geometry");
		stage.show();
	}
}
