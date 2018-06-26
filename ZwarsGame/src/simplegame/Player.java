package simplegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Player extends GameObject{
	
	private Handler handler;
	//protected float gunx, guny;
	protected float angle, angularVel;
	private BufferedImage image;
	

	


	public Player(float x, float y, float angle, ID player, Handler handler, BufferedImage image) {
		super(x, y, player);
		this.image = image;
		this.handler = handler;
		this.angle = angle;
		
	}

	public void tick() {
	    //System.out.println("tick" + id);
		this.x += velX;
		this.y += velY;
		this.angle += angularVel;
		
		
		
		x = Game.clamp(x, Game.WIDTH -72 , 0);
		y = Game.clamp(y,Game.HEIGHT  -64, 32);
		
		if(angle < 0) angle = 360 + angle;
		if(angle > 360) angle = angle-360;
		
		//gunx = Game.clamp(gunx, Game.WIDTH -48 , 32);
		//guny = Game.clamp(guny,Game.HEIGHT  -64, 16);
		
		collision();
	}
	
	private void collision() {
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			if(temp.getID() == ID.PlayerBullet) {
				PlayerBullet bullet = (PlayerBullet) temp;
				if(this.getBounds().intersects(temp.getBounds())) {
					if(bullet.getParent() != this.getID()) {
						if(bullet.getParent() == ID.Player1) {
							HUDP2.HEALTH-= 2;
							HUDP2.greenValue -= 3;
							handler.removeObject(bullet);
						}else if(bullet.getParent() == ID.Player2) {
							HUDP1.HEALTH-= 2;
							HUDP1.greenValue -= 3;
							handler.removeObject(bullet);
						}
					}
				}
			}
		}
		
	}
	
	public void rotateImage(Graphics g) {
		
		//Graphics2D gg = (Graphics2D)g.create();
		/*gg.setColor(Color.WHITE);
		System.out.println("angle" +angle);
		gg.rotate(Math.toRadians(angle),(int)x+16,(int)y+16);
	   
	    
	    gg.dispose();*/
		
		g.drawImage(image, (int)x, (int)y, null);
	    float xAim = (float)(100*Math.cos(Math.toRadians(angle)));
	    float yAim = (float)(100*Math.sin(Math.toRadians(angle)));
	    g.setColor(Color.RED);
	    g.fillRect((int)(this.x + xAim + 16), (int)(this.y + (int)yAim + 16), 8, 8);
	    
	    
		
		/*ImageLoader loader = new ImageLoader();
		BufferedImage c = loader.loadImage("/sprite.png");
		BufferedImage canvas = loader.crop(c, 0, 0, 32, 32);
		//BufferedImage canvas = = NewBufferedImage(60,60,BufferedImage.TYPE_INT_ARGB);
		//Graphics g2d = canvas.getGraphics();
		Graphics2D g2d = (Graphics2D)canvas.getGraphics();
		//g2d.setColor(Color.WHITE);
		System.out.println("in rotate method");
		g2d.rotate(Math.toRadians(45),12,12);
		
		
		//g2d.drawRect(100, 100, 32, 32);
		g2d.drawImage(image, (int)x, (int)y, null);
		this.image = canvas;*/
	}
	public float getAngle() {
		return this.angle;
	}
	public float getAngularVel() {
		return this.angularVel;
	}
	public void setAngle(float a) {
		this.angle = a;
	}
	public void setAngularVel(float v) {
		this.angularVel = v;
	}
	/*public float getGunX() {
		return this.gunx;
	}
	
	public float getGunY() {
		return this.guny;
	}
	
	public void setGunX(int gunx) {
		this.gunx = gunx;
	}
	
	public void setGunY(int guny) {
		this.guny = guny;
	}
	*/
	public void render(Graphics g) {
		//g.drawImage(image,(int) x,(int) y, null);
		rotateImage(g);
		//Graphics2D g2d = (Graphics2D)g;
		//g.setColor(Color.white);
		//g.fillRect((int)x, (int)y, 32, 32);
		//g.fillRect((int)gunx, (int)guny, 40, 16);
		/*
		Path2D.Double path = new Path2D.Double();
	    path.append(new Rectangle((int)x,(int)y, 32 ,32), false);
	    AffineTransform t = new AffineTransform();
	    t.rotate(45);
	    path.transform(t);
	    g2d.draw(path);
*/
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, 32, 32);
	}
}
