package split.limplungs.com;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class World extends Canvas
{
	private static final long serialVersionUID = 6405524727711665587L;

	@SuppressWarnings("unused")
	private String name;

	private GFrame frame;

	public World(String name)
	{
		this.name = name;

		this.isEnabled();
		this.setVisible(true);
		this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());

		frame = new GFrame();
		frame.add(this);
		frame.pack();
		frame.setSize(this.getPreferredSize());
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public GFrame getFrame()
	{
		return frame;
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);

		for (int i = 0; i < this.getSize().getWidth(); i += 16)
			for (int j = 0; j < this.getSize().getWidth(); j += 16)
			{
				g.setColor(Color.BLACK);
				g.drawRect(j, i, 16, 16);
				g.setColor(Color.DARK_GRAY);
				g.fillRect(j + 1, i + 1, 16 - 2, 16 - 2);
			}

		int[] pix = new int[3 * 16];
		
		for (int i = 0; i < pix.length; i++)
		{
			if (i % 3 == 0)
				pix[i] = i % 16;
			else if (i % 3 == 1)
				pix[i] = i / 16;
			else if (i % 3 == 2)
				pix[i] = Color.BLUE.getRGB();
		}
		
		Entity nt = new Entity(pix);
		g.drawImage(nt.getImage(), 1, 1, 16, 16, 1, 1, 16, 16, nt.getObserver());
	}

}
