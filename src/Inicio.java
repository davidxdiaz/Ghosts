
import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class Inicio {
	public static JPanel panel;
	public static JFrame frame;
	public static JButton btnLogin;
	public static JButton btnNew;
	public static JButton btnExit;

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
			// TODO: handle exception
		}
		JFrame.setDefaultLookAndFeelDecorated(true);
		panel = new JPanel();
		frame = new JFrame("Inicio");
		btnNew = new JButton("Nuevo Jugador");
		btnExit = new JButton("Salir");
		btnLogin = new JButton("Iniciar Sesion");

		panel.setLayout(new MigLayout());
		ListenForButton l = new ListenForButton();

		panel.add(btnLogin, "wrap, push, grow");
		panel.add(btnNew, "wrap, push, grow");
		panel.add(btnExit, "wrap, push, grow");
		ListenForButton.addListeners(panel, l);


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
