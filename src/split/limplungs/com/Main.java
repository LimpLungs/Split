package split.limplungs.com;

public class Main
{
	public static final boolean DEBUG = false;

	public static GameThread THREAD = new GameThread();

	public static void main(String[] args)
	{
		World city = new World("City");
		city.addEntity(new Player(17,17,city.TotalEntities));
		city.addEntity(new Zombie(5,5,city.TotalEntities));
		
		try
		{
			THREAD.run(city);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
}
