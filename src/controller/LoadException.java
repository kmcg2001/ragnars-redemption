/***************************************************************************
* AUTHOR: Kade McGarraghy                                                  *
* LAST MODIFIED: 31/05/20                                                  *
* FILE NAME: LoadException.java                                            * 
* PURPOSE: custom exception for LoadShopFromSource to throw                *
***************************************************************************/
package controller;

public class LoadException extends Exception
{
	public LoadException (String msg)
	{
		super(msg);
	}

	public LoadException (String msg, Throwable cause)
	{
		super(msg, cause);
	}
}