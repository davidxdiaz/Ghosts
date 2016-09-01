
import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class Login
{
	public static JFrame frame;
	public static JPanel panel;
	public static JTextField txtuser;
	public static JPasswordField txtpassw;
	public static JButton btnOk;
	public static JButton btnCancel;


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
		panel = new JPanel();
		frame = new JFrame("Iniciar Sesion");
		txtuser = new JTextField(20);
		txtpassw = new JPasswordField(20);
		btnOk = new JButton("Aceptar");
		btnCancel = new JButton("Cancelar");

		panel.setLayout(new MigLayout());
		panel.add(new JLabel("Usuario"));
		panel.add(txtuser, "wrap, pushx, growx");
		panel.add(new JLabel("Contraseña"));
		panel.add(txtpassw, "wrap, pushx, growx");
		panel.add(btnOk, "skip, split 2");
		panel.add(btnCancel);

		ListenForButton l = new ListenForButton();
		btnOk.addActionListener(l);
		btnCancel.addActionListener(l);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.getRootPane().setDefaultButton(btnOk); //Establece que cuando ENTER, el boton se presione

	}

}
