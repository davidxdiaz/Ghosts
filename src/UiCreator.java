
import java.awt.*;

import javax.swing.*;

public class UiCreator {
	
	
	/**
	 * <b>addComp</b> Es un metodo encargado de diseñar 
	 * la interfaz grafica en un contenedor que utilice un 
	 * <b>GridBagLayout</b>
	 * @param panel es el panel que contiene al componente
	 * @param comp el componente que se desea agregar
	 * @param xpos posicion x
	 * @param ypos posicion y
	 * @param compWidth la cantidad de columnas que requiere
	 * @param compHeigth la cantidad de filas que requiere
	 * @param place la posicion a la que se ancla el componente
	 * @param stretch de que manera se estira el componente
	 * @see GridBagLayout
	 */
	
	public static void addcomp(
            JPanel panel, JComponent comp, int xpos, int ypos,
            int compWidth, int compHeigth, int place, int stretch)
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = xpos;
        gbc.gridy = ypos;
        gbc.gridwidth = compWidth;
        gbc.gridheight = compHeigth;
        gbc.weightx = 100;
        gbc.weighty = 100;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = place;
        gbc.fill = stretch;

        panel.add(comp, gbc);

    }
	/**
	 * <b>getLookAndFeelClassName</b> Es una funcion que simplifica 
	 * el proceso de verificar si un LookAndFeel se encuentra instalado
	 * @param nameSnippet Es el nombre de el LookAndFeel del cual se quiere verificar su instalacion
	 * @return <code>null</code> si no se encuentra instalado
	 */
	public static String getLookAndFeelClassName(String nameSnippet)
    {
        UIManager.LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo info : plafs)
        {
            if (info.getName().contains(nameSnippet))
            {
                return info.getClassName();
            }
        }
        return null;
    }
}
