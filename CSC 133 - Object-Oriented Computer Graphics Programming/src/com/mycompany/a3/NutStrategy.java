package com.mycompany.a3;

import com.codename1.util.MathUtil;

public class NutStrategy implements IStrategy {
	private IIterator objectCollection;
	private Nut nut;
	private NonPlayableSquirrel npc;
	private boolean nextNutFound = false;
	
	public NutStrategy(NonPlayableSquirrel npc, IIterator collection)
	{
		objectCollection = collection;
		this.npc = npc;
		// looks for  the next uneaten nut in the iterator
		while(objectCollection.hasNext() && !nextNutFound)
		{
			Object collectionObject = objectCollection.getNext();
			if(collectionObject instanceof Nut)
			{
				nut = (Nut)collectionObject;
				if (nut.getLastNutEaten()+1 == nut.getSequence())
					nextNutFound = true;
			}
		}
	}
	@Override
	public void apply() {
		float xDistance = nut.getLocation().getX() - npc.getLocation().getX();
		float yDistance = nut.getLocation().getY() - npc.getLocation().getY();
		//double idealHeading = MathUtil.asin(xDistance/Math.sqrt(xDistance*xDistance + yDistance*yDistance));
		double idealHeading = Math.toDegrees(MathUtil.atan2(yDistance,xDistance));
		int idealCompassAngle = 90-(int)idealHeading;
		int appliedTurns = 0;
		int appliedHeading = idealCompassAngle - npc.getHeading();
		npc.turn(0);
		//compare ideal compass angle to current angle
		//move the difference between the two
		appliedHeading = Math.abs(appliedHeading);
		boolean turnLeft = appliedHeading/180 > 0;
		if(turnLeft)
		{
			appliedHeading = 180-appliedHeading+180;
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
		return "Nut strategy";
	}
}