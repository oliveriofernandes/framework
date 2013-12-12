package agentsBKP;

import jade.core.Agent;
/*
 * Agente ItemBasedPearson � uma feature alternativa,
 * definida pelo padr�o Strategy e � executada de 5 em 5 segundos
 * afim de que seja executada ou n�o sua medida no processo da
 * experimenta��o
 */
// O agente sera executado de 5 em 5 segundos
public class AgenteMRSE extends Agent {
	
	private static final long serialVersionUID = 6776055151529992525L;

	protected void setup(){
		
		super.setup();
		
		addBehaviour(new AgenteItemBasedPearsonBehavior(this, 10000));
	}

}
