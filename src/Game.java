
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

public class Game
{
	public static int newx;
	public static int newy;
	public static int x;
	public static int y;
	public static boolean firstclic = true;
	private static JPanel contentPane;
	public static JButton[][] squares = new JButton[6][6];
	private static final String COLUMNAS = "ABCDEF";
	public static JPanel panel;
	public static JFrame frame;
	public static Ghosts[][] ghosts = new Ghosts[6][6];
	public static ImageIcon icon_bad;
	public static ImageIcon icon_good;
	public static String playerTwo = "";
	public static boolean turn = true; //Turno de player 1
	public static JButton ff; //ff significa Forfeit, o give up
	public static JLabel lblstatus;


	//TODO crear arreglo de Ghosts y agregar a los botones

	public static void hide()
	{
		frame.dispose();
	}

	public static void show()
	{
		try
		{
			Image image = ImageIO.read(Game.class.getResource("/res/bad.png"));
			Image image_bad = image.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
			icon_bad = new ImageIcon(image_bad);

			Image image2 = ImageIO.read(Game.class.getResource("/res/good.png"));
			Image image_good = image2.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
			icon_good = new ImageIcon(image_good);


		} catch (IOException e)
		{
			ErrorWindow.showException(e);
		}

		/**
		 * CAMBIAR LOOKANDFEEL
		 */

		try
		{
			//String className = UiCreator.getLookAndFeelClassName("Metal");
			UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");

		} catch (Exception e)
		{
			// TODO: handle exception
		}

		Gui();
		if (playerTwo == null)
		{
			return;
		}
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame = new JFrame("Ghost Game");
		frame.add(contentPane);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);


	}

	public static void Gui()
	{
		lblstatus = new JLabel("");
		Game.lblstatus.setText("Turno Jugador 1");
		ff = new JButton("Rendirse");
		contentPane = new JPanel(); //Panel principal
		contentPane.setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		JToolBar tools = new JToolBar();
		tools.setFloatable(false);
		contentPane.add(tools, BorderLayout.PAGE_START);
		tools.addSeparator();
		tools.add(ff);
		tools.addSeparator();
		tools.add(lblstatus);
		JPanel stats = new JPanel(new GridLayout(10, 2));
		contentPane.add(stats, BorderLayout.LINE_START);
		ListenForButton l = new ListenForButton();
		ff.addActionListener(l);
		do
		{
			playerTwo = JOptionPane.showInputDialog(null, "Ingrese jugador 2");

			if (playerTwo == null)
			{
				return;
			}

			if ("".equals(playerTwo))
			{
				JOptionPane.showMessageDialog(null, "El jugador no puede estar vacio");
				continue;
			}

			if (Players.exists(playerTwo) && !playerTwo.equalsIgnoreCase(Players.getLoggedPlayer()))
			{
				break;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "El jugador no existe o ingreselo correctamente");
				continue;
			}


		} while (true);

		//vengo del break

		stats.add(new JLabel(
				"JUGADOR 1: " + Players.getLoggedPlayer().toUpperCase()));
		stats.add(new JLabel("JUGADOR 2: " + playerTwo.toUpperCase()));
		GridLayout layout = new GridLayout(0, 7);
		panel = new JPanel(layout);
		//panel.setBackground(new Color(139,69,19));
		panel.setBorder(new LineBorder(Color.black));

		generatePositions(); //Genera las posiciones de los fantasmas
		crearTablero(panel); //Crea el arreglo de botones
		paintGhosts(); //Pintar iconos fantas
		contentPane.add(panel);
		/*
        for (int x = 0; x < ghosts.length; x++)
        {
            for (int y = 0; y < ghosts[x].length; y++)
            {
                System.out.print(ghosts[x][y] + " ");

            }
            System.out.println();
        }
        */

	}

	public static void crearTablero(JPanel panel)
	{
		for (int x = 0; x < squares.length; x++)
		{
			for (int y = 0; y < squares[x].length; y++)
			{
				JButton btn = new JButton();
				ListenForButton l = new ListenForButton();
				btn.addActionListener(l);

				btn.setMargin(new Insets(2, 2, 2, 2));
				btn.setPreferredSize(new Dimension(64, 64));

				if ((x % 2 == 1 && y % 2 == 1) || (x % 2 == 0 && y % 2 == 0))
				{
					btn.setBackground(Color.white);
				}
				else
				{
					btn.setBackground(Color.gray);
				}

				btn.setName(x + "," + y);
				squares[x][y] = btn;
			}
		}

		panel.add(new JLabel(""));

		for (int x = 0; x < 6; x++)
		{
			panel.add(new JLabel(COLUMNAS.substring(x, x + 1), SwingConstants.CENTER));

		}

		for (int x = 0; x < 6; x++)
		{
			for (int y = 0; y < 6; y++)
			{
				switch (y)
				{
					case 0:
						panel.add(new JLabel("" + (x + 1), SwingConstants.CENTER));
					default:
						panel.add(squares[x][y]);
				}
			}

		}
	}

	private static void generatePositions()
	{
		int fbuenos = 0;
		int fmalos = 0;

		for (int x = 0; x <= 1; x++)
		{
			for (int y = 1; y <= 4; y++)
			{
				int r = randInt(1, 2);

				if (r == 1)
				{
					if (fmalos < 4)
					{
						fmalos++;
						ghosts[x][y] = new Ghosts(x, y, Ghosts.PLAYER_2, Ghosts.TYPE_BAD);
					}
					else
					{
						fbuenos++;
						ghosts[x][y] = new Ghosts(x, y, Ghosts.PLAYER_2, Ghosts.TYPE_GOOD);
					}
				}
				else
				{
					if (fbuenos < 4)
					{
						fbuenos++;
						ghosts[x][y] = new Ghosts(x, y, Ghosts.PLAYER_2, Ghosts.TYPE_GOOD);
					}
					else
					{
						fmalos++;
						ghosts[x][y] = new Ghosts(x, y, Ghosts.PLAYER_2, Ghosts.TYPE_BAD);
					}
				}

			}
		}

		fbuenos = 0;
		fmalos = 0;

		for (int x = 4; x <= 5; x++)
		{
			for (int y = 1; y <= 4; y++)
			{
				int r = randInt(1, 2);

				if (r == 1)
				{
					if (fmalos < 4)
					{
						fmalos++;
						ghosts[x][y] = new Ghosts(x, y, Ghosts.PLAYER_1, Ghosts.TYPE_BAD);
					}
					else
					{
						fbuenos++;
						ghosts[x][y] = new Ghosts(x, y, Ghosts.PLAYER_1, Ghosts.TYPE_GOOD);
					}
				}
				else
				{
					if (fbuenos < 4)
					{
						fbuenos++;
						ghosts[x][y] = new Ghosts(x, y, Ghosts.PLAYER_1, Ghosts.TYPE_GOOD);
					}
					else
					{
						fmalos++;
						ghosts[x][y] = new Ghosts(x, y, Ghosts.PLAYER_1, Ghosts.TYPE_BAD);
					}
				}
			}
		}
	}

	public static void paintGhosts()
	{
		for (int x = 0; x < ghosts.length; x++)
		{
			for (int y = 0; y < ghosts[x].length; y++)
			{
				if (ghosts[x][y] != null && ghosts[x][y].getType() == Ghosts.TYPE_GOOD)
				{
					try
					{
						squares[x][y].setIcon(icon_good);

					} catch (Exception e)
					{
						e.printStackTrace();
					}

				}
				else if (ghosts[x][y] != null && ghosts[x][y].getType() == Ghosts.TYPE_BAD)
				{
					try
					{
						squares[x][y].setIcon(icon_bad);
					} catch (Exception e)
					{
						ErrorWindow.showException(e);
					}
				}
				else
				{
					squares[x][y].setIcon(null);
				}
			}
		}
	}

	public static int randInt(int min, int max)
	{

		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	public static void debugDialog()
	{
		JDialog dialog = new JDialog();
		dialog.setTitle("DEBUG");

		JTextArea textArea = new JTextArea();

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		for (int x = 0; x < Game.ghosts.length; x++)
		{
			for (int y = 0; y < Game.ghosts[x].length; y++)
			{
				if (Game.ghosts[x][y] != null)
				{
					pw.write(Game.ghosts[x][y].getType() + "\t");
				}
				else
				{
					pw.write(0 + "\t");
				}
			}
			pw.write("\n");
		}

		textArea.setText(sw.toString());
		dialog.add(textArea);
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}

}
