package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.mycompany.a3.IStrategy;

public class NonPlayableSquirrel extends Squirrel {
	private IStrategy strategy;
	public NonPlayableSquirrel(double x, double y)
	{
		super(x,y);
		energyLevel = 999999999;
		setSpeed(20);
	}
	
	// @override Squirrel.updateMaximuSpeed
	// increases the amount of times that the squirrel can be hit
	public void turn(int steeringSet)
	{
		steeringDirection = steeringSet;
	}
	public void updateMaximumSpeed()
	{
		maximumSpeed = (int)(30-30.0*((float)damageLevel/6)); // can be hit 6 times
		if(getSpeed() > maximumSpeed)
			setSpeed(maximumSpeed);
	}
	public void setStrategy(IStrategy strat)
	{
		strategy = strat;
	}
	public void invokeStrategy()
	{
		strategy.apply();
	}
	public String toString()
	{
		return super.toString() + " Strategy: " + strategy.toString();
	}
	public String strategyString()
	{
		return strategy.toString();
	}
	public void draw(Graphics g, Point pCmpRelPrnt)
	{
		//unfilled square
		
		float x = pCmpRelPrnt.getX() + getLocation().getX();
		float y = pCmpRelPrnt.getY() + getLocation().getY();
		g.drawRect((int)x-getSize()/2, (int)y-getSize()/2, getSize(), getSize());
	}
}
