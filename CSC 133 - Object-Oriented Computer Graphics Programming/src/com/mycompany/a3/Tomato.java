package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.FixedGameObject;

// class used to restore the energy level of a squirrel
public class Tomato extends FixedGameObject {
	private int nutrition; //nutrition is capacity?
	
	public Tomato()
	{
		setColor(255,0,0); // all tomoatoes shall be red
		nutrition = getSize();
	}
	public int getNutrition()
	{
		return nutrition;
	}
	// used when the squirrel eats a tomato
	public void usedTomato()
	{
		nutrition = 0;
		setColor(255,119,111);
	}
	public String toString()
	{
		String str = "Tomato: " + super.toString() + " capacity=" + nutrition;
		return str;
	}
	public void draw(Graphics g, Point pCmpRelPrnt)
	{
		//filled circles
		g.setColor(getColor());
		//text displaying capactiy
		float x = pCmpRelPrnt.getX() + getLocation().getX();
		float y = pCmpRelPrnt.getY() + getLocation().getY();
		g.fillArc((int)x-getSize()/2, (int)y-getSize()/2, getSize(), getSize(), 0, 360);
		char[] data = String.valueOf(getNutrition()).toCharArray();
		g.setColor(ColorUtil.BLACK);
		g.drawChars(data, 0, data.length, (int)x, (int)y);
	}
}
