package project;

import game.Direction;
import game.Drawable;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import ui.GridPanel;

/**
 * Class that allows creatures are created  
 * Provides some methods which creatures should have 
 * 
 * @author Ali BATIR
 */
public abstract class Creature implements Drawable {
private double health;
private int X,Y;
/**
 * Setter the X coordinate
 * @param x the value of X coordinate
 */
public void setX(int x) {
	X = x;
}
/**
 * Setter the Y coordinate
 * @param y the value of Y coordinate
 */
public void setY(int y) {
	Y = y;
}
/**
 * Creates a new Creature instance
 * @param X the coordinate of location of creature along the width on the grid 
 * @param Y the coordinate of location of creature along the height on the grid
 */
public Creature(int X, int Y) {
	this.X=X;
	this.Y=Y;
}
/**
 * Change the value of health
 * @param health the new value of health
 */
public void setHealth(double health) {
	this.health = health;
	//controlling the value of health is bigger than MAX_HEALTH or not for Plant
	if(this instanceof Plant && health>=Plant.getMaxHealth())
	{ 
		this.health=Plant.getMaxHealth(); //health cannot be bigger than the value of Max_health
	//controlling the value of health is bigger than MAX_HEALTH or not for Herbivore
	}else if(this instanceof Herbivore && health>=Herbivore.getMaxHealth())
	{
		this.health=Herbivore.getMaxHealth(); //health cannot be bigger than the value of Max_health
	}
	
}

/**
 * getter the the value of health
 * @return health
 */
public double getHealth() {
	return health;
}

/**
 * getter the the value of X
 * @return X returning the coordinate of X
 */
public int getX() {
	return X;
}

/**
 * getter the value of Y
 * @return Y returning the coordinate of Y 
 */
public int getY() {
	return Y;
}



/**
 * it provides that all creatures can be visualized on the UI
 * Every subclass should implement this method to seen on the grid.
 * @param panel the property of Grid 
 */
@Override
public abstract void draw(GridPanel panel);


/**
 * Via this method,the creature will decide on which action to perform depending on the information about its environment. 
 * Every subclass should implement this method to define its action choice .
 * @param information representing the information a creature has about its surroundings
 * @return action the action chosen by the creature
 */
public abstract Action chooseAction(LocalInformation information);


/**
 * Creatures can stay at the same space
 */
public abstract void stay();
/**
 * Creating a new creature of the same type on an adjacent empty space
 * @param direction representing the empty space where surroundings of a creature
 * @return a new creature
 */
public abstract Creature reproduce(Direction direction);
/**
 * Changing  the position of the creature 
 * @param direction modifying its position to the direction which is an adjacent empty cell
 */
public abstract void move(Direction direction);
/**
 * Creatures can attack another adjacent creature,in which case it will kill(remove) it and move to its position
 * @param attackedCreature the attacked Creature which should be Plant
 */
public abstract void attack(Creature attackedCreature);


}
