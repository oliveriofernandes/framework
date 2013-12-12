package agents;

import jade.core.behaviours.OneShotBehaviour;
import java.awt.EventQueue;
import view.BenchMarkUI;

public class ManagerBehavior extends OneShotBehaviour {

	private static final long serialVersionUID = 1L;

	@Override
	public void action() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new BenchMarkUI().mountScreen();
			}
		});
	}
}