
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


/**
 * Clase publica dedicada a escuchar las acciones de los botones
 * <p>
 * @author David
 * @version 1.1
 * @since 0.9
 */
public class ListenForButton implements ActionListener{

	private boolean firstclic = true;
	private int newx;
	private int newy;
	private int x;
	private int y;

	/**
	 * Agrega un ActionListener a todos los botones en un JPanel panel
	 * @param panel : Objeto JPanel contenedor.
	 * @param l : La instancia de ListenForButton
	 */
	public static void addListeners(JPanel panel, ActionListener l)
	{
		// TODO Auto-generated method stub
		for (Component c: panel.getComponents())
		{
			if (c instanceof JButton)
			{
				((JButton)c).addActionListener(l);
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		/**
		 * MENU INICIO: BOTON SALIR
		 */
		if (e.getSource() == Inicio.btnExit)
		{
			System.exit(0);

		}

		/**
		 * MENU INICIO: BOTON INICIAR SESION
		 */
		if (e.getSource() == Inicio.btnLogin)
		{
			Inicio.hide();
			Login.show();
		}

		/**
		 * MENU INICIO: BOTON NUEVO JUGADOR
		 */
		if (e.getSource() == Inicio.btnNew)
		{
			Inicio.hide();
			SignUp.show();

		}

		/**
		 * CREAR JUGADOR: BOTON ACEPTAR
		 */
		if (e.getSource() == SignUp.btnOk)
		{
			String passw = "";

			for (char c: SignUp.txtpassw.getPassword())
			{
				passw += c;
			}

			if (Players.exists(SignUp.txtuser.getText()) == false)
			{
				Players.add(SignUp.txtuser.getText(), passw);
				SignUp.hide();
				Inicio.show();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "EL USUARIO YA EXISTE");
			}

			Players.showPlayers();
		}

		/**
		 * CREAR JUGADOR: BOTON CANCEL
		 */
		if (e.getSource() == SignUp.btnCancel)
		{
			SignUp.hide();
			Inicio.show();
		}

		/**
		 * INICIAR SESION: BOTON ACEPTAR
		 */
		if (e.getSource() == Login.btnOk)
		{
			String passw = "";

			for (char c: Login.txtpassw.getPassword())
			{
				passw += c;
			}

			if (Players.verify(Login.txtuser.getText(), passw))
			{
				Players.setLoggedPlayer(Login.txtuser.getText());
				MainMenu.show();
				Login.hide();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Usuario y contraseña incorrectos.\n"
						+ "Debe crear el usuario o intentar con otro usuario y contraseña",
						"Error", JOptionPane.ERROR_MESSAGE);
			}


		}

		/**
		 * INICIAR SESION: BOTON CANCELAR
		 */
		if (e.getSource() == Login.btnCancel)
		{
			Login.hide();
			Inicio.show();
		}
		/**
		 * MENU PRINCIPAL: BOTON JUGAR
		 */
		if (e.getSource() == MainMenu.btnPlay)
		{
			Game.show();
		}
		/**
		 * MENU PRINCIPAL: BOTON CONFIG
		 */
		if (e.getSource() == MainMenu.btnConfig)
		{
			Config.show();
		}

		/**
		 * MENU PRINCIPAL: BOTON REPORTES
		 */
		if (e.getSource() == MainMenu.btnReport)
		{
			Reportes.show();
		}

		/**
		 * MENU PRINCIPAL: BOTON COMOJUGAR
		 */
		if (e.getSource() == MainMenu.btnHowTo)
		{
			HowTo.show();
		}

		/**
		 * MENU PRINCIPAL: BOTON CerrarSesion
		 */
		if (e.getSource() == MainMenu.btnLogout)
		{
			MainMenu.hide();
			Inicio.show();
			Players.setLoggedPlayer(null);
		}

		/**
		 * MENU PRINCIPAL: BOTON PERFIL
		 */

		if (e.getSource() == MainMenu.btnReport)
		{
			//TODO: crear reportes
		}

		/**
		 * CONFIGURACION: BOTON ACEPTAR
		 */
		if (e.getSource() == Config.btnAceptar)
		{
			Config.setConfig(Config.cboDif.getSelectedIndex(), Config.cboMode.getSelectedIndex());
		}


		if (Game.frame.isActive())
		{


			JButton b = (JButton) e.getSource();
			String key = b.getName();


			try
			{

				if (firstclic == true)
				{
					System.out.println("firstclic = " + firstclic);
					x = Integer.parseInt(String.valueOf(key.charAt(0)));
					y = Integer.parseInt(String.valueOf(key.charAt(2)));
					firstclic = false;

				}
				else
				{
					System.out.println("firstclic = " + firstclic);
					newx = Integer.parseInt(String.valueOf(key.charAt(0)));
					newy = Integer.parseInt(String.valueOf(key.charAt(2)));

					Game.ghosts[x][y].moveGhost(newx, newy);
					firstclic = true;
				}
			}
			catch (NullPointerException ex)
			{
				ErrorWindow.showException(ex);
			}



		}

	}
}

class listenKey implements KeyListener
{

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub
		boolean isOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
		if (isOn == true)
		{
			if (SignUp.frame.isActive())
			{
				SignUp.caps.setVisible(true);
			}
			if (Login.frame.isActive())
			{
				//Login.caps.setVisible(true);
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub
		boolean isOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
		if (isOn == true)
		{
			if (SignUp.frame.isActive())
			{
				SignUp.caps.setVisible(false);
			}
			if (Login.frame.isActive())
			{
				//Login.caps.setVisible(false);
			}
		}
	}

}

class windowsEvent implements WindowListener
{

	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		// TODO Auto-generated method stub
		if (e.getSource() == MainMenu.frame)
		{
			Inicio.show();
			Players.setLoggedPlayer(null);
		}

		if (e.getSource() == HowTo.frame)
		{
			MainMenu.show();
		}

		if (e.getSource() == Reportes.frame)
		{
			MainMenu.show();
		}

		if (e.getSource() == Config.frame)
		{
			MainMenu.show();
		}



	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO Auto-generated method stub

	}



}
