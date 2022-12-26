/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: UserInterface.java                                            *
* PURPOSE: to show messages to user and take their input                   *
***************************************************************************/
package view;

import java.lang.*;
import java.util.*;

public class UserInterface
{
	private Scanner sc;
	
	public UserInterface()
	{
		sc = new Scanner(System.in);
	}
	
	
	public void gameIntro(String playerName)
    {

        System.out.println("Welcome, " + playerName + ". The merciful Lord Magnus has spared your life, " +
						   "and has given you a chance to earn your freedom. \nYou will battle it out against the deadliest creatures " +
						   "that his fighting pit has to offer, \nand you will be released if you can prove your worth as a warrior to Lord Magnus.\n");
		System.out.println("You will have noticed that you have been stripped of all your posessions. Don't worry, you can get them back. For a price.\n");
		System.out.println("\nPress 'ENTER' to continue...");
		sc.nextLine();

    }
	
	public String getMenuOptions()
	{
		String menuOptions = "\nPlease select the number that corresponds with the option you would like to choose:" +
						"\n1. Go to Shop" +
						"\n2. Choose Character Name" +
						"\n3. Choose Weapon" +
						"\n4. Choose Armour" +
						"\n5. Start Battle" +
						"\n6. Exit Game";
						
		return menuOptions;
	}
	
	public void showBorder()
	{
		System.out.print("--------------------------------------------------------------------------------------------------------------------------");
	}
	
	public void showPlayerName (String name)
	{
		System.out.println("\nCharacter name: " + name);
	}
	
	public void showPlayerHealth (int health)
	{
		System.out.println("Health: " + health);
	}
	
	public void showPlayerWeapon (String weapon)
	{
		System.out.println("Equipped Weapon: " + weapon);
	}
	
	public void showPlayerArmour (String armour)
	{
		System.out.println("Equipped Armour: " + armour);
	}
	
	public void showPlayerGold (int  gold)
	{
		System.out.println("Gold: " + gold);
	}
	
	public void showPlayerInvent (List<String> invent)
	{
		String inventList = String.join(", ", invent);
		System.out.println("Inventory: " + inventList);
	}
	
		
	public void listAllShopItems(String shopItems)
	{
		String str = "\nItems available to buy\n" + shopItems;
		System.out.println(str);
	}
	
	public String numberPlayerItems(String header, List<String> itemNames)
	{
		String itemsListStr = header + "\n";
		int num = 1;
		for (String itemName : itemNames)
		{
			if (num == 1)
			{
				itemsListStr = itemsListStr + "\n\t" + num + ". " + itemName + " *Equipped*";
			}
			else
			{
				itemsListStr = itemsListStr + "\n\t" + num + ". " + itemName;
			}
			
			num++;
		}
		
		return itemsListStr;
		
	}
	
	public String numberPlayerPotions(List<String> potionNames)
	{
		String potionsListStr = "Potions:\n";
		int num = 1;
		for (String potionName : potionNames)
		{
			potionsListStr = potionsListStr + "\n\t" + num + ". " + potionName;
			num++;
		}
		
		return potionsListStr;
	}
		
	
	public String numberPlayerItemsForSale(List<String> itemNames, List<Integer> salePrices)
	{
		String str = "Your Inventory:\n";
		
		for (int i = 0; i < itemNames.size(); i++)
		{
			if (i < 2)
			{
				str = str + "\n\t" + (i+1) + ". " + itemNames.get(i) + " *Equipped* (+" + salePrices.get(i) + " Gold)";
			}
			else
			{
				str = str + "\n\t" + (i+1) + ". " + itemNames.get(i) + " (+" + salePrices.get(i) + " Gold)";
			}
			
		}
		

		return str;
	}
	
	
	public void showItemDescription(String itemName, String description)
	{
		System.out.println(itemName + ": " + description);
	}
	
	public char shopMenu()
	{
			
		char choice;
        String prompt = "\nEnter 'B' to buy an item, 'S' to sell one of your items, or 'X' to exit the shop";
		String str = prompt;
		String error = "Please enter a valid letter to make your choice";
		
		do
		{
			try
			{
				System.out.println(str);
				str = error + "\n" + prompt;
				choice = sc.next().charAt(0);
				choice = java.lang.Character.toUpperCase(choice);
				
			} 
			catch (InputMismatchException e)
			{
				sc.nextLine();
				System.out.println(error);
				choice = 'E';
			}
		}
		while ((choice != 'B') && (choice != 'S') && (choice != 'X'));
			
		return choice;

	}
	
	public void showSuccessfulPurchase(int cost)
	{
		System.out.println("Your purchase was successful. You have lost " + cost + " Gold.");
	}
	
	public void showFailedPurchase(String errorMsg)
	{
		System.out.println("Your purchase was unsuccessful. " + errorMsg);
	}
	
	public void showSuccessfulSale(int price)
	{
		System.out.println("You have successfully sold your item. You have gained " + price + " Gold.");
	}
	
	
	public void showFailedSale(String errorMsg)
	{
		System.out.println("You cannot sell this item as it's equipped. " + errorMsg);
	}
	
	public void showFailedPotionUse()
	{
		System.out.println("You have no potions you can use right now.");
	}
	
	public String listItemCategoryForSale(String itemCategory, List<String> itemNames, List<Integer> itemCosts)
	{
		String str = itemCategory + "\n:";
		
		str = itemCategory + ":\n";
		for (int i = 0; i < itemNames.size(); i++)
		{
			str = str + "\n\t" + (i+1) + ". " + itemNames.get(i) + " (-" + itemCosts.get(i) + " Gold)";
		}
		
		return str;
	}
	
	public char buyMenu()
	{
		
		char itemType;
        String prompt = "\nEnter 'W' to buy a weapon, 'A' to buy armour, 'P' to buy a potion, 'E' to buy an enchantment, or 'X' to return to shop menu";
		String str = prompt;
		String error = "Please enter a valid letter to make your choice";

			do
			{
				try
				{
					System.out.println(str);
					str = error + "\n" + prompt;
					itemType = sc.next().charAt(0);
					itemType = java.lang.Character.toUpperCase(itemType);
				}
				catch (InputMismatchException e)
				{
					sc.nextLine();
					itemType = 'E';
				}
			}
			while ((itemType != 'W') && (itemType != 'A') && (itemType != 'P') && (itemType != 'E') && (itemType != 'X'));
		
		
		return itemType;
	}
	
	
	public int intInput(String prompt, int min, int max)
    {
		int userInput = min - 1;
        String str = prompt;
		String error = ("Please enter an integer number between " + min + " and " + max + ".");
        do
        {
            try
            {
                System.out.println(str);
                str = error + "\n" + prompt;
                userInput = sc.nextInt();
            } 
			catch (InputMismatchException e)
            {
                sc.nextLine();
            }
        }
        while ((userInput < min) || (userInput > max));
        
		return userInput;
    }
	
	public char confirm (String prompt)
	{
		char confirmation;
		String str = prompt;
		String error = "Please enter either 'Y' for YES or 'N' for NO";
		do
		{
			try
			{
				System.out.println(str);
				str = error + "\n" + prompt;
				confirmation = sc.next().charAt(0);
				confirmation = java.lang.Character.toUpperCase(confirmation);
			}
			catch (InputMismatchException e)
			{
				sc.nextLine();
				System.out.println(error);
				confirmation = 'E';
			}
				
		}
		while ((confirmation != 'N') && (confirmation != 'Y'));
		
		return confirmation;
	}
	
	public String retrieveNewCharacterName()
	{
		String name;
		name = stringInput("Please enter your character's new name.");
		System.out.println("Your character's name is now '" + name + "'.");
		
		return name;
	}
	
	public String stringInput(String prompt)
    {
		String userInput;
        
		sc.nextLine();
		System.out.println(prompt);
		userInput = sc.nextLine();
			
		return userInput;
    }
	
	public void showFailedEquip(String itemName)
	{
		System.out.println("You already have this " + itemName + " equipped.");
	}
	
	public void showSuccessfulEquip(String itemName)
	{
		System.out.println("You have equipped " + itemName + ".");
	}
	
	public void revealBattleEnemy(String enemySpecies)
	{
		System.out.println("\nYour enemy for this battle is a " + enemySpecies + ".");
	}
	
	
	public void showPlayerAttack (String enemySpecies, int damageGiven, int damageTaken, int enemyHealth)
	{
		int absorbed = damageGiven - damageTaken; // find out how much damage was absorbed by taking the difference in damage
		if (damageGiven > (enemyHealth + absorbed)) // make sure player can't do more damage than enemy's health + defence combined
		{
			damageGiven = enemyHealth + absorbed; // if they do more damage than that, cut off the extra damage
		}
		damageTaken = Math.min(enemyHealth, damageTaken); // enemy can't take more damage than their current health
		
		System.out.println("\nYou attacked the " + enemySpecies + " for " + damageGiven + " damage.");
		System.out.println("The " + enemySpecies + " lost " + damageTaken + " health after it absorbed " + absorbed + " damage.");
		
		enemyHealth = enemyHealth - damageTaken; 
		System.out.println("The " + enemySpecies + "'s health is now " + enemyHealth  + "."); // shows enemy's health because player's shown in attributes
	}
	
	public void showEnemyAttack (String enemySpecies, int damageGiven, int damageTaken, int playerHealth)
	{
		int absorbed = damageGiven - damageTaken; // find out how much damage was absorbed by taking the difference in damage
		if (damageGiven > (playerHealth + absorbed)) // make sure enemy can't do more damage than player's health + armour defence combined
		{
			damageGiven = playerHealth + absorbed; // if they do more damage than that, cut off the extra damage
		}
		damageTaken = Math.min(playerHealth, damageTaken); // player can't take more damage than their current health
		
		System.out.println("\nThe " + enemySpecies + " attacked you for " + damageGiven + " damage.");
		System.out.println("You lost " + damageTaken + " health after your armour absorbed " + absorbed + " damage.");
			
	}
	
	public void showPlayerDeath (String enemySpecies)
	{
		System.out.println("\nYou have been slain by the " + enemySpecies + "." +
						   "\nGAME OVER!");
	}
	
	public void showPlayerVictory (String enemySpecies, int gold)
	{		
		System.out.println("\nYou have slain the " + enemySpecies + "! You have been awarded " + gold + " Gold as a reward.");
	}
	
	public void showFinalPlayerVictory()
	{
		System.out.println("\nCongratulations! You have proven your worth as a warrior to the Great Lord Magnus. You have been freed.");
	}
	
	public void showPotionUse (String potionName)
	{
		System.out.println("\nYou have used your " + potionName + ".");
	}
	
	public void showPlayerHealing (int healing, int health)
	{
		System.out.println("\nYou have healed yourself, restoring " + healing + " points of health. Your health is now " + health + ".");
	}
	
	public void showEnemySpecialAbility (String enemySpecies, String specialAbility)
	{
		System.out.println("\n" + enemySpecies + "'s SPECIAL ABILITY triggered! " + specialAbility + ".");
	}
	
	public void showInvalidItem (String itemName, String msg)
	{
		System.out.println("WARNING: Error loading item: '" + itemName + "': " + msg + "\n");
	}
	
	public void showException (String userMessage, String exceptionMessage)
	{
		System.out.println(userMessage + exceptionMessage);
	}
	
	public void showExitMessage()
	{
		System.out.println("Leaving so soon? Lord Magnus sends his regards.");
	}		
		
}

	
	