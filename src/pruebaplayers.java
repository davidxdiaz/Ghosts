/**
 * Created by David on 9/3/16.
 */
public class pruebaplayers
{
	public static void main(String[] args)
	{
		Players.add("david", "fefe");
		Players.add("david2", "fefe");
		Players.add("david3", "fefe");
		Players.add("david4", "fefe");
		Players.showPlayers();

		Players.delete("david3");
		Players.showPlayers();
		Players.add("david5", "fefe");
		Players.showPlayers();
		Players.delete("david5");
		Players.showPlayers();

	}
}
