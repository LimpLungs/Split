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
		
		render(world);

		endFrameDelay();
	}

	public void render(World world)
	{
		// repaint spot of change
		//world.repaint(0, 0, 0, 0);
	}

	// Set the start time for frame calculations to ensure a consistent 20ms frame.
	private void startFrameDelay()
	{
		delta = System.currentTimeMillis();
	}

	// Calculate the difference in the start of the frame and the end of the frame
	// Adjust delta to be the target frame time of 20ms - time elapsed.
	// Sleep on delta remaining.
	private void endFrameDelay()
	{
		delta = 20 - (System.currentTimeMillis() - delta);

		try
		{
			if (delta > 0)
			{
				Thread.sleep(delta);
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public void exit(World world)
	{
		world.getFrame().setVisible(false);
		world.getFrame().setEnabled(false);
		world.setVisible(false);
		world.setEnabled(false);
	}
}
