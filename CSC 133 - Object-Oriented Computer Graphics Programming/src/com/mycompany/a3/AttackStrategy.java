package com.mycompany.a3;

import com.codename1.util.MathUtil;

public class AttackStrategy implements IStrategy {
	
	private NonPlayableSquirrel npc;
	private PlayerSquirrel pc;
	
	public AttackStrategy(NonPlayableSquirrel npc,PlayerSquirrel pc)
	{
		this.npc = npc;
		this.pc = pc;
	}
	
	// calculates the player squirrel direction and sets the steering 
	// to manipulate the heading to go towards the squirrel
	public void apply() {
		float xDistance = pc.getLocation().getX() - npc.getLocation().getX();
		float yDistance = pc.getLocation().getY() - npc.getLocation().getY();
		double idealHeading = Math.toDegrees(MathUtil.atan2(yDistance,xDistance));
		int idealCompassAngle = 90-(int)idealHeading;
		int appliedTurns = 0;
		int appliedHeading = idealCompassAngle - npc.getHeading();
		//compare ideal compass angle to current angle
		//move the difference between the two
		appliedHeading = Math.abs(appliedHeading);
		boolean turnLeft = appliedHeading/180 > 0;
		if(turnLeft)
		{
			// returns the degrees needed to turn left
			appliedHeading = 360 - appliedHeading;
			// limits the steering at 40/-40
			// otherwise chooses the remainder of what's needed
			if(appliedHeading/40 > 0)
				appliedTurns = -40;
			else
				appliedTurns = -appliedHeading%40;
		} else {
			if(appliedHeading/40 > 0)
				appliedTurns = 40;
			else
				appliedTurns = appliedHeading%40;
		}
		npc.turn(appliedTurns);
		npc.updateHeader();
	}
	public String toString()
	{
		return "Attack Strategy";
	}
}