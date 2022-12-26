/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: Potion.java                                                   *
* PURPOSE:  abstract class for potion                                      *
***************************************************************************/
package model;

public abstract class Potion implements Item
{
	protected String name;
	protected int minEffect; // maxEffect = max healing / max damage
	protected int maxEffect; // minEffect = min healing / max damage
	protected int cost;
	
	public Potion()
	{
		name = "";
		minEffect = 0;
		maxEffect = 0;
		cost = 0;
	}
	
	public Potion(String inName, int inMinEffect, int inMaxEffect, int inCost)
	{
		name = inName;
		minEffect = inMinEffect;
		maxEffect = inMaxEffect;
		cost = inCost;
	}
	
	public Potion(Potion inPotion)
    {
        name = inPotion.getName();
		minEffect = inPotion.getMinEffect();
		maxEffect = inPotion.getMaxEffect();
		cost = inPotion.getCost();
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
	
	private int getMinEffect()
	{
		return minEffect;
	}
	
	private int getMaxEffect()
	{
		return maxEffect;
	}
	
	public abstract String toString();
	
	
	public abstract int calcEffect();
	

	public abstract int calcHealingMultiplier();
	

	public abstract int calcDamageMultiplier();
	
	
	public abstract Item clone();
	
	@Override
	public boolean equals(Object inObj)
    {
        boolean same = false;
        if (inObj instanceof Potion)
        {
            Potion potion = (Potion)inObj;
			same = (name.equals(potion.getName())) &&
                    (minEffect == potion.getMinEffect()) &&
					(maxEffect == potion.getMaxEffect()) &&
					(cost ==  potion.getCost());
        }
        return same;
    }  
}
	