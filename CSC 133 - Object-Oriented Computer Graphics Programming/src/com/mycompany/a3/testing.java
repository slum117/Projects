package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
import java.lang.String;
import com.codename1.charts.models.Point;

import java.util.*;
public class testing {
	public static void main(String[] args)
	{
		//System.out.println("Hello from testing!");
		
		Random rand = new Random();
		float f = rand.nextInt(1001) + rand.nextFloat();
		//System.out.println(rand.nextInt(1001) + rand.nextFloat());
		
		//GameObject tstObj = new GameObject();
		//Squirrel John = new Squirrel(1,0,10);
		Nut nut = new Nut(1,0,7);
		Tomato tomatoe = new Tomato();
		Bird birb = new Bird();
		//System.out.println(nut + "\n" + tomatoe + "\n" + birb + "\n" + John);
		ArrayList<GameObject> x = new ArrayList<GameObject>();
		x.add(new Tomato());
		System.out.println(x.get(0));
		((Tomato)x.get(0)).usedTomato();
		System.out.println(x.get(0));
		//System.out.println(birb instanceof MovableGameObject);
	}
}
