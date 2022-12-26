/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: Enchantment.java                                              *
* PURPOSE: enchantment interface                                           *
***************************************************************************/
package model;

public abstract class Enchantment implements DamageDealer
{
	protected DamageDealer next;
	
	public Enchantment(DamageDealer inNext)
	{
		next = inNext;
	}
	
	@Override 
	public String getName()
	{
		return next.getName();
	}
	
	@Override 
	public int getCost()
	{
		return next.getCost() + getEnchantmentCost();
	}
	
	@Override
	public String toString()
	{
		return next.toString();
	}
	
	@Override
	public int calcEffect()
	{
		return next.calcEffect();
	}
	
	@Override
	public Item clone()
	{
		return next.clone();
	}
	
	public abstract String getEnchantmentName();

	public abstract int getEnchantmentCost();

	public abstract String getEnchantmentEffect();
	
	
}