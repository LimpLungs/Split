package split.limplungs.com;

public class Main
{
	public static final boolean DEBUG = true;

	public static GameThread THREAD = new GameThread();
	public static World world = new World("City");

	public static void main(String[] args)
	{
		try
		{
			THREAD.run();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
