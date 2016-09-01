
import javax.imageio.ImageIO;
import javax.swing.*;

import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class Inicio {
	public static JPanel panel;
	public static JFrame frame;
	public static JButton btnLogin;
	public static JButton btnNew;
	public static JButton btnExit;

	public static void hide()
	{
		frame.dispose(); //Deshace del formulario (JFrame)
		//Dispose: deshace el espacio en memoria
		//distinto de JFrame.setvisible(false);

	}

	public static void show()
	{
		//Cambiar LookAndFeel
		try {
			String className = UiCreator.getLookAndFeelClassName("Nimbus");
			UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
		} catch (Exception e) {
			// TODO: handle exception
		}

		JFrame.setDefaultLookAndFeelDecorated(true); //Decorar el jframe
		panel = new JPanel();
		frame = new JFrame("Inicio");
		btnNew = new JButton("Nuevo Jugador");
		btnExit = new JButton("Salir");
		btnLogin = new JButton("Iniciar Sesion");

		panel.setLayout(new MigLayout());
		ListenForButton l = new ListenForButton(); //Escuchar acciones botones

		panel.add(btnLogin, "wrap, push, grow");
		panel.add(btnNew, "wrap, push, grow");
		panel.add(btnExit, "wrap, push, grow");
		ListenForButton.addListeners(panel, l);


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cuando haga clic en Cerra (x) entonces SALIMOS
		frame.add(panel);
		frame.setResizable(false); //No se pueda cambiar de tamaño
		frame.pack(); //Empaquetar: crear el JFrame con el tamaño minimo para los componentes
		frame.setLocationRelativeTo(null); //Centra el formulario en pantalla
		frame.setVisible(true);
	}

}
