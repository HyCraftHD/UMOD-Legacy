package net.hycrafthd.umod.item.tools.emerald;

import java.util.List;

import net.hycrafthd.umod.UReference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEmeraldSpade extends ItemSpade {

	public ItemEmeraldSpade(ToolMaterial material) {
		super(material);
		setCreativeTab(UReference.tab);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		UReference.proxy.addTooltip(stack, player, tooltip, advanced);
	}

}
