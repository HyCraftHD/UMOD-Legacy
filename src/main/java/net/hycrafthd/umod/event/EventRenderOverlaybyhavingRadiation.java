package net.hycrafthd.umod.event;

import net.hycrafthd.umod.UPotion;
import net.hycrafthd.umod.armor.ArmorRadiation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventRenderOverlaybyhavingRadiation {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onGameOverlayEvent(RenderGameOverlayEvent event) {
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayerSP sp = mc.thePlayer;
		if (mc.inGameHasFocus) {
			if (sp != null) {
				if (sp.isPotionActive(UPotion.radiationPotion)) {

					try {
						boolean full = false;
						for (ItemStack armor : sp.inventory.armorInventory) {
							if (armor != null && (armor.getItem() instanceof ArmorRadiation)) {
								full = false;
							} else {
								full = true;
								break;
							}
						}
						if (full) {

							sp.timeInPortal += 0.006666667F;

							if (sp.timeInPortal > 1.0F) {
								sp.timeInPortal = 1.0F;
							}
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				}
			}
		}

	}

}
