package agentes;


import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class AgenteIUserBasedPearsonBehavior extends TickerBehaviour{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4468228483805851156L;


	public AgenteIUserBasedPearsonBehavior(Agent a, long period) {
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
