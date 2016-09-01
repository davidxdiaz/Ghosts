
import java.awt.Component;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class SignUp
{
	public static JFrame frame;
	public static JPanel panel;
	public static JTextField txtuser;
	public static JPasswordField txtpassw;
	public static JButton btnOk;
	public static JButton btnCancel;
	public static JLabel caps;


	public static void hide()
	{
		frame.dispose();
	}

	public static void show()
	{
		try
		{
			UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		JFrame.setDefaultLookAndFeelDecorated(true);
		panel = new JPanel(new MigLayout());
		frame = new JFrame("Crear Jugador");
		txtuser = new JTextField(20);
		txtpassw = new JPasswordField(20);
		btnOk = new JButton("Aceptar");
		btnCancel = new JButton("Cancelar");
		caps = new JLabel("<html><font color='red'>MAYUSC ACTIVADO</font></html>");
		caps.setVisible(false);


		panel.add(new JLabel("Usuario"));
		panel.add(txtuser, "wrap, pushx, growx");
		panel.add(new JLabel("Contraseña"));
		panel.add(txtpassw, "wrap, pushx, growx");
		panel.add(caps, "span, center");
		panel.add(btnOk, "skip, split 2");
		panel.add(btnCancel);

		listenKey k = new listenKey();

		//Limpia todos los textfiels y passwordfield
		for (Component c: panel.getComponents())
		{
			if (c instanceof JTextField)
			{
				((JTextField)c).addKeyListener(k);
			}
			if (c instanceof JPasswordField)
			{
				((JPasswordField)c).addKeyListener(k);
			}
		}

		ListenForButton l = new ListenForButton();
		ListenForButton.addListeners(panel, l);

		frame.getRootPane().setDefaultButton(btnOk);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}



}
