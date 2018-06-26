package zwars;

import java.awt.*;


/**
  abstract Superclass for all the Objects visible on screen of the game.
 */
public abstract class GameObject  {

	protected float x,y;//x,y coordinates of the player on screen
	protected ID id;//type of the game object
	protected float velX, velY;//movement speed in x and y direction of game object
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;

	}
	/**
	  class inheriting GameObject has to override the tick method and update its necessary attributes in it.
	 */
	public abstract void tick();

	/**
	  class inheriting GameObect has to override the render method and decide how it will get rendered on screen.
	  @param g graphics on which the object will be rendered.
	 */
	public abstract void render(Graphics g);

	/**
	  Get the bounds of the given object. Mainly used to implement collisions.
	  @return returns an object of Rectangle specifying the rectangular bounds of the object.
	 */
	public abstract Rectangle getBounds();

	/**
	  setter method for the x coordinate of the GameObject.
	  @param x value of x coordinate.
	 */
	public void setX(float x) {
		this.x = x;
	}
	/**
	  setter method for the y coordinate of the GameObject.
	  @param y value of y coordinate.
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	  setter method for the velocity of the GameObject in X direction.
	  @param velX velocity in X direction.
	 */
	public void setVelX(float velX) {
		this.velX = velX;
	}
	/**
	  setter method for the velocity of the GameObject in Y direction.
	  @param velY velocity in Y direction.
	 */
	public void setVelY(float velY) {
		this.velY = velY;
	}
	/**
	 getter method for the X coordinate of the GameObject.
	 * @return returns the value of X coordinate.
	 */
	public float getX() {
		return this.x;
	}
	/**
	  getter method for the Y coordinate of the GameObject.
	  @return returns the value of Y coordinate.
	 */
	public float getY() {
		return this.y;
	}
	/**
	  getter method for the velocity of GameObject in X direction.
	  @return returns the X velocity.
	 */
	public float getVelX() {
		return this.velX;
	}
	/**
	  getter method for the velocity of GameObject in Y direction.
	  @return returns the Y velocity.
	 */
	public float getVelY() {
		return this.velY;
	}

	/**
	  getter method for the ID of the GameObject. ID shows what is the type of this GameObject.
	  @return returns the ID.
	 */
	public ID getID() {
		return this.id;
	}
	/**
	  setter method for the ID of the GameObject. ID shows what is the type of this GameObject.
	  @param id Id of the type of GameObject.
	 */
	public void setID(ID id) {
		this.id = id;
	}


}
