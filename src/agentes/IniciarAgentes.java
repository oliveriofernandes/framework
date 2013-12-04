package agentes;
import jade.Boot;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;


/**
 * Inicializaçaõ dos agentes.
 */
public class IniciarAgentes {

	public IniciarAgentes() {	}
	
	public IniciarAgentes(Agent agent, String nameAgent, String nameContainer) {
		setAgentInContainer(agent, nameAgent, nameContainer);
	}
	
	/**
	 * MŽtodo para adicionar agente ao container.
	 */
	private void setAgentInContainer(Agent agent, String nameAgent, String nameContainer){
		
		Runtime runtime = Runtime.instance();
        
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.CONTAINER_NAME, nameContainer);
        
        AgentContainer controllerAgentContainer = runtime.createAgentContainer(profile);
        
        try {
            AgentController controller;
            System.out.println("\n\n Nome do agente: "+ nameAgent);
            System.out.println("\n\n Agente: "+ agent);
            controller  = controllerAgentContainer.acceptNewAgent( nameAgent, agent);
            controller.start();
        } catch (StaleProxyException ex) {
            System.out.println("Agente nao pode ser iniciado");
        }
	}

	public static void init(){
		System.out.println("agentes...");
		Boot.main(new String[]{"-gui"});
		
		new IniciarAgentes(new AgenteItemBasedPearson(), "ItemBasedPearson", "evalContainer");
		new IniciarAgentes(new AgenteItemBasedPearson(), "itemBasedCossine", "evalContainer");	}
	

}
