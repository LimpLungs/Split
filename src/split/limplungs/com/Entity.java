package split.limplungs.com;

import java.awt.Canvas;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Entity extends Canvas
{
	private static final long serialVersionUID = -2393885498281593601L;

	public enum Type
	{
		PLAYER, PERSON, ZOMBIE
	}
	
	private int[] pixels;
	private BufferedImage image;
	private int XTile;
	private int YTile;
	
	private boolean moveable;
	private double id = 0.00000;
	private Type type = null;

	private ImageObserver observer = new ImageObserver()
	{
		@Override
		public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height)
		{
			return false;
		}
	};

	public Entity(int[] pixels)
	{
		this.setPixels(pixels);

		BufferedImage img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		for (int i = 0; i < pixels.length; i += 3)
			img.setRGB(pixels[i], pixels[i + 1], pixels[i + 2]);

		this.setImage(img);
		
		this.setId(Main.TotalEntities);
		
		Main.TotalEntities += .00001;
	}

	public int[] getPixels()
	{
		return pixels;
	}

	public void setPixels(int[] pixels)
	{
		this.pixels = pixels;
	}

	public BufferedImage getImage()
	{
		return image;
	}

	public void setImage(BufferedImage image)
	{
		this.image = image;
	}

	public ImageObserver getObserver()
	{
		return observer;
	}

	public void setObserver(ImageObserver observer)
	{
		this.observer = observer;
	}

	protected void createPixelArray(){}

	public boolean isMoveable()
	{
		return moveable;
	}

	public void setMoveable(boolean moveable)
	{
		this.moveable = moveable;
	}

	public int getXTile()
	{
		return XTile;
	}

	public void setXTile(int xTile)
	{
		XTile = xTile;
	}

	public int getYTile()
	{
		return YTile;
	}

	public void setYTile(int yTile)
	{
		YTile = yTile;
	}

	public double getId()
	{
		return id;
	}

	public void setId(double id)
	{
		this.id = id;
	}

	public Type getType()
	{
		return type;
	}

	public void setType(Type type)
	{
		this.type = type;
	}

	public boolean isDirty()
	{
		return false;
	}
}
