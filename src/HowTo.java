
import java.io.*;

import javax.swing.*;
import javax.swing.text.StyledDocument;

import net.miginfocom.swing.MigLayout;

public class HowTo
{
	public static JFrame frame;
	public static JPanel panel;
	public static JTextPane textPane;
	public static JScrollPane scroll;
	public static StyledDocument doc;
	public static String contents = "src/res/contents.html";

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

		frame = new JFrame("Como Jugar");

		panel = new JPanel(new MigLayout());
		textPane = new JTextPane();
		scroll = new JScrollPane(textPane);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textPane.setEditable(false);
		textPane.setContentType("text/html");
		try
		{
			String[] c = openFile(contents);
			String e = "";

			for (String cad : c)
			{
				e = e + cad;
			}
			System.out.println(e);
			textPane.setText(e);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		panel.add(scroll, "push, grow");

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(panel);
		frame.setSize(1024,720);
		//frame.setResizable(false);
		//frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		//frame.getRootPane().setDefaultButton(btnOk); // TODO: agregar esto a todos los formularios

	}

	public static int readLines(String path) throws Exception
	{
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		int lines = 0;
		String line;

		while ((line = br.readLine()) != null )
		{
			lines++;

		}
		br.close();

		return lines;
	}

	public static String[] openFile(String path) throws Exception
	{
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);

		String[] data = new String[readLines(path)];

		for (int x = 0; x < readLines(path); x++)
		{
			data[x] = br.readLine();
		}
		br.close();
		return data;

	}
}
