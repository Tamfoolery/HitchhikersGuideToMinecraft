package com.giftedpineapples.h2mc.handler;

import com.giftedpineapples.h2mc.reference.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler {

	public static Configuration configuration;

	public static boolean enableWhales = true;
	public static boolean enablePetunias = true;

	public static void init(File configFile)
	{

		// Create the config object from the file
		if (configuration == null)
		{
			configuration = new Configuration(configFile);
			loadConfiguration();
		}

	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
		{
			loadConfiguration();
		}
	}

	private static void loadConfiguration()
	{

		enableWhales = configuration.getBoolean("enableWhales", Configuration.CATEGORY_GENERAL, true, "Hello, ground!");
		enablePetunias = configuration.getBoolean("enablePetunias", Configuration.CATEGORY_GENERAL, true, "Oh no, not again.");

		if (configuration.hasChanged()) configuration.save();

	}

}
