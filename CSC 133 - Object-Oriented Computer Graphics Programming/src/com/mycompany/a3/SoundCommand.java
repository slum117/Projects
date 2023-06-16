package com.mycompany.a3;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SoundCommand extends Command {
	GameWorld target;
	Game myForm;
	public SoundCommand(String x, GameWorld target, Game altForm)
	{
		super(x);
		this.target = target;
		myForm = altForm;
	}
	public void actionPerformed(ActionEvent evt)
	{
		if(((CheckBox)evt.getComponent()).isSelected())
			myForm.setCheckStatus(true);
		else
			myForm.setCheckStatus(false);
		target.changeSound();
	}
}
