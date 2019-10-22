package dad.javafx.calculadora;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.AbstractMap;
import java.util.ArrayList;

/**
 * Clase para implementar la vista de la calculadora
 * @author David Fernández Nieves
 *
 */
public class CalculadoraView extends GridPane {

	public enum eBtLogic {
		
		// Hacemos un "map" de nuestros botones
		BT_SUM,
		BT_MINUS, 
		BT_MULTIPLY,
		BT_DIVIDE, 
		BT_EQUAL, 
		BT_COMMA, 
		BT_ERASEALL,
		BT_ERASE 
	};
	
	public Map<eBtLogic, Entry<Integer, String>> logicBtMap = new EnumMap<eBtLogic, Entry<Integer, String>>(eBtLogic.class);
	
	private TextField numberTxt;
	private Button[] numberBts;
	private ArrayList<Button> logicBts;
	
	public CalculadoraView() {
		super();
		
		numberTxt = new TextField();
		numberTxt.setAlignment(Pos.BASELINE_RIGHT);
		numberTxt.setPrefHeight(32);
		
		numberBts = new Button[10];
		for( int i = 0; i < numberBts.length; i++ ) {
			Button bt = new Button(""+i);
			bt.setMaxWidth(Double.MAX_VALUE);  // Ajustamos para que ocupen todo el espacio posible
			bt.setMaxHeight(Double.MAX_VALUE);
			GridPane.setHgrow(bt, Priority.ALWAYS);
			GridPane.setFillWidth(bt, true);
			GridPane.setVgrow(bt, Priority.ALWAYS);
			GridPane.setFillHeight(bt, true);
			numberBts[i] = bt;
		}
		
		logicBts = new ArrayList<Button>();
		
		initMaps();
		
		// Usamos la función foreach del map para obtener lo que necesitamos
		// Fijémonos que lo está añadiendo en orden, por lo que no es muy fácil acceder en el array
		logicBtMap.forEach((key, value) -> {
			Button bt = new Button(value.getValue());
			bt.setMaxWidth(Double.MAX_VALUE);  // Ajustamos para que ocupen todo el espacio posible
			bt.setMaxHeight(Double.MAX_VALUE);
			GridPane.setHgrow(bt, Priority.ALWAYS);
			GridPane.setFillWidth(bt, true);
			GridPane.setVgrow(bt, Priority.ALWAYS);
			GridPane.setFillHeight(bt, true);
			
			logicBts.add(bt);
		});
	
		
		// Añadimos las filas
		addRow(0, numberTxt);
		GridPane.setColumnSpan(numberTxt, 5);
		
		addRow(1, numberBts[7], numberBts[8], numberBts[9], 
				  logicBts.get(logicBtMap.get(eBtLogic.BT_ERASEALL).getKey()), // Obtenemos el ínidice en donde estará
				  logicBts.get(logicBtMap.get(eBtLogic.BT_ERASE).getKey()));
		
		addRow(2, numberBts[4], numberBts[5], numberBts[6], 
				  logicBts.get(logicBtMap.get(eBtLogic.BT_MULTIPLY).getKey()), 
				  logicBts.get(logicBtMap.get(eBtLogic.BT_DIVIDE).getKey()));
		
		
		addRow(3, numberBts[1], numberBts[2], numberBts[3], 
				  logicBts.get(logicBtMap.get(eBtLogic.BT_MINUS).getKey()), 
				  logicBts.get(logicBtMap.get(eBtLogic.BT_EQUAL).getKey()));
		
		
		logicBts.get(logicBtMap.get(eBtLogic.BT_EQUAL).getKey()).setDefaultButton(true);
		
		addRow(4, numberBts[0]);
		setColumnSpan(numberBts[0], 2);
		
		add(logicBts.get(logicBtMap.get(eBtLogic.BT_COMMA).getKey()), 2, 4);
		add(logicBts.get(logicBtMap.get(eBtLogic.BT_SUM).getKey()), 3, 4);
		
		setRowSpan(logicBts.get(logicBtMap.get(eBtLogic.BT_EQUAL).getKey()), 2);
		
		setHgap(5);
		setVgap(5);
		setPadding(new Insets(5));
		
	}
	
	
	public Map<eBtLogic, Entry<Integer, String>> getLogicBtMap() {
		return logicBtMap;
	}


	public TextField getNumberTxt() {
		return numberTxt;
	}


	public Button[] getNumberBts() {
		return numberBts;
	}


	public ArrayList<Button> getLogicBts() {
		return logicBts;
	}


	private void initMaps() {
		
		// Tenemos cuidado y lo ponemos en orden
		// 0 -> +
		// 1 -> -, .......
		logicBtMap.put(eBtLogic.BT_SUM, new AbstractMap.SimpleEntry<Integer, String>(0, "+"));
		logicBtMap.put(eBtLogic.BT_MINUS, new AbstractMap.SimpleEntry<Integer, String>(1, "-"));
		logicBtMap.put(eBtLogic.BT_MULTIPLY, new AbstractMap.SimpleEntry<Integer, String>(2, "*"));
		logicBtMap.put(eBtLogic.BT_DIVIDE, new AbstractMap.SimpleEntry<Integer, String>(3, "/"));
		logicBtMap.put(eBtLogic.BT_EQUAL, new AbstractMap.SimpleEntry<Integer, String>(4, "="));
		logicBtMap.put(eBtLogic.BT_COMMA, new AbstractMap.SimpleEntry<Integer, String>(5, ","));
		logicBtMap.put(eBtLogic.BT_ERASEALL, new AbstractMap.SimpleEntry<Integer, String>(6, "CE"));
		logicBtMap.put(eBtLogic.BT_ERASE, new AbstractMap.SimpleEntry<Integer, String>(7, "C"));
	}
	
}
