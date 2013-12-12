package init;
import agents.ManagerAgent;
import agents.InitAgents;

public class InitializeApp {

	public static void main(String[] args) {
		InitAgents.insertAgent(new ManagerAgent());
	}
}
