package split.limplungs.com;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;

public class World extends Canvas
{
	private static final long serialVersionUID = 6405524727711665587L;

	@SuppressWarnings("unused")
	private String name;

	private GFrame frame;

	public double TotalEntities = 0.00001;

	public ArrayList<Entity> entities = new ArrayList<Entity>();

	// TODO: turn to arraylist at some point
	public int[][] dirties = new int[][] { new int[] { 5 }, new int[] { 5 } };

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
	}

	// TODO: needs to convert for arraylist at some point
	private void redraw()
	{
		// for (int i = 0; i < dirties[0].length; i++)
		// this.repaint(dirties[0][i], dirties[1][i], dirties[0][i] + 15,
		// dirties[1][i] + 15);
	}

	public void update()
	{

	}

	public void render()
	{
		for (int i = 0; i < this.entities.size(); i++)
		{
			if (this.entities.get(i).isDirty())
			{
				this.entities.get(i).setDirty(false);
				this.getGraphics().drawImage(this.entities.get(i).getImage(), this.entities.get(i).getXTile() * 16, this.entities.get(i).getYTile() * 16, this.entities.get(i).getXTile() * 16 + 15, this.entities.get(i).getYTile() * 16 + 15, 0, 0, 15, 15, this.entities.get(i).getObserver());
			}
		}

		redraw();
	}

	public int findEntity(double d)
	{
		for (int i = 0; i < this.entities.size(); i++)
			if (this.entities.get(i).getId() == d)
				return i;

		return -1;
	}

	public void balanceEntities(ArrayList<Entity> e)
	{
		for (int i = 0; i < e.size(); i++)
			e.get(i).setId(e.get(i).getId() - .00001);

		this.TotalEntities -= .00001;
	}

	public void addEntity(Entity e)
	{
		e.setDirty(true);
		this.entities.add(e);
		this.TotalEntities += .00001;
	}

}
