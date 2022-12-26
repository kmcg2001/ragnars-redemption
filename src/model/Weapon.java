/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: Weapon.java                                                   *
* PURPOSE:  represents a weapon that player can use to attack              *
***************************************************************************/
package model;

import java.util.*;
public class Weapon implements DamageDealer
{
	private String name;
	private int minAttack;
	private int maxAttack;
	private int cost;
	private String type;
	private String damageType;
	
	public Weapon()
	{
		name = "";
		minAttack = 0;
		maxAttack = 0;
		cost = 0;
		type = "";
		damageType = "";
	}
	
	public Weapon(String inName, int inMinAttack, int inMaxAttack, int inCost, String inType, String inDamageType)
	{
		
		name = inName;
		minAttack = inMinAttack;
		maxAttack = inMaxAttack;
		cost = inCost;
		type = inType;
		damageType = inDamageType;
	}
	
	public Weapon(Weapon inWeapon)
    {
        name = inWeapon.getName();
		minAttack = inWeapon.getMinAttack();
		maxAttack = inWeapon.getMaxAttack();
		cost = inWeapon.getCost();
		type = inWeapon.getType();
		damageType = inWeapon.getDamageType();
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
	
	private int getMinAttack()
	{
		return minAttack;
	}
	
	private int getMaxAttack()
	{
		return maxAttack;
	}
	
	private String getType()
	{
		return type;
	}
	
	private String getDamageType()
	{
		return damageType;
	}
	
	@Override
	public String toString()
	{
		String str;
		str = "The " + name + " is a " + damageType + " " + type + " that does between " + minAttack + " and " + maxAttack + " damage to the enemy.";
		return str;
	}
	
	@Override
	public int calcEffect()
	{
		Random rand = new Random();
		double realDamage;
		int intDamage;
		realDamage = minAttack + (rand.nextDouble() * (maxAttack - minAttack)); // gets random number between min and max attack
		intDamage = (int) Math.round(realDamage); // rounds to integer
		return intDamage;
	}
	
	@Override
	public Item clone()
    {
        Weapon weaponClone = new Weapon(this);
        return weaponClone;
    }
	
	@Override
	public boolean equals(Object inObj)
    {
        boolean same = false;
        if (inObj instanceof Weapon)
        {
            Weapon weapon = (Weapon)inObj;
			same = (name.equals(weapon.getName())) &&
                    (minAttack == weapon.getMinAttack()) &&
					(maxAttack == weapon.getMaxAttack()) &&
					(cost ==  weapon.getCost()) &&
					(type.equals(weapon.getType())) &&
					(damageType.equals(weapon.getDamageType()));
		}
        return same;
    }
}
		