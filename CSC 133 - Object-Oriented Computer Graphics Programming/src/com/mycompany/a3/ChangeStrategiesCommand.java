package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ChangeStrategiesCommand extends Command{
	private GameWorld target;
	public ChangeStrategiesCommand (String x,GameWorld target)
	{
		super(x);
		this.target = target;
	}
	public void actionPerformed(ActionEvent evt)
	{
		target.changeStrategies();
	}
}