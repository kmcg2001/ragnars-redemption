import model.Character;
import model.*;
import view.*;
import controller.*;


public class RagnarsRedemption
{
	public static void main (String[] args) throws LoadException
	{
		Character player = new Character();
		Shop shop = new Shop();
		UserInterface ui = new UserInterface();
		EnemyManager enemyManager = new EnemyManager();
		
		try
		{
			LoadShopFromSource loadShopFromFile = new LoadShopFromFile(shop, player, ui);
			loadShop(loadShopFromFile, shop, ui);
			
			Engine engine = new Engine(player, shop, ui, enemyManager);
			
			engine.gameMenu();
		}
		catch (LoadException e)
		{
			ui.showException("Error loading shop: ", e.getMessage());
		}

	}
	
	private static void loadShop(LoadShopFromSource loadShopFromSource, Shop shop, UserInterface ui) throws LoadException
	{
		try
		{
			LoadShopFromSource shopLoader = loadShopFromSource;
			shopLoader.load();
			EnchantmentLoader enchantmentLoader = new EnchantmentLoader(shop);
			enchantmentLoader.load();
		}
		catch (LoadException e)
		{
			throw new LoadException(e.getMessage());
		}
	}
	
}