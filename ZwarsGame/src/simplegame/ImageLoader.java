package simplegame;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public ImageLoader() {
		
	}
	
	public BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public BufferedImage crop(BufferedImage img, int x, int y, int width, int height) {
		BufferedImage result = img.getSubimage(x, y, width, height);
		
		return result;
	}

}
