package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.charts.models.Point;
import java.util.*;

// used to create game objects
public abstract class GameObject implements IDrawable{
	//change size from private to protected?
	private int size;
	private Point location;
	private int color;
	private static int width;
	private static int height;
	// create a random to assign random sizes and location in GameObject
	// applies to everything but nuts and squirrels
	public GameObject() {
		Random rand = new Random();
		// random size between 10 - 50
		size = rand.nextInt(50) + 50;
		
		// random location within (0,0) (1000,0) (1000,1000) (0,1000)
		float pointX = rand.nextInt(width) + (float)rand.nextFloat();
		float pointY = rand.nextInt(height) + (float)rand.nextFloat();
		
		// randomizes points X, Y if above 1000
		while(pointX > width)
			pointX = (float)rand.nextInt(1000) + (float)rand.nextFloat();
		while(pointY > height)
			pointX = (float)rand.nextInt(1000) + (float)rand.nextFloat();
		
		location = new Point(pointX,pointY);
	}
	// used to generate a fixed object at location XX
	public GameObject(double x, double y)
	{
		Random rand = new Random();
		size = rand.nextInt(41) + 10;
		location = new Point((float)x, (float)y);
	}
	// Used to set the size for squirrels and nuts
	public void setSize(int x)
	{
		size = x;
	}
	public void setLocation(double x, double y)
	{
		location = new Point((float) x, (float) y);
	}
	public void setColor(int r,int g,int b)
	{
		color = ColorUtil.rgb(r, g, b);
	}
	public int getSize()
	{
		return size;
	}
	public Point getLocation()
	{
		return location;
	}
	public int getColor()
	{
		return color;
	}
	public static void setWidth(int x) {width = x;}
	public static void setHeight(int y) {height = y;}
	public static int getWidth() {return width;}
	public static int getHeight() {return height;}
	public abstract void draw(Graphics g, Point pCmpRelPrnt);
	//public abstract String toString();
}
