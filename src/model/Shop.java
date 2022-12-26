/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: Shop.java                                                     *
* PURPOSE:  represents a shop full of different item types                 *
***************************************************************************/
package model;

import java.util.*;

public class Shop
{
	// Class Constants
	public static final double SALE_PRICE_MULTIPLIER = 0.5;
	
	private List<Weapon> weapons;
	private List<Armour> armour;
	private List<Potion> potions;
	private List<Enchantment> enchantments;
	
	private List<String> weaponNames;
	private List<String> armourNames;
	private List<String> potionNames;
	private List<String> enchantmentNames;
	
	private List<Integer> weaponCosts;
	private List<Integer> armourCosts;
	private List<Integer> potionCosts;
	private List<Integer> enchantmentCosts;
	
	
	public Shop()
	{
		weapons = new ArrayList<Weapon>();
		armour = new ArrayList<Armour>();
		potions = new ArrayList<Potion>();
		enchantments = new ArrayList<Enchantment>();
		
		weaponNames = new ArrayList<String>();
		armourNames = new ArrayList<String>();
		potionNames = new ArrayList<String>();
		enchantmentNames = new ArrayList<String>();
		
		weaponCosts = new ArrayList<Integer>();
		armourCosts = new ArrayList<Integer>();
		potionCosts = new ArrayList<Integer>();
		enchantmentCosts = new ArrayList<Integer>();
	}
	
	public List<Weapon> getWeapons()
	{
		return Collections.unmodifiableList(weapons);
	}
	
	public void addWeapon(Weapon newWeapon)
	{
		weapons.add(newWeapon);
		weaponNames.add(newWeapon.getName());
		weaponCosts.add(newWeapon.getCost());
	}
	
	public List<Armour> getArmour()
	{
		return Collections.unmodifiableList(armour);
	}
	
	public void addArmour(Armour newArmour)
	{
		armour.add(newArmour);
		armourNames.add(newArmour.getName());
		armourCosts.add(newArmour.getCost());
	}

	public List<Potion> getPotions()
	{
		return Collections.unmodifiableList(potions);
	}
	
	public void addPotion(Potion newPotion)
	{
		potions.add(newPotion);
		potionNames.add(newPotion.getName());
		potionCosts.add(newPotion.getCost());
	}
	
	public List<Enchantment> getEnchantments()
	{
		return Collections.unmodifiableList(enchantments);
	}	
	
	public void addEnchantment(int index, Enchantment newEnchantment)
	{
		enchantments.add(index, newEnchantment);
		enchantmentNames.add(index, newEnchantment.getEnchantmentName());
		enchantmentCosts.add(index, newEnchantment.getEnchantmentCost());
	}
	
	public List<String> getWeaponNames() 
	{

		return Collections.unmodifiableList(weaponNames);
	}
	
	public List<Integer> getWeaponCosts()
	{
		return Collections.unmodifiableList(weaponCosts);
	}
	
	public List<String> getArmourNames() 
	{

		return Collections.unmodifiableList(armourNames);
	}
	
	public List<Integer> getArmourCosts()
	{
		return Collections.unmodifiableList(armourCosts);
	}
	
	public List<String> getPotionNames() 
	{

		return Collections.unmodifiableList(potionNames);
	}
	
	public List<Integer> getPotionCosts() 
	{
		return Collections.unmodifiableList(potionCosts);
	}
	
	public List<String> getEnchantmentNames() 
	{

		return Collections.unmodifiableList(enchantmentNames);
	}
	
	public List<Integer> getEnchantmentCosts() 
	{
		return Collections.unmodifiableList(enchantmentCosts);
	}
	
	public List<Integer> getSalePrices(Character player)
	{
		List<Integer> salePrices = player.getItemCosts();
		for (int i = 0; i < salePrices.size(); i++)
		{
			salePrices.set(i, (int) Math.round(salePrices.get(i) * SALE_PRICE_MULTIPLIER));
		}
		
		return salePrices;
	}

	public Item browseItem(char itemType, int itemIndex)
	{
		Item item = new Weapon();
		
		switch (itemType)
		{
			case 'W':
			{
				item = weapons.get(itemIndex - 1);
				
			}
			break;
			
			case 'A':
			{
				item = armour.get(itemIndex - 1);
			}
			break;
			
			case 'P':
			{
				item = potions.get(itemIndex - 1);
			}
			break;
		}
		Item clonedItem = item.clone();
		return clonedItem;
		
	}				
	
	public Enchantment browseEnchantment(int enchantmentIndex)
	{
		
		return enchantments.get(enchantmentIndex - 1);
	}
	
	public DamageDealer addEnchantmentToWeapon(Character player, DamageDealer weapon, int enchantmentIndex)
	{
		DamageDealer enchantedWeapon;
		
		enchantmentIndex = enchantmentIndex - 1;
		switch (enchantmentIndex)
		{
			case 0:
			{
				enchantedWeapon = new DamageAdd2(weapon);
			}
			break;
			
			case 1:
			{
				enchantedWeapon = new DamageAdd5(weapon);
			}
			break;
			
			case 2:
			{
				enchantedWeapon = new FireDamage(weapon);
			}
			break;
			
			case 3:
			{
				enchantedWeapon = new PowerUp(weapon);
			}
			break;
			
			default:
			{
				enchantedWeapon = null;
			}
		}
		
		if (enchantedWeapon != null)
		{
			if (enchantedWeapon.getCost() > player.getGold())
			{
				enchantedWeapon = null;
			}
		}

		return enchantedWeapon;
	}		
	
				
	public Item authorisePurchase(Character player, Item requestedItem)
	{
		Item purchasedItem;
		
		if (requestedItem.getCost() > player.getGold())
		{
			purchasedItem = null;
		}
		else
		{
			purchasedItem = requestedItem;
		}
		
		return purchasedItem;
	}
				
	public void authoriseSale(Character player, Item unwantedItem)
	{
		player.removeItem(unwantedItem);
		double salePrice = unwantedItem.getCost() * SALE_PRICE_MULTIPLIER;
		player.receiveGold((int) salePrice);
	}
	

	public String toString()
	{
		String str = "Weapons:\n";
		String weaponsList = String.join(", ", weaponNames);
		str = str + weaponsList;
		
		str = str + "\n\nArmour:\n";
		String armourList = String.join(", ", armourNames);
		str = str + armourList;

		str = str + "\n\nPotions:\n";
		String potionsList = String.join(", ", potionNames);
		str = str + potionsList;
		
		
		str = str + "\n\nEnchantments:\n";
		String enchantmentsList = String.join(", ", enchantmentNames);
		str = str + enchantmentsList;
		
		return str;
	}			
			
}
	
	