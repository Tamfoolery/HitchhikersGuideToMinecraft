package com.giftedpineapples.h2mc.client.gui.inventory;

import com.giftedpineapples.h2mc.inventory.ContainerTheGuide;
import com.giftedpineapples.h2mc.reference.Textures;
import org.lwjgl.opengl.GL11;

public class GuiTheGuide extends GuiH2MC {

	public GuiTheGuide()
	{
		super(new ContainerTheGuide());
		xSize = 8;
		ySize = 8;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int x, int y)
	{
		int padding = 18;

		// Top Outline
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(Textures.Gui.theGuide.BORDER_TOP);
		this.drawTexturedModalRect((padding + 1), padding, 0, 0, (width - (padding * 3) + 2), ySize);

		// Right Outline
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(Textures.Gui.theGuide.BORDER_RIGHT);
		this.drawTexturedModalRect((width - (padding * 2) + 3), (padding + 3), 0, 0, xSize, (height - (padding * 3)));

		// Left Outline
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(Textures.Gui.theGuide.BORDER_LEFT);
		this.drawTexturedModalRect(padding, (padding + 1), 0, 0, xSize, (height - (padding * 3) + 2));

		// Bottom Outline
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(Textures.Gui.theGuide.BORDER_BOTTOM);
		this.drawTexturedModalRect((padding + 2), (height - (padding * 2) + 3), 0, 0, (width - (padding * 3) + 1), ySize);

		// Top Left Corner
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(Textures.Gui.theGuide.CORNERS);
		this.drawTexturedModalRect(padding, padding, 0, 0, xSize, ySize);

		// Top Right Corner
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(Textures.Gui.theGuide.CORNERS);
		this.drawTexturedModalRect((width - (padding * 2) + 3), padding, (xSize + 2), 0, (xSize + 1), ySize);

		// Bottom Left Corner
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(Textures.Gui.theGuide.CORNERS);
		this.drawTexturedModalRect(padding, (height - (padding * 2) + 3), 0, (ySize + 2), xSize, ySize);

		// Bottom Right Corner
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(Textures.Gui.theGuide.CORNERS);
		this.drawTexturedModalRect((width - (padding * 2) + 3), (height - (padding * 2) + 3), (xSize + 2), (ySize + 2), xSize, ySize);
	}

}
