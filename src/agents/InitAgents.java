package agents;

import jade.Boot;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class InitAgents {

	/**
	 * Método que adiciona agentes ao container
	 * @param agent
	 */
	public static void insertAgent(Agent agent) {
		Boot.main(new String[]{"-gui"});
		// Instancia Runtime do Jade.
		Runtime runtime = Runtime.instance();

		Profile profile = new ProfileImpl();
		profile.setParameter(ProfileImpl.CONTAINER_NAME, "AgentContainer");

		AgentContainer agentContainer = runtime.createAgentContainer(profile);

		try {

			AgentController agentController = agentContainer.acceptNewAgent(agent.getClass().getSimpleName(), agent);
			agentController.start();

			System.out.println("\n\n Nome do agente: " + agent.getClass().getSimpleName());
			System.out.println("\n\n Agente: " + agent);

		} catch (StaleProxyException ex) {
			System.out.println("Agente nao pode ser iniciado");
			ex.printStackTrace();
		}

	}
	
}