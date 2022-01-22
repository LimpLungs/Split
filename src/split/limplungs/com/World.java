package split.limplungs.com;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
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
		
		update(g);
	}
	
	public void update(Graphics g)
	{
		for (int i = 0; i < Main.entities.size(); i++)
		{
			g.drawImage(Main.entities.get(i).getImage(), Main.entities.get(i).getX() * 16, Main.entities.get(i).getY() * 16, Main.entities.get(i).getX() * 16 + 15, Main.entities.get(i).getY() * 16 + 15, 0, 0, 15, 15, Main.entities.get(i).getObserver());
		}
	}
	
	public int findEntity(double d)
	{
		for (int i = 0; i < Main.entities.size(); i++)
			if (Main.entities.get(i).getId() == d)
				return i;
		
		return -1;
	}

}
