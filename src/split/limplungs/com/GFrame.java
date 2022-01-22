package split.limplungs.com;

import java.awt.*;
import javax.swing.*;

// This class is from this stack exchange article on the setExtendedState bug in Swing.
// https://stackoverflow.com/questions/6422931/why-jframe-hides-taskbar-when-maximized
public class GFrame extends JFrame
{
	private static final long serialVersionUID = -3741084689312696138L;

	private Rectangle maxBounds;

	public GFrame()
	{
		super();
		maxBounds = null;
	}

	// Full implementation has other JFrame constructors

	public Rectangle getMaximizedBounds()
	{
		return (maxBounds);
	}

	public synchronized void setMaximizedBounds(Rectangle maxBounds)
	{
		this.maxBounds = maxBounds;
		super.setMaximizedBounds(maxBounds);
	}

	@Override
	public synchronized void setExtendedState(final int state)
	{
		if ((state & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH)
		{
			final GraphicsConfiguration cfg = getGraphicsConfiguration();
			final Insets screenInsets = getToolkit().getScreenInsets(cfg);
			final Rectangle screenBounds = cfg.getBounds();
			final int x = screenInsets.left + screenBounds.x * 0;
			final int y = screenInsets.top + screenBounds.y * 0;
			final int w = screenBounds.width - screenInsets.right - screenInsets.left;
			final int h = screenBounds.height - screenInsets.bottom - screenInsets.top;
			final Rectangle maximizedBounds = new Rectangle(x, y, w, h);

			if (Main.DEBUG)
				System.out.println("cfg (" + cfg + ") screen.{bounds: " + screenBounds + ", insets: " + screenInsets + ", maxBounds: " + maximizedBounds);

			super.setMaximizedBounds(maximizedBounds);
		}
		super.setExtendedState(state);
	}
}
