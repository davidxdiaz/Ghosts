import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by David on 8/28/16.
 */

//TODO alert dialog with Exception printed

public class ErrorWindow
{

	public static void showException(Exception ex)
	{

		try
		{
			UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		JDialog.setDefaultLookAndFeelDecorated(true);
		JPanel panel = new JPanel(new BorderLayout());
		panel.setLayout(new BorderLayout());
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String exceptionText = sw.toString();
		JLabel label = new JLabel("<html><font color='red' size=\"5\">" + ex.toString() + "</font></html>");

		JTextArea textArea = new JTextArea(exceptionText);
		JScrollPane scroll = new JScrollPane(textArea);
		textArea.setEditable(false);
		textArea.setFont(new Font("Sans-Serif", Font.PLAIN, 12));
		textArea.setWrapStyleWord(true);
		//textArea.setLineWrap(true);

		panel.add(label, BorderLayout.PAGE_START);
		panel.add(scroll, BorderLayout.CENTER);

		//JOptionPane.showMessageDialog(f, panel, "ERROR", JOptionPane.ERROR_MESSAGE);

		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		//btnPanel.setBorder(new LineBorder(Color.BLUE));
		JButton btnDetails = new JButton("^ Mostrar Detalles");
		JLabel iconLabel = new JLabel();
		try
		{
			Image image = ImageIO.read(Game.class.getResource("/res/error.png"));
			Image newimage = image.getScaledInstance(55,55,Image.SCALE_SMOOTH);
			iconLabel.setIcon(new ImageIcon(newimage));
		}
		catch (Exception e)
		{
		}

		//iconLabel.setIcon(UIManager.getIcon("OptionPane.errorIcon"));
		JPanel dpanel = new JPanel(new GridBagLayout());
		JDialog dialog = new JDialog();
		dialog.setTitle(ex.toString());
		JButton btnok = new JButton("Aceptar");
		btnok.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dialog.dispose();
			}
		});

		btnDetails.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null, "Proximamente");
			}
		});

		btnPanel.add(btnok);
		dialog.setLayout(new GridBagLayout());
		UiCreator.addcomp(dpanel, iconLabel , 0, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		UiCreator.addcomp(dpanel, label     , 1, 0, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		UiCreator.addcomp(dpanel, textArea  , 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		UiCreator.addcomp(dpanel, btnDetails, 1, 2, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		UiCreator.addcomp(dpanel, btnPanel  , 0, 3, 2, 1, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL);




		dialog.add(dpanel);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.setVisible(true);

	}


	public static void main(String[] args)
	{
		ErrorWindow.showException(new NullPointerException());
	}
}
