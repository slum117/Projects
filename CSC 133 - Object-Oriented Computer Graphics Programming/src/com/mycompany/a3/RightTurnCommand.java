package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class RightTurnCommand extends Command{
	private GameWorld target;
	public RightTurnCommand(String x,GameWorld target)
	{
		super(x);
		this.target = target;
	}
	public void actionPerformed(ActionEvent evt)
	{
		target.rightTurn();
	}
}