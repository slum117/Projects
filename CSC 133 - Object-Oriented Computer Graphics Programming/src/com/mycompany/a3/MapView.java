package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.IIterator;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;

public class MapView extends Container implements Observer {
	IIterator iterator;
	public MapView()
	{
		this.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.BLUE));
	}
	@Override
	public void update(Observable observable, Object data) {

		//repaint();
		GameWorld gw = (GameWorld)observable;
		iterator = gw.getIterator();
		repaint();
		
	}
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		// parent point
		Point pCmpRelPrnt = new Point(getX(),getY());
		if(iterator != null)
			while(iterator.hasNext()) {
				GameObject x = (GameObject)iterator.getNext();
				x.draw(g, pCmpRelPrnt);
			}
		//g.setColor(ColorUtil.rgb(30, 40, 30));
		//g.fillRect(getX(), getY(), 90, 100);
	}

}
