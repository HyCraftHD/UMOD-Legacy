package net.hycrafthd.umod.block;

import java.util.List;

import net.hycrafthd.umod.*;
import net.hycrafthd.umod.api.*;
import net.hycrafthd.umod.api.energy.IEnergyMessage;
import net.hycrafthd.umod.entity.EntityFX;
import net.hycrafthd.umod.tileentity.TileEntityCable;
import net.hycrafthd.umod.utils.NBTUtils;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.relauncher.*;

public class BlockCable extends Block implements ITileEntityProvider, IEnergyMessage, ISpiritProvider, IConduitBlock {
	
	public int powertrans;
	public int lo;
	public boolean iso;
	public String asp;
	
	public BlockCable(String name, int transf, int loos, boolean iso, String sp) {
		super(Material.iron);
		this.powertrans = transf;
		this.iso = iso;
		this.setHardness(6F);
		this.setResistance(5F);
		this.setUnlocalizedName(name);
		this.setCreativeTab(UReference.tab);
		this.lo = loos;
		this.asp = sp;
		this.setBlockBounds(0.4F, 0.4F, 0.4F, 0.6F, 0.6F, 0.6F);
	}
	
	@Override
	public boolean canDropFromExplosion(Explosion explosionIn) {
		return false;
	}
	
	@Override
	public Item getItem(World worldIn, BlockPos pos) {
		if (pos != null && worldIn != null && worldIn.isRemote) {
			EntityPlayer pl = Minecraft.getMinecraft().thePlayer;
			if (pl == null)
				return super.getItem(worldIn, pos);
			TileEntity en = worldIn.getTileEntity(pos);
			if (en != null && (!((IConduitProvider) en).hasConduit() || (pl.getCurrentEquippedItem() != null && Block.getBlockFromItem(pl.getCurrentEquippedItem().getItem()) != null && Block.getBlockFromItem(pl.getCurrentEquippedItem().getItem()) instanceof IConduitBlock))) {
			} else {
				return ((IConduitProvider) en).getConduit().getItem();
			}
		}
		return super.getItem(worldIn, pos);
	}
	
	@Override
	public String getSpirte() {
		return asp;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityCable(powertrans, lo);
	}
	
	@Override
	public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB mask, @SuppressWarnings("rawtypes") List list, Entity collidingEntity) {
		this.setBlockBoundsBasedOnState(world, pos);
		super.addCollisionBoxesToList(world, pos, state, mask, list, collidingEntity);
	}
	
	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return false;
	}
	
	public boolean isFullCube() {
		return false;
	}
	
	public boolean isOpaqueCube() {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess w, BlockPos pos, EnumFacing side) {
		return side == EnumFacing.DOWN ? super.shouldSideBeRendered(w, pos, side) : true;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntityCable cab = (TileEntityCable) worldIn.getTileEntity(pos);
		if (playerIn.getCurrentEquippedItem() != null) {
			Block rand = Block.getBlockFromItem(playerIn.getCurrentEquippedItem().getItem());
			if (!cab.hasConduit() && rand != null && rand instanceof BlockConduit) {
				cab.setConduit(NBTUtils.getStackFromConduit(playerIn.getCurrentEquippedItem()));
				playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, null);
				playerIn.worldObj.playSoundAtEntity(playerIn, "step.stone", 0.2F, ((playerIn.getRNG().nextFloat() - playerIn.getRNG().nextFloat()) * 0.7F + 1.0F) * 2.0F);
				return true;
			} else if (cab.hasConduit() && Block.getBlockFromItem(playerIn.getCurrentEquippedItem().getItem()) != null && !(Block.getBlockFromItem(playerIn.getCurrentEquippedItem().getItem()) instanceof BlockCable)) {
				dropForPlayer(playerIn, cab);
				cab.setConduit(null);
				return true;
			}
		} else if (cab.hasConduit()) {
			dropForPlayer(playerIn, cab);
			cab.setConduit(null);
			return true;
		}
		return false;
	}
	
	private void dropForPlayer(EntityPlayer playerIn, TileEntityCable cab) {
		ItemStack stack = new ItemStack(UBlocks.conduit);
		NBTUtils.addStackToConduit(stack, cab.getConduit());
		boolean flag = playerIn.inventory.addItemStackToInventory(stack);
		
		if (flag) {
			playerIn.worldObj.playSoundAtEntity(playerIn, "random.pop", 0.2F, ((playerIn.getRNG().nextFloat() - playerIn.getRNG().nextFloat()) * 0.7F + 1.0F) * 2.0F);
			playerIn.inventoryContainer.detectAndSendChanges();
		}
		
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
		TileEntityCable cab = (TileEntityCable) worldIn.getTileEntity(pos);
		if (cab == null)
			return;
		IBlockAccess w = worldIn;
		if (w instanceof WorldServer && ((WorldServer) w).isRemote) {
			EntityPlayer pl = ClientProxy.player;
			if (cab.hasConduit() && (pl.getCurrentEquippedItem() == null || Block.getBlockFromItem(pl.getCurrentEquippedItem().getItem()) == null || !(Block.getBlockFromItem(pl.getCurrentEquippedItem().getItem()) instanceof BlockCable))) {
				this.setBlockBounds(0, 0, 0, 1, 1, 1);
				return;
			}
		} else if (cab.hasConduit()) {
			this.setBlockBounds(0, 0, 0, 1, 1, 1);
			return;
		}
		TileEntityCable pip = (TileEntityCable) w.getTileEntity(pos);
		if (pip != null) {
			boolean csouth = pip.canConnect(w, pos.south());
			boolean cnorth = pip.canConnect(w, pos.north());
			boolean cdown = pip.canConnect(w, pos.down());
			boolean cup = pip.canConnect(w, pos.up());
			boolean ceast = pip.canConnect(w, pos.east());
			boolean cwest = pip.canConnect(w, pos.west());
			float anfangunten = 0.4F;
			float anfnagoben = 0.6F;
			float anfangX = 0.4F;
			float endeX = 0.6F;
			float anfangZ = 0.4F;
			float endeZ = 0.6F;
			
			if (cup) {
				anfnagoben = 1;
			}
			if (cdown) {
				anfangunten = 0;
			}
			if (cwest) {
				anfangX = 0;
			}
			if (ceast) {
				endeX = 1;
			}
			if (cnorth) {
				anfangZ = 0;
			}
			if (csouth) {
				endeZ = 1;
			}
			
			this.setBlockBounds(anfangX, anfangunten, anfangZ, endeX, anfnagoben, endeZ);
			
		}
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
		if (!isIsolated()) {
			entityIn.attackEntityFrom(UDamageSource.electroshock, 5);
		}
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, Entity entityIn) {
		super.onEntityCollidedWithBlock(worldIn, pos, entityIn);
		if (!isIsolated()) {
			entityIn.attackEntityFrom(UDamageSource.electroshock, 5);
		}
	}
	
	public boolean isIsolated() {
		return iso;
	}
	
	@Override
	public String getMessage(int i) {
		return "Transports " + powertrans + "UE/t";
	}
	
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
	}
	
	@Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock) {
		((TileEntityCable) world.getTileEntity(pos)).onBlockSetInWorld();
	}
	
	@Override
	public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn) {
		entityClear(worldIn, pos);
		super.onBlockDestroyedByExplosion(worldIn, pos, explosionIn);
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		entityClear(worldIn, pos);
		super.onBlockDestroyedByPlayer(worldIn, pos, state);
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		entityClear(worldIn, pos);
		((TileEntityCable) worldIn.getTileEntity(pos)).onBlockBreak();
		super.breakBlock(worldIn, pos, state);
	}
	
	private void entityClear(World worldIn, BlockPos pos) {
		@SuppressWarnings("unchecked")
		List<EntityFX> p = worldIn.getEntitiesWithinAABB(EntityFX.class, new AxisAlignedBB(pos, pos.add(1, 1, 1)));
		for (EntityFX fx : p) {
			fx.setDead();
		}
	}
}
