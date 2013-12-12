package agentsBKP;


import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class AgenteItemBasedPearsonBehavior extends TickerBehaviour{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4468228483805851156L;


	public AgenteItemBasedPearsonBehavior(Agent a, long period) {
		super(a, period);
	}


	@Override
	protected void onTick() {
		//Aplica?�o do padr�o de projeto Strategy
		//CriticaCardapio � uma feature alternativa. 
		//Poderia ser CriticaCardapioDietaEstrategia
//		 Contexto contexto = new Contexto(new CriticaCardapioSimplesEstrategia());
//	     contexto.executeEstrategia();	
	}

}
