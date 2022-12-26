/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: DamageDealer.java                                             *
* PURPOSE: decoration interface for weapon and enchantments                *
***************************************************************************/
package model;

public interface DamageDealer extends Item
{
	String getName();
	int getCost();
	String toString();
	int calcEffect();
	Item clone();

}