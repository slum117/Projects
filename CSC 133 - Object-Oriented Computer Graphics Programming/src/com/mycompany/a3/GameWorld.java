package com.mycompany.a3;

import java.util.*;

import com.mycompany.a3.AttackStrategy;
import com.mycompany.a3.GameObjectCollection;
import com.mycompany.a3.IIterator;
import com.mycompany.a3.MovableGameObject;
import com.mycompany.a3.NutStrategy;

// manipulates gameWorld
// methods called from Game class
// generates player squirrel called Adam
// sets up an array list of game objects that hold all other objects
public class GameWorld extends Observable{
	private int lives = 3;
	private int clock = 0;
	private GameObjectCollection memory;
	private boolean confirmExit;
	private int maxNuts;
	private boolean soundOn = true;
	private int width;
	private int height;
	public void init(int width, int height)
	{
		this.width = width;
		this.height = height;
		GameObject.setHeight(height);
		GameObject.setWidth(width);
		PlayerSquirrel Adam = PlayerSquirrel.createPlayer(25,25); //Player Squirrel
		memory = new GameObjectCollection(); // objects in the game
		memory.add(Adam);
		Nut nut = new Nut(600,20,30);
		memory.add(nut);
		memory.add(new Nut(30,10,30));
		memory.add(new Nut(200,50,30));
		memory.add(new Nut(300,700,30));
		
		NonPlayableSquirrel npc1 = new NonPlayableSquirrel(500,0);
		npc1.setStrategy(new NutStrategy(npc1,memory.getIterator()));
		memory.add(npc1);
		NonPlayableSquirrel npc2 = new NonPlayableSquirrel(0,500);
		npc2.setStrategy(new NutStrategy(npc2,memory.getIterator()));
		memory.add(npc2);
		NonPlayableSquirrel npc3 = new NonPlayableSquirrel(1000,1000);
		npc3.setStrategy(new NutStrategy(npc3,memory.getIterator()));
		memory.add(npc3);
		NonPlayableSquirrel npc4 = new NonPlayableSquirrel(500,1000);
		npc4.setStrategy(new NutStrategy(npc4,memory.getIterator()));
		memory.add(npc4);
		
		memory.add(new Tomato());
		memory.add(new Tomato());
		memory.add(new Bird());
		memory.add(new Bird());		
		maxNuts = nut.getCount() - 1;
	}
	// 'a' command accelerate
	public void accererate()
	{
		IIterator iterator = memory.getIterator();
		PlayerSquirrel Adam = (PlayerSquirrel)iterator.getNext();
		Adam.accSpd(1);
		this.setChanged();
		this.notifyObservers();
	}
	// 'b' command brake
	public void brake()
	{
		IIterator iterator = memory.getIterator();
		PlayerSquirrel Adam = (PlayerSquirrel)iterator.getNext();
		Adam.brk(1);
		this.setChanged();
		this.notifyObservers();
	}
	// 'l' command
	// change steering by -5
	public void leftTurn()
	{
		IIterator iterator = memory.getIterator();
		PlayerSquirrel Adam = (PlayerSquirrel)iterator.getNext();
		System.out.println("Turning Left");
		Adam.turn('l');
		this.setChanged();
		this.notifyObservers();
	}
	// 'r' command
	// change steering by +5
	public void rightTurn()
	{
		IIterator iterator = memory.getIterator();
		PlayerSquirrel Adam = (PlayerSquirrel)iterator.getNext();
		System.out.println("Turning Right");
		Adam.turn('r');
		this.setChanged();
		this.notifyObservers();
	}
	// 'c' command
	// 'collide' with another squirrel/bird

	public void squirrelCollide()
	{
		IIterator iterator = memory.getIterator();
		PlayerSquirrel Adam = (PlayerSquirrel)iterator.getNext();
		System.out.println("oW! -2hp");
		Adam.updateDamageLevel(2);

		checkDamage(); // checks speed of squirrel to see if consumes a life
		this.setChanged();
		this.notifyObservers();
	}
	// 'g' command
	public void birdCollide()
	{
		IIterator iterator = memory.getIterator();
		PlayerSquirrel Adam = (PlayerSquirrel)iterator.getNext();
		System.out.println("oW! -1hp");
		Adam.updateDamageLevel(1);
		checkDamage();
		this.setChanged();
		this.notifyObservers();
	}
	// 1-9 command 
	// compares the next nut from last nut reached
	// to the nut being passed in
	public void collideNut(int seqNum)
	{
		IIterator iterator = memory.getIterator();
		PlayerSquirrel Adam = (PlayerSquirrel)iterator.getNext();
		int nextNut = Adam.getLastNut()+1;
		if(seqNum == nextNut)
		{
			System.out.println("+1 Nut");
			Nut.incLastNutEaten();
			Adam.setLastNut(seqNum);
		} else if(seqNum > maxNuts)
			System.out.println("Nut #" + seqNum + " does not exist.");
		else
			System.out.println("You can't eat this nut");
		// if all the nuts on the map are consumed
		boolean getAllNuts = Adam.getLastNut() == maxNuts;
		if(getAllNuts) {
			System.out.println("Game over, you win! Total time: #" + clock);
			System.exit(0);
		}
		this.setChanged();
		this.notifyObservers();
	}
	// 'e' command
	// tomato is used up and restores energy of player squirrel
	public void consumeTomatoe()
	{
		int nutrients = 0;
		IIterator findTomatoes = memory.getIterator();
		PlayerSquirrel Adam = (PlayerSquirrel)findTomatoes.getNext();
		Object collectionObject;
		while(findTomatoes.hasNext())
		{
			collectionObject = findTomatoes.getNext();
			if(collectionObject instanceof Tomato)
			{
				
				Tomato tomato = (Tomato)collectionObject;
				if(tomato.getNutrition() > 0)
				{
					nutrients = tomato.getNutrition();
					tomato.usedTomato();
					memory.add(new Tomato());
					break;
				}
			}
		}
		Adam.consumeTomato(nutrients);
		if(nutrients == 0)
			System.out.println("There must be no tomatoes. :(");
		this.setChanged();
		this.notifyObservers();
	}
	// 't' command
	// moves then updates header with steering
	// all birds heading is updated then they are moved
	// all squirrels need to reapply attack strategy if
	// not chasing nut
	public void tick()
	{
		IIterator iterator = memory.getIterator();
		PlayerSquirrel Adam = (PlayerSquirrel)iterator.getNext();
		if(Adam.getEnergyLevel() > 0) {
			Adam.move();
			Adam.updateHeader(); 
			Adam.consumeEnergy();
		}

		Object collectionObject;
		while(iterator.hasNext())
		{

			collectionObject = iterator.getNext();
			if(collectionObject instanceof MovableGameObject)
			{
				if(collectionObject instanceof Bird)
				{
					Bird bird = (Bird)collectionObject;
					bird.birdChangeHeading();
					bird.move();
				} else if (collectionObject instanceof NonPlayableSquirrel)
				{
					NonPlayableSquirrel npcSquirrel = (NonPlayableSquirrel)collectionObject;
					npcSquirrel.invokeStrategy();
					npcSquirrel.move();
				}
			}
		} // end while loop
		clock++;
		this.setChanged();
		this.notifyObservers();
	} // end tick
	
	// 'd' command
	// display lives, clock, highest nut
	// energy level, current damage
	public void display()
	{
		IIterator findMovable = memory.getIterator();
		PlayerSquirrel Adam = (PlayerSquirrel)findMovable.getNext();
		System.out.println("Clock: " + clock);
		System.out.println("Lives: " + lives);
		System.out.println("Last Nut: " + Adam.getLastNut());
		System.out.println("Energy Level: " + Adam.getEnergyLevel());
		System.out.println("Current Damage: " + Adam.getDamageLevel());
	}
	// 'm' command
	// output a 'map'
	public void map()
	{
		//System.out.println(Adam); player squirrel first in collection object
		IIterator findMovable = memory.getIterator();
		Object collectionObject;
		while(findMovable.hasNext())
		{
			collectionObject = findMovable.getNext();
			System.out.println(collectionObject); // should use overrided toString methods
		}
	}
	// 'x' command exit
	// must confirm intent
	public void exit()
	{
		//System.out.println("Are you sure you want to exit? [Y/S]");
		confirmExit = true;
		//System.exit(0);
	}
	// 'y' command
	// confirms to exiting
	public void yes()
	{
		if(confirmExit) {
			//System.out.println("goodbye");
			System.exit(0);
		}
	}
	// 'n' command
	// continues to play
	public void no()
	{
		confirmExit = false;
		System.out.println("Game resumed");
	}
	// checks max speed to see if a life has been lost
	// ends game if max speed is 0 and no lives are left
	public void checkDamage()
	{
		IIterator iterator = memory.getIterator();
		PlayerSquirrel Adam = (PlayerSquirrel)iterator.getNext();
		int adamSpeed = Adam.getMaxSpeed();
		if(adamSpeed == 0)
		{
			if(lives >= 0) {
			Adam.resetMaxSpeedAndDamage();
			lives--;
			init(width,height); // reinitializes game world when you die
			}
		}
		if(adamSpeed == 0 && lives == 0) {
			System.out.println("Game Over! You lost!");
			//force quit when game is lost
			System.exit(0);
		}
	}
	public int getClock() { return clock; }
	public int getLives() {return lives;}
	
	public PlayerSquirrel getPlayer() {
		return (PlayerSquirrel)memory.getIterator().getNext();
	}
	// changes strategies of all npc squirrels
	public void changeStrategies()
	{
		IIterator findMovable = memory.getIterator();
		PlayerSquirrel playerSquirrel = (PlayerSquirrel)findMovable.getNext();
		while(findMovable.hasNext())
		{
			Object collectionObject = findMovable.getNext();
			if (collectionObject instanceof NonPlayableSquirrel)
				{
					NonPlayableSquirrel npc = (NonPlayableSquirrel)collectionObject;
					String strategy = npc.strategyString();
					if(strategy.equals("Attack Strategy"))
						npc.setStrategy(new NutStrategy(npc,memory.getIterator()));
					else
						npc.setStrategy(new AttackStrategy(npc,playerSquirrel));
				}
		}
	}
	public void changeSound()
	{
		soundOn = !soundOn;
		this.setChanged();
		this.notifyObservers();
	}
	public boolean getSound() {return soundOn;}
	public IIterator getIterator()
	{
		return memory.getIterator();
	}
}