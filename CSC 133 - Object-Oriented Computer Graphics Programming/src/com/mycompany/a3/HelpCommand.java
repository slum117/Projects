package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class HelpCommand extends Command{
	public HelpCommand(String x)
	{
		super(x);
	}
	public void actionPerformed(ActionEvent evt)
	{
		//dialog box with pressable keys
		Command cOK = new Command ("OK");
		Dialog.show("Help", "KeyBindings:\na-accelerate\nb-brake\nl-left turn\n"
				+ "r-right turn\ne-eat tomato\ng-hit bird\nt-tick", cOK);
	}
}