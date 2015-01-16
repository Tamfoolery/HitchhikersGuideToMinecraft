package com.giftedpineapples.h2mc.block;

import net.minecraft.block.material.Material;

public class BlockH2MCDirectional extends BlockH2MC {

	protected BlockH2MCDirectional(Material p_i45401_1_)
	{
		super(p_i45401_1_);
	}

	public static int getDirection(int p_149895_0_)
	{
		return p_149895_0_ & 3;
	}

}
