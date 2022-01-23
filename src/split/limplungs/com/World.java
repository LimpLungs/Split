package split.limplungs.com;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
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

	public static ArrayList<Point> occupied = new ArrayList<Point>();

	public static int totalMoves = 0;
	public static int playerMoves = 5;

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
		while (World.dirties.size() > 0)
		{
			if (World.occupied.size() > 0 && World.dirties.size() > 0)
				for (int i = 0; i < World.occupied.size(); i++)
				{
					if (World.occupied.get(i) != null && World.dirties.get(0) != null)
						if (World.occupied.get(i).x == World.dirties.get(0).x && World.occupied.get(i).y == World.dirties.get(0).y)
							World.occupied.remove(i);
				}

			this.repaint(World.dirties.get(0).x * 16, World.dirties.get(0).y * 16, 16, 16);
			World.dirties.remove(0);
		}
	}

	public void update()
	{

	}

	public void render()
	{
		for (int i = 0; i < World.entities.size(); i++)
		{
			if (this.getGraphics() != null)
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
		for (int i = 0; i < World.occupied.size(); i++)
		{
			if (World.occupied.get(i).x == e.getXTile() && World.occupied.get(i).y == e.getYTile())
				return;
		}

		World.entities.add(e);
		World.occupied.add(new Point(e.getXTile(), e.getYTile()));

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
			boolean stop = false;
			switch (e.getKeyCode())
			{
				case KeyEvent.VK_W:

					if (World.occupied.size() > 0 && World.entities.size() > 0)
						for (int i = 0; i < World.occupied.size(); i++)
						{
							if (World.occupied.get(i) != null && World.entities.get(0) != null)
								if (World.occupied.get(i).x == World.entities.get(0).getXTile() && World.occupied.get(i).y == World.entities.get(0).getYTile() - 1)
									stop = true;
						}

					if (!stop)
					{
						World.dirties.add(new Point(World.entities.get(0).getXTile(), World.entities.get(0).getYTile()));
						World.entities.get(0).moveUp();
						World.occupied.add(new Point(World.entities.get(0).getXTile(), World.entities.get(0).getYTile()));
						World.moveHumans();
						World.runMovements();
					}
					break;

				case KeyEvent.VK_A:

					if (World.occupied.size() > 0 && World.entities.size() > 0)
						for (int i = 0; i < World.occupied.size(); i++)
						{
							if (World.occupied.get(i) != null && World.entities.get(0) != null)
								if (World.occupied.get(i).x == World.entities.get(0).getXTile() - 1 && World.occupied.get(i).y == World.entities.get(0).getYTile())
									stop = true;
						}

					if (!stop)
					{
						World.dirties.add(new Point(World.entities.get(0).getXTile(), World.entities.get(0).getYTile()));
						World.entities.get(0).moveLeft();
						World.occupied.add(new Point(World.entities.get(0).getXTile(), World.entities.get(0).getYTile()));
						World.moveHumans();
						World.runMovements();
					}
					break;

				case KeyEvent.VK_S:

					if (World.occupied.size() > 0 && World.entities.size() > 0)
						for (int i = 0; i < World.occupied.size(); i++)
						{
							if (World.occupied.get(i) != null && World.entities.get(0) != null)
								if (World.occupied.get(i).x == World.entities.get(0).getXTile() && World.occupied.get(i).y == World.entities.get(0).getYTile() + 1)
									stop = true;
						}

					if (!stop)
					{
						World.dirties.add(new Point(World.entities.get(0).getXTile(), World.entities.get(0).getYTile()));
						World.entities.get(0).moveDown();
						World.occupied.add(new Point(World.entities.get(0).getXTile(), World.entities.get(0).getYTile()));
						World.moveHumans();
						World.runMovements();
					}
					break;

				case KeyEvent.VK_D:

					if (World.occupied.size() > 0 && World.entities.size() > 0)
						for (int i = 0; i < World.occupied.size(); i++)
						{
							if (World.occupied.get(i) != null && World.entities.get(0) != null)
								if (World.occupied.get(i).x == World.entities.get(0).getXTile() + 1 && World.occupied.get(i).y == World.entities.get(0).getYTile())
									stop = true;
						}

					if (!stop)
					{
						World.dirties.add(new Point(World.entities.get(0).getXTile(), World.entities.get(0).getYTile()));
						World.entities.get(0).moveRight();
						World.occupied.add(new Point(World.entities.get(0).getXTile(), World.entities.get(0).getYTile()));
						World.moveHumans();
						World.runMovements();
					}
					break;
			}

		}

		@Override
		public void keyReleased(KeyEvent e)
		{
		}

	};

	protected static void runMovements()
	{
		totalMoves += 1;

		if (totalMoves % playerMoves == 0)
		{
			moveZombies();
		}

	}

	protected static void moveHumans()
	{
		// TODO Auto-generated method stub

	}

	protected static void moveZombies()
	{
		int size = World.entities.size();
		for (int z = 0; z < size; z++)
		{
			boolean stop = false;

			if (World.entities.get(z) != null && World.entities.get(0) != null)
				if (World.entities.get(z).getType() == Entity.Type.ZOMBIE)
				{
					int dx = World.entities.get(z).getXTile() - World.entities.get(0).getXTile();
					int dy = World.entities.get(z).getYTile() - World.entities.get(0).getYTile();

					if (Math.abs(dx) > Math.abs(dy))
					{
						dy = 0;

						if (dx > 0)
							dx = -1;
						else
							dx = 1;
					}
					else if (Math.abs(dx) < Math.abs(dy))
					{
						dx = 0;

						if (dy > 0)
							dy = -1;
						else
							dy = 1;
					}
					else
					{
						Boolean bool = new Random().nextBoolean();
						if (bool)
						{
							dy = 0;

							if (dx > 0)
								dx = -1;
							else
								dx = 1;
						}
						else
						{
							dx = 0;

							if (dy > 0)
								dy = -1;
							else
								dy = 1;
						}
					}

					if (World.occupied.size() > 0 && World.entities.size() > 0)
						for (int i = 0; i < World.occupied.size(); i++)
						{
							if (World.occupied.get(i) != null && World.entities.get(z) != null)
								if (World.occupied.get(i).x == World.entities.get(z).getXTile() + dx && World.occupied.get(i).y == World.entities.get(z).getYTile() + dy)
									stop = true;
						}

					if (!stop)
					{
						World.dirties.add(new Point(World.entities.get(z).getXTile(), World.entities.get(z).getYTile()));

						if (dx == 1)
							World.entities.get(z).moveRight();
						if (dx == -1)
							World.entities.get(z).moveLeft();
						if (dy == 1)
							World.entities.get(z).moveDown();
						if (dy == -1)
							World.entities.get(z).moveUp();

						World.occupied.add(new Point(World.entities.get(z).getXTile(), World.entities.get(z).getYTile()));
					}
				}
			
			size = World.entities.size();
		}
	}
}
