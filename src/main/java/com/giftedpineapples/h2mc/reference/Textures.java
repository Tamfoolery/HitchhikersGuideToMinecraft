package com.giftedpineapples.h2mc.reference;

import com.giftedpineapples.h2mc.utility.ResourceLocationHelper;
import net.minecraft.util.ResourceLocation;

public final class Textures {

	public static final String RESOURCE_PREFIX = Reference.MOD_ID.toLowerCase() + ":";

	public static final class Gui
	{
		private static final String GUI_SHEET_LOCATION = "textures/gui/";
		private static final String THE_GUIDE_LOCATION = GUI_SHEET_LOCATION + "theGuide/";

		public static final class theGuide
		{
			public static final ResourceLocation BORDER_TOP = ResourceLocationHelper.getResourceLocation(THE_GUIDE_LOCATION + "topBorder.png");
			public static final ResourceLocation BORDER_RIGHT = ResourceLocationHelper.getResourceLocation(THE_GUIDE_LOCATION + "rightBorder.png");
			public static final ResourceLocation BORDER_BOTTOM = ResourceLocationHelper.getResourceLocation(THE_GUIDE_LOCATION + "bottomBorder.png");
			public static final ResourceLocation BORDER_LEFT = ResourceLocationHelper.getResourceLocation(THE_GUIDE_LOCATION + "leftBorder.png");
			public static final ResourceLocation CORNERS = ResourceLocationHelper.getResourceLocation(THE_GUIDE_LOCATION + "corners.png");
		}
	}

}