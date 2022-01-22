package split.limplungs.com;

public class GameThread
{
	public enum State
	{
		ON, OFF
	}

	protected volatile State STATE;

	private Thread THREAD;

	private long delta;

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
		startFrameDelay();

		if (world.getFrame().isVisible() == false)
		{
			exit(world);

			this.STATE = GameThread.State.OFF;
		}

		endFrameDelay();

		if (delta < 20)
			try
			{
				this.THREAD.sleep(20 - delta);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
	}

	public void render(World world)
	{

	}

	private void startFrameDelay()
	{
		delta = System.currentTimeMillis();
	}

	private void endFrameDelay()
	{
		delta = System.currentTimeMillis() - delta;
	}

	public void exit(World world)
	{
		world.getFrame().setVisible(false);
		world.getFrame().setEnabled(false);
		world.setVisible(false);
		world.setEnabled(false);
	}
}
