package com.giftedpineapples.h2mc.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public abstract class ContainerH2MC extends Container {

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer)
	{
		return true;
	}

}
