package net.hycrafthd.umod.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

public class ExtendedFontRender extends FontRenderer {
	
	public ExtendedFontRender() {
		super(Minecraft.getMinecraft().gameSettings, new ResourceLocation("textures/font/ascii.png"), Minecraft.getMinecraft().getTextureManager(), false);
	}
	
	protected float renderDefaultChar(int p_78266_1_, boolean p_78266_2_) {
		float f = (float) (p_78266_1_ % 16 * 8);
		float f1 = (float) (p_78266_1_ / 16 * 8);
		float f2 = p_78266_2_ ? 1.0F : 0.0F;
		bindTexture(this.locationFontTexture);
		float f3 = (float) this.charWidth[p_78266_1_] - 0.01F;
		GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
		GL11.glTexCoord2f(f / 128.0F, f1 / 128.0F);
		GL11.glVertex3f(this.posX + f2, this.posY, 0);
		GL11.glTexCoord2f(f / 128.0F, (f1 + 7.99F) / 128.0F);
		GL11.glVertex3f(this.posX - f2, this.posY + 7.99F, 0);
		GL11.glTexCoord2f((f + f3 - 1.0F) / 128.0F, f1 / 128.0F);
		GL11.glVertex3f(this.posX + f3 - 1.0F + f2, this.posY, 0);
		GL11.glTexCoord2f((f + f3 - 1.0F) / 128.0F, (f1 + 7.99F) / 128.0F);
		GL11.glVertex3f(this.posX + f3 - 1.0F - f2, this.posY + 7.99F, 0);
		GL11.glEnd();
		return (float) this.charWidth[p_78266_1_];
	}
	
}
