
import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class Reportes
{
	public static JPanel panel;
	public static JFrame frame;
	public static JList<String> list;
	public static DefaultListModel<String> model = new DefaultListModel<>();
	public static String[] stats =
		{
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
		};

	public static void hide()
	{
		frame.dispose();
	}

	public static void show()
	{
		try {
			String className = UiCreator.getLookAndFeelClassName("Nimbus");
			UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
		} catch (Exception e) {

		}
		JFrame.setDefaultLookAndFeelDecorated(true);
		panel = new JPanel();
		frame = new JFrame("Reportes");
		list = new JList<>(stats);
		list.setFixedCellHeight(30);
		list.setFixedCellWidth(500);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		for (String stat : stats)
		{
			model.addElement(stat);
		}

		//ListenForButton l = new ListenForButton();

		panel.setLayout(new MigLayout());

		panel.add(new JLabel("Reportes de Jugador: " + Players.getLoggedPlayer()), "wrap");
		panel.add(list, "push, grow");

		//ListenForButton.addListeners(panel, l);


		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
