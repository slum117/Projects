package com.mycompany.a3;

import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.AboutCommand;
import com.mycompany.a3.AccelerateCommand;
import com.mycompany.a3.BrakeCommand;
import com.mycompany.a3.ChangeStrategiesCommand;
import com.mycompany.a3.CollideBirdCommand;
import com.mycompany.a3.CollideNutCommand;
import com.mycompany.a3.CollideSquirrelCommand;
import com.mycompany.a3.CollideTomatoCommand;
import com.mycompany.a3.ExitCommand;
import com.mycompany.a3.HelpCommand;
import com.mycompany.a3.LeftTurnCommand;
import com.mycompany.a3.RightTurnCommand;
import com.mycompany.a3.ScoreView;
import com.mycompany.a3.SoundCommand;
import com.mycompany.a3.TickCommand;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import java.lang.String;

public class Game extends Form implements Runnable {
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	public Game()
	{
		gw = new GameWorld();
		
		// creation of containers
		mv = new MapView();
		sv = new ScoreView();
		Container leftSide = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Container rightSide = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Container bottomSide = new Container(new BoxLayout(BoxLayout.X_AXIS));
		bottomSide.setLayout(new FlowLayout(Component.CENTER));
		
		// creating commands
		///leftSide.add(new Label("HELLO!"));
		Toolbar tb = new Toolbar();
		AccelerateCommand accelerate = new AccelerateCommand("Accelerate",gw);
		BrakeCommand brake = new BrakeCommand("Brake",gw);
		LeftTurnCommand leftTurn = new LeftTurnCommand("Left Turn",gw);
		RightTurnCommand rightTurn = new RightTurnCommand("Right Turn",gw);
		CollideSquirrelCommand collideSquirrel = new CollideSquirrelCommand("Collide with NPC",gw);
		CollideNutCommand collideNut = new CollideNutCommand("Collide with Nut",gw);
		CollideTomatoCommand collideTomato = new CollideTomatoCommand("Collide with Tomato",gw);
		CollideBirdCommand collideBird = new CollideBirdCommand("Collide with Bird",gw);
		
		TickCommand tick = new TickCommand("Tick",gw);
		ChangeStrategiesCommand changeStrategies = new ChangeStrategiesCommand("Change Strategies",gw);
		HelpCommand help = new HelpCommand("Help");
		AboutCommand about = new AboutCommand("About",gw);
		ExitCommand exit = new ExitCommand("Exit",gw);
		SoundCommand sound = new SoundCommand("Sound: ",gw, this);
		
		Button accelerateButton = new Button(accelerate);
		Button brakeButton = new Button(brake);
		Button leftTurnButton = new Button(leftTurn);
		Button rightTurnButton = new Button(rightTurn);
		Button hitNpcButton = new Button(collideSquirrel);
		Button getNutButton = new Button(collideNut);
		Button getTomatoButton = new Button(collideTomato);
		Button hitBirdButton = new Button(collideBird);
		Button tickButton = new Button(tick);
		Button strategyButton = new Button(changeStrategies);
		CheckBox soundCheck = new CheckBox("Sound: ");
		soundCheck.getAllStyles().setBgTransparency(255);
		soundCheck.getAllStyles().setBgColor(ColorUtil.MAGENTA);
		soundCheck.setCommand(sound);
		soundCheck.isSelected();
		//set transparency
		accelerateButton.getUnselectedStyle().setBgTransparency(255);
		brakeButton.getUnselectedStyle().setBgTransparency(255);
		leftTurnButton.getUnselectedStyle().setBgTransparency(255);
		rightTurnButton.getUnselectedStyle().setBgTransparency(255);
		hitNpcButton.getUnselectedStyle().setBgTransparency(255);
		getNutButton.getUnselectedStyle().setBgTransparency(255);
		getTomatoButton.getUnselectedStyle().setBgTransparency(255);
		hitBirdButton.getUnselectedStyle().setBgTransparency(255);
		tickButton.getUnselectedStyle().setBgTransparency(255);
		strategyButton.getUnselectedStyle().setBgTransparency(255);
		// set color
		accelerateButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		brakeButton.getUnselectedStyle().setBgColor(ColorUtil.LTGRAY);
		leftTurnButton.getUnselectedStyle().setBgColor(ColorUtil.LTGRAY);
		rightTurnButton.getUnselectedStyle().setBgColor(ColorUtil.LTGRAY);
		hitNpcButton.getUnselectedStyle().setBgColor(ColorUtil.LTGRAY);
		getNutButton.getUnselectedStyle().setBgColor(ColorUtil.LTGRAY);
		getTomatoButton.getUnselectedStyle().setBgColor(ColorUtil.LTGRAY);
		hitBirdButton.getUnselectedStyle().setBgColor(ColorUtil.LTGRAY);
		tickButton.getUnselectedStyle().setBgColor(ColorUtil.LTGRAY);
		strategyButton.getUnselectedStyle().setBgColor(ColorUtil.LTGRAY);
		
		leftSide.add(accelerateButton);
		leftSide.add(leftTurnButton);
		leftSide.add(strategyButton);
		rightSide.add(brakeButton);
		rightSide.add(rightTurnButton);
		bottomSide.add(hitNpcButton);
		bottomSide.add(getNutButton);
		bottomSide.add(getTomatoButton);
		bottomSide.add(hitBirdButton);
		bottomSide.add(tickButton);
		
		this.setToolbar(tb);
		tb.addCommandToSideMenu(accelerate);
		tb.addCommandToRightBar(help);
		tb.addCommandToSideMenu(about);
		tb.addCommandToSideMenu(sound);
		tb.addCommandToSideMenu(exit);
		tb.addComponentToSideMenu(soundCheck);
		
		addKeyListener('a', accelerate);
		addKeyListener('b', brake);
		addKeyListener('l',leftTurn);
		addKeyListener('r',rightTurn);
		addKeyListener('e',collideTomato);
		addKeyListener('g', collideBird);
		addKeyListener('t', tick);
		
		// adding things to the form
		setLayout(new BorderLayout());
		add(BorderLayout.NORTH,sv);
		add(BorderLayout.CENTER,mv);
		add(BorderLayout.WEST,leftSide);
		add(BorderLayout.EAST,rightSide);
		add(BorderLayout.SOUTH,bottomSide);
		
		setTitle("Bad Squirrel");
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		//timer
		UITimer timer = new UITimer(this);
		timer.schedule(20, true, this);
		
		// find out how to set the constraints of world
		// from game class
		int width = mv.getWidth();
		int height = mv.getHeight();
		// temp sets widthxheight to 1000x1000
		width = 1000;
		height = 1000;
		//System.out.print(width);
		//System.out.println("x"+height);
		gw.init(width,height);
		this.show();
	}
	public void setCheckStatus(boolean val)
	{
		sv.changeSound(val);
	}
	public void run()
	{
		gw.tick();
	}
}
