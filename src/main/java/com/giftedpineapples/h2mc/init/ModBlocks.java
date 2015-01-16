package com.giftedpineapples.h2mc.init;

import com.giftedpineapples.h2mc.block.BlockH2MC;
import com.giftedpineapples.h2mc.block.BlockTowel;
import com.giftedpineapples.h2mc.reference.Names;
import com.giftedpineapples.h2mc.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

	public static final BlockH2MC towelBlock = new BlockTowel();

	public static void init()
	{
		GameRegistry.registerBlock(towelBlock, Names.Blocks.TOWEL);
	}

}
