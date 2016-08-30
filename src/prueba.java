import java.util.Scanner;

/**
 * Created by David on 8/29/16.
 */
public class prueba
{
	public static void main(String[] args)
	{
		Scanner read  = new Scanner(System.in);
		String[][] arr = new String[3][4];
		read.useDelimiter("\n");
		int lfila = 0;

		for (int x = 0; x < arr.length; x++)
		{
			for (int y = 0; y < arr[x].length; y++)
			{
				arr[x][y] = read.next();
			}
		}

		for (int x = 0; x < arr.length; x++)
		{
			for (int y = 0; y < arr[x].length; y++)
			{
				lfila += arr[x][y].length();
				System.out.print(arr[x][y] + "\t");
			}
			System.out.print("\tLongitud: " + lfila + "\n");
			lfila = 0;
		}
	}

}
