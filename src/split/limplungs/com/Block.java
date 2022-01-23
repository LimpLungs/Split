package split.limplungs.com;

import java.awt.Color;

public class Block extends Entity
{
	public Block(int x, int y, double id)
	{
		super(id, Type.BLOCK, createPixelArray());

		this.setMoveable(false);

		this.setXTile(x);
		this.setYTile(y);
	}

	public Block(double id)
	{
		super(id, Type.BLOCK, createPixelArray());

		this.setMoveable(false);
	}

	public static int[] createPixelArray()
	{
		int[] pix = new int[3 * 16 * 16];

		for (int i = 0; i < pix.length; i += 3)
		{
			pix[i] = (i / 3) % 16;
			pix[i + 1] = (i / 3) / 16;
			pix[i + 2] = new Color(165,40,40).getRGB();
		}

		return pix;
	}

}
