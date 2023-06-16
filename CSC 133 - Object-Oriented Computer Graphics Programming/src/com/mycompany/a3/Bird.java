package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.mycompany.a3.MovableGameObject;

//unplayable character meant to obstruct the player
public class Bird extends MovableGameObject {
	public Bird()
	{
		setColor(0,0,255); // all blues are blue
	}
	
	// changes the value of the bird's heading from 0 to 5
	public void birdChangeHeading()
	{
		Random rand = new Random();
		int selectOne = rand.nextInt(2);
		int value = rand.nextInt(6);
		int head = getHeading();
		if(value != 0)
			if(selectOne == 1) {
				setHeading(head+value);
			} else {
				setHeading(head-value);
			}
		hitEdge();
	}
	// function to change heading when hitting the edge of the world
	// assuming the edge of the world is 1000 x 1000
	public void hitEdge()
	{
		Point currentLocation = getLocation();
		float x = currentLocation.getX();
		float y = currentLocation.getY();
		if(x > getWidth())
			setHeading(getHeading()-180);
		else if(x < 0)
			setHeading(getHeading()-180);
		if(y > getHeight())
			setHeading(getHeading()-180);
		else if(y < 0)
			setHeading(getHeading()-180);
	}
	public String toString()
	{
		String str = "Bird: " + super.toString();
		return str;
	}
	public void draw(Graphics g, Point pCmpRelPrnt)
	{
		//unfilled isosceles triangle
		g.setColor(getColor());
		int x = (int) (pCmpRelPrnt.getX() + getLocation().getX());
		int y = (int) (pCmpRelPrnt.getY() + getLocation().getY());
		g.fillTriangle(x+15, y+15, x, y, x+30, y);
	}
}
