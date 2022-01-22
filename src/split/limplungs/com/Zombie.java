package split.limplungs.com;

import java.awt.Color;

public class Zombie extends Entity
{
	private static int[] pix = new int[3 * 16 * 16];
	
	public Zombie()
	{
		super(pix);
		
		for (int i = 0; i < pix.length; i += 3)
		{
			pix[i] = (i / 3) % 16;
			pix[i + 1] = (i / 3) / 16;
			pix[i + 2] = Color.GREEN.getRGB();
		}
		
		this.setPixels(pix);
	}

	private static final long serialVersionUID = -8893875991021986068L;

}
