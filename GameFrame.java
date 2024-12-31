//importing packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GameFrame
{
  boolean playAi = false;//boolean to enable AI if 2 player mode is selected
  void run()//procedure to set up window and game
  {
    Draw draw = new Draw();//making instance of Draw class called draw
    Borders border = new Borders();//making instance of Borders class called border
    ////////////////////////////////////////////////////
	JFrame frame = new JFrame("Module 3"); //making instance of JFrame class called frame (game window)
    draw.setPreferredSize(new Dimension(600,600));//setting size of draw
	frame.add(draw);//adding draw to frame
    frame.setSize(600,600);//setting size of window
    frame.setBackground(Color.black);//setting background colour of window
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//default close operation
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);//maximizing window
	////////////////////////////////////////////////////
	JFrame menu = new JFrame("Menu");//making instance of JFrame class called menu (menu window)
	menu.setSize(640,480);//setting size of menu
	menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//default close operation
	JPanel menu2 = new JPanel();//panel for menu
	menu2.setLayout(new GridBagLayout());//layout of panel
	JButton[] buttons = new JButton[2];//array of buttons
    buttons[0] = new JButton("Single Player");//first button is for if the user wants to player Single Player mode
    buttons[1] = new JButton("2 Players");//second button is for if the user wants to play 2 Players
	GridBagConstraints constraints = new GridBagConstraints();//creating instance of GridBagConstraints class called constraints
    constraints.gridx = 0;//setting x contraints
    constraints.gridy = 0;//setting y constraints
    constraints.insets = new Insets(0,0,10,10);//insets
    menu2.add(buttons[0],constraints);//adding first button and contraints to menu panel
    constraints.gridx = 0;//setting x contraitns
    constraints.gridy = 1;//setting y constraints
    constraints.insets = new Insets(0,0,10,10);//insets
    menu2.add(buttons[1],constraints);//adding second button and constraints to menu panel
    menu.add(menu2);//adding menu panel to menu window
	menu.setVisible(true);//setting menu window to be visible
	buttons[0].addActionListener(new ActionListener()//actionlistener for first button
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			menu.setVisible(false);//set menu window to be not visible
			frame.setVisible(true);//set game window to be visible
            playAi = true;//setting playAi to true
			draw.clicked = true;//setting clicked to true
		}
	});
	buttons[1].addActionListener(new ActionListener() 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			menu.setVisible(false);//set menu window to be not visible
			frame.setVisible(true);//set game window to be visible
			draw.clicked = true;//setting playAi to true
		}
	});
    while(true)//loop to keep calling update to check if there is a collision
    {
      
      draw.update(playAi);//calling draw.update with playAi perameter
      if (draw.win == true)//checking if someone won
      {
    	  draw.win = false;//setting win variable back to false (so doesn't repeat)
    	  draw.clicked = false;//setting clicked variable to false (so game stops)
    	  frame.setVisible(false);//set game window to be not visible
    	  menu.setVisible(true);//set menu window to be visible
      }   
	  try
      {
        Thread.sleep(10);//delay
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    
  }
}