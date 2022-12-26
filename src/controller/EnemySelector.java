/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: EnemySelector.java                                            * 
* PURPOSE:  selects an enemy randomly based on its chance                  *
***************************************************************************/
package controller;
import model.Enemy;

import java.util.*;

public class EnemySelector
{
	private class EnemyEntry
	{
		public Enemy enemy;
		public double chance;
		
		public EnemyEntry(Enemy inEnemy, double inChance)
		{
			enemy = inEnemy;
			chance = inChance;
		}
	}
	
	private List<EnemyEntry> enemyList;
	private double combinedChance;
	
	public EnemySelector()
	{
		enemyList = new ArrayList<EnemyEntry>();
		combinedChance = 0.0;
	}
	
	public Enemy select()
	{
		Random rand = new Random();
		double num = rand.nextDouble();
		
		for (EnemyEntry enemyEntry : enemyList)
		{
			if (Double.compare(enemyEntry.chance, num) >= 0) 
			{
				return enemyEntry.enemy; // first enemy to have chance >= num will be selected
			}
		}
		return null;
	}
	
	public void addEnemy(Enemy newEnemy, double chance)
	{
		combinedChance = combinedChance + chance;
		EnemyEntry newEnemyEntry = new EnemyEntry(newEnemy, combinedChance);
		enemyList.add(newEnemyEntry);
	}
}
	
	
	