
import javax.imageio.ImageIO;
import javax.swing.*;

import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.io.IOException;

public class Config
{
	public static JFrame frame;
	public static JPanel panel;
	public static JButton btnAceptar;
	public static JComboBox<String> cboDif;
	public static JComboBox<String> cboMode;
	public static int dificultad;
	public static int modoJuego;

	public static void hide()
	{
		frame.dispose();
	}

	public static void show()
	{
		//Cambiar Look and feel
		try
		{
			UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
		} catch (Exception e)
		{
			// TODO: handle exception
		}

		JFrame.setDefaultLookAndFeelDecorated(true);

		frame = new JFrame("Configuracion");
		panel = new JPanel(new MigLayout(
				"", // Layout Constraints
				"50[]20[]50", // Espaciado entre columnas 20, entre bordes 50
				"[]20[]")); //Espaciado entre filas 20px
		btnAceptar = new JButton("Aceptar");
		cboDif = new JComboBox<>();
		cboMode = new JComboBox<>();

		cboDif.addItem("NORMAL");
		cboDif.addItem("EXPERT");
		cboDif.addItem("GENIUS");

		cboMode.addItem("ALEATORIO");
		cboMode.addItem("NORMAL");

		cboDif.setToolTipText("Cambia la dificultad del juego");
		cboMode.setToolTipText("Cambia la manera en la que se distribuyen las fichas");

		panel.add(new JLabel("Dificultad"), "center");
		panel.add(cboDif, "wrap, growx");
		panel.add(new JLabel("Modo"), "right");
		panel.add(cboMode, "wrap");
		panel.add(btnAceptar, "span 2, center");

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(panel);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		try
		{
			Image image = ImageIO.read(Game.class.getResource("/res/ghost.png"));
			Image newimage = image.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
			frame.setIconImage(newimage);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		frame.setVisible(true);

	}

	public static void setConfig(int dif, int modo)
	{
		dificultad = dif;
		modoJuego = modo;
	}

}
