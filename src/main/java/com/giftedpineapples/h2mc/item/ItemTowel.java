package com.giftedpineapples.h2mc.item;

import com.giftedpineapples.h2mc.block.BlockTowel;
import com.giftedpineapples.h2mc.init.ModBlocks;
import com.giftedpineapples.h2mc.init.ModItems;
import com.giftedpineapples.h2mc.reference.Names;
import com.giftedpineapples.h2mc.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ItemTowel extends ItemH2MC {

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public ItemTowel()
	{
		super();
		this.setUnlocalizedName(Names.Items.TOWEL);
		this.setHasSubtypes(true);
	}

	public static List<ItemStack> getTowels()
	{
		List<ItemStack> towelStacks = new ArrayList<ItemStack>();

		for (int meta = 0; meta < Names.Items.TOWEL_SUBTYPES.length; meta++)
		{
			towelStacks.add(new ItemStack(ModItems.towel, 1, meta));
		}

		return towelStacks;
	}

	@Override
	public String getUnlocalizedName()
	{
		return String.format("item.%s%s", Textures.RESOURCE_PREFIX, Names.Items.TOWEL);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return String.format("item.%s%s.%s", Textures.RESOURCE_PREFIX, Names.Items.TOWEL, Names.Items.TOWEL_SUBTYPES[MathHelper.clamp_int(itemStack.getItemDamage(), 0, Names.Items.TOWEL_SUBTYPES.length - 1)]);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTab, List list)
	{
		for (int meta = 0; meta < Names.Items.TOWEL_SUBTYPES.length; ++meta)
		{
			list.add(new ItemStack(this, 1, meta));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		return icons[MathHelper.clamp_int(meta, 0, Names.Items.TOWEL_SUBTYPES.length - 1)];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		icons = new IIcon[Names.Items.TOWEL_SUBTYPES.length];

		for (int i = 0; i < Names.Items.TOWEL_SUBTYPES.length; i++)
		{
			icons[i] = iconRegister.registerIcon(Textures.RESOURCE_PREFIX + Names.Items.TOWEL + "." + Names.Items.TOWEL_SUBTYPES[i]);
		}
	}

	/**
	 * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
	 * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
	 */
	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int worldX, int worldY, int worldZ, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
	{
		if (world.isRemote)
		{
			return true;
		}
		else if (p_77648_7_ != 1)
		{
			return false;
		}
		else
		{
			++worldY;
			BlockTowel blockTowel = (BlockTowel) ModBlocks.towelBlock;
			int i1 = MathHelper.floor_double((double) (entityPlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
			byte b0 = 0;
			byte b1 = 0;

			if (i1 == 0)
			{
				b1 = 1;
			}

			if (i1 == 1)
			{
				b0 = -1;
			}

			if (i1 == 2)
			{
				b1 = -1;
			}

			if (i1 == 3)
			{
				b0 = 1;
			}

			if (entityPlayer.canPlayerEdit(worldX, worldY, worldZ, p_77648_7_, itemStack) && entityPlayer.canPlayerEdit(worldX + b0, worldY, worldZ + b1, p_77648_7_, itemStack))
			{
				if (world.isAirBlock(worldX, worldY, worldZ) && world.isAirBlock(worldX + b0, worldY, worldZ + b1) && World.doesBlockHaveSolidTopSurface(world, worldX, worldY - 1, worldZ) && World.doesBlockHaveSolidTopSurface(world, worldX + b0, worldY - 1, worldZ + b1))
				{
					world.setBlock(worldX, worldY, worldZ, blockTowel, i1, 3);

					if (world.getBlock(worldX, worldY, worldZ) == blockTowel)
					{
						world.setBlock(worldX + b0, worldY, worldZ + b1, blockTowel, i1 + 8, 3);
					}

					--itemStack.stackSize;
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
	}

}