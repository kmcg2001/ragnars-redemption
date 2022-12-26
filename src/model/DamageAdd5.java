/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: DamageAdd5.java                                               *
* PURPOSE: represents a Damage + 5 enchantment type for weapon             *
***************************************************************************/
package model;

public class DamageAdd5 extends Enchantment
{
	private String enchantmentName = "Damage +5";
	private int enchantmentCost = 10;
	private String enchantmentEffect = "Attacks do 5 more damage";
	
	public DamageAdd5()
	{
		super(new Weapon());
	}
		
	public DamageAdd5(DamageDealer next)
	{
		super(next);
	}
	
	@Override
	public int calcEffect()
	{
		return next.calcEffect() + 5;
	}
	
	@Override
	public String getEnchantmentName()
	{
		return enchantmentName;
	}

	
	@Override
	public int getEnchantmentCost()
	{
		return enchantmentCost;
	}
	
	@Override
	public String getEnchantmentEffect()
	{
		return enchantmentEffect;
	}
	
	@Override
	public String toString()
	{
		return next.toString() + "\n*" + enchantmentName + ": " + enchantmentEffect;
	}
	
}