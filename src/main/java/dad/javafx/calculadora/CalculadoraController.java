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

/**
 * Programa controlador de la calculadora.
 * @author David Fernñandez Nieves
 *
 */
public class CalculadoraController implements Initializable {

	private enum eBtLogic {
		BT_SUM,
		BT_MINUS, 
		BT_MULTIPLY,
		BT_DIVIDE, 
		BT_EQUAL, 
		BT_COMMA, 
		BT_CLEAR,
		BT_CLEARALL;
	};
	
	// La calculadora hace todas las operaciones
	private Calculadora calc = new Calculadora();
	
	// model
	private StringProperty number = new SimpleStringProperty("0.0");
	
	// FXML Related : View
	// ----------------------------------------------------------
	
	@FXML
	private GridPane view;
	
	// Incorporamos los números, los metemos en un array
	@FXML
	private List<Button> btnList;
	
	// El texto
	@FXML
	private TextField numberTxt;
	
	// Los bótones de la calculadora
	@FXML
	private Button bt_sum, bt_minus, bt_multiply, bt_divide,
				   bt_comma, bt_equal, bt_clear, bt_clearAll;	
	
	// ----------------------------------------------------------
	
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
		
		bt_sum.setOnAction( evt -> onLogicAction(eBtLogic.BT_SUM, '+'));
		bt_minus.setOnAction( evt -> onLogicAction(eBtLogic.BT_MINUS, '-'));
		bt_multiply.setOnAction( evt -> onLogicAction(eBtLogic.BT_MULTIPLY, '*'));
		bt_divide.setOnAction( evt -> onLogicAction(eBtLogic.BT_DIVIDE, '/'));
		bt_comma.setOnAction( evt -> onLogicAction(eBtLogic.BT_COMMA, '.'));
		bt_equal.setOnAction( evt -> onLogicAction(eBtLogic.BT_EQUAL, '='));
		bt_clear.setOnAction( evt -> onLogicAction(eBtLogic.BT_CLEAR, '0'));
		bt_clearAll.setOnAction( evt -> onLogicAction(eBtLogic.BT_CLEARALL, '0'));
	}
	
	/**
	 * Presionamos un número
	 * @param btText El numero introducido
	 */
	private void onNumberAction(String btText) {
		calc.insertar(btText.charAt(0));  // Le pasamos el texto de los botones, que debrían de ser números
		number.set(calc.getPantalla());
	}
	
	/**
	 * Determinamos que acción debe tomar la calculadora atendiendo al botón pulsado.
	 * @param logicAction El botón utilizado
	 * @param operator En caso de los operadores, '+', '-', '*', '/' ó '='
	 */
	private void onLogicAction(eBtLogic logicAction, char operator) {
		
		switch(logicAction) {
		
		case BT_SUM:
		case BT_MINUS:
		case BT_MULTIPLY:
		case BT_DIVIDE:
		case BT_EQUAL:
			calc.operar(operator);
			break;
		case BT_COMMA:
			calc.insertarComa();
			break;
		case BT_CLEAR:
			calc.borrar();
			break;
		case BT_CLEARALL:
			calc.borrarTodo();
			break;
		default:
			break;
		}
		
		// Refrescamos la pantalla
		number.set(calc.getPantalla());
	}


	public GridPane getRootView() {
		return view;
	}
}
