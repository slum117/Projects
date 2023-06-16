package com.mycompany.a3;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class CollideNutCommand extends Command{
	private GameWorld target;
	public CollideNutCommand(String x,GameWorld target)
	{
		super(x);
		this.target = target;
	}
	public void actionPerformed(ActionEvent evt)
	{
		//need to open a dialog box to get sequence number
		TextField tf = new TextField();
		Command cOK = new Command("OK");
		Command cCancel = new Command("Cancel");
		Command[] cList = new Command[] {cOK,cCancel};
		Command c = Dialog.show("Enter the nut number:", tf, cList);
		int seqNum;
		if(c==cOK) {
			seqNum = Integer.parseInt(tf.getText());
			target.collideNut(seqNum);
		}
	}
}