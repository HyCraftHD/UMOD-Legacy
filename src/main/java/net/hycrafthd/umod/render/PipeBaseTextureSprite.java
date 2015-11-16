package net.hycrafthd.umod.render;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PipeBaseTextureSprite extends TextureAtlasSprite{

	protected PipeBaseTextureSprite(String spriteName) {
		super(spriteName);
	}

}
