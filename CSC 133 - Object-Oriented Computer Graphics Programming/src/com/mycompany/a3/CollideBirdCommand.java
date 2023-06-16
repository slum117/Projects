package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CollideBirdCommand extends Command{
	private GameWorld target;
	public CollideBirdCommand(String x,GameWorld target)
	{
		super(x);
		this.target = target;
	}
	public void actionPerformed(ActionEvent evt)
	{
		target.birdCollide();
	}
}