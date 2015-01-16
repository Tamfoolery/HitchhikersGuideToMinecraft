package com.giftedpineapples.h2mc.handler;

import com.giftedpineapples.h2mc.client.gui.inventory.GuiTheGuide;
import com.giftedpineapples.h2mc.inventory.ContainerTheGuide;
import com.giftedpineapples.h2mc.reference.GUIs;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z)
	{
		if (id == GUIs.THE_GUIDE.ordinal())
		{
//			return new ContainerTheGuide(entityPlayer.inventory);
			return new ContainerTheGuide();
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z)
	{
		if (id == GUIs.THE_GUIDE.ordinal())
		{
//			return new ContainerTheGuide(entityPlayer.inventory);
			return new GuiTheGuide();
		}

		return null;
	}

}
