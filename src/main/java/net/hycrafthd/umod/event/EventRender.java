package net.hycrafthd.umod.event;

import net.hycrafthd.umod.UPotion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventRender{

	@SideOnly(Side.CLIENT)
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
							sp.cameraPitch = f3;
							sp.cameraYaw = f2;
							
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}

	}

}
