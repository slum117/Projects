package com.mycompany.a3;
import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
public interface IDrawable {
	//used to draw objects
	public abstract void draw(Graphics g, Point pCmpRelPrnt);
}
