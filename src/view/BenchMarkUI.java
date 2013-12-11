package view;

import jade.Boot;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.components.AbaChooseAlgorithm;
import view.components.AbaConfigureTestExperiments;
import view.components.AbaVisualization;
import view.components.SelectDataSet;
import agentes.AgentTest;

public class BenchMarkUI {

	private JFrame window;
	private JPanel mainPanel;
	private JTabbedPane tabbedPane;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new BenchMarkUI().mountScreen();
				//inicializa o jade
				Boot.main(new String[]{"-gui"});
				insertAgent(new AgentTest() );
			}
		});
	}

	
	public static void insertAgent(Agent agent){
		
		//Instancia um objeto Runtime do Jade.
		Runtime runtime = Runtime.instance();
		
		Profile profile = new ProfileImpl();
		profile.setParameter(ProfileImpl.CONTAINER_NAME, "AgentContainer");
		
		AgentContainer agentContainer = runtime.createAgentContainer(profile);
		
		try {
			
			AgentController agentController = agentContainer.acceptNewAgent(agent.getClass().getName(), agent);
			agentController.start();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	private void mountScreen() {
		mountWindow();
		mountMainPanel();
		mountTabbedPanel();
		mountAbaConfigure();
		mountAbaChooseSimilarity();
		mountAbaConfigureTestExperiments();
		mountAbaVisualization();
		displayWindow();
	}

	private void mountWindow() {
		window = new JFrame("Benchmark");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			 UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(window);
	}

	private void displayWindow() {

		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		window.pack();
		window.setSize(width - (width / 5),height - ( height / 5));
		window.setLocationRelativeTo(null);
		window.setResizable(true);
		window.setVisible(true);
	}

	private void mountMainPanel() {
		mainPanel = new JPanel();
		window.add(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	}

	private void mountTabbedPanel() {
		tabbedPane = new JTabbedPane();
		mainPanel.add(tabbedPane);
	}

	private void mountAbaConfigure() {
		tabbedPane.addTab("Select DataSet", null, new SelectDataSet(), null);
	}

	public void mountAbaChooseSimilarity() {
		tabbedPane.addTab("Choose Algorithm", null, new AbaChooseAlgorithm(), null);
	}
	
	public void mountAbaConfigureTestExperiments(){
		tabbedPane.addTab("Configure Test Experiments", null, new AbaConfigureTestExperiments(), null);
	}
	
	public void mountAbaVisualization(){
		tabbedPane.addTab("Visualization", null, new AbaVisualization(), null);
	}
}