package project;

import java.awt.Color;
import java.util.ArrayList;
import game.Direction;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import naturesimulator.Action.Type;
import ui.GridPanel;
/**
 * Class that allows herbivores are created  
 * implementing some methods which herbivores should have 
 * 
 * @author Ali BATIR
 */
public class Herbivore extends Creature {
	//max_health represents which herbivores can have the value of maximum health
	  private static final double MAX_HEALTH=20.0;
      /**
	   * Creates a new Herbivore instance
	   * @param X the coordinate of location of creature along the width on the grid 
	   * @param Y the coordinate of location of creature along the height on the grid
	   */
	public Herbivore(int x,int y)
	{
		super(x,y);
		setHealth(10.0); //set the value of herbivores' initial health 
	}
	/**
	 * getter the value of MAX_HEALTH
	 * @return the value of herbivore's maximum health
	 */
    public static double getMaxHealth() {
		return MAX_HEALTH;
	}
    /**
     * Via this method,the herbivore will decide on which action to perform depending on the information about its environment. 
     * @param information representing the information a herbivore has about its surroundings
     * @return action the action chosen by the herbivore
     */
	@Override
	public Action chooseAction(LocalInformation information) {
		//direction is equal to one of freeDirection spaces
		Direction direction=LocalInformation.getRandomDirection(information.getFreeDirections());
	
		//REPRODUCE
		if(getHealth()==MAX_HEALTH && direction!=null)
	{
			return new Action(Type.REPRODUCE,direction);
			
	}else {
	//ATTACK
		//if reproduction is not possible then there are some creature around
		//All of plants which are around add the ArrayList
		 ArrayList<Direction> IstherePlant = new ArrayList<>();
		if(information.getCreatureDown() instanceof Plant)
		{
			IstherePlant.add(Direction.DOWN);
		}
		if(information.getCreatureUp() instanceof Plant)
		{
			IstherePlant.add(Direction.UP);
		}
		if(information.getCreatureRight() instanceof Plant)
		{
			IstherePlant.add(Direction.RIGHT);
	    }
		if(information.getCreatureLeft() instanceof Plant)
		{
			IstherePlant.add(Direction.LEFT);
			
		}
		//we control which there is plant around or not 
		if(IstherePlant.size()!=0)
		{
		Direction plantDirection=LocalInformation.getRandomDirection(IstherePlant);
		return new Action(Type.ATTACK,plantDirection);
		}
//		
		     // MOVE
			
			
		if(getHealth()>1.0 && direction !=null)
			{ 
				return new Action(Type.MOVE,direction);
			}
		
	//if no other action is possible then a herbivore should choose to stay  
	}
		return new Action(Type.STAY);
	
	}
	/**
	 * it provides that all herbivores can be visualized on the UI
	 * @param panel the properties of Grid 
	 */
	@Override
	public void draw(GridPanel panel)
	{
		//vary the color of a creature according to its remaining health
	    //healthier herbivores are darker red
		   if(getHealth()<=4.0)
		   {
		   
		panel.drawSquare(getX(),getY(),new Color(255,102,102));
		   }else if(getHealth()>4.0 && getHealth()<=8.0)
		   {
			   panel.drawSquare(getX(),getY(),new Color(255,51,51));   
		   }else if(getHealth()>8.0 && getHealth()<=12.0)
		   {
			   panel.drawSquare(getX(), getY(),new Color(255,0,0));   
		   }else if( getHealth()>12.0 &&  getHealth()<=16.0)
		   {
			   panel.drawSquare( getX(), getY(),new Color(204,0,0));   
		   }else if( getHealth()>16.0 &&  getHealth()<=MAX_HEALTH)
		   {
			   panel.drawSquare( getX(),getY(),new Color(153,0,0));   
		   }
	}
	/**
	 * Herbivores can stay at the same space
	 */
	@Override
	public void stay() {
		setHealth(getHealth()-0.1); //herbivores lose 0.1 health 
	}
	/**
	 * Creating a new herbivore of the same type on an adjacent empty space
	 * @param direction representing the empty space where surroundings of a herbivore
	 * @return a new herbivore
	 */
	@Override
	public Creature reproduce(Direction direction) {
		Herbivore newHerbivore=null;
		if(Direction.RIGHT==direction) {
			newHerbivore=new Herbivore(getX()+1,getY());
		}else if(Direction.LEFT==direction) {
			newHerbivore=new Herbivore(getX()-1,getY());
	    }else if(Direction.UP==direction) {
			newHerbivore=new Herbivore(getX(),getY()-1);
		}else if(Direction.DOWN==direction) {
			newHerbivore=new Herbivore(getX(),getY()+1);
		}
		newHerbivore.setHealth(getHealth()*20/100);
		setHealth(getHealth()*40/100);
		return newHerbivore;
	}
	/**
	 * Changing  the position of the herbivores 
	 * @param direction modifying its position to the direction which is an adjacent empty cell
	 */
	@Override
	public void move(Direction direction) {
      
		if(Direction.RIGHT==direction)
        {
        	setX(getX()+1);
        	
        }else if(Direction.LEFT==direction)
        {
        	setX(getX()-1);
       
        }else if(Direction.DOWN==direction)
        {
        	setY(getY()+1);
        	
        }else if(Direction.UP==direction) {
        	setY(getY()-1);
        
        }
		//moving makes the herbivores lose 1.0 health
		setHealth((getHealth()-1.0)); 
	}


	/**
	 * Herbivores can attack another adjacent plants,in which case it will kill(remove) it and move to its position
	 * @param attackedCreature the attacked Creature which should be Plant
	 */
	@Override
	public void attack(Creature attackedCreature) {
		setHealth(getHealth()+attackedCreature.getHealth());
	    attackedCreature.setHealth(0.0);
	    setX(attackedCreature.getX());
	    setY(attackedCreature.getY());
		
		
	}


	}

	
	

