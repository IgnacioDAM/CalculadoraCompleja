package dad.javafx.calculadora.compleja;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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

public class Compleja extends Application {
	private DoubleProperty real = new SimpleDoubleProperty(0);
	private DoubleProperty imaginario = new SimpleDoubleProperty(0);
	private ComboBox<Object> calculos;

	public Compleja() {
	}

	public Compleja(double real, double imaginario) {
		super();
		setReal(real);
		setImaginario(imaginario);
	}

	public final DoubleProperty realProperty() {
		return this.real;
	}

	public final double getReal() {
		return this.realProperty().get();
	}

	public final void setReal(final double real) {
		this.realProperty().set(real);
	}

	public final DoubleProperty imaginarioProperty() {
		return this.imaginario;
	}

	public final double getImaginario() {
		return this.imaginarioProperty().get();
	}

	public final void setImaginario(final double imaginario) {
		this.imaginarioProperty().set(imaginario);
	}

	@Override
	public String toString() {
		return getReal() + "+" + getImaginario() + "i";
	}

	public Compleja añadir(Compleja c) {
		Compleja sum = new Compleja();
		sum.realProperty().bind(real.add(c.realProperty()));
		sum.imaginarioProperty().bind(imaginario.add(c.imaginarioProperty()));
		return sum;
	}

	public Compleja quitar(Compleja c) {
		Compleja res = new Compleja();
		res.realProperty().bind(real.divide(c.realProperty()));
		res.imaginarioProperty().bind(imaginario.divide(c.imaginarioProperty()));
		return res;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Label signo = new Label(" + ");
		Label letra = new Label(" i");
		
		TextField numeroA = new TextField();
		numeroA.setPrefColumnCount(3);
		numeroA.setMaxWidth(100);
		numeroA.setAlignment(Pos.CENTER);
		
		TextField numeroB = new TextField();
		numeroB.setPrefColumnCount(3);
		numeroB.setMaxWidth(100);
		numeroB.setAlignment(Pos.CENTER);
				
		numeroA.textProperty().bindBidirectional(realProperty(), new NumberStringConverter());
		numeroB.textProperty().bindBidirectional(imaginarioProperty(), new NumberStringConverter());
		
		TextField numeroC = new TextField();
		numeroC.setPrefColumnCount(3);
		numeroC.setMaxWidth(100);
		numeroC.setAlignment(Pos.CENTER);
		
		TextField numeroD = new TextField();
		numeroD.setPrefColumnCount(3);
		numeroD.setMaxWidth(100);
		numeroD.setAlignment(Pos.CENTER);
		
		numeroC.textProperty().bindBidirectional(realProperty(), new NumberStringConverter());
		numeroD.textProperty().bindBidirectional(imaginarioProperty(), new NumberStringConverter());
		
		TextField resultadocomplejoA = new TextField();
		resultadocomplejoA.setPrefColumnCount(3);
		resultadocomplejoA.setMaxWidth(100);
		resultadocomplejoA.setAlignment(Pos.CENTER);
		
		TextField resultadocomplejoB = new TextField();
		resultadocomplejoB.setPrefColumnCount(3);
		resultadocomplejoB.setMaxWidth(100);
		resultadocomplejoB.setAlignment(Pos.CENTER);
		
		HBox calculo1 = new HBox();
		calculo1.setAlignment(Pos.BASELINE_CENTER);
		calculo1.setSpacing(5);
		calculo1.getChildren().addAll(numeroA, signo, numeroC, letra);
		
		HBox calculo2 = new HBox();
		calculo2.setAlignment(Pos.BASELINE_CENTER);
		calculo2.setSpacing(5);
		calculo2.getChildren().addAll(numeroB, signo, numeroD, letra);
		
		Separator separador = new Separator();
		
		HBox resultadocomplejo = new HBox();
		resultadocomplejo.setAlignment(Pos.BASELINE_CENTER);
		resultadocomplejo.setSpacing(5);
		resultadocomplejo.getChildren().addAll(resultadocomplejoA, signo , resultadocomplejoB, letra);
		
		calculos = new ComboBox<>();
		calculos.getItems().addAll(añadir(), quitar(null));
		calculos.getSelectionModel().selectFirst();
		
		VBox operacionbox = new VBox();
		operacionbox.setAlignment(Pos.CENTER);
		operacionbox.setSpacing(5);
		operacionbox.getChildren().addAll(operacionbox);
		
		VBox calculosbox = new VBox();
		calculosbox.setAlignment(Pos.CENTER);
		calculosbox.setSpacing(5);
		calculosbox.getChildren().addAll(calculo1, calculo2, separador, resultadocomplejo);
		
		HBox root = new HBox();
		root.setAlignment(Pos.BASELINE_CENTER);
		root.setSpacing(5);
		root.getChildren().addAll(operacionbox, calculosbox);

		Scene escena = new Scene(root, 320, 200);

		primaryStage.setScene(escena);
		primaryStage.setTitle("IMC");
		primaryStage.show();
	}

	public static void main(String[] args) {
		Compleja a = new Compleja(1, 2);
		Compleja b = new Compleja(3, 4);
		Compleja c = a.añadir(b);
		System.out.println(c);
		a.setReal(10);
		System.out.println(c);
	}

}
