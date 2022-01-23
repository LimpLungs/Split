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
				g.setColor(Color.DARK_GRAY);
				g.fillRect(j + 0, i + 0, 16 - 0, 16 - 0);
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
			switch (this.entities.get(i).getType())
			{
				case ZOMBIE:
					Zombie zombie = (Zombie) entities.get(i);
					this.getGraphics().drawImage(zombie.getImage(), zombie.getXTile() * 16, zombie.getYTile() * 16, zombie.getXTile() * 16 + 15, zombie.getYTile() * 16 + 15, 0, 0, 15, 15, zombie.getObserver());
					break;
				case PLAYER:
					Player player = (Player) entities.get(i);
					this.getGraphics().drawImage(player.getImage(), player.getXTile() * 16, player.getYTile() * 16, player.getXTile() * 16 + 15, player.getYTile() * 16 + 15, 0, 0, 15, 15, player.getObserver());
					break;
				case PERSON:
					Person person = (Person) entities.get(i);
					this.getGraphics().drawImage(person.getImage(), person.getXTile() * 16, person.getYTile() * 16, person.getXTile() * 16 + 15, person.getYTile() * 16 + 15, 0, 0, 15, 15, person.getObserver());
					break;
			}
			if (this.entities.get(i).isDirty())
			{
				this.entities.get(i).setDirty(false);

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
