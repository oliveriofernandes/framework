package control;
/**
 * @author Oliverio
 * 
 */
public class Result {

	double real;
	double predicted;
	Comparision comparision;
	
	public Comparision getComparision() {
		return comparision;
	}
	public void setComparision(Comparision comparision) {
		this.comparision = comparision;
	}
	public double getReal() {
		return real;
	}
	public void setReal(double real) {
		this.real = real;
	}
	public double getPredicted() {
		return predicted;
	}
	public void setPredicted(double predicted) {
		this.predicted = predicted;
	}


	
}
