/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: LoadShopFromSource.java                                       * 
* PURPOSE: interface for loading shop                                      *
***************************************************************************/
package controller;

import model.Shop;

import java.io.*;

public interface LoadShopFromSource
{
	void load() throws LoadException;
}