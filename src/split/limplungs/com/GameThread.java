package split.limplungs.com;

public abstract class GameThread
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
		THREAD = new Thread(this.update());
		THREAD.start();
	}

	public void stop()
	{
		STATE = State.OFF;
	}

	public abstract Runnable update();
}
