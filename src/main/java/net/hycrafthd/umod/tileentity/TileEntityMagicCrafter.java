package net.hycrafthd.umod.tileentity;


import net.hycrafthd.umod.api.*;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMagicCrafter extends TileEntity implements IUpdatePlayerListBox{

	public InventoryBasic inv;
	ItemStack input;
	ItemStack input2;
	boolean isCrafting = false;
	final static int finalCooldown = 400;
	public static int cooldown = finalCooldown;
	MagicCrafterRecipe rec;
	boolean isCreating = false;
	MagicCrafterRecipe recipe;
	
	public TileEntityMagicCrafter() {
		inv = new InventoryBasic("Magic Crafter", false, 4);
	}
	
	public void update(){
		
		/** 0 crystal slot, 1&2 input slot, 3 output slot */
		if(isCreating){
						
			if(!Crystal.isStackCrystal(inv.getStackInSlot(0)) || inv.getStackInSlot(1) != input || inv.getStackInSlot(2) != input2){
				
				isCreating = false;
				cooldown = 0;
				
			}
			
			if(isCreating && cooldown <= 0){
				
				isCrafting = false;
				inv.setInventorySlotContents(0, removeItemFromStack(inv.getStackInSlot(0)));
				inv.setInventorySlotContents(1, removeItemFromStack(inv.getStackInSlot(1)));
				inv.setInventorySlotContents(2, removeItemFromStack(inv.getStackInSlot(2)));
				inv.setInventorySlotContents(3, recipe.getOutput());
				
			}
			
			cooldown -= 1;

			
		}else if(Crystal.isStackCrystal(inv.getStackInSlot(0)) && inv.getStackInSlot(1) != null && inv.getStackInSlot(2) != null && inv.getStackInSlot(3) == null){
			
			MagicCrafterRecipe mcr = MagicCrafterRecipe.getRecipe(inv.getStackInSlot(1), inv.getStackInSlot(2));

			
			if(mcr != null){
				
				input = inv.getStackInSlot(1);
				input2 = inv.getStackInSlot(2);
				recipe = mcr;
				isCreating = true;
				cooldown = finalCooldown;
				
			}
			
		}
		
	}
	
	private ItemStack removeItemFromStack(ItemStack is){
		
		if(!(is.stackSize <= 1)){
			
			return new ItemStack(is.getItem(), is.stackSize -1, is.getMetadata());
			
		}else{
			
			return null;
			
		}
		
	}
	
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		
		/** saves the items in the magic crafter block inventory */
		NBTTagList list = new NBTTagList();
		
		for(int i = 0; i<inv.getSizeInventory(); i++){
			if(inv.getStackInSlot(i) != null){
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				inv.getStackInSlot(i).writeToNBT(tag);
				list.appendTag(tag);
			}
		}
		
		compound.setTag("ItemStacks", list);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		
		/** loads the items for the magic crafter block inventory */
		NBTTagList list = compound.getTagList("ItemStacks", 10);
		
		this.inv = new InventoryBasic("Magic Crafter", false, 4);
		
		for(int i = 0; i<list.tagCount(); i++){
			
			NBTTagCompound tag = list.getCompoundTagAt(i);
			byte b = tag.getByte("Slot");
			if(b >= 0 && b < inv.getSizeInventory()){
				inv.setInventorySlotContents(b, ItemStack.loadItemStackFromNBT(tag));
			}
			
		}
	}
	
	public static TileEntityMagicCrafter getfromTileEntity(TileEntity te){
		
		return (TileEntityMagicCrafter) te;
		
	}
	
}
