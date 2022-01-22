package split.limplungs.com;

import java.util.ArrayList;

public class Main
{
	public static final boolean DEBUG = false;

	public static GameThread THREAD = new GameThread();

	public static void main(String[] args)
	{
		
		try
		{
			THREAD.run(new World("City"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
}
