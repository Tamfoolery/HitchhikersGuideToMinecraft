package com.giftedpineapples.h2mc.creativetab;

import com.giftedpineapples.h2mc.init.ModItems;
import com.giftedpineapples.h2mc.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabH2MC {

	public static final CreativeTabs H2MC_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase()) {

		@Override
		public Item getTabIconItem() {
			return ModItems.theGuide;
		}

	};

}
