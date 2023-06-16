package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command{
	public AboutCommand(String x,GameWorld target)
	{
		super(x);
	}
	public void actionPerformed(ActionEvent evt)
	{
		Command cOK = new Command("OK");
		Dialog.show("About","Steven Lum\nCSC133\nBad Squirrel V2", cOK);
	}
}