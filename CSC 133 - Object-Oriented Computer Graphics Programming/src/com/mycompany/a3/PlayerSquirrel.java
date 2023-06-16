package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

// Plyaer squirrel implements singleton design pattern to disallow multiple player squirrels
public class PlayerSquirrel extends Squirrel {
	public static PlayerSquirrel playableSquirrel;
	private PlayerSquirrel(double x, double y)
	{
		super(x,y);
	}
	public static PlayerSquirrel createPlayer(double Xlocation, double Ylocation)
	{
		if(playableSquirrel == null)
			playableSquirrel = new PlayerSquirrel(Xlocation,Ylocation);
		return playableSquirrel;
	}
	public String toString()
	{
		return "Player " + super.toString();
	}
	public void draw(Graphics g, Point pCmpRelPrnt)
	{
		//filled square
		g.setColor(getColor());
		float x = pCmpRelPrnt.getX() + getLocation().getX();
		float y = pCmpRelPrnt.getY() + getLocation().getY();
		g.fillRect((int)x-getSize()/2, (int)y-getSize()/2, getSize(), getSize());
	}
}
