/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: Armour.java                                                   *
* PURPOSE: represents armour that the player can wear to absorb damage     *
***************************************************************************/
package model;

import java.util.*;

public class Armour implements Item
{
	private String name;
	private int minDefence;
	private int maxDefence;
	private int cost;
	private String material;
	
	public Armour()
	{
		name = "";
		minDefence = 0;
		maxDefence = 0;
		cost = 0;
		material = "";
	}
	
	public Armour(String inName, int inMinDefence, int inMaxDefence, int inCost, String inMaterial)
	{
		name = inName;
		minDefence = inMinDefence;
		maxDefence = inMaxDefence;
		cost = inCost;
		material = inMaterial;
	}
	
	public Armour(Armour inArmour)
    {
        name = inArmour.getName();
		minDefence = inArmour.getMinDefence();
		maxDefence = inArmour.getMaxDefence();
		cost = inArmour.getCost();
		material = inArmour.getMaterial();
    }
	
	@Override
	public String getName()
	{
		return name;
	}
	
	@Override
	public int getCost()
	{
		return cost;
	}
	
	private int getMinDefence()
	{
		return minDefence;
	}
	
	private int getMaxDefence()
	{
		return maxDefence;
	}
	
	private String getMaterial()
	{
		return material;
	}
	
	@Override
	public String toString()
	{
		String str;
		str = name + " is made of " + material + " and absorbs " + minDefence + " to " + maxDefence + " damage from the enemy.";
		return str;
	}
	
	@Override
	public int calcEffect()
	{
		Random rand = new Random();
		double realDefence;
		int intDefence;
		realDefence = minDefence + (rand.nextDouble() * (maxDefence - minDefence)); // gets random number between min and max defence
		intDefence = (int) Math.round(realDefence); // rounds to integer
		return intDefence;
	}
	
	@Override
	public Item clone()
    {
        Armour armourClone = new Armour(this);
        return armourClone;
    }
	
	@Override
	public boolean equals(Object inObj)
    {
        boolean same = false;
        if (inObj instanceof Armour)
        {
            Armour armour = (Armour)inObj;
			same = (name.equals(armour.getName())) &&
                    (minDefence == armour.getMinDefence()) &&
					(maxDefence == armour.getMaxDefence()) &&
					(cost ==  armour.getCost()) &&
					(material.equals(armour.getMaterial()));
		}
        return same;
    }
}
	