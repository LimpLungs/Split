package split.limplungs.com;

public class GameThread
{
	public enum State
	{
		ON, OFF
	}

	protected volatile State STATE;

	private Thread THREAD;

	public void run(World world)
	{
		STATE = State.ON;
		THREAD = new Thread(this.start(world));
		THREAD.start();
	}

	public void stop()
	{
		STATE = State.OFF;
	}

	public Runnable start(World world)
	{
		while (this.STATE == GameThread.State.ON)
		{
			update(world);
			render(world);
			if (Main.DEBUG)
				System.out.println("Running...");
		}

		if (Main.DEBUG)
			System.out.println("Game stopped.");

		return THREAD;
	}

	public void update(World world)
	{
		if (world.getFrame().isVisible() == false)
		{
			exit(world);
			
			this.STATE = GameThread.State.OFF;
		}
	}

	public void render(World world)
	{

	}
	
	public void exit(World world)
	{
		world.getFrame().setVisible(false);
		world.getFrame().setEnabled(false);
		world.setVisible(false);
		world.setEnabled(false);
	}
}
