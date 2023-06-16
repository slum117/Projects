package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class AccelerateCommand extends Command{
	private GameWorld target;
	public AccelerateCommand(String x,GameWorld target)
	{
		super(x);
		this.target = target;
	}
	public void actionPerformed(ActionEvent evt)
	{
		target.accererate();
	}
}
