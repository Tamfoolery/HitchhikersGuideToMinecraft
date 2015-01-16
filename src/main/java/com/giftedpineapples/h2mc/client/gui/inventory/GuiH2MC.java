package com.giftedpineapples.h2mc.client.gui.inventory;

import com.giftedpineapples.h2mc.utility.ResourceLocationHelper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiH2MC extends GuiContainer {

	protected ResourceLocation guiTexture;

	public GuiH2MC(Container container)
	{
		super(container);
	}

	public GuiH2MC(Container container, String texture)
	{
		this(container, ResourceLocationHelper.getResourceLocation(texture));
	}

	public GuiH2MC(Container container, ResourceLocation guiTexture)
	{
		super(container);
		this.guiTexture = guiTexture;
	}

	@Override
	public void initGui()
	{
		super.initGui();
	}

	@Override
	public void drawScreen(int x, int y, float partialTick)
	{
		super.drawScreen(x, y, partialTick);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		super.drawGuiContainerForegroundLayer(x, y);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int x, int y)
	{
		//
	}

	@Override
	protected void keyTyped(char characterTyped, int keyPressed)
	{
		if (keyPressed == 1 || keyPressed == mc.gameSettings.keyBindInventory.getKeyCode()) mc.thePlayer.closeScreen();
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		if (!mc.thePlayer.isEntityAlive() || mc.thePlayer.isDead) mc.thePlayer.closeScreen();
	}

	@Override
	public void handleMouseInput()
	{
		//
	}

	@Override
	protected void mouseClicked(int mX, int mY, int mouseButton)
	{
		//
	}

	@Override
	protected void mouseMovedOrUp(int mX, int mY, int mouseButton)
	{
		//
	}

	@Override
	protected void mouseClickMove(int mX, int mY, int lastClick, long timeSinceClick)
	{
		//
	}

}
