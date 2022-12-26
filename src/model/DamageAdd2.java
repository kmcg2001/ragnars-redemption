/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: DamageAdd2.java                                               *
* PURPOSE: represents a Damage + 2 enchantment type for a weapon           *
***************************************************************************/
package model;

public class DamageAdd2 extends Enchantment
{
	private String enchantmentName = "Damage +2";
	private int enchantmentCost = 5;
	private String enchantmentEffect = "Attacks do 2 more damage";
	
	public DamageAdd2()
	{
		super(new Weapon());
	}
	
	public DamageAdd2(DamageDealer next)
	{
		super(next);
	}
	
	@Override
	public int calcEffect()
	{
		return next.calcEffect() + 2;
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