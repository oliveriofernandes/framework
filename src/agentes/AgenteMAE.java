package agentes;

import jade.core.Agent;
/*
 * Agente ItemBasedPearson é uma feature alternativa,
 * definida pelo padrão Strategy e é executada de 5 em 5 segundos
 * afim de que seja executada ou não sua medida no processo da
 * experimentação
 */
// O agente sera executado de 5 em 5 segundos
public class AgenteMAE extends Agent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6776055151529992525L;

	protected void setup(){
		
		super.setup();
		
		addBehaviour(new AgenteItemBasedPearsonBehavior(this, 10000));
	}

}
