/*
 * José Ignacio Pérez Delgado
 * Calculadora compleja
 */
package dad.javafx.calculadora.compleja;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class Calculadora extends Application {
	private ComboBox<String> calculos;
	private String[] operacionesarray = { "+", "-", "*", "/" };

	private TextField numeroA, numeroB, numeroC, numeroD, resultado1, resultado2;

	private Compleja complejaA = new Compleja();
	private Compleja complejaB = new Compleja();
	private Compleja complejaResultante = new Compleja();

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Creamos el comboBox y lo añadimos a su HBox
		calculos = new ComboBox<String>();
		calculos.getItems().addAll(operacionesarray);
		calculos.setOnAction(e -> onOperacionAction());

		VBox operacionbox = new VBox();
		operacionbox.setAlignment(Pos.CENTER);
		operacionbox.setSpacing(5);
		operacionbox.getChildren().addAll(calculos);

		// Numero complejo 1
		numeroA = new TextField();
		numeroA.setPrefColumnCount(3);
		numeroA.setMaxWidth(100);
		numeroA.setAlignment(Pos.CENTER);

		numeroB = new TextField();
		numeroB.setPrefColumnCount(3);
		numeroB.setMaxWidth(100);
		numeroB.setAlignment(Pos.CENTER);

		Bindings.bindBidirectional(numeroA.textProperty(), complejaA.realProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(numeroB.textProperty(), complejaA.imaginarioProperty(), new NumberStringConverter());

		// HBox del numero complejo 1
		HBox calculo1 = new HBox();
		calculo1.setAlignment(Pos.BASELINE_CENTER);
		calculo1.setSpacing(5);
		calculo1.getChildren().addAll(numeroA, new Label(" + "), numeroC, new Label(" i"));

		// Numero complejo 2
		numeroC = new TextField();
		numeroC.setPrefColumnCount(3);
		numeroC.setMaxWidth(100);
		numeroC.setAlignment(Pos.CENTER);

		numeroD = new TextField();
		numeroD.setPrefColumnCount(3);
		numeroD.setMaxWidth(100);
		numeroD.setAlignment(Pos.CENTER);

		Bindings.bindBidirectional(numeroC.textProperty(), complejaB.realProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(numeroD.textProperty(), complejaB.imaginarioProperty(), new NumberStringConverter());

		// Hbox del segundo numero complejo
		HBox calculo2 = new HBox();
		calculo2.setAlignment(Pos.BASELINE_CENTER);
		calculo2.setSpacing(5);
		calculo2.getChildren().addAll(numeroB, new Label(" + "), numeroD, new Label(" i"));

		// El separador de los numeros complejos
		Separator separador = new Separator();

		// Resultado complejo
		resultado1 = new TextField();
		resultado1.setPrefColumnCount(3);
		resultado1.setMaxWidth(100);
		resultado1.setAlignment(Pos.CENTER);
		resultado1.setDisable(true); // para que no se pueda modificar el textfield directamente

		resultado2 = new TextField();
		resultado2.setPrefColumnCount(3);
		resultado2.setMaxWidth(100);
		resultado2.setAlignment(Pos.CENTER);
		resultado2.setDisable(true); // igual

		Bindings.bindBidirectional(resultado1.textProperty(), complejaResultante.realProperty(),
				new NumberStringConverter());
		Bindings.bindBidirectional(resultado2.textProperty(), complejaResultante.imaginarioProperty(),
				new NumberStringConverter());
		
		//Hbox del resultado complejo
		HBox resultadocomplejo = new HBox();
		resultadocomplejo.setAlignment(Pos.BASELINE_CENTER);
		resultadocomplejo.setSpacing(5);
		resultadocomplejo.getChildren().addAll(resultado1, new Label(" + "), resultado2, new Label(" i"));
		
		//Vbox de la operacion
		VBox calculosbox = new VBox();
		calculosbox.setAlignment(Pos.CENTER);
		calculosbox.setSpacing(5);
		calculosbox.getChildren().addAll(calculo1, calculo2, separador, resultadocomplejo);
		
		//Hbox que contiene toda la calculadora
		HBox root = new HBox();
		root.setAlignment(Pos.BASELINE_CENTER);
		root.setSpacing(5);
		root.getChildren().addAll(operacionbox, calculosbox);
		root.setAlignment(Pos.CENTER);

		Scene escena = new Scene(root, 320, 200);

		primaryStage.setScene(escena);
		primaryStage.setTitle("Calculadora Compleja");
		primaryStage.show();
	}

	private void onOperacionAction() {
		String operacion = calculos.getSelectionModel().getSelectedItem();

		if (operacion.equals("+")) { //Suma
			complejaResultante.realProperty().bind(complejaA.realProperty().add(complejaB.realProperty()));

			complejaResultante.imaginarioProperty()
					.bind(complejaA.imaginarioProperty().add(complejaB.imaginarioProperty()));

		} else if (operacion.equals("-")) { //Resta
			complejaResultante.realProperty().bind(complejaA.realProperty().subtract(complejaB.realProperty()));

			complejaResultante.imaginarioProperty()
					.bind(complejaA.imaginarioProperty().subtract(complejaB.imaginarioProperty()));

		} else if (operacion.equals("*")) { //Multiplicacion
			complejaResultante.realProperty().bind(complejaA.realProperty().multiply(complejaB.realProperty())
					.subtract(complejaA.imaginarioProperty().multiply(complejaB.imaginarioProperty())));

			complejaResultante.imaginarioProperty()
					.bind(complejaA.realProperty().multiply(complejaB.imaginarioProperty())
							.add(complejaA.imaginarioProperty().multiply(complejaB.realProperty())));

		} else { //division
			complejaResultante.realProperty()
					.bind(complejaA.realProperty().multiply(complejaB.realProperty())
							.add(complejaA.imaginarioProperty().multiply(complejaB.imaginarioProperty())
									.divide(complejaB.realProperty()).multiply(complejaB.realProperty())
									.add(complejaB.imaginarioProperty().multiply(complejaB.imaginarioProperty()))));

			complejaResultante.imaginarioProperty()
					.bind(complejaA.imaginarioProperty().multiply(complejaB.realProperty())
							.subtract(complejaA.realProperty().multiply(complejaB.imaginarioProperty())
									.divide(complejaB.realProperty()).multiply(complejaB.realProperty())
									.add(complejaB.imaginarioProperty().multiply(complejaB.imaginarioProperty()))));
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
