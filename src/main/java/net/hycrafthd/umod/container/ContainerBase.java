package net.hycrafthd.umod.container;

import java.util.ArrayList;
import java.util.List;

import net.hycrafthd.umod.UMod;
import net.hycrafthd.umod.enumtype.EnumTypeGui;
import net.hycrafthd.umod.inventory.BaseBatteryInputSlot;
import net.hycrafthd.umod.inventory.BaseSlot;
import net.hycrafthd.umod.render.RGBA;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ContainerBase extends Container{

	public TileEntity ent;
	public EntityPlayer pls;
	public BlockPos pos;
	public World worldObj;
	public Mode mode;
	public boolean B;
	
	public ContainerBase(IInventory inv,EntityPlayer pl,BlockPos pos,World wo,boolean b) {
		 this.ent = (TileEntity) inv;
		  this.pls = pl;
         this.pos = pos;
         this.worldObj = wo;
         mode = Mode.NORMAL;
         if(b){
         BaseSlot sl = new BaseBatteryInputSlot(inv, inv.getSizeInventory() - 1, 80, 28);
         sl.setVisible(false);
         RGBA back = new RGBA(255, 0, 0, 125);
         RGBA nback = new RGBA(255, 0, 0, 50);
         sl.setHoverColor(nback,nback,back, back);
         super.addSlotToContainer(sl);
	     }
         B = b;
	}

	public ContainerBase(IInventory inv,EntityPlayer pl,BlockPos pos,World wo) {
	 this(inv,pl,pos,wo,true);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
	
	@Override
	protected void retrySlotClick(int p_75133_1_, int p_75133_2_, boolean p_75133_3_, EntityPlayer p_75133_4_) {}
	
	@Override
	public void onContainerClosed(EntityPlayer entityPlayer)
	{
		super.onContainerClosed(entityPlayer);
		((IInventory) this.ent).closeInventory(entityPlayer);
	}
	
	@Override
	public boolean canDragIntoSlot(Slot p_94531_1_) {
		return true;
	}
	
	@Override
	public ItemStack slotClick(int slotId, int clickedButton, int mode, EntityPlayer playerIn) {
		return super.slotClick(slotId, clickedButton, mode, playerIn);
	}

	public void setVisisble(int i,boolean b){
		if(inventorySlots.get(i) instanceof BaseSlot){
			((BaseSlot)inventorySlots.get(i)).setVisible(b);
		}
	}
		
	public static enum Mode{
		
		NORMAL(0),BATTERY(1),OUTPUT(2);
		
		public int getID() {
			return id;
		}

		public static Mode byID(int id) {
			if (id < 0 || id >= all.length) {
				id = 0;
			}
			return all[id];
		}
		
		public static Mode getTurndMode(Mode m){
			switch(m){
			case BATTERY:
				return OUTPUT;
			case NORMAL:
				return BATTERY;
			case OUTPUT:
				return NORMAL;
			default:
				return NORMAL;
			}
		}

		private int id;

		private static final Mode[] all = new Mode[values().length];

		private Mode(int id) {
			this.id = id;
		}

		static {
			for (Mode type : values()) {
				all[type.getID()] = type;
			}
		}
		
	}
	
	public void setMode(Mode m){
		mode = m;
	}

}
