package split.limplungs.com;

import java.awt.Canvas;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Entity extends Canvas
{
	private static final long serialVersionUID = -2393885498281593601L;
	
	private int[] pixels;
	private BufferedImage image;
	
	private ImageObserver observer = new ImageObserver()
	{
		@Override
		public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height)
		{
			return false;
		}
	};

	public Entity (int[] pixels)
	{
		this.setPixels(pixels);
		
		BufferedImage img = new BufferedImage(16,16,BufferedImage.TYPE_INT_ARGB);
		
		for (int i = 0; i < pixels.length; i += 3)
				img.setRGB(pixels[i], pixels[i+1], pixels[i+2]);
		
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
}
