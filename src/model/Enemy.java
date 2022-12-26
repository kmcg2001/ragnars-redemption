/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: Enemy.java                                                    *
* PURPOSE: enemy interface                                                 *
***************************************************************************/
package model;

import java.util.*;

public abstract class Enemy
{
	protected String species;
	protected int maxHealth;
	protected int curHealth;
	protected int minAttack;
	protected int maxAttack;
	protected int minDefence;
	protected int maxDefence;
	protected int goldAward;
	protected List<String> specialAbilities;
	
	public Enemy (String inSpecies, int inMaxHealth, int inMinAttack, int inMaxAttack, int inMinDefence, 
				  int inMaxDefence, int inGoldAward)
	{
		species = inSpecies;
		maxHealth = inMaxHealth;
		curHealth = inMaxHealth;
		minAttack = inMinAttack;
		maxAttack = inMaxAttack;
		minDefence = inMinDefence;
		maxDefence = inMaxDefence;
		goldAward = inGoldAward;
		specialAbilities = new ArrayList<String>();
	}
	
	public String getSpecies()
	{
		return species;
	}
	
	public int getMaxHealth()
	{
		return maxHealth;
	}
	
	public int getCurHealth()
	{
		return curHealth;
	}
	
	public int getGoldAward()
	{
		return goldAward;
	}
		
	public int defend(int damage)
	{
		int damageTaken = Math.max(0, (damage - calcDefence())); 
		curHealth = Math.max(0, curHealth - damageTaken);
		
		return damageTaken;
	}
	
	public int calcDefence()
	{
		Random rand = new Random();
		double realDefence;
		int intDefence;
		realDefence = minDefence + (rand.nextDouble() * (maxDefence - minDefence));
		intDefence = (int) Math.round(realDefence);
		
		return intDefence;
	}
	
	public abstract String checkForSpecialAbility();
	
	public abstract int attack(String specialAbility);
}
	

	