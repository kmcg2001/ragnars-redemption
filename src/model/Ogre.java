/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: Ogre.java                                                     *
* PURPOSE:  represents enemy whose species is ogre                         *
***************************************************************************/
package model;

import java.util.*;

public class Ogre extends Enemy
{
	public Ogre()
	{
		super("Ogre", 40, 5, 10, 6, 12, 40);
		specialAbilities.add(0, "Gets to attack twice");
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
		
		if (specialAbility.equals(specialAbilities.get(0))) // if attack twice special ability triggered
		{	
			realAttackDamage = realAttackDamage + (double) attack(""); // calls attack() twice to show two separate attacks in a row
		}
		
		intAttackDamage = (int) Math.round(realAttackDamage);
		return intAttackDamage;
	}
}