package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;

// class used to make Tomato and Nut class
public abstract class FixedGameObject extends GameObject {
	public FixedGameObject()
	{
		super();
	}
	//used to generate a nut at (x,y)
	public FixedGameObject(double x, double y)
	{
		super(x,y);
	}
	//overwrite setLocation from game object
	public void setLocation()
	{
		
	}
	public String toString()
	{
		int color = getColor();
		String str = "loc=" + getLocation().getX() + 
						"," + getLocation().getY() + " color=[" +
						ColorUtil.red(color) + "," + ColorUtil.green(color) +
						"," + ColorUtil.blue(color) + "] size=" + getSize();
		return str;
	}
}
