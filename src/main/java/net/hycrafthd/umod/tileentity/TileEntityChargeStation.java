package net.hycrafthd.umod.tileentity;

import net.hycrafthd.umod.UItems;
import net.hycrafthd.umod.api.IGuiProvider;
import net.hycrafthd.umod.api.IPowerProvieder;
import net.hycrafthd.umod.block.BlockBaseMachine;
import net.hycrafthd.umod.container.ContainerChargeStation;
import net.hycrafthd.umod.enumtype.EnumTypeGui;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class TileEntityChargeStation extends TileEntityBase implements IGuiProvider,IPowerProvieder{

	ItemStack stack = null;
	
	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return new int[] {0};
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		if(direction.equals(EnumFacing.UP)){
		return true;
		}
		return false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		if(direction.equals(EnumFacing.DOWN)){
		return true;
		}
		return false;
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return stack;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (this.stack != null)
        {
            ItemStack itemstack;

            if (this.stack.stackSize <= count)
            {
                itemstack = this.stack;
                this.stack = null;
                return itemstack;
            }
            else
            {
                itemstack = this.stack.splitStack(count);

                if (this.stack.stackSize == 0)
                {
                    this.stack = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
          this.stack = stack;		
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
        if(stack.isItemEqual(new ItemStack(UItems.battery))){
        	return true;
        }
		return false;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		stack = null;
	}

	@Override
	public String getName() {
		return "tile.entity.chargstation";
	}

	public boolean getMode(){
		return mode;
	}
	
	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer pl) {
		return new ContainerChargeStation((IInventory) worldObj.getTileEntity(pos), pl, pos, worldObj);
	}

	@Override
	public String getGuiID() {
		return null;
	}

	@Override
	public int getGui() {
		return EnumTypeGui.CHARGESTATION.getID();
	}

	int stored;
	public static final int MAXIMAL_POWER = 50000;
	private boolean mode = false;
	
	@Override
	public void update() {
       if(stack != null && stack.getItemDamage() > 0 && this.canAddPower(2) && mode){
    	   stack.setItemDamage(stack.getItemDamage() + 2);
    	   stored += 2;
       }else if(!mode && stack != null && stack.getItemDamage() < stack.getMaxDamage() && stored - 2 >= 0){
    	   stored -= 2;
    	   stack.setItemDamage(stack.getItemDamage() - 2);
       }
       
   	BlockPos[] list = {pos.east(),pos.north(),pos.south(),pos.west(),pos.up(),pos.down()};
	for(int i = 0;i < list.length;i++){
	Block b = worldObj.getBlockState(list[i]).getBlock();
    TileEntity e = worldObj.getTileEntity(list[i]);
	if(e instanceof IPowerProvieder && !(b instanceof BlockBaseMachine)){
		IPowerProvieder p = (IPowerProvieder) e;
		if(!(p instanceof TileEntityPipe) && p.canGetPower(2) && this.canAddPower(2)){
			stored += p.getPower(2);
		}else if(p instanceof TileEntityPipe){
			if(p.canGetPower(p.getMaximalPower()) && this.canAddPower(p.getMaximalPower())){
				stored += p.getPower(p.getMaximalPower());
			}else if(this.canAddPower(p.getStoredPower())){
				stored += p.getPower(p.getStoredPower());
			}else if(p.canGetPower(this.MAXIMAL_POWER - this.stored)){
				stored += this.MAXIMAL_POWER - this.stored;
			}
		}
	}
	}
	}
	
	public void setMode(boolean m){
		mode = m;
	}

	@Override
	public int getStoredPower() {
		return stored;
	}

	@Override
	public void addPower(int power) {
		stored += power;
	}

	@Override
	public int getPower(int powerneed) {
		stored -= powerneed;
		return powerneed;
	}

	@Override
	public boolean canGetPower(int power) {
		return false;
	}

	@Override
	public boolean canAddPower(int power) {
		return power + stored <= MAXIMAL_POWER;
	}

	@Override
	public int getMaximalPower() {
		return MAXIMAL_POWER;
	}

	@Override
	public boolean isWorking() {
		return (stack != null) && stack.getItemDamage() < stack.getMaxDamage();
	}

	@Override
	public String getErrorMessage() {
		return null;
	}

	@Override
	public boolean hasPower() {
		return stored > 0;
	}

	@Override
	public int getPowerProducNeeds() {
		return 2;
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("Stored", stored);
		compound.setBoolean("Mode", mode);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.stored = compound.getInteger("Stored");
		this.mode = compound.getBoolean("Mode");
	}
	
}
