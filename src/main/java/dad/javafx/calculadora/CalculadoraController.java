package dad.javafx.calculadora;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import dad.javafx.utils.Calculadora;;

public class CalculadoraController implements Initializable {

	
	// model
	private StringProperty number = new SimpleStringProperty("0.0");
	
	// La calculadora hace todas las operaciones
	private Calculadora calc = new Calculadora();
	
	// FXML Related
	@FXML
	private GridPane view;
	
	// Incorporamos los números, los metemos en un array
	@FXML
	private List<Button> btnList;
	
	@FXML
	private TextField numberTxt;
	
	public CalculadoraController() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CalculadoraView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Bind
		numberTxt.textProperty().bind(number);
		// Events
		for( Button numberBt : btnList ) {
			numberBt.setOnAction( evt -> onNumberAction(numberBt.getText()));
		}
	}
	
	/**
	 * Presionamos un número
	 * @param text El numero introducido
	 */
	private void onNumberAction(String text) {
		calc.insertar(text.charAt(0));  // Le pasamos el texto de los botones, que debrían de ser números
		number.set(calc.getPantalla());
	}


	public GridPane getRootView() {
		return view;
	}





}
