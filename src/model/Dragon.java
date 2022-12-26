/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: Dragon.java                                                   *
* PURPOSE: represents enemy whose species is dragon                        *
***************************************************************************/
package model;

import java.util.*;

public class Dragon extends Enemy
{
	
	public Dragon()
	{
		super("Dragon", 100, 15, 30, 15, 20, 100);
		specialAbilities.add(0, "10 health recovered");
		specialAbilities.add(1, "Damage doubled");
	
	}
	
	@Override
	public String checkForSpecialAbility()
	{
		String specialAbility;
		double rand = Math.random();
		if (rand <= 0.1) // 10% chance
		{
			specialAbility = specialAbilities.get(0);
		}
		else if (rand <= 0.35) // 25% chance
		{
			specialAbility = specialAbilities.get(1);
		}
		else
		{
			specialAbility = ""; // no special ability
		}
		
		return specialAbility;
	}
	
	@Override
	public int attack(String specialAbility)
	{
		Random rand = new Random();
		double realAttackDamage;
		int intAttackDamage;
		
		realAttackDamage = minAttack + (rand.nextDouble() * (maxAttack - minAttack));
		
	
		if (specialAbility.equals(specialAbilities.get(0))) // if 10 health recovered ability triggered
		{
			curHealth = Math.min(maxHealth, curHealth + 10);
		}
		else if (specialAbility.equals(specialAbilities.get(1))) // if damage doubled ability triggered
		{
			
			realAttackDamage = 2.0 * realAttackDamage;
		}
		
		intAttackDamage = (int) Math.round(realAttackDamage);
		return intAttackDamage;
	}
}