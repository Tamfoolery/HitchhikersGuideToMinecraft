package com.giftedpineapples.h2mc.item;

import com.giftedpineapples.h2mc.HitchhikersGuideToMinecraft;
import com.giftedpineapples.h2mc.reference.GUIs;
import com.giftedpineapples.h2mc.reference.Names;
import com.giftedpineapples.h2mc.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTheGuide extends ItemH2MC {

	public ItemTheGuide()
	{
		super();
		this.setUnlocalizedName(Names.Items.THE_GUIDE);
	}

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		openTheGuide("Index", entityPlayer);
		return itemStack;
	}

	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int worldX, int worldY, int worldZ, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
	{
		Block block = world.getBlock(worldX, worldY, worldZ);

		if (block != null && !entityPlayer.isSneaking() && !world.isRemote) return false;

		LogHelper.info(block.getUnlocalizedName());
		openTheGuide("Slug", entityPlayer);
		return true;
	}

	private void openTheGuide(String slug, EntityPlayer entityPlayer)
	{
		LogHelper.info("Opened The Guide to: " + slug);
		entityPlayer.openGui(HitchhikersGuideToMinecraft.instance, GUIs.THE_GUIDE.ordinal(), entityPlayer.worldObj, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
	}

}
