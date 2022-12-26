/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: DamagingPotion.java                                           *
* PURPOSE: represents potion that does damage                              *
***************************************************************************/
package model;

import java.util.*;

public class DamagingPotion extends Potion
{
	public DamagingPotion()
	{
		super();
	}
	
	public DamagingPotion(String inName, int inMinEffect, int inMaxEffect, int inCost)
	{
		super(inName, inMinEffect, inMaxEffect, inCost);
	}
	
	public DamagingPotion(DamagingPotion inDamagingPotion)
    {
		super(inDamagingPotion);
    }
	
	@Override
	public String toString()
	{
		String str;
		str = ("This " + name + " is a damaging potion that reduces the enemy's health by " + minEffect + " to " + maxEffect +
			  ". It costs " + cost + " Gold.");
		return str;
	}

	@Override
	public int calcEffect()
	{
		Random rand = new Random();
		double realDamage;
		int intDamage;
		realDamage = minEffect + (rand.nextDouble() * (maxEffect - minEffect)); // gets random number between min and max potion damage
		intDamage = (int) Math.round(realDamage); // rounds to int
		return intDamage;
	}
	
	@Override
	public int calcHealingMultiplier()
	{
		return 0;  // potion does no healing
	}
	
	@Override
	public int calcDamageMultiplier()
	{
		return 1; // potion only does damage.
	}
	
	public Item clone()
    {
        DamagingPotion damagingPotionClone = new DamagingPotion(this);
        return damagingPotionClone;
    }
	
	@Override
	public boolean equals(Object inObj)
    {
        boolean same = false;
        if (inObj instanceof DamagingPotion)
        {
            DamagingPotion damagingPotion = (DamagingPotion)inObj;
			same = super.equals(damagingPotion);
		}
        return same;
    }
	
}