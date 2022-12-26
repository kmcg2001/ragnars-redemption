/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: LoadShopFromFile.java                                         * 
* PURPOSE: loads shop items from file                                      *
***************************************************************************/
package controller;
import view.*;
import model.Character;
import model.*;

import java.io.*;

public class LoadShopFromFile implements LoadShopFromSource
{
	private Shop shop;
	private Character player;
	private UserInterface ui;
	
	private int numWeapons;
	private int numArmour;
	private int cheapestWeaponCost;
	private int cheapestArmourCost;
	private Weapon defaultWeapon;
	private Armour defaultArmour;
	
	public LoadShopFromFile(Shop inShop, Character inPlayer, UserInterface inUi)
	{
		shop = inShop;
		player = inPlayer;
		ui = inUi;
		
		numWeapons = 0;
		numArmour = 0;
		cheapestWeaponCost = 0;
		cheapestArmourCost = 0;
		defaultWeapon = null;
		defaultArmour = null;
	}
	
	public void load() throws LoadException
	{
		FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
		
        int lineNum = 0;
        String line;
		
        try
        {
            fileStrm = new FileInputStream("resources/shop_items.txt");
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);
			
            line = bufRdr.readLine();
            while (line != null)
            {
                lineNum++;
				processLine(line);
				line = bufRdr.readLine();
			}
			
			fileStrm.close();
			
			if ((defaultWeapon == null) || (defaultArmour == null))
			{
				throw new LoadException("Need at least one armour and one weapon provided");
			}
			else
			{
				boolean enoughSpace = true;
				enoughSpace = player.addItem(defaultWeapon) && player.addItem(defaultArmour);
				if (!enoughSpace)
				{
					throw new LoadException("Not enough inventory space for default weapon and armour");
				}
			}
		}
		
		catch (IOException e)
		{
			throw new LoadException("Invalid 'shop_items.txt' file", e);
		}
		
		finally
		{
			try
			{
				if (fileStrm != null)
				{
					fileStrm.close();
				}
			}
			catch (IOException e)
			{
			}
		}
	}
	
	private void processLine(String line)
	{
		boolean success = true;
		char choice;
		
		/* temp variables to hold item attributes */
		
		String name;
		int minEffect = 0, maxEffect = 0, cost = 0;
		
		/* Weapons */
		String weaponType = "", weaponDamageType = "";
		
		/* Armour */
		String armourMaterial = "";
		
		/* Potions */
		char potionType = 'H';
		
		
		String[] tokens = line.split(",[ ]*"); // splits string by commas, spaces are ok 
		if (tokens.length >= 6)
		{
			try
			{
				choice = (tokens[0].toUpperCase()).charAt(0);
				
				name = tokens[1];
				if (name.equals(""))
				{
					success = false;
				}
				
				minEffect = Integer.parseInt(tokens[2]);
				if (minEffect < 0)
				{
					success = false;
				}
				
				maxEffect = Integer.parseInt(tokens[3]);
				if (maxEffect < 0 || maxEffect < minEffect)
				{
					success = false;
				}
				
				cost = Integer.parseInt(tokens[4]);
				if (cost <= 0)
				{
					success = false;
				}
				
				if (!success)
				{
					ui.showInvalidItem(tokens[1], "Invalid item attributes.");
				}
				else 
				{
					switch (choice)
					{
						case 'W':
						{
							numWeapons++;
							if (tokens.length == 7)
							{							
								weaponDamageType = tokens[5];
								if ((weaponDamageType.equals("")) || weaponDamageType.equals(null))
								{
									success = false;
								}
								
								weaponType = tokens[6];
								if ((weaponType.equals("")) || weaponType.equals(null))
								{
									success = false;
								}
								
								if (!success)
								{
									ui.showInvalidItem(tokens[1], "Weapon strings must be something");
								}
							}
							else
							{
								ui.showInvalidItem(tokens[1], "Weapon does not have a valid number of arguments");
								success = false;
							}
							
							if (success)
							{						
								Weapon newWeapon = new Weapon(name, minEffect, maxEffect, cost, weaponType, weaponDamageType);
								if (numWeapons == 1)
								{
									cheapestWeaponCost = cost;
									defaultWeapon = newWeapon;
								}
								if (cost < cheapestWeaponCost)
								{
									cheapestWeaponCost = cost;
									defaultWeapon = newWeapon;
								}
								shop.addWeapon(newWeapon);
								
							}
						}
						break;
						
						case 'A':
						{
							numArmour++;
							if (tokens.length == 6)
							{
								armourMaterial = tokens[5];
								if ((armourMaterial.equals("")) || (armourMaterial.equals(null)))
								{
									ui.showInvalidItem(tokens[1], "Armour material must be something");
									success = false;
								}
								
							}
							else
							{
								ui.showInvalidItem(tokens[1], "Potion does not have a valid number of arguments");
								success = false;
							}
							
							if (success)
							{						
								Armour newArmour = new Armour(name, minEffect, maxEffect, cost, armourMaterial);
								if (numArmour == 1)
								{
									cheapestArmourCost = cost;
									defaultArmour = newArmour;
								}	
								if (cost < cheapestArmourCost)
								{
									cheapestArmourCost = cost;
									defaultArmour = newArmour;
								}
								shop.addArmour(newArmour);
							}
						}
						break;
						
						case 'P':
						{
							if (tokens.length == 6)
							{
								potionType = tokens[5].toUpperCase().charAt(0);
								if ((potionType != 'H') && (potionType != 'D'))
								{
									ui.showInvalidItem(tokens[1], "Potion can only be Healing ('H') or Damaging ('D')");
									success = false;
								}
								
							}
							else
							{
								ui.showInvalidItem(tokens[1], "Potion does not have a valid number of arguments");
								success = false;
							}
							
							if (success)
							{
								PotionFactory potionFactory = new PotionFactory();
								Potion newPotion = potionFactory.makePotion(potionType, name, minEffect, maxEffect, cost);
								shop.addPotion(newPotion);
							}
						}
						break;
						
						default:
						{
							ui.showInvalidItem(tokens[1], "Item can only be a Weapon ('W'), Armour ('A'), or Potion ('P')");
						}
					}
				}
			}
			catch (NumberFormatException e)
			{
				ui.showInvalidItem(tokens[1], "Check that attributes are in the right order");
			}
		}
		else
		{
			if (tokens.length >= 2) // if it doesn't have a name, we can't even give them the name of the item that failed
			{
				ui.showInvalidItem(tokens[1], "Item does not have a valid number of arguments");
			}
			else
			{
				ui.showInvalidItem("", "Item does not have a valid number of arguments");
			}
		}
	}
}
					