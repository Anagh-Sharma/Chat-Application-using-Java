package com.anaghdev.chatapp.utils;

import java.util.ResourceBundle;

public class ConfigReader 
{
	/*
	Resource bundles contain locale-specific objects. 
	When your program needs a locale-specific resource, 
	a String for example, your program can load it from 
	the resource bundle that is appropriate for the 
	current user's locale. In this way, you can write 
	program code that is largely independent of the user's 
	locale isolating most, if not all, of the locale-specific 
	information in resource bundles.
	*/
	private static ResourceBundle rb = ResourceBundle.getBundle("config");
	/*
	 * getValue function will be passed an attribute (key) 
	 * and will return its corresponding value.
	 */
	public static String getValue(String key)
	{
		return rb.getString(key);
	}
}


