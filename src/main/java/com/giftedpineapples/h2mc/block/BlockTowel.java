package com.giftedpineapples.h2mc.block;

import com.giftedpineapples.h2mc.init.ModItems;
import com.giftedpineapples.h2mc.reference.Names;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.Iterator;
import java.util.Random;

public class BlockTowel extends BlockH2MCDirectional {

	public static final int[][] partCoordinates = new int[][] {{0, 1}, { -1, 0}, {0, -1}, {1, 0}};
	@SideOnly(Side.CLIENT)
	private IIcon[] endTexture;
	@SideOnly(Side.CLIENT)
	private IIcon[] sideTexture;
	@SideOnly(Side.CLIENT)
	private IIcon[] topTexture;

	public BlockTowel()
	{
		super(Material.cloth);

		this.setBlockName(Names.Blocks.TOWEL);
		this.setBlockTextureName(Names.Blocks.TOWEL);

		this.setHardness(0.0F);
		this.setLightOpacity(1);

		this.setStepSound(soundTypeCloth);

		this.setCreativeTab(null); // Hide from creative tab

		this.disableStats();
		this.setTowelBlockBounds();
	}

	private void setTowelBlockBounds()
	{
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.1F, 1.0F);
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	public boolean onBlockActivated(World world, int worldX, int worldY, int worldZ, EntityPlayer entityPlayer, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		if (world.isRemote)
		{
			return true;
		}
		else
		{
			int i1 = world.getBlockMetadata(worldX, worldY, worldZ);

			if (!isBlockHeadOfBed(i1))
			{
				int j1 = getDirection(i1);
				worldX += partCoordinates[j1][0];
				worldZ += partCoordinates[j1][1];

				if (world.getBlock(worldX, worldY, worldZ) != this)
				{
					return true;
				}

				i1 = world.getBlockMetadata(worldX, worldY, worldZ);
			}

			if (world.provider.canRespawnHere() && world.getBiomeGenForCoords(worldX, worldZ) != BiomeGenBase.hell)
			{
				if (isBlockFootOfBed(i1))
				{
					EntityPlayer entityPlayer1 = null;
					Iterator iterator = world.playerEntities.iterator();

					while (iterator.hasNext())
					{
						EntityPlayer entityPlayer2 = (EntityPlayer)iterator.next();

						if (entityPlayer2.isPlayerSleeping())
						{
							ChunkCoordinates chunkcoordinates = entityPlayer2.playerLocation;

							if (chunkcoordinates.posX == worldX && chunkcoordinates.posY == worldY && chunkcoordinates.posZ == worldZ)
							{
								entityPlayer1 = entityPlayer2;
							}
						}
					}

					if (entityPlayer1 != null)
					{
						entityPlayer.addChatComponentMessage(new ChatComponentTranslation("tile.bed.occupied", new Object[0]));
						return true;
					}

					setBlockMetaAccordingToPartOfBed(world, worldX, worldY, worldZ, false);
				}

				EntityPlayer.EnumStatus enumstatus = entityPlayer.sleepInBedAt(worldX, worldY, worldZ);

				if (enumstatus == EntityPlayer.EnumStatus.OK)
				{
					setBlockMetaAccordingToPartOfBed(world, worldX, worldY, worldZ, true);
					return true;
				}
				else
				{
					if (enumstatus == EntityPlayer.EnumStatus.NOT_POSSIBLE_NOW)
					{
						entityPlayer.addChatComponentMessage(new ChatComponentTranslation("tile.bed.noSleep", new Object[0]));
					}
					else if (enumstatus == EntityPlayer.EnumStatus.NOT_SAFE)
					{
						entityPlayer.addChatComponentMessage(new ChatComponentTranslation("tile.bed.notSafe", new Object[0]));
					}

					return true;
				}
			}
			else
			{
				double d2 = (double)worldX + 0.5D;
				double d0 = (double)worldY + 0.5D;
				double d1 = (double)worldZ + 0.5D;
				world.setBlockToAir(worldX, worldY, worldZ);
				int k1 = getDirection(i1);
				worldX += partCoordinates[k1][0];
				worldZ += partCoordinates[k1][1];

				if (world.getBlock(worldX, worldY, worldZ) == this)
				{
					world.setBlockToAir(worldX, worldY, worldZ);
					d2 = (d2 + (double)worldX + 0.5D) / 2.0D;
					d0 = (d0 + (double)worldY + 0.5D) / 2.0D;
					d1 = (d1 + (double)worldZ + 0.5D) / 2.0D;
				}

				world.newExplosion((Entity)null, (double)((float)worldX + 0.5F), (double)((float)worldY + 0.5F), (double)((float)worldZ + 0.5F), 5.0F, true, true);
				return true;
			}
		}
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (side == 0)
		{
			return Blocks.planks.getBlockTextureFromSide(side);
		}
		else
		{
			int k = getDirection(meta);
			int l = Direction.bedDirection[k][side];
			int i1 = isBlockHeadOfBed(meta) ? 1 : 0;
			return (i1 != 1 || l != 2) && (i1 != 0 || l != 3) ? (l != 5 && l != 4 ? this.topTexture[i1] : this.sideTexture[i1]) : this.endTexture[i1];
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.topTexture = new IIcon[] {iconRegister.registerIcon(this.getTextureName() + "_feet_top"), iconRegister.registerIcon(this.getTextureName() + "_head_top")};
		this.endTexture = new IIcon[] {iconRegister.registerIcon(this.getTextureName() + "_feet_end"), iconRegister.registerIcon(this.getTextureName() + "_head_end")};
		this.sideTexture = new IIcon[] {iconRegister.registerIcon(this.getTextureName() + "_feet_side"), iconRegister.registerIcon(this.getTextureName() + "_head_side")};
	}

	/**
	 * The type of render function that is called for this block
	 */
	public int getRenderType()
	{
		return 14;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		this.setTowelBlockBounds();
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
	 * their own) Args: x, y, z, neighbor Block
	 */
	public void onNeighborBlockChange(World world, int x, int y, int z, BlockH2MC block)
	{
		int l = world.getBlockMetadata(x, y, z);
		int i1 = getDirection(l);

		if (isBlockHeadOfBed(l))
		{
			if (world.getBlock(x - partCoordinates[i1][0], y, z - partCoordinates[i1][1]) != this)
			{
				world.setBlockToAir(x, y, z);
			}
		}
		else if (world.getBlock(x + partCoordinates[i1][0], y, z + partCoordinates[i1][1]) != this)
		{
			world.setBlockToAir(x, y, z);

			if (!world.isRemote)
			{
				this.dropBlockAsItem(world, x, y, z, l, 0);
			}
		}
	}

	public Item getItemDropped(int bedBlockPart, Random p_149650_2_, int p_149650_3_)
	{
		/**
		 * Returns whether or not this bed block is the head of the bed.
		 */
		return isBlockHeadOfBed(bedBlockPart) ? Item.getItemById(0) : ModItems.towel;
	}

	/**
	 * Returns whether or not this bed block is the head of the bed.
	 */
	public static boolean isBlockHeadOfBed(int partOfBed)
	{
		return (partOfBed & 8) != 0;
	}

	public static boolean isBlockFootOfBed(int partOfBed)
	{
		return (partOfBed & 4) != 0;
	}

	public static void setBlockMetaAccordingToPartOfBed(World world, int worldX, int worldY, int worldZ, boolean p_149979_4_)
	{
		int meta = world.getBlockMetadata(worldX, worldY, worldZ);

		if (p_149979_4_)
		{
			meta |= 4;
		}
		else
		{
			meta &= -5;
		}

		// Flag 4 prevents the block from being re-rendered
		world.setBlockMetadataWithNotify(worldX, worldY, worldZ, meta, 4);
	}

	public static ChunkCoordinates func_149977_a(World p_149977_0_, int p_149977_1_, int p_149977_2_, int p_149977_3_, int p_149977_4_)
	{
		int i1 = p_149977_0_.getBlockMetadata(p_149977_1_, p_149977_2_, p_149977_3_);
		int j1 = BlockH2MCDirectional.getDirection(i1);

		for (int k1 = 0; k1 <= 1; ++k1)
		{
			int l1 = p_149977_1_ - partCoordinates[j1][0] * k1 - 1;
			int i2 = p_149977_3_ - partCoordinates[j1][1] * k1 - 1;
			int j2 = l1 + 2;
			int k2 = i2 + 2;

			for (int l2 = l1; l2 <= j2; ++l2)
			{
				for (int i3 = i2; i3 <= k2; ++i3)
				{
					if (World.doesBlockHaveSolidTopSurface(p_149977_0_, l2, p_149977_2_ - 1, i3) && !p_149977_0_.getBlock(l2, p_149977_2_, i3).getMaterial().isOpaque() && !p_149977_0_.getBlock(l2, p_149977_2_ + 1, i3).getMaterial().isOpaque())
					{
						if (p_149977_4_ <= 0)
						{
							return new ChunkCoordinates(l2, p_149977_2_, i3);
						}

						--p_149977_4_;
					}
				}
			}
		}

		return null;
	}

	/**
	 * Drops the block items with a specified chance of dropping the specified items
	 */
	public void dropBlockAsItemWithChance(World world, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_)
	{
		if (!isBlockHeadOfBed(p_149690_5_))
		{
			super.dropBlockAsItemWithChance(world, p_149690_2_, p_149690_3_, p_149690_4_, p_149690_5_, p_149690_6_, 0);
		}
	}

	/**
	 * Returns the mobility information of the block, 0 = free, 1 = can't push but can move over, 2 = total immobility
	 * and stop pistons
	 */
	public int getMobilityFlag()
	{
		return 1;
	}

	/**
	 * Gets an item for the block being called on. Args: world, x, y, z
	 */
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z)
	{
		return ModItems.towel;
	}

	/**
	 * Called when the block is attempted to be harvested
	 */
	public void onBlockHarvested(World world, int p_149681_2_, int p_149681_3_, int p_149681_4_, int p_149681_5_, EntityPlayer entityPlayer)
	{
		if (entityPlayer.capabilities.isCreativeMode && isBlockHeadOfBed(p_149681_5_))
		{
			int i1 = getDirection(p_149681_5_);
			p_149681_2_ -= partCoordinates[i1][0];
			p_149681_4_ -= partCoordinates[i1][1];

			if (world.getBlock(p_149681_2_, p_149681_3_, p_149681_4_) == this)
			{
				world.setBlockToAir(p_149681_2_, p_149681_3_, p_149681_4_);
			}
		}
	}

}
