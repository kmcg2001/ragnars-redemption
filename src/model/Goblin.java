/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: Goblin.java                                                   *
* PURPOSE:  holds all the enemies and their probabilities                  *
***************************************************************************/
package model;

import java.util.*;

public class Goblin extends Enemy
{
	public Goblin()
	{
		super("Goblin", 30, 3, 8, 4, 8, 20);
		specialAbilities.add(0, "Attack does 3 more damage");
	}
	
	@Override
	public String checkForSpecialAbility()
	{
		String specialAbility;
		double rand = Math.random();
		if (rand <= 0.5) // 50 % chance
		{
			specialAbility = specialAbilities.get(0);
		}
		else
		{
			specialAbility = ""; // no special ability triggered
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

		if (specialAbility.equals(specialAbilities.get(0))) // if +3 attack ability triggered
		{
			realAttackDamage = realAttackDamage + 3.0; 
		}
		
		
		intAttackDamage = (int) Math.round(realAttackDamage);
		return intAttackDamage;
	}
}