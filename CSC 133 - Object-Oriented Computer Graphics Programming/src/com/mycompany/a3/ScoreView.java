package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

public class ScoreView extends Container implements Observer {
	
	private Label timeLabel;
	private Label lifeLabel;
	private Label playerNutLabel;
	private Label energyLabel;
	private Label damageLabel;
	private Label soundLabel;
	private Label onOffLabel;
	public ScoreView()
	{
		onOffLabel = new Label("OFF");
		timeLabel = new Label("Time: " + 0);
		lifeLabel = new Label("Lives: " + 3); // always start with 3 lives
		playerNutLabel = new Label("Player Last Nut Reached: " + 1);
		energyLabel = new Label("Player Energy Level: " + 20);
		damageLabel = new Label("Player Damage Level: " + 0);
		soundLabel = new Label("Sound: ");
		timeLabel.getAllStyles().setPadding(Component.RIGHT, 2);
		lifeLabel.getAllStyles().setPadding(Component.RIGHT, 2);
		lifeLabel.getAllStyles().setPadding(Component.LEFT, 2);
		energyLabel.getAllStyles().setPadding(Component.RIGHT, 2);
		playerNutLabel.getAllStyles().setPadding(Component.RIGHT, 2);
		damageLabel.getAllStyles().setPadding(Component.RIGHT, 2);
		soundLabel.getAllStyles().setPadding(Component.RIGHT, 2);
		this.add(timeLabel);
		this.add(lifeLabel);
		this.add(playerNutLabel);
		this.add(energyLabel);
		this.add(damageLabel);
		this.add(soundLabel);
		add(onOffLabel);
		this.setLayout(new FlowLayout(Component.CENTER));
	}
	@Override
	public void update(Observable observable, Object data) {
		GameWorld gw = (GameWorld)observable;
		
		// TODO Auto-generated method stub
		timeLabel.setText("Time: " + gw.getClock());
		lifeLabel.setText("Lives: " + gw.getLives());
		playerNutLabel.setText("Player Last Nut Reached: " + gw.getPlayer().getLastNut());
		energyLabel.setText("Player Energy Level: " + gw.getPlayer().getEnergyLevel());
		damageLabel.setText("Player Damage Level: " + gw.getPlayer().getDamageLevel());
	}
	public void changeSound(boolean val)
	{
		if(val)
			onOffLabel.setText("ON");
		else
			onOffLabel.setText("OFF");
	}

}
