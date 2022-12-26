/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: FireDamage.java                                               *
* PURPOSE:  represents fire damage enchantment for weapon                  *
***************************************************************************/
package model;

import java.util.*;

public class FireDamage extends Enchantment
{
	private String enchantmentName = "Fire Damage";
	private int enchantmentCost = 20;
	private String enchantmentEffect = "Attacks do 5 to 10 more damage";
	
	public FireDamage()
	{
		super(new Weapon());
	}
	
	public FireDamage(DamageDealer next)
	{
		super(next);
	}
	
	// effect
	@Override
	public int calcEffect()
	{
		Random rand = new Random();
		double minFireDamage = 5;
		double maxFireDamage = 10;
		double realFireDamage;
		int intFireDamage;
		realFireDamage = minFireDamage + (rand.nextDouble() * (maxFireDamage - minFireDamage));
		intFireDamage = (int) Math.round(realFireDamage);
		System.out.println("hellooo");
		return next.calcEffect() + intFireDamage;
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