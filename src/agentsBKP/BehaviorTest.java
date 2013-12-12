package agentsBKP;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class BehaviorTest extends TickerBehaviour {

	public BehaviorTest(Agent a, long period) {
		super(a, period);
	}

	private static final long serialVersionUID = 1L;

	@Override
	protected void onTick() {
		//Executar estrategia
		System.out.println("behavior");
	}
}