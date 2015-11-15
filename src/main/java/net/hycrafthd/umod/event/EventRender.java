package net.hycrafthd.umod.event;

import java.lang.reflect.Field;

import net.hycrafthd.umod.UPotion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventRender {

	@SubscribeEvent
	public void onEntityUpdate(RenderGameOverlayEvent event) {
		// Only for Client! (Not ready!)
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayerSP sp = mc.thePlayer;
		if (mc.inGameHasFocus) {
			if (sp != null) {
				if (sp.isPotionActive(UPotion.radiationPotion)) {
					try {

						float f2 = mc.thePlayer.prevTimeInPortal + (mc.thePlayer.timeInPortal - mc.thePlayer.prevTimeInPortal) * event.partialTicks;
						f2 = MathHelper.sin(event.partialTicks);
						System.out.println(f2);
						if (f2 > 0.0F) {
							byte b0 = 7;

							float f3 = 5.0F / (f2 * f2 + 5.0F) - f2 * 0.04F;
							f3 *= f3;
							System.out.println("test");
							GlStateManager.popMatrix();
							GlStateManager.rotate(((float) event.partialTicks) * (float) b0, 0.0F, 1.0F, 1.0F);
							GlStateManager.scale(1.0F / f3, 1.0F, 1.0F);
							GlStateManager.rotate(-((float) event.partialTicks) * (float) b0, 0.0F, 1.0F, 1.0F);
							GlStateManager.pushMatrix();
							
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}

	}

}
