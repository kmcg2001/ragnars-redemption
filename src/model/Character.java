/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: Character.java                                                *
* PURPOSE: represents a character (player) in the game                     *
***************************************************************************/
package model;

import java.util.*;

public class Character
{
	// Class Constants
	public static final int INVENT_CAPACITY = 15;
	
	private String name;
	private int maxHealth;
	private int curHealth;
	private List<Item> invent; // 0th spot in invent reserved for equipped weapon, 1st for equipped armour
	private Item[] equipped;
	private int gold;
	
	public Character()
	{
		name = "Ragnar";
		maxHealth = 30;
		curHealth = maxHealth;
		
		invent = new ArrayList<Item>();
		equipped = new Item[2];
		
		gold = 100;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getMaxHealth()
	{
		return maxHealth;
	}
	
	public int getCurHealth()
	{
		return curHealth;
	}
	
	public int getGold()
	{
		return gold;
	}
	
	public List<Item> getInvent()
	{
		return Collections.unmodifiableList(invent);
	}
	
	public void setName(String inName)
	{
		name = inName;
	}
	
	public void setHealth(int newHealth)
	{
		curHealth = newHealth;
	}
	
	public List<String> getItemNames()
	{
		List<String> itemNames = new ArrayList<String>();
		for (Item item : invent)
		{
			itemNames.add(item.getName());
		}
		
		return Collections.unmodifiableList(itemNames);
	}
	
	public List<Integer> getItemCosts()
	{
		List<Integer> itemCosts = new ArrayList<Integer>();
		for (Item item : invent)
		{
			itemCosts.add(item.getCost());
		}
		
		return itemCosts;
	}

	
	public int getNumWeapons()
	{
		int numWeapons = 0;
		for (Item item : invent)
		{
			if (item instanceof DamageDealer)
			{
				numWeapons++;
			}
		}
		return numWeapons;
	}
	
	public int getNumArmour()
	{
		int numArmour = 0;
		for (Item item : invent)
		{
			if (item instanceof Armour)
			{
				numArmour++;
			}
		}
		return numArmour;
	}
	
	public int getNumPotions()
	{
		int numPotions = 0;
		for (Item item : invent)
		{
			if (item instanceof Potion)
			{
				numPotions++;
			}
		}
		return numPotions;
	}
	
	public Item getItem(int itemIndex)
	{
		itemIndex = itemIndex - 1;
		return invent.get(itemIndex);
	}
	
	public boolean addItem(Item newItem)
	{
		boolean success = false;
		if (equipped[0] == null)
		{
			if (newItem instanceof DamageDealer)
			{
				if (invent.size() < INVENT_CAPACITY)
				{
					invent.add(newItem);
					equipped[0] = newItem;
					success = true;
				}
			}
		}
		
		if (equipped[1] == null)
		{
			if (newItem instanceof Armour)
			{
				if (invent.size() < INVENT_CAPACITY)
				{	
					invent.add(newItem);
					equipped[1] = newItem;
					success = true;
				}
			}
		}
		else if ((equipped[0] != null) && (equipped[1] != null))
		{			
			if (invent.size() < INVENT_CAPACITY)
			{
				invent.add(newItem);
				success = true;
			}
		}
		return success;
	}
	
	public void removeItem(Item unwantedItem)
	{
		int oldIndex = invent.indexOf(unwantedItem);
		invent.remove(oldIndex);
	}
	
	public DamageDealer getWeapon(int weaponIndex)
	{
		int num = 0;
		weaponIndex = weaponIndex - 1;
		for (Item item : invent)
		{
			if (item instanceof DamageDealer)
			{
				if (num == weaponIndex)
				{
					return (DamageDealer) item;
				}
				num++;
			}
		}
		return null;
	}
	
	public void addWeaponEnchantment(DamageDealer oldWeapon, DamageDealer enchantedWeapon)
	{
		int oldIndex = invent.indexOf(oldWeapon);
		invent.set(oldIndex, enchantedWeapon);
		
		if (equipped[0].equals(oldWeapon))
		{
			equipped[0] = enchantedWeapon;
		}
	}
	
	public Armour getArmour(int armourIndex)
	{
		armourIndex = armourIndex - 1;
		int num = 0;
		for (Item item : invent)
		{
			if (item instanceof Armour)
			{
				if (num == armourIndex)
				{
					Item clonedItem = item.clone();
					return (Armour) clonedItem;
				}
				num++;
			}
		}
		return null;
	}
	
	public Potion getPotion(int potionIndex)
	{
		potionIndex = potionIndex - 1;
		int num = 0;
		for (Item item : invent)
		{
			if (item instanceof Potion)
			{
				if (num == potionIndex)
				{
					Item clonedItem = item.clone();
					return (Potion) clonedItem;
				}
				num++;
			}
		}
		return null;
	}

	public DamageDealer getEquippedWeapon()
	{
		return (DamageDealer) equipped[0];
	}
	
	public Armour getEquippedArmour()
	{
		Item armourClone = equipped[1].clone();
		return (Armour) armourClone;
	}
	
	public void equipWeapon(DamageDealer chosenWeapon)
	{
		Item oldEquippedWeapon = equipped[0];
		equipped[0] = chosenWeapon;
		
		int oldIndex;
		oldIndex = invent.indexOf(chosenWeapon);
		invent.remove(oldIndex); // takes chosen weapon out of its old spot
		
		invent.set(0, chosenWeapon); // puts chosen weapon in designated spot for chosen weapon
		invent.add(oldEquippedWeapon); // moves old equipped weapon to the end of the list
	}
		
	public void equipArmour(Armour chosenArmour)
	{

		Item oldEquippedArmour = equipped[1];
		
		int oldIndex;
		oldIndex = invent.indexOf(chosenArmour);
		invent.remove(oldIndex);
		
		invent.set(1, chosenArmour); 
		invent.add(oldEquippedArmour);
		
	}
				
	public List<String> getWeaponNames()
	{
		List<String> weaponNames = new ArrayList<String>();
		for (Item item : invent)
		{
			if (item instanceof DamageDealer)
			{
				weaponNames.add(item.getName());
			}
		}		

		return Collections.unmodifiableList(weaponNames);
	}
	
	public List<String> getArmourNames()
	{
		List<String> armourNames = new ArrayList<String>();
		for (Item item : invent)
		{
			if (item instanceof Armour)
			{
				armourNames.add(item.getName());
			}
		}		

		return Collections.unmodifiableList(armourNames);
	}
		
	
	public List<String> getPotionNames()
	{
		List<String> potionNames = new ArrayList<String>();
		for (Item item : invent)
		{
			if (item instanceof Potion)
			{
				potionNames.add(item.getName());
			}
		}		

		return Collections.unmodifiableList(potionNames);
	}
	
	public int attack()
	{
		int damage;
		Item weapon = equipped[0];
		damage = weapon.calcEffect();
		return damage;
	}
	
	public int defend(int damage)
	{
		Item armour = equipped[1];
		int damageTaken = Math.max(0, (damage - armour.calcEffect()));
		curHealth = Math.max(0, curHealth - damageTaken);
		
		return damageTaken;
	}
	
	public int[] usePotion(Potion potion)
	{
		int damage, healing, itemIndex;
		int[] potionValues = new int[2];
		
		damage = potion.calcEffect() * potion.calcDamageMultiplier();
		potionValues[0] = damage;
		
		healing = potion.calcEffect() * potion.calcHealingMultiplier();
		curHealth = Math.min(maxHealth, (curHealth + healing));
		potionValues[1] = healing;
		
		itemIndex = invent.indexOf(potion);
		invent.remove(itemIndex);
		
		return potionValues;
	}
	
	public void receiveGold(int goldAmount)
	{
		gold = gold + goldAmount;
	}
	
	public void loseGold(int goldAmount)
	{
		gold = gold - goldAmount;
	}
}
		
		
	