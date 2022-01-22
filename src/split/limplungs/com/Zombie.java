package split.limplungs.com;

import java.awt.Color;

public class Zombie extends Entity
{
	private static final long serialVersionUID = -8893875991021986068L;
	
	private static int[] pix = new int[3 * 16 * 16];

	public Zombie()
	{
		super(pix);

		for (int i = 0; i < pix.length; i += 3)
		{
			pix[i] = (i / 3) % 16;
			pix[i + 1] = (i / 3) / 16;
			pix[i + 2] = new Color(0, 0, 0, 0).getRGB();
		}
		
		this.createPixelArray();

		this.setPixels(pix);
	}

	private void createPixelArray()
	{

		for (int i = 16 * 2 + 3; i < 16 * 2 + 12; i++)
			pix[2 + (3 * i)] = Color.GREEN.getRGB();

		for (int j = 3; j < 10; j++)
			for (int k = 16 * j + 2; k < 16 * j + 13; k++)
				pix[2 + (3 * k)] = Color.GREEN.getRGB();

		for (int l = 16 * 10 + 3; l < 16 * 10 + 12; l++)
			pix[2 + (3 * l)] = Color.GREEN.getRGB();

		for (int m = 16 * 11 + 3; m < 16 * 11 + 12; m++)
			pix[2 + (3 * m)] = Color.GREEN.getRGB();

		for (int n = 16 * 12 + 4; n < 16 * 12 + 11; n++)
			pix[2 + (3 * n)] = Color.GREEN.getRGB();

		for (int n = 16 * 13 + 6; n < 16 * 13 + 9; n++)
			pix[2 + (3 * n)] = Color.GREEN.getRGB();

		pix[2 + (3 * (16 * (3 + 1) + 5))] = Color.DARK_GRAY.getRGB();
		pix[2 + (3 * (16 * (3 + 1) + 10))] = Color.DARK_GRAY.getRGB();
		pix[2 + (3 * (16 * (4 + 1) + 6))] = Color.DARK_GRAY.getRGB();
		pix[2 + (3 * (16 * (4 + 1) + 9))] = Color.DARK_GRAY.getRGB();
		
		pix[2 + (3 * (16 * (5 + 1) + 5))] = Color.WHITE.getRGB();
		pix[2 + (3 * (16 * (5 + 1) + 6))] = Color.WHITE.getRGB();
		pix[2 + (3 * (16 * (5 + 1) + 9))] = Color.WHITE.getRGB();
		pix[2 + (3 * (16 * (5 + 1) + 10))] = Color.WHITE.getRGB();
		
		pix[2 + (3 * (16 * (8 + 1) + 5))] = Color.DARK_GRAY.getRGB();
		pix[2 + (3 * (16 * (8 + 1) + 6))] = Color.DARK_GRAY.getRGB();
		pix[2 + (3 * (16 * (8 + 1) + 7))] = Color.DARK_GRAY.getRGB();
		pix[2 + (3 * (16 * (8 + 1) + 8))] = Color.DARK_GRAY.getRGB();
		pix[2 + (3 * (16 * (8 + 1) + 9))] = Color.DARK_GRAY.getRGB();
		pix[2 + (3 * (16 * (8 + 1) + 10))] = Color.DARK_GRAY.getRGB();
		
		pix[2 + (3 * (16 * (9 + 1) + 6))] = Color.DARK_GRAY.getRGB();
		pix[2 + (3 * (16 * (9 + 1) + 7))] = Color.DARK_GRAY.getRGB();
		pix[2 + (3 * (16 * (9 + 1) + 8))] = Color.DARK_GRAY.getRGB();
		pix[2 + (3 * (16 * (9 + 1) + 9))] = Color.DARK_GRAY.getRGB();
	}

}
