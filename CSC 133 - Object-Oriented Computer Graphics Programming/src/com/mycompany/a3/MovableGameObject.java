package com.mycompany.a3;

import java.util.Random;
import com.codename1.charts.util.ColorUtil;
import java.text.Format;
//used to create the squirrel and bird class to move around the game
public abstract class MovableGameObject extends GameObject {
	private int heading;
	private int speed;
	
	// create a movable object that looks anywhere
	// with a speed of 1 to 30
	public MovableGameObject()
	{
		Random rand = new Random();
		heading = rand.nextInt(361);
		speed = rand.nextInt(30)+1;
	}
	// used to place a squirrel from the GameWorld class
	public MovableGameObject(double x, double y)
	{
		super(x,y);
		Random rand = new Random();
		heading = rand.nextInt(361);
		speed = rand.nextInt(30)+1;
	}
	public void move()
	{
		//change to degrees?
		double newX = getLocation().getX() + speed * (float)Math.cos(Math.toRadians(90 - heading));
		double newY = getLocation().getY() + speed * (float)Math.sin(Math.toRadians(90 - heading));
		setLocation(newX, newY);
	}
	public int getHeading()
	{
		return heading;
	}
	public int getSpeed()
	{
		return speed;
	}
	public void setHeading(int x)
	{
		heading = x;
	}
	public void setSpeed(int x)
	{
		speed = x;
	}
	public String toString()
	{
		int color = getColor();
		String str = "loc=" + getLocation().getX() + 
				"," + getLocation().getY() + " color=[" +
				ColorUtil.red(color) + "," + ColorUtil.green(color) +
				"," + ColorUtil.blue(color) + "] heading=" + getHeading() +
				" speed=" + getSpeed() + " size=" + getSize();
		return str;
	}
}
