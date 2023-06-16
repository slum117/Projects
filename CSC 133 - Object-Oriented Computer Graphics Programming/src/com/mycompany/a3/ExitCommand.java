package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class ExitCommand extends Command{
	private GameWorld target;
	public ExitCommand(String x,GameWorld target)
	{
		super(x);
		this.target = target;
	}
	public void actionPerformed(ActionEvent evt)
	{
		Command cYes = new Command("Yes");
		Command cNo = new Command("No");
		Command exitBox = Dialog.show("Exit?","Confirm Exit", cYes,cNo);
		target.exit();
		if(exitBox == cYes)
			target.yes();
		else
			target.no();
	}
}