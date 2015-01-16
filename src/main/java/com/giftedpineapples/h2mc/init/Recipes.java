package com.giftedpineapples.h2mc.init;

import com.giftedpineapples.h2mc.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipes {

	public static void init()
	{

		// Towel Recipe
		for (int meta = 0; meta < Names.Items.TOWEL_SUBTYPES.length; meta++)
		{
			GameRegistry.addRecipe(new ItemStack(ModItems.towel, 1, meta), "ww ", "ww ", "ww ", 'w', new ItemStack(Blocks.wool, 1, meta));
		}

		// Guide Recipe
		for (int meta = 0; meta < Names.Items.TOWEL_SUBTYPES.length; meta++)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(ModItems.theGuide), new ItemStack(ModItems.towel, 1, meta), new ItemStack(Items.book));
		}

	}

}
