package dad.javafx.main;

import dad.javafx.calculadora.CalculadoraView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalculadoraApp extends Application {

	private CalculadoraView view;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		view = new CalculadoraView();
		
		Scene scene = new Scene(view, 320, 200);
		
		primaryStage.setTitle("Calculadora");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

}
