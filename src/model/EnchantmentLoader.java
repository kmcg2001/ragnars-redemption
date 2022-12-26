/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: EnchantmentLoader.java                                        *
* PURPOSE: loads enchantments to shop                                      *
***************************************************************************/
package model;

import java.util.*;

public class EnchantmentLoader
{
	private Shop shop;
	
	public EnchantmentLoader(Shop inShop)
	{
		shop = inShop;
	}
	
	public void load()
	{
		shop.addEnchantment(0, new DamageAdd2());
		shop.addEnchantment(1, new DamageAdd5());
		shop.addEnchantment(2, new FireDamage());
		shop.addEnchantment(3, new PowerUp());
	}
}
		
