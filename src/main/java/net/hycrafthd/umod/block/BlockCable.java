package net.hycrafthd.umod.block;

import java.util.List;

import net.hycrafthd.umod.UDamageSource;
import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.energy.IEnergyMessage;
import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.tileentity.TileEntityCable;
import net.hycrafthd.umod.utils.EnergyUtils;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCable extends Block implements ITileEntityProvider, IEnergyMessage {
	
	public int powertrans;
	public int lo;
	public boolean iso;
	public String asp;

	public BlockCable(String name, int transf, int loos, boolean iso,String sp) {
		super(Material.iron);
		this.powertrans = EnergyUtils.inUE(transf);
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

	public String getSpirte() {
		return asp;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityCable(powertrans, lo);
	}

	@Override
	public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
	{
		TileEntityCable cab = (TileEntityCable) world.getTileEntity(pos);
		if(cab == null)return;
		if(cab.hasConduit()){
			this.setBlockBounds(0, 0, 0, 1, 1, 1);
			super.addCollisionBoxesToList(world, pos, state, mask, list, collidingEntity);
			return;
		}
		IBlockAccess w = world;
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
            super.addCollisionBoxesToList(world, pos, state, mask, list, collidingEntity);
		}
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
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntityCable cab = (TileEntityCable) worldIn.getTileEntity(pos);
		if(playerIn.getCurrentEquippedItem() != null){
		Block rand = Block.getBlockFromItem(playerIn.getCurrentEquippedItem().getItem());
		if(!cab.hasConduit() && rand != null && !(rand instanceof BlockCable) && rand.isFullBlock() && rand.isSolidFullCube() && rand.isNormalCube() && (playerIn.inventory.consumeInventoryItem(new ItemStack(rand).getItem()) || playerIn.capabilities.isCreativeMode)){
			cab.setConduit(rand);
		}else if(cab.hasConduit()){
			EntityItem item = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(cab.getConduit()));
			worldIn.spawnEntityInWorld(item);
			cab.setConduit(null);
		}}else if(cab.hasConduit()){
			EntityItem item = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(cab.getConduit()));
			worldIn.spawnEntityInWorld(item);
			cab.setConduit(null);
		}
		return false;
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
		TileEntityCable cab = (TileEntityCable) worldIn.getTileEntity(pos);
		if(cab == null)return;
		if(cab.hasConduit()){
			this.setBlockBounds(0, 0, 0, 1, 1, 1);
			return;
		}
		IBlockAccess w = worldIn;
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
			entityIn.attackEntityFrom(UDamageSource.electroshock, ((IPowerProvieder) worldIn.getTileEntity(pos)).getStoredPower() / 2);
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, Entity entityIn) {
		super.onEntityCollidedWithBlock(worldIn, pos, entityIn);
		if (!isIsolated()) {
			entityIn.attackEntityFrom(UDamageSource.electroshock, ((IPowerProvieder) worldIn.getTileEntity(pos)).getStoredPower());
		}
	}

	public boolean isIsolated() {
		return iso;
	}

	@Override
	public String getMessage(int i) {
		return "Transports " + EnergyUtils.inUE(powertrans) + "UE/t";
	}
}
