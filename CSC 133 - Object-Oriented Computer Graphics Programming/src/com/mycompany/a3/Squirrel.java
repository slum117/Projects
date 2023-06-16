package com.mycompany.a3;

import com.mycompany.a3.ISteerable;
import com.mycompany.a3.MovableGameObject;

// player character used to get all nuts
// controlled through the methods in the gameworld
public abstract class Squirrel extends MovableGameObject implements ISteerable {
	protected int steeringDirection;
	protected int maximumSpeed;
	protected int energyLevel;
	protected int energyConsumptionRate;
	protected int damageLevel;
	protected static int lastNutReached; // static used for AttackStrategy
	protected int maxSteer = 40;
	
	//need work
	//maximum speed = 30-30*(damageLevel/10) - 10 hits to die?
	//set up constructor without size
	public Squirrel(double x, double y) //, int size)
	{
		setSize(100);
		setLocation((float) x,(float) y); 		// location set to that on the first nut?
		setColor(150,75,0); 	// all squirrels are brown
		setHeading(0);
		setSpeed(3);
		energyConsumptionRate = 1;
		steeringDirection = 0;
		maximumSpeed = 30;
		energyLevel = 20;
		damageLevel = 0;
		lastNutReached = 1;
	}
	public int getMaxSpeed()
	{
		return maximumSpeed;
	}
	public int getLastNut()
	{
		return lastNutReached;
	}
	public int getEnergyLevel()
	{
		return energyLevel;
	}
	public int getDamageLevel()
	{
		return damageLevel;
	}
	public int getSteeringDirection()
	{
		return steeringDirection;
	}
	public void setLastNut(int x)
	{
		lastNutReached = x;
	}
	public void resetMaxSpeedAndDamage()
	{
		energyLevel = 20;
		maximumSpeed = 30;
		damageLevel = 0;
		setColor(150,75,0);
	}
	public void consumeEnergy()
	{
		energyLevel = energyLevel - energyConsumptionRate;
	}
	// adds capacity 'x' to the energy level of the squirrel
	public void consumeTomato(int x)
	{
		energyLevel += x;
	}
	// used to brake or accelerate
	public void accSpd(int x)
	{
		int newSpd = getSpeed() + x;
		if(newSpd <= maximumSpeed) {
			System.out.println("Going Fast");
			setSpeed(newSpd);
		} else 
			System.out.println("You can't go faster!");
	}
	public void brk(int x)
	{
		int newSpd = getSpeed() - x;
		if(newSpd >= 0) {
			System.out.println("Slowing down!");
			setSpeed(newSpd);
		} else
			System.out.println("You're not moving!");
	}
	public void turn(char x)
	{
		switch(x) {
		// counter clockwise
		case 'l':
			if(steeringDirection > -40) // ignore going below -40
				steeringDirection -= 1;
			break;
		// clockwise
		case 'r':
			if(steeringDirection < 40) // ignore going above 40
				steeringDirection += 1;
			break;
		}
	}
	// change the header
	// call during every tick?
	public void updateHeader()
	{
		setHeading((getHeading() + steeringDirection)%360);
	}
	
	// call when update damageLevel
	public void updateMaximumSpeed()
	{
		maximumSpeed = (int)(30-30.0*((float)damageLevel/4)); // can be hit 4 times
		if(getSpeed() > maximumSpeed)
			setSpeed(maximumSpeed);
	}
	// occurs when collision
	public void updateDamageLevel(int x)
	{
		damageLevel += x;
		updateMaximumSpeed();
		// change color
		if(damageLevel == 1)
			setColor(148,98,42); // 2 hp
		else if (damageLevel == 2)
			setColor(146,119,91); // 1 hp
		else if (damageLevel == 3)
			setColor(71,40,11);
		else {
			setColor(255,255,255); // gameover color
		}
	}
	public String toString()
	{
		String str = "Squirrel: " + super.toString() + " maxSpeed=" + maximumSpeed +
						" steeringDirection=" + steeringDirection + " energyLevel=" +
						energyLevel + " damageLevel= " + damageLevel;
		return str;
	}
}
