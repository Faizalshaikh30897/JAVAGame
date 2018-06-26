package zwars;

import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;

/**
  utility class to handle operations on images
 */
public class ImageLoader {

	public ImageLoader() {

	}
	/**
	  load an image for the given path in file system
	  @param path location of the image in the file system
	  @return the image is returned as an object of BufferedImage
	 */
	public BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	/**
	   utility method to crop a part as image from a given image
	  @param img image whose subimage has to be obtained
	  @param x x-coordinate of the image from where the cropped image starts
	  @param y  y-coordinate of the image from where the cropped image starts
	  @param width width of the image to be cropped
	  @param height height of the image to be cropped
	  @return returns the cropped image as BufferedImage
	 */
	public BufferedImage crop(BufferedImage img, int x, int y, int width, int height) {
		BufferedImage result = img.getSubimage(x, y, width, height);

		return result;
	}

}
