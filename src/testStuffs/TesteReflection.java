package testStuffs;

import java.lang.reflect.Field;
import java.util.Collection;

import control.EvaluationMetric;

/**
 * @author Oliverio
 */

public class TesteReflection {
	public Collection<EvaluationMetric> evaluationMetrics;
	
	public static void main(String[] args) throws SecurityException, NoSuchFieldException {
		Class<TesteReflection> klass = TesteReflection.class;
		
		Field field = klass.getField("evaluationMetrics");

		if (field.getType().equals(Collection.class))
			System.out.println("é sso aê!"
					);
	}
}
