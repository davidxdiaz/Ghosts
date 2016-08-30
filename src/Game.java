
import java.awt.*;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

public class Game
{


    private static JPanel contentPane;
    public static JButton[][] squares = new JButton[6][6];
    private static final String COLUMNAS = "ABCDEF";
    public static JPanel panel;
    public static JFrame frame;
    public static String status = "";
    public static Ghosts[][] ghosts = new Ghosts[6][6];
    //TODO crear arreglo de Ghosts y agregar a los botones

    public static void hide()
    {
        frame.dispose();
    }

    public static void show()
    {
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
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Ghost Game");
        frame.add(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void Gui()
    {

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        contentPane.add(tools, BorderLayout.PAGE_START);
        tools.addSeparator();
        tools.add(new JButton("RENDIRSE")); // TODO - Agregar Funcionalidad
        tools.addSeparator();
        tools.add(new JLabel("PREPARADO PARA JUGAR"));
        JPanel stats = new JPanel(new GridLayout(10, 2));
        contentPane.add(stats, BorderLayout.LINE_START);
        String playerTwo = JOptionPane.showInputDialog(null, "Ingrese jugador 2");
        stats.add(new JLabel(
                "JUGADOR 1: " /* + Player.loggedPlayer.toUpperCase() **/));
        stats.add(new JLabel("JUGADOR 2: " + playerTwo.toUpperCase()));
        GridLayout layout = new GridLayout(0,7);
        panel = new JPanel(layout);
        //panel.setBackground(new Color(139,69,19));
        panel.setBorder(new LineBorder(Color.black));
        generatePositions();
        crearTablero(panel);
        paintGhosts();
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
                } else
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
                int r = randInt(1,2);

                if (r == 1)
                {
                    if (fmalos < 4)
                    {
                        fmalos++;
                        ghosts[x][y] = new Ghosts(x,y,Ghosts.PLAYER_2,Ghosts.TYPE_BAD);
                    }
                    else
                    {
                        fbuenos++;
                        ghosts[x][y] = new Ghosts(x,y,Ghosts.PLAYER_2,Ghosts.TYPE_GOOD);
                    }
                }
                else
                {
                    if (fbuenos < 4)
                    {
                        fbuenos++;
                        ghosts[x][y] = new Ghosts(x,y,Ghosts.PLAYER_2,Ghosts.TYPE_GOOD);
                    }
                    else
                    {
                        fmalos++;
                        ghosts[x][y] = new Ghosts(x,y,Ghosts.PLAYER_2,Ghosts.TYPE_BAD);
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
                int r = randInt(1,2);

                if (r == 1)
                {
                    if (fmalos < 4)
                    {
                        fmalos++;
                        ghosts[x][y] = new Ghosts(x,y,Ghosts.PLAYER_1,Ghosts.TYPE_BAD);
                    }
                    else
                    {
                        fbuenos++;
                        ghosts[x][y] = new Ghosts(x,y,Ghosts.PLAYER_1,Ghosts.TYPE_GOOD);
                    }
                }
                else
                {
                    if (fbuenos < 4)
                    {
                        fbuenos++;
                        ghosts[x][y] = new Ghosts(x,y,Ghosts.PLAYER_1,Ghosts.TYPE_GOOD);
                    }
                    else
                    {
                        fmalos++;
                        ghosts[x][y] = new Ghosts(x,y,Ghosts.PLAYER_1,Ghosts.TYPE_BAD);
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
                        Image image = ImageIO.read(Game.class.getResource("/res/good.png"));
                        Image newimage = image.getScaledInstance(55,55,Image.SCALE_SMOOTH);
                        squares[x][y].setIcon(new ImageIcon(newimage));
                        //
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
                else if (ghosts[x][y] != null && ghosts[x][y].getType() == Ghosts.TYPE_BAD)
                {
                    try
                    {
                        Image image = ImageIO.read(Game.class.getResource("/res/bad.png"));
                        Image newimage = image.getScaledInstance(55,55,Image.SCALE_SMOOTH);
                        squares[x][y].setIcon(new ImageIcon(newimage));
                    }
                    catch (Exception e)
                    {
                        ErrorWindow.showException(e);
                    }
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

}
