package split.limplungs.com;

import java.util.ArrayList;

public class Main
{
	public static final boolean DEBUG = false;

	public static GameThread THREAD = new GameThread();
	
	public static double TotalEntities = 0.00001;
	
	public static ArrayList<Entity> entities = new ArrayList<Entity>();

	public static void main(String[] args)
	{
		entities.add(new Zombie(5,5));
		
		try
		{
			THREAD.run(new World("City"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	public static void balanceEntities(ArrayList<Entity> e)
	{
		for (int i = 0; i < e.size(); i++)
			e.get(i).setId(e.get(i).getId() - .00000001);
		
		Main.TotalEntities -= .00000001;
	}
}
