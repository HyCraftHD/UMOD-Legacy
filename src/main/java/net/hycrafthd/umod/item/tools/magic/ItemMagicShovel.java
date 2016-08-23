package net.hycrafthd.umod.item.tools.magic;

import java.util.List;

import net.hycrafthd.umod.UReference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraftforge.fml.relauncher.*;

public class ItemMagicShovel extends ItemSpade{

	public ItemMagicShovel(ToolMaterial material) {
		super(material);
		setCreativeTab(UReference.magic);
	}

	@SuppressWarnings("rawtypes")
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		UReference.proxy.addTooltip(stack, player, tooltip, advanced);
	}
	
}
