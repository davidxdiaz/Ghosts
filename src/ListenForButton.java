
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;


/**
 * Clase publica dedicada a escuchar las acciones de los botones
 * <p>
 *
 * @author David
 * @version 1.1
 * @since 0.9
 */
public class ListenForButton implements ActionListener
{


	/**
	 * Agrega un ActionListener a todos los botones en un JPanel panel
	 *
	 * @param panel : Objeto JPanel contenedor.
	 * @param l     : La instancia de ListenForButton
	 */
	public static void addListeners(JPanel panel, ActionListener l)
	{
		// TODO Auto-generated method stub
		for (Component c : panel.getComponents())
		{
			if (c instanceof JButton)
			{
				((JButton) c).addActionListener(l);
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		/*
		 * CREAR JUGADOR: BOTON ACEPTAR
		 */
		if (e.getSource() == SignUp.btnOk)
		{
			String passw = "";

			for (char c : SignUp.txtpassw.getPassword())
			{
				passw += c;
			}

			if (!Players.exists(SignUp.txtuser.getText()))
			{
				Players.add(SignUp.txtuser.getText(), passw);
				SignUp.hide();
				Players.setLoggedPlayer(SignUp.txtuser.getText());
				MainMenu.show();

			}
			else
			{
				JOptionPane.showMessageDialog(null, "EL USUARIO YA EXISTE");
			}

			Players.showPlayers();
		}

		/*
		 * MENU INICIO: BOTON SALIR
		 */
		if (e.getSource() == Inicio.btnExit)
		{
			System.exit(0);

		}

		/*
		 * MENU INICIO: BOTON INICIAR SESION
		 */
		if (e.getSource() == Inicio.btnLogin)
		{
			Inicio.hide();
			Login.show();
		}

		/*
		 * MENU INICIO: BOTON NUEVO JUGADOR
		 */
		if (e.getSource() == Inicio.btnNew)
		{
			Inicio.hide();
			SignUp.show();

		}

		/*
		 * CREAR JUGADOR: BOTON CANCEL
		 */
		if (e.getSource() == SignUp.btnCancel)
		{
			SignUp.hide();
			Inicio.show();
		}

		/*
		 * INICIAR SESION: BOTON ACEPTAR
		 */
		if (e.getSource() == Login.btnOk)
		{
			String passw = "";

			for (char c : Login.txtpassw.getPassword())
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

		/*
		 * INICIAR SESION: BOTON CANCELAR
		 */
		if (e.getSource() == Login.btnCancel)
		{
			Login.hide();
			Inicio.show();
		}
		/*
		 * MENU PRINCIPAL: BOTON JUGAR
		 */
		if (e.getSource() == MainMenu.btnPlay)
		{
			Game.show();
		}
		/*
		 * MENU PRINCIPAL: BOTON CONFIG
		 */
		if (e.getSource() == MainMenu.btnConfig)
		{
			Config.show();
		}

		/*
		 * MENU PRINCIPAL: BOTON REPORTES
		 */
		if (e.getSource() == MainMenu.btnReport)
		{
			Reportes.show();
		}

		/*
		 * MENU PRINCIPAL: BOTON COMOJUGAR
		 */
		if (e.getSource() == MainMenu.btnHowTo)
		{
			HowTo.show();
		}

		/*
		 * MENU PRINCIPAL: BOTON CerrarSesion
		 */
		if (e.getSource() == MainMenu.btnLogout)
		{
			MainMenu.hide();
			Inicio.show();
			Players.setLoggedPlayer(null);
		}

		/*
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

		/**
		 * BOTON RENDIRSE: GAME
		 */
		if (e.getSource() == Game.ff)
		{
			System.out.println("SE RINDE");
			int rendirse = JOptionPane.showConfirmDialog(null, "Seguro quiere rendirse?", "Rendirse?", JOptionPane.YES_NO_OPTION);
			if (rendirse == JOptionPane.YES_OPTION)
			{
				Players.setWin(Game.playerTwo);
				Game.hide();
				Players.showPoints();
			}
		}

		try
		{
			if (Game.frame.isActive() && e.getSource() != Game.ff)
			{
				JButton b = (JButton) e.getSource();
				String key = b.getName();
				try
				{
					if (Game.firstclic) //PRIMER CLIC, es decir el clic que decide que pieza se mueve
					{

						System.out.println("PRIMER CLIC");
						Game.x = Integer.parseInt(String.valueOf(key.charAt(0)));
						Game.y = Integer.parseInt(String.valueOf(key.charAt(2)));
						System.out.println(Game.ghosts[Game.x][Game.y]);

							if (Game.ghosts[Game.x][Game.y] != null)
							{
								Game.firstclic = false;
							}
							else
							{
								Game.firstclic = true;
							}
					}
					else //SEGUNDO CLIC, es decir, el clic que decide a donde se mueve la pieza
					{
						System.out.println("SEGUNDO CLIC");
						Game.newx = Integer.parseInt(String.valueOf(key.charAt(0)));
						Game.newy = Integer.parseInt(String.valueOf(key.charAt(2)));

						if (Game.turn) //Turno del player 1
						{
							if (Game.ghosts[Game.x][Game.y].getPlayer() == Ghosts.PLAYER_1) //Ficha a mover es de player 1
							{
								//Mueve el fantasma
								Ghosts g = new Ghosts();
								g = Game.ghosts[Game.x][Game.y];
								Game.ghosts[Game.x][Game.y] = null;
								Game.ghosts[Game.newx][Game.newy] = g;
								Game.ghosts[Game.newx][Game.newy].setX(Game.newx);
								Game.ghosts[Game.newx][Game.newy].setY(Game.newy);


								Game.firstclic = true;
								Game.paintGhosts();
								Game.debugDialog();
								Game.turn = false;
								Game.lblstatus.setText("Turno Jugador 2");
							}
							else
							{
								JOptionPane.showMessageDialog(null, "No puedes mover esa ficha");
								Game.firstclic = true;
								return;
							}
						}
						else //Turno del jugador 2
						{

							if (Game.ghosts[Game.x][Game.y].getPlayer() == Ghosts.PLAYER_2)
							{
								//Mueve el fantasma
								Ghosts g = new Ghosts();
								g = Game.ghosts[Game.x][Game.y];
								Game.ghosts[Game.x][Game.y] = null;
								Game.ghosts[Game.newx][Game.newy] = g;
								Game.ghosts[Game.newx][Game.newy].setX(Game.newx);
								Game.ghosts[Game.newx][Game.newy].setY(Game.newy);


								Game.firstclic = true;
								Game.paintGhosts();
								Game.debugDialog();
								Game.turn = true;
								Game.lblstatus.setText("Turno Jugador 1");
							}
							else
							{
								JOptionPane.showMessageDialog(null, "No puedes mover esa ficha");
								Game.firstclic = true;
								return;
							}
						}
					}
				} catch (NullPointerException ex)
				{
					ErrorWindow.showException(ex);
				}
			}
		} catch (Exception ex)
		{
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
