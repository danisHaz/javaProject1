package ui;

import java.io.FileInputStream;
import java.io.IOException;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

// import com.mongodb.MongoClientURI;

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
	private MongoClient mongoClient = null;
	private MongoClientSettings mongoSettings = null;
	private final ConnectionString mongoClientUri = new ConnectionString("mongodb+srv://juvenal:geometrynoname@javageometryclustrer.yzcxxso.mongodb.net/?retryWrites=true&w=majority");

	public Pane getPane() { return root; }
	public MongoClient getMongoClient() { return mongoClient; }

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		String fxmlDocPath = "./src/main/java/ui/Main.fxml";
		FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
		mongoSettings = MongoClientSettings.builder()
			.applyConnectionString(mongoClientUri)
			.build();
		mongoClient = MongoClients.create(mongoSettings);
		
		loader.setController(new MainController(this));
		root = (Pane) loader.load(fxmlStream);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Geometry");
		stage.show();
	}

	@Override
	public void stop() {
		mongoClient.close();
		mongoClient = null;
		mongoSettings = null;
	}
}
