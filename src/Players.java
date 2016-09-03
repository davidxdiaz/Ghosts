

public class Players {
	private static String loggedPlayer;
	private static String[][] players = new String[10][3]; //columna 0: index columna 1: user, columna 2: password
	private static int index = 0; //cuenta el numero de usuarios

	/**
	 * Metodo que añade un nuevo jugador a la lista de jugadores
	 * <p>
	 * @param user : Nombre de usuario
	 * @param pass : Contraseña
	 * @author David
	 * @version 1.1
	 * @since 1.0
	 */
	public static void add(String user, String pass)
	{
		players[index][0] = "" + index;
		players[index][1] = user;
		players[index][2] = pass.equals("") ? "password" : pass;
		index++;
	}

	/**
	 * Un metodo creado para mostrar todos los jugadores que existen
	 * <p>
	 * <b>Nota:</b> Solo para motivos de prueba
	 * @author David
	 * @version 1.1
	 * @since 1.0
	 */
	public static void showPlayers()
	{
		System.out.println();
		for (String[] p : players)
		{
			if (p[1] != null)
			{
				System.out.println("+---------------------------+");
				System.out.println("|" + p[0] + "\t|\t" + p[1] + "\t|\t" + p[2] + "\t|");




			}
		}
		System.out.println("+---------------------------+");
	}

	/**
	 * <h1>Verificar Existencia Jugador</h1>
	 * <p>
	 * Funcion que verifica si el usuario ya se encuentra registrado
	 * <p>
	 * @param user : Nombre de usuario
	 * @return <code>true</code> si el jugador ya se encuentra registrado.
	 * De lo contrario <code>false</code>
	 * @author David
	 * @version 1.1
	 * @since 1.0
	 */
	public static boolean exists(String user)
	{
		boolean b = false;
		for (String[] p : players)
		{
			if (p[0] != null)
			{
				if (p[1].equals(user))
				{
					b = true;
					break;
				}
			}
		}
		return b;
	}

	/**
	 * Metodo que establece el usuario <code>LOGGED-IN</code>
	 * @param user : nombre de usuario
	 */
	public static void setLoggedPlayer(String user) {
		loggedPlayer = user;
	}
	/**
	 * Funcion que devuelve el usuario que esta actualmente <code>LOGGED-IN</code>
	 * @return <code>loggedPlayer</code>
	 */
	public static String getLoggedPlayer() {
		return loggedPlayer;
	}

	/**
	 * Funcion para obtener el numero de lista del usuario
	 * @param user : Nombre de usuario
	 * @return <code>index</code>
	 */
	private static int getPlayerIndex(String user)
	{
		int index = 0;
		for (int x = 0; x < players.length; x++)
		{
			if (players[x][1].equals(user))
			{
				index = x;
				break;
			}
		}
		return index;
	}

	/**
	 * <h1>Eliminar Usuario</h1>
	 * <p>
	 * Elimina el usuario especificado en <code>user</code>
	 * @param user : Nombre de usuario
	 */
	public static void delete(String user) {
		int index = getPlayerIndex(user);

		for (int i = index; i < players.length - 1; i++)
		{
			players[i] = players[i+1];
			players[i+1][0] = "" + i;
		}
		Players.index--;

	}

	/**
	 * Valida los datos de un usuario, devuelve verdadero si estos estan correctos
	 * @param user
	 * @param passw
	 * @return
	 */
	public static boolean verify(String user, String passw)
	{
		boolean b = false;
		for (String[] p : players)
		{
			if (p[0] != null)
			{
				if (p[1].equals(user) && p[2].equals(passw))
				{
					b = true;
					break;
				}
			}
		}
		return b;
	}
}
