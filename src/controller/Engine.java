/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: Engine.java                                                   * 
* PURPOSE: the game's engine: controls everything that happens in the game *
***************************************************************************/
package controller;
import model.Character;
import model.*;
import view.*;

import java.util.*;

public class Engine
{
	private Character player;
	private Shop shop;
	private UserInterface ui;
	private EnemyManager enemyManager;
	
	public Engine (Character inPlayer, Shop inShop, UserInterface inUi, EnemyManager inEnemyManager)
	{
		player = inPlayer;
		shop = inShop;
		ui = inUi;
		enemyManager = inEnemyManager;
	}
	
	public void gameMenu()
	{
		int choice, minOption = 1, maxOption = 6;
		String menuOptions = ui.getMenuOptions();
		boolean gameOver = false;
		
		ui.gameIntro(player.getName());
		
		do
		{
			showPlayerAttributesWithInvent();
			
			choice = ui.intInput(menuOptions, minOption, maxOption); 
			switch (choice)
			{
				case 1: // Go to shop
				{
					goToShop();

				}
				break;

				case 2: // Choose character name
				{
					chooseCharacterName();
				}
				break;

				case 3: // choose weapon
				{
					chooseWeapon();
				}
				break;

				case 4: // chose armour
				{
					chooseArmour();
				}
				break;

				case 5: // start battle
				{
					gameOver = doBattle();
				}
				break;

				case 6: // exit game
				{
					ui.showExitMessage();
				}
				break;
			}
		}
		while ((choice != 6) && (!gameOver));
	}

     
	private void showPlayerAttributes()
	{
		ui.showBorder();
		ui.showPlayerName(player.getName());
		ui.showPlayerHealth(player.getCurHealth());
		ui.showPlayerWeapon(player.getEquippedWeapon().getName());
		ui.showPlayerArmour(player.getEquippedArmour().getName());
		ui.showPlayerGold(player.getGold());
	}
	
	private void showPlayerAttributesWithoutInvent()
	{
		showPlayerAttributes();
		ui.showBorder();
	}
		
	private void showPlayerAttributesWithInvent()
	{
		showPlayerAttributes();
		ui.showPlayerInvent(player.getItemNames());
		ui.showBorder();
	}
	
	private void goToShop()
	{
		char choice;
		
			showPlayerAttributesWithInvent();
			ui.listAllShopItems(shop.toString());
			
			do
			{
				choice = ui.shopMenu();
				
				switch (choice)
				{
					
					case 'B':
					{
						buyItem();
					}
					break;
					
					case 'S':
					{
						sellItem();
					}
					break;
					
					case 'X':
					{
					}
					break;
				}
			}
			while (choice != 'X');
		
	}
	
	private void buyItem()
	{
		char itemType, confirmation;
		int minSelection = 0, maxSelection = 0, itemIndex = 0;
		Item item;
		String prompt = "", list;
		
		
		do
		{
			itemType = ui.buyMenu();
			switch (itemType)
			{
				case 'W':
				{
					list = ui.listItemCategoryForSale("Weapons", shop.getWeaponNames(), shop.getWeaponCosts());
					prompt = "\nEnter the number of the weapon you would like to buy. Enter '0' to return to select another item type.\n" + list;
					maxSelection = shop.getWeapons().size();
					
				}
				break;
				
				case 'A':
				{
					list = ui.listItemCategoryForSale("Armour", shop.getArmourNames(), shop.getArmourCosts());
					prompt = "\nEnter the number of the armour you would like to buy. Enter '0' to return to select another item type\n" + list;
					maxSelection = shop.getArmour().size();
				}
				break;
				
				case 'P':
				{
					list = ui.listItemCategoryForSale("Potions", shop.getPotionNames(), shop.getPotionCosts());
					prompt = "\nEnter the number of the potion you would like to buy. Enter '0' to select another item type.\n" + list;
					maxSelection = shop.getPotions().size();
					
				}
				break;
				
				case 'E':
				{
					DamageDealer weapon;
					list = ui.listItemCategoryForSale("Enchantments", shop.getEnchantmentNames(), shop.getEnchantmentCosts());
					prompt = "\nEnter the number of the enchantment you would like to buy. Enter '0' to buy something else.\n" + list;
					maxSelection = shop.getEnchantments().size();
					int enchantmentIndex = ui.intInput(prompt, minSelection, maxSelection);
					if (enchantmentIndex != 0)
					{
					
						Enchantment enchantment = shop.browseEnchantment(enchantmentIndex);
						ui.showItemDescription(enchantment.getEnchantmentName(), enchantment.getEnchantmentEffect());
						
						list = ui.numberPlayerItems("Weapons:", player.getWeaponNames());
						
						prompt = "\nEnter the number of the weapon you would like to add a " + enchantment.getEnchantmentName() + " enchantment to. Enter '0' if you don't want to add it to any weapon.\n" + list;
						maxSelection = player.getNumWeapons();
						itemIndex = ui.intInput(prompt, minSelection, maxSelection);
						if (itemIndex != 0)
						{
							weapon = player.getWeapon(itemIndex);
						
							confirmation = ui.confirm("\nAre you sure you would like to buy this " + enchantment.getEnchantmentName() + " enchantment for your " + weapon.getName() + "?" +
												   "\nIt will cost you " + enchantment.getEnchantmentCost() + " Gold. Enter 'Y' for YES and 'N' for NO.");
							if (confirmation == 'Y')
							{
								DamageDealer enchantedWeapon = shop.addEnchantmentToWeapon(player, weapon, enchantmentIndex);
								
								if (enchantedWeapon != null)
								{
									player.addWeaponEnchantment(weapon, enchantedWeapon);
									player.loseGold(enchantment.getEnchantmentCost());
									ui.showSuccessfulPurchase(enchantment.getEnchantmentCost());
								}
								else
								{
									ui.showFailedPurchase("You should get some more Gold first.");
									
								}
							}	
						}
					}						
				}
				break;
				
				case 'X':
				{
				}
				break;
	
			}
			if ((itemType != 'X') && (itemType != 'E'))
			{
				itemIndex = ui.intInput(prompt, minSelection, maxSelection);
				if (itemIndex != 0)
				{
					item = shop.browseItem(itemType, itemIndex);
					ui.showItemDescription(item.getName(), item.toString());
					
					confirmation = ui.confirm("Are you sure you would like to buy this " + item.getName() + " for " + item.getCost() + " Gold? Enter 'Y' for YES and 'N' for NO.");
					if (confirmation == 'Y')
					{
						item = shop.authorisePurchase(player, item); 
						
						if (item != null)
						{
							if (player.addItem(item))
							{
								player.loseGold(item.getCost());
								ui.showSuccessfulPurchase(item.getCost());
							}
							else
							{
								ui.showFailedPurchase("You should clear some space in your inventory first.");
							}
						}
						else
						{
							ui.showFailedPurchase("You should get some more Gold first.");
						}
					}
				}
			}
		}
		while (itemType != 'X');
	}
	
	private void sellItem()
	{
		String prompt, list;
		Item item;
		char confirmation;
		int minSelection = 0, maxSelection = 0, itemIndex = 0;
		do
		{
			
			list = ui.numberPlayerItemsForSale(player.getItemNames(), shop.getSalePrices(player));
			prompt = "\nEnter the number of the item you would like to sell. Enter '0' to return to the shop menu.\n" + list;
			maxSelection = player.getInvent().size();
			itemIndex = ui.intInput(prompt, minSelection, maxSelection);
			if (itemIndex != 0)
			{
				item = player.getItem(itemIndex);
				ui.showItemDescription(item.getName(), item.toString());
				confirmation = ui.confirm("Are you sure you would like to sell this " + item.getName() + " for " + (int) Math.round(item.getCost() * shop.SALE_PRICE_MULTIPLIER) + " Gold? Enter 'Y' for YES and 'N' for NO.");

				
				if (confirmation == 'Y')
				{
					if (item.equals(player.getEquippedWeapon()))
					{
						ui.showFailedSale("You should equip another weapon first.");
					}
					else if (item.equals(player.getEquippedArmour()))
					{
						ui.showFailedSale("You should equip other armour first.");
					}
					else
					{
						shop.authoriseSale(player, item);
						ui.showSuccessfulSale((int) Math.round(item.getCost() * shop.SALE_PRICE_MULTIPLIER));
					}
				}
			}
		}
		while (itemIndex != 0);
	}
	
	private void chooseCharacterName()
	{
		player.setName(ui.retrieveNewCharacterName());
	}
		
	private void chooseWeapon()
	{
		String prompt, list;
		char confirmation;
		int minSelection = 0, maxSelection = 0, weaponIndex = 0;
		DamageDealer weapon;
		
		list = ui.numberPlayerItems("Weapons:", player.getWeaponNames());
		prompt = "\nEnter the number of the weapon you would like to equip. Enter '0' to return to the main menu.\n" + list;
		maxSelection = player.getNumWeapons();
		weaponIndex = ui.intInput(prompt, minSelection, maxSelection);
		if (weaponIndex != 0)
		{
			weapon = player.getWeapon(weaponIndex);
			ui.showItemDescription(weapon.getName(), weapon.toString());

			confirmation = ui.confirm("Are you sure you would like to equip this " + weapon.getName() + "? Enter 'Y' for YES and 'N' for NO.");
			if (confirmation == 'Y')
			{
				if (weapon.equals(player.getEquippedWeapon()))
				{
					ui.showFailedEquip(weapon.getName());
				}
				else
				{
					player.equipWeapon(weapon);
					ui.showSuccessfulEquip(weapon.getName());
				}
			}
		}
	}
	
	private void chooseArmour()
	{
		String prompt, list;
		char confirmation;
		int minSelection = 0, maxSelection = 0, armourIndex = 0;
		Armour armour;
		
		list = ui.numberPlayerItems("Armour:", player.getArmourNames());
		prompt = "\nEnter the number of the armour you would like to equip. Enter '0' to return to the main menu.\n" + list;
		maxSelection = player.getNumArmour();
		armourIndex = ui.intInput(prompt, minSelection, maxSelection);
		if (armourIndex != 0)
		{
			armour = player.getArmour(armourIndex);
			ui.showItemDescription(armour.getName(), armour.toString());

			confirmation = ui.confirm("Are you sure you would like to equip this " + armour.getName() + "? Enter 'Y' for YES and 'N' for NO.");
			if (confirmation == 'Y')
			{
				if (armour.equals(player.getEquippedArmour()))
				{
					ui.showFailedEquip(armour.getName());
				}
				else
				{
					player.equipArmour(armour);
					ui.showSuccessfulEquip(armour.getName());
				}
			}
		}
	}
	
	private boolean doBattle()
	{
		boolean done = false, playerWin = false, enemyWin = false, moveMade = false, gameOver = false;
		String choices, prompt;
		int minSelection = 1, maxSelection = 2, selection, damageGiven, damageTaken;
		
		Battle battle = new Battle(player);
		EnemySelector enemySelector = new EnemySelector();
		
		List<Enemy> enemies = enemyManager.getEnemies();
		
		Map<Enemy, Double> chances = enemyManager.getChances();
		
		
		for (Enemy enemy : enemies)
		{
			enemySelector.addEnemy(enemy, chances.get(enemy));
		}
		
		Enemy newEnemy = enemySelector.select();
		
		battle.setEnemy(newEnemy);
		String enemySpecies = newEnemy.getSpecies();
		ui.revealBattleEnemy(enemySpecies);
		
		while (!done)
		{
			showPlayerAttributesWithoutInvent();
			choices = ("\nYou're up! Enter the number corresponding to your move" + 
							 "\t\n1. Attack with " + player.getEquippedWeapon().getName() +
							 "\t\n2. Use potion");
			do
			{
				minSelection = 1;
				maxSelection = 2;
				moveMade = false;
				selection = ui.intInput(choices, minSelection, maxSelection);	
				switch (selection)
				{
					case 1:
					{
						moveMade = true;
						int prevEnemyHealth = newEnemy.getCurHealth();
						damageGiven = battle.playerAttack(); 
						damageTaken = battle.enemyDefend(damageGiven);
						ui.showPlayerAttack(enemySpecies, damageGiven, damageTaken, prevEnemyHealth);
						
						playerWin = battle.isEnemyDead();
					}
					break;
					
					case 2:
					{
						int numPotions = player.getNumPotions();
						if (numPotions >= 1)
						{
							
							prompt = "\nEnter the number of the potion you would like to use. Enter '0' to make another move.";
							prompt = prompt + "\n" + ui.numberPlayerPotions(player.getPotionNames());
							maxSelection = numPotions;
							int potionIndex = ui.intInput(prompt, minSelection, maxSelection);
							if (potionIndex != 0)
							{
								moveMade = true;
								Potion potion = player.getPotion(potionIndex);
								int[] potionValues = player.usePotion(potion);
								ui.showPotionUse(potion.getName());
								
								if (potionValues[0] != 0)
								{
									int prevEnemyHealth = newEnemy.getCurHealth();
									damageGiven = battle.playerAttackUsingPotion(potion);
									damageTaken = battle.enemyDefend(damageGiven);
									ui.showPlayerAttack(enemySpecies, damageGiven, damageTaken, prevEnemyHealth);
									
									playerWin = battle.isEnemyDead();
								}
								else
								{
									int healing = potionValues[1];
									ui.showPlayerHealing(healing, player.getCurHealth());
								}
							}
						}
						else
						{
							ui.showFailedPotionUse();
						}
					}
					break;
				}
			}
			while (moveMade == false);
			
			if (playerWin) // if player wins the battle
			{
				int goldReward = battle.rewardPlayer();
				ui.showPlayerVictory(enemySpecies, goldReward);
				if (enemySpecies.equals("Dragon")) // sets Dragon as final boss, can easily change final boss - just need species name
				{
					ui.showFinalPlayerVictory();
					gameOver = true;
				}
				break; // get out of the loop, we don't want enemy to get their turned if 
			}
			
			int prevPlayerHealth = player.getCurHealth();
			
			String specialAbility = newEnemy.checkForSpecialAbility();
			if (!specialAbility.equals(""))
			{
				ui.showEnemySpecialAbility(enemySpecies, specialAbility);
			}
			damageGiven = battle.enemyAttack(specialAbility);
			
			damageTaken = battle.playerDefend(damageGiven);
			ui.showEnemyAttack(enemySpecies, damageGiven, damageTaken, prevPlayerHealth);
			
			enemyWin = battle.isPlayerDead();
			
			if (enemyWin) // if enemy wins the battle
			{
				ui.showPlayerDeath(enemySpecies);
				gameOver = true;
				done = true;
			}	
		}
		enemyManager.changeChances();

		return gameOver;
	}
}