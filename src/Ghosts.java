

public class Ghosts
{

    /**
     * Pertenece a jugador 1
     */
    public static final int PLAYER_1 = 1;

    /**
     * Pertenece a jugador 2
     */
    public static final int PLAYER_2 = 2;
    /**
     * Tipo de fantasma: Malo
     */
    public static final int TYPE_BAD = 1;
    /**
     * Tipo de fantasma Bueno
     */
    public static final int TYPE_GOOD = 2;



    /**
     * Imagen perteneciente al fantasma
     */
   // private Image image;
    /**
     * Posicion x del fantasma
     */
    private int x;
    /**
     * Posicion y del fantasma
     */
    private int y;
    /**
     * A que jugador pertenece
     */
    private int player;
    /**
     * Tipo de fantasma
     */
    private int type;

	public Ghosts()
	{
		this.x = 0;
		this.y = 0;
		this.player = 0;
		this.type = 0;
	}

    public Ghosts(int x, int y, int player, int type) {
        this.x = x;
        this.y = y;
        this.player = player;
        this.type = type;
    }

/*
    public Ghosts(Image image, int x, int y, int player, int type) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.player = player;
        this.type = type;
    }
    */


	public void moveGhost(int newx, int newy)
	{
		this.x = newx;
		this.y = newy;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public void setPlayer(int player)
	{
		this.player = player;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public int getPlayer()
	{
		return player;
	}

	public int getType()
	{
		int t = 0;

		switch (type)
		{
			case 1:
				t = 1;
				break;
			case 2:
				t = 2;
				break;
			default:
				t = 0;
				break;
		}

		return t;
	}

	@Override
    public String toString()
    {

        String player = (this.player == 1 ? "Player 1" : "Player 2");
        String tipo = (this.type == 1 ? "Malo" : "Bueno");

        return "Posicion x = " + x + "\nPosicion y = " + y + "\nJugador = " + player + "\nTipo de fantasma = " + tipo + "\n";
    }
}
