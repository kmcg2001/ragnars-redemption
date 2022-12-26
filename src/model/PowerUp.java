/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: PowerUp.java                                                  *
* PURPOSE:  represents power up enchantment for weapon                     *
***************************************************************************/
package model;

public class PowerUp extends Enchantment
{
	private String enchantmentName = "Power Up";
	private int enchantmentCost = 10;
	private String enchantmentEffect = "Attack damage is multiplied by 1.1";
	
	
	public PowerUp()
	{
		super(new Weapon());
	}
	
	public PowerUp(DamageDealer next)
	{
		super(next);
	}
	
	// effect
	@Override
	public int calcEffect()
	{
		int intAttackDamage = (int) Math.round(next.calcEffect() * 1.1);
		return intAttackDamage;
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