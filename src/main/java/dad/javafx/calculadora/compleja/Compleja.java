package dad.javafx.calculadora.compleja;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Compleja{
	private DoubleProperty real = new SimpleDoubleProperty(0);
	private DoubleProperty imaginario = new SimpleDoubleProperty(0);
	
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

	/*public Compleja añadir(Compleja c) {
		Compleja sum = new Compleja();
		sum.realProperty().bind(real.add(c.realProperty()));
		sum.imaginarioProperty().bind(imaginario.add(c.imaginarioProperty()));
		return sum;
	}

	public Compleja quitar(Compleja c) {
		Compleja res = new Compleja();
		res.realProperty().bind(realProperty().subtract(c.realProperty()));
		res.imaginarioProperty().bind(imaginarioProperty().subtract(c.imaginarioProperty()));
		return res;
	}
	
	public static void main(String[] args) {
	Compleja a = new Compleja(1, 2);
	Compleja b = new Compleja(3, 4);
	Compleja c = a.añadir(b);
	System.out.println(c);
	a.setReal(10);
	System.out.println(c);
	}*/

}
