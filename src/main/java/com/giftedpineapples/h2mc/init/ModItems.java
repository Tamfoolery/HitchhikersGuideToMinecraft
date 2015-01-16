package com.giftedpineapples.h2mc.init;

import com.giftedpineapples.h2mc.item.ItemH2MC;
import com.giftedpineapples.h2mc.item.ItemTheGuide;
import com.giftedpineapples.h2mc.item.ItemTowel;
import com.giftedpineapples.h2mc.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {

	public static final ItemH2MC theGuide = new ItemTheGuide();
	public static final ItemH2MC towel = new ItemTowel();

	public static void init()
	{
		GameRegistry.registerItem(theGuide, Names.Items.THE_GUIDE);
		GameRegistry.registerItem(towel, Names.Items.TOWEL);
	}

}
