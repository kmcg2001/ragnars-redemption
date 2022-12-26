/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: Slime.java                                                    *
* PURPOSE:  represents an enemy whose species is slime                     *
***************************************************************************/
package model;

import java.util.*;

public class Slime extends Enemy
{

	public Slime()
	{
		super("Slime", 10, 3, 5, 0, 2, 10);
		specialAbilities.add(0, "Attack has no damage");
	}

	@Override
	public String checkForSpecialAbility()
	{
		String specialAbility;
		double rand = Math.random();
		if (rand <= 0.2)
		{
			specialAbility = specialAbilities.get(0);
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
		
		if (specialAbility.equals(specialAbilities.get(0))) // if no damage special ability triggered
		{
			realAttackDamage = 0.0;
		}
		
		intAttackDamage = (int) Math.round(realAttackDamage);
		return intAttackDamage;
	}
	
}