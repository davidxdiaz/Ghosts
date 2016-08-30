
import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class MainMenu {
	public static JPanel panel; //Panel contenedor
	public static JFrame frame; //Frame principal
	public static JButton btnPlay; //Boton jugar
	public static JButton btnConfig; //Boton Configuracion
	public static JButton btnReport; //Boton Reportes
	public static JButton btnProfile; //Boton perfiles
	public static JButton btnHowTo; //Boton Como jugar
	public static JButton btnLogout; //Boton Cerrar Sesion

	/**
	 * ESCONDE LA VENTANA
	 */
	public static void hide()
	{
		frame.dispose();
	}

	/**
	 * CREA Y MUESTRA LA VENTANA
	 */
	public static void show()
	{
		/**
		 * CAMBIAR LOOKANDFEEL
		 */
		try {
			UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
		} catch (Exception e) {
			// TODO: handle exception
		}
		/**
		 * DECLARACION DE VARIABLES
		 */
		JFrame.setDefaultLookAndFeelDecorated(true);
		panel = new JPanel();
		frame = new JFrame("Menu principal");
		btnPlay = new JButton("Jugar");
		btnConfig = new JButton("Configuracion");
		btnReport = new JButton("Reportes de Jugador");
		btnProfile = new JButton("Perfil de Jugador");
		btnHowTo  = new JButton("Como Jugar");
		btnLogout = new JButton("Cerrar Sesion");

		/**
		 * AGREGAR ACTION LISTENERS
		 */
		ListenForButton l = new ListenForButton();
		panel.setLayout(new MigLayout());
		windowsEvent w = new windowsEvent();
		frame.addWindowListener(w);

		/**
		 * AÑADIR COMPONENTES A PANEL
		 */
		panel.add(btnPlay, "wrap, push, grow");
		panel.add(btnConfig, "wrap, push, grow");
		panel.add(btnReport, "wrap, push, grow");
		panel.add(btnProfile, "wrap, push, grow");
		panel.add(btnHowTo, "wrap, push, grow");
		panel.add(btnLogout, "wrap, push, grow");
		ListenForButton.addListeners(panel, l);
		/**
		 * DISEÑAR FRAME
		 */
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(panel);
		//frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
