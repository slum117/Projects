package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.mycompany.a3.FixedGameObject;

// object that is objective to completing the game
public class Nut extends FixedGameObject {
	private static int count = 1;
	private static int lastNutEaten = 0;
	private int sequenceNumber;
	 
	public Nut()
	{
		super(500.0,500.0); // generate a nut at (500,500)
		sequenceNumber = count;
		count++;
		setColor(0,255,255); 
	}
	
	public Nut(double x, double y, int size)
	{
		super(x,y);
		sequenceNumber = count;
		count++;
		setSize(size);
		setColor(0,255,0);
	}
	public static void incLastNutEaten()
	{
		lastNutEaten++;
	}
	public int getLastNutEaten()
	{
		return lastNutEaten;
	}
	public int getCount()
	{
		return count;
	}
	public int getSequence()
	{
		return sequenceNumber;
	}
	public String toString()
	{
		String str = "Nut: " + super.toString() + " seqNum=" + getSequence();
		return str;
	}
	public void draw(Graphics g, Point pCmpRelPrnt)
	{
		//filled isosceles triangel
		g.setColor(getColor());
		//text displayed nut #
		//get offset of parent
		int x = (int) (pCmpRelPrnt.getX() + getLocation().getX());
		int y = (int) (pCmpRelPrnt.getY() + getLocation().getY());
		g.drawChar((char) (sequenceNumber+'0'), x, y);
	}
}
