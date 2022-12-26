/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: EnemyManager.java                                             *
* PURPOSE:  holds all the enemies and their probabilities                  *
***************************************************************************/
package model;

import java.util.*;

public class EnemyManager
{

	private List<Enemy> enemies;
	private Map<Enemy, Double> chances;
	
	public EnemyManager()
	{
		enemies = new ArrayList<Enemy>();
		
		enemies.add(0, new Slime());
		enemies.add(1, new Goblin());
		enemies.add(2, new Ogre());
		enemies.add(3, new Dragon());
		
		chances = new HashMap<Enemy, Double>();
		
		chances.put(enemies.get(0), 0.5);
		chances.put(enemies.get(1), 0.3);
		chances.put(enemies.get(2), 0.2);
		chances.put(enemies.get(3), 0.0);
		
	}
	
	public List<Enemy> getEnemies()
	{
		return Collections.unmodifiableList(enemies);
	}
	
	public Map<Enemy, Double> getChances()
	{
		return Collections.unmodifiableMap(chances);
	}
	
	public void changeChances()
	{
		double chance;
		
		chance = chances.get(enemies.get(0)) - 0.05;
		if (Double.compare(chance, 0.05) >= 0) // if chance would be < 5%, don't do
		{
			chances.replace(enemies.get(0), chance);
		}
		
		chance = chances.get(enemies.get(1)) - 0.05;
		if (Double.compare(chance, 0.05) >= 0)
		{
			chances.replace(enemies.get(1), chance);
		}
		
		chance = chances.get(enemies.get(2)) - 0.05;
		if (Double.compare(chance, 0.05) >= 0)
		{
			chances.replace(enemies.get(2), chance);
		}
		
		chance = chances.get(enemies.get(3)) + 0.15;
		if (Double.compare(chance, 0.85) < 0) // if chance would be > 85%, don't do.
		{
			chances.replace(enemies.get(3), chance);
		}
	}
	
}
		
