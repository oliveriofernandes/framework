package evaluationMetrics;

import java.util.Map;

import control.Comparision;
/**
 * @author Oliverio
 */

public abstract class BaseMetric {

	public abstract  Map<Comparision, Double> executeMetric();
}
