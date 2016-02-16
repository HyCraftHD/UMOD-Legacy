package net.hycrafthd.umod.item;

import java.util.List;

import net.hycrafthd.umod.utils.TileNBTUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemBattery extends ItemBase {

	public ItemBattery() {
		this.setMaxDamage(500);
		this.setMaxStackSize(1);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side,
			float hitX, float hitY, float hitZ) {
		TileEntity ent = worldIn.getTileEntity(pos);
		if(ent != null){
			NBTTagCompound tag = new NBTTagCompound();
			ent.writeToNBT(tag);
			TileNBTUtils.saveNBT(tag, worldIn.getBlockState(pos).getBlock().getLocalizedName());
			return true;
		}
		return false;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, @SuppressWarnings("rawtypes") List tooltip, boolean advanced) {
	}

}
