/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: PotionFactory.java                                            * 
* PURPOSE: creates a desired instance of potion                            *
***************************************************************************/
package controller;

import model.Potion;
import model.HealingPotion;
import model.DamagingPotion;

public class PotionFactory
{	
	public Potion makePotion(char inType, String inName, int inMinEffect, int inMaxEffect, int inCost)
	{
		Potion potion = null;
		if (inType == 'H')
		{
			potion = new HealingPotion(inName, inMinEffect, inMaxEffect, inCost);
		}
		else if (inType == 'D')
		{
			potion = new DamagingPotion(inName, inMinEffect, inMaxEffect, inCost);
		}
		return potion;
	}
}