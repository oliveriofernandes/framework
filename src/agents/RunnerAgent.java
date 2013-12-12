package agents;

import jade.core.Agent;

public class RunnerAgent extends Agent{
	
	private static final long serialVersionUID = 1L;

	protected void setup() {
		super.setup();
		System.out.println("The " + this.getName() + " agent( AID = " + this.getAID() + ") has been started!");
		removeBehaviour(new RunnerBehaviour());
	}

	protected void takeDown() {
		// Imprimindo uma mensagem de saida
		System.out.println("O agente  " + getAID().getName() + " está sendo finalizado.");
	}

}
