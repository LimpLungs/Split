package split.limplungs.com;

public class GameThread
{
	public enum State
	{
		ON, OFF
	}

	protected volatile State STATE;

	private Thread THREAD;

	public void run()
	{
		STATE = State.ON;
		THREAD = new Thread(this.start());
		THREAD.start();
	}

	public void stop()
	{
		STATE = State.OFF;
	}

	public Runnable start()
	{
		int i = 0;
		while (this.STATE == GameThread.State.ON)
		{
			update();
			render();
			if (Main.DEBUG)
			{
				System.out.println("Running...");

				i++;

				if (i > 1000000)
				{
					this.STATE = GameThread.State.OFF;
					System.out.println("STOPPED");
				}
			}
		}

		return THREAD;
	}

	public void update()
	{

	}

	public void render()
	{

	}
}
