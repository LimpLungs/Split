package split.limplungs.com;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class World extends JPanel
{
	private static final long serialVersionUID = 6405524727711665587L;

	@SuppressWarnings("unused")
	private String name;

	private GFrame frame;

	public double TotalEntities = 0.00001;

	public static ArrayList<Entity> entities = new ArrayList<Entity>();

	public static ArrayList<Point> dirties = new ArrayList<Point>();

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
		frame.addKeyListener(CONTROLS);
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

	private void redraw()
	{
		while (dirties.size() > 0)
		{
			this.repaint(dirties.get(0).x * 16, dirties.get(0).y  * 16, 16, 16);
			dirties.remove(0);
		}
	}

	public void update()
	{
		
	}

	public void render()
	{
		for (int i = 0; i < World.entities.size(); i++)
		{
			switch (World.entities.get(i).getType())
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
		}

		redraw();

	}

	public int findEntity(double d)
	{
		for (int i = 0; i < World.entities.size(); i++)
			if (World.entities.get(i).getId() == d)
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
		World.entities.add(e);
		this.TotalEntities += .00001;
	}


	public static KeyListener CONTROLS = new KeyListener()
	{
		@Override
		public void keyTyped(KeyEvent e)
		{
		}

		@Override
		public void keyPressed(KeyEvent e)
		{
			switch (e.getKeyCode())
			{
				case KeyEvent.VK_W:
					World.dirties.add(new Point(((Entity)World.entities.get(0)).getXTile(), ((Entity)World.entities.get(0)).getYTile()));
					((Entity)World.entities.get(0)).moveUp();
					break;
				case KeyEvent.VK_A:
					World.dirties.add(new Point(((Entity)World.entities.get(0)).getXTile(), ((Entity)World.entities.get(0)).getYTile()));
					((Entity)World.entities.get(0)).moveLeft();
					break;
				case KeyEvent.VK_S:
					World.dirties.add(new Point(((Entity)World.entities.get(0)).getXTile(), ((Entity)World.entities.get(0)).getYTile()));
					((Entity)World.entities.get(0)).moveDown();
					break;
				case KeyEvent.VK_D:
					World.dirties.add(new Point(((Entity)World.entities.get(0)).getXTile(), ((Entity)World.entities.get(0)).getYTile()));
					((Entity)World.entities.get(0)).moveRight();
					break;
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e)
		{
		}

	};
}
