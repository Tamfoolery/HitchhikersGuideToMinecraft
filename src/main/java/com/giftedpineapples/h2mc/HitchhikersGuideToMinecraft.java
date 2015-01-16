package com.giftedpineapples.h2mc;

import com.giftedpineapples.h2mc.handler.ConfigurationHandler;
import com.giftedpineapples.h2mc.handler.GuiHandler;
import com.giftedpineapples.h2mc.init.ModBlocks;
import com.giftedpineapples.h2mc.init.ModItems;
import com.giftedpineapples.h2mc.init.Recipes;
import com.giftedpineapples.h2mc.proxy.IProxy;
import com.giftedpineapples.h2mc.reference.Reference;
import com.giftedpineapples.h2mc.utility.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class HitchhikersGuideToMinecraft {

	/**
	 * The instance of the mod
	 */
	@Mod.Instance(Reference.MOD_ID)
	public static HitchhikersGuideToMinecraft instance;

	/**
	 * Client-server proxy
	 */
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;

	/**
	 * Pre-init: Items, Blocks, Configs
	 *
	 * @param event FMLPreInitializationEvent
	 */
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{

		// Pass config file to handler
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

		// Register Items
		ModItems.init();

		// Register Blocks
		ModBlocks.init();

	}

	/**
	 * Init: GUI, Tile Entities, Crafting Recipes
	 *
	 * @param e FMLInitializationEvent
	 */
	@Mod.EventHandler
	public void init(FMLInitializationEvent e)
	{

		// Register the GUI Handler
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());

		// Register recipies
		Recipes.init();

	}

	/**
	 * Post-init: Wrap up, after all other mods have finished init
	 *
	 * @param e FMLPostInitializationEvent
	 */
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e)
	{
		/**  @@@@@
		 * _@@@@@@@_ <-- Totally a bowl of Petunias. Honest.
		 * \       /
		 *  \____*/
		LogHelper.info("Why is there a bowl of Petunias back here?");
		// TODO
	}

}