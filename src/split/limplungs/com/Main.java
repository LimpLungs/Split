package split.limplungs.com;

import java.awt.Toolkit;
import java.util.Random;

public class Main
{
	public static final boolean DEBUG = false;

	public static GameThread THREAD = new GameThread();

	public static void main(String[] args)
	{
		World city = new World("City");
		
		for (int i = 0; i < Toolkit.getDefaultToolkit().getScreenSize().height / 16; i++)
			for (int j = 0; j < Toolkit.getDefaultToolkit().getScreenSize().width / 16; j++)
				if (new Random().nextInt(20) == 1)
					if (World.entities.size() == 0)
						city.addEntity(new Player(j,i,city.TotalEntities));
					else
						if (new Random().nextInt(15) == 1)
						city.addEntity(new Block(j,i,city.TotalEntities));
					else
						if (new Random().nextInt(15) == 2)
						city.addEntity(new Person(j,i,city.TotalEntities));
					else
						city.addEntity(new Zombie(j,i,city.TotalEntities));
		
		try
		{
			THREAD.run(city);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
}
