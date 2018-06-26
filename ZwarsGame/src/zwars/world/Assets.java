package zwars.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import zwars.ImageLoader;
/**
Assets class stores all the resource images for rendering map.
*/
public class Assets {
	private static ImageLoader loader = new ImageLoader();
	public static BufferedImage wall,log,tree,grass,house,road;
    public static void init(){
		String path = new File("wall.jpg").getAbsolutePath();
		BufferedImage walls=convertToARGB(loader.loadImage(path));
	 	path = new File("tree.png").getAbsolutePath();
		BufferedImage trees=convertToARGB(loader.loadImage(path));
		path = new File("log.png").getAbsolutePath();
		BufferedImage logs=convertToARGB(loader.loadImage(path));
		path = new File("grass.png").getAbsolutePath();
		BufferedImage grasss=convertToARGB(loader.loadImage(path));
		path = new File("house.png").getAbsolutePath();
		BufferedImage houses=convertToARGB(loader.loadImage(path));
		path = new File("road.png").getAbsolutePath();
		BufferedImage roads=convertToARGB(loader.loadImage(path));

    	/*BufferedImage walls=convertToARGB(loader.loadImage("C:\\javaproject\\wall.jpg"));
    	BufferedImage logs=convertToARGB(loader.loadImage("C:\\javaproject\\log.png"));
    	BufferedImage trees=convertToARGB(loader.loadImage("C:\\javaproject\\tree.png"));
    	BufferedImage grasss=convertToARGB(loader.loadImage("C:\\javaproject\\grass.png"));
    	BufferedImage houses=convertToARGB(loader.loadImage("C:\\javaproject\\house.png"));
    	BufferedImage roads=convertToARGB(loader.loadImage("C:\\javaproject\\road.png"));
		*/
    	wall= loader.crop(walls,0, 0, 64, 64);
    	log= loader.crop(logs,0, 0, 64, 64);
    	tree = loader.crop(trees,0, 0, 64, 64);
    	grass= loader.crop(grasss,0, 0, 64, 64);
    	house= loader.crop(houses,0, 0, 192,192);
    	road= loader.crop(roads,0, 0, 64, 64);

    }
	/**
	converts the high resolution image to simple rgb type image for faster rendering
	*/
    private static BufferedImage convertToARGB(BufferedImage image)
    {
        BufferedImage newImage = new BufferedImage(
            image.getWidth(), image.getHeight(),
            BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }
}
