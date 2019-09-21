package project;

import java.awt.Color;
import game.Direction;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import naturesimulator.Action.Type;
import ui.GridPanel;
/**
 * Class that allows plants are created  
 * implementing some methods which plants should have 
 * 
 * @author Ali BATIR
 */
public class Plant extends Creature {

	//max_health represents which plants can have the value of maximum health
	private static final double MAX_HEALTH=1.0;
	/**
	 * Creates a new Plant instance
	 * @param X the coordinate of location of creature along the width on the grid 
	 * @param Y the coordinate of location of creature along the height on the grid
	 */
	public Plant(int x,int y)
	{
		super(x,y);
		setHealth(0.5); //set the value of plants' initial health 
	}
	/**
	 * getter the value of MAX_HEALTH
	 * @return the value of plant's maximum health
	 */
	public static double getMaxHealth() {
		return MAX_HEALTH;
	}
	/**
     * Via this method,the plant will decide on which action to perform depending on the information about its environment. 
     * @param information representing the information a plant has about its surroundings
     * @return action the action chosen by the plant
     */
	@Override
	public Action chooseAction(LocalInformation information) 
{    	//direction is equal to one of freeDirection spaces
		Direction direction=LocalInformation.getRandomDirection(information.getFreeDirections());
	     //rules for reproduce 
		if(getHealth()>=0.75 && direction!=null) 
	      {
					return new Action(Type.REPRODUCE,direction);
		   }
       //if reproduce is not possible then the plant should choose to stay
		return new Action(Type.STAY);
	}
	/**
	 * it provides that all plants can be visualized on the UI
	 * @param panel the properties of Grid 
	 */
@Override
	public void draw(GridPanel panel)
	{   //vary the color of a creature according to its remaining health
	    //healthier plants are darker green
		
		   if(getHealth()<=0.1) 
		   {
		   panel.drawSquare(getX(),getY(),new Color(102,255,102)); 
		   }else if(getHealth()>0.1 && getHealth()<=0.3)
		   {
			   panel.drawSquare(getX(),getY(),new Color(0,255,21));   
		   }else if(getHealth()>0.3 && getHealth()<=0.5)
		   {
			   panel.drawSquare(getX(),getY(),new Color(0,204,0));   
		   }else if(getHealth()>0.5 && getHealth()<=0.7)
		   {
			   panel.drawSquare(getX(),getY(),new Color(0,153,0));   
		   }else if(getHealth()>0.7 && getHealth()<=MAX_HEALTH)
		   {
			   panel.drawSquare(getX(),getY(),new Color(0,102,0));   
		   }
		}
 
 /**
 * Plants can stay at the same space
 */
	@Override
	public void stay() {
		//increasing the Plant's health by 0.05
		setHealth(getHealth()+0.05);
	}
	/**
	 * Creating a new plant of the same type on an adjacent empty space
	 * @param direction representing the empty space where surroundings of a plant
	 * @return a new plant
	 */
	@Override
	public Creature reproduce(Direction direction) {
		Plant newPlant=null;
		if(Direction.RIGHT==direction) {
			newPlant=new Plant(getX()+1,getY());
			
		}else if(Direction.LEFT==direction) {
			
			newPlant=new Plant(getX()-1,getY());
	
		}else if(Direction.UP==direction) {
			
			newPlant=new Plant(getX(),getY()-1);
		
		}else if(Direction.DOWN==direction) {
			
			newPlant=new Plant(getX(),getY()+1);
			
		}
		newPlant.setHealth(getHealth()*10/100);
		setHealth(getHealth()*70/100);
		return newPlant;
}/**
	 * Changing  the position of the creature 
	 * this method is not implemented by Plants
	 * @param direction modifying its position to the direction which is an adjacent empty cell
	 */
	@Override
	public void move(Direction direction) {
		// plants do not support the action move
		
	}
	/**
	 * This method is not implemented by Plants
	 * Creatures can attack another adjacent creature,in which case it will kill(remove) it and move to its position
	 * @param attackedCreature the attacked Creature which should be Plant
	 */
	@Override
	public void attack(Creature attackedCreature) {
		// plants do not support the action attack
		
	}
	}




	


