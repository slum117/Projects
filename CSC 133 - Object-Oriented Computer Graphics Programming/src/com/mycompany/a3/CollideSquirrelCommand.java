package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CollideSquirrelCommand extends Command{
	private GameWorld target;
	public CollideSquirrelCommand(String x,GameWorld target)
	{
		super(x);
		this.target = target;
	}
	public void actionPerformed(ActionEvent evt)
	{
		target.squirrelCollide();
	}
}