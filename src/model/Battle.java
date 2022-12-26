/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: Battle.java                                                   *
* PURPOSE: holds different events that can occur in player/enemy battle    *
***************************************************************************/
package model;

public class Battle
{
	private Character player;
	private Enemy enemy;
	
	public Battle(Character inPlayer)
	{
		player = inPlayer;
	}
	
	public void setEnemy(Enemy newEnemy)
	{		
		enemy = newEnemy;
		
	}
	
	public int playerAttack()
	{
		int damageGiven = player.attack();
		return damageGiven;
	}
	
	public int playerDefend(int damageGiven)
	{
		int damageTaken;
		damageTaken = player.defend(damageGiven);
		return damageTaken;
	}
	
	public int playerAttackUsingPotion(Potion potion)
	{
		int damageGiven = potion.calcEffect();
		return damageGiven;
	}
	
	public int enemyAttack(String specialAbility)
	{
		int damageGiven = enemy.attack(specialAbility); // enemy does attack, attack will be modified by special ability if its been triggered
		return damageGiven;
	}
	
	public int enemyDefend(int damageGiven)
	{
		int damageTaken;
		damageTaken = enemy.defend(damageGiven);
		return damageTaken;
	}
	
	public boolean isPlayerDead()
	{
		boolean dead = false;
		if (player.getCurHealth() == 0)
		{
			dead = true;
		}
		return dead;
	}
	
	public boolean isEnemyDead()
	{
		boolean dead = false;
		if (enemy.getCurHealth() == 0)
		{
			dead = true;
		}
		return dead;
	}
		
	public int rewardPlayer()
	{
		player.setHealth((int) Math.min(player.getMaxHealth(), player.getCurHealth() * 1.5));
		player.receiveGold(enemy.getGoldAward());
		return enemy.getGoldAward();
	}
}
	
		
	
	
			