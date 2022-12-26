/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: HealingPotion.java                                            *
* PURPOSE:  represents potion that does healing                            *
***************************************************************************/
package model;

import java.util.*;

public class HealingPotion extends Potion
{
	public HealingPotion()
	{
		super();
	}
	
	public HealingPotion(String inName, int inMinEffect, int inMaxEffect, int inCost)
	{
		super(inName, inMinEffect, inMaxEffect, inCost);
	}
	
	public HealingPotion(HealingPotion inHealingPotion)
    {
		super(inHealingPotion);
    }
	
	@Override
	public String toString()
	{
		String str;
		str = ("This " + name + " is a healing potion that restores " + minEffect + " to " + maxEffect + " points of your health. " +
			   "It costs " + cost + " Gold.");
		return str;
	}
	
	
	@Override
	public int calcEffect()
	{
		Random rand = new Random();
		double realHealing;
		int intHealing;
		realHealing = minEffect + (rand.nextDouble() * (maxEffect - minEffect)); // gets random number between min and max healing
		intHealing = (int) Math.round(realHealing); // rounds to integer
		return intHealing;
	}
	
	@Override
	public int calcHealingMultiplier()
	{
		return 1; // potion only heals.
	}
	
	@Override
	public int calcDamageMultiplier()
	{
		return 0; // potion does no damage
	}
	
	@Override
	public Item clone()
    {
        HealingPotion healingPotionClone = new HealingPotion(this);
        return healingPotionClone;
    }
	
	@Override
	public boolean equals(Object inObj)
    {
        boolean same = false;
        if (inObj instanceof HealingPotion)
        {
            HealingPotion healingPotion = (HealingPotion)inObj;
			same = super.equals(healingPotion);
		}
        return same;
    }
}