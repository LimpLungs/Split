package split.limplungs.com;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Entity
{
	public enum Type
	{
		PLAYER, PERSON, ZOMBIE, BLOCK
	}

	private int[] pixels;
	private BufferedImage image;

	private Type type = null;
	private double id = 0.00000;

	private boolean moveable;

	private int XTile;
	private int YTile;

	private ImageObserver observer = new ImageObserver()
	{
		@Override
		public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height)
		{
			return false;
		}
	};

	public Entity(double id, Type type, int[] pixels)
	{
		this.setId(id);
		this.setType(type);
		this.setPixels(pixels);

		BufferedImage img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		for (int i = 0; i < pixels.length; i += 3)
			img.setRGB(pixels[i], pixels[i + 1], pixels[i + 2]);

		this.setImage(img);
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

	public void moveLeft()
	{
		this.setXTile(this.getXTile() - 1);
	}

	public void moveRight()
	{
		this.setXTile(this.getXTile() + 1);
	}

	public void moveUp()
	{
		this.setYTile(this.getYTile() - 1);
	}

	public void moveDown()
	{
		this.setYTile(this.getYTile() + 1);
	}
}
