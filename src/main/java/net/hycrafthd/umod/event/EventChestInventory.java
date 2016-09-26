package net.hycrafthd.umod.event;

import net.minecraft.block.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.*;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventChestInventory {

	public static final String KEY = "key.chest";
	
	@SubscribeEvent
	public void onChestDrop(BlockEvent.BreakEvent ev){
		if(ev.state.getBlock() instanceof BlockChest){
			TileEntity et = ev.world.getTileEntity(ev.pos);
			if(et != null && et instanceof TileEntityChest){
				TileEntityChest chest = (TileEntityChest) et;
				NBTTagCompound comp = new NBTTagCompound();
				chest.writeToNBT(comp);
				chest.clear();
				ItemStack st = new ItemStack(Blocks.chest, 1);
				st.setTagInfo(KEY, comp);
				Block.spawnAsEntity(ev.world, ev.pos, st);
				ev.world.setBlockToAir(ev.pos);
				ev.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent
	public void onChestPlaced(BlockEvent.PlaceEvent ev){
		if(ev.state.getBlock() instanceof BlockChest){
			TileEntity et = ev.world.getTileEntity(ev.pos);
			if(et != null && et instanceof TileEntityChest){
				TileEntityChest chest = (TileEntityChest) et;
				NBTTagCompound comp = ev.itemInHand.getSubCompound(KEY, false);
				if(comp != null){
					chest.readFromNBT(comp);
					chest.setPos(ev.pos);
					chest.setWorldObj(ev.world);
					EntityPlayer pl = ev.player;
					ItemStack st = pl.getCurrentEquippedItem();
					if(st != null){
						if(st.stackSize > 1){
							pl.getCurrentEquippedItem().stackSize--;
						}else{
							pl.inventory.mainInventory[pl.inventory.currentItem] = null;
						}
					}
				}
			}
		}
	}
}
