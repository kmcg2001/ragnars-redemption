/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: Item.java                                                     *
* PURPOSE:  interface for item                                             *
***************************************************************************/
package model;

import java.util.*;

public interface Item  
{

	String getName();
	int getCost();
	String toString();
	int calcEffect();
	Item clone();
	boolean equals(Object inObj);
}

 



