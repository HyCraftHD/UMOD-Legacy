package net.hycrafthd.umod.block;

import java.util.List;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.IPlugabel;
import net.hycrafthd.umod.tileentity.TileEntityPipe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockWall;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockModelRenderer.EnumNeighborInfo;
import net.minecraft.client.renderer.EnumFaceDirection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.property.Properties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPipe extends Block implements ITileEntityProvider{

	
	public static final PropertyBool UP = PropertyBool.create("up");
	public static final PropertyBool DOWN = PropertyBool.create("down");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool WEST = PropertyBool.create("west");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool NORTH = PropertyBool.create("north");

	
	public BlockPipe(String name) {
    super(Material.iron);
    this.setHardness(6F);
    this.setResistance(5F);
    this.setUnlocalizedName(name);
    this.setDefaultState(this.blockState.getBaseState().withProperty(UP, Boolean.valueOf(false)).withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)).withProperty(DOWN, Boolean.valueOf(false)));
	this.setCreativeTab(UReference.tab);
	}
	
	@Override
	public boolean canDropFromExplosion(Explosion explosionIn) {
		return false;
	}
	
	public BlockState getState(){
		return this.blockState;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityPipe();
	}
	
	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[]{UP,DOWN,EAST,WEST,SOUTH,NORTH});
	}
	
	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return false;
	}
	
    public boolean isFullCube()
    {
        return false;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }
    
    @Override
    public IBlockState getStateForEntityRender(IBlockState state) {
    	return this.blockState.getBaseState().withProperty(UP, Boolean.valueOf(false)).withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)).withProperty(DOWN, Boolean.valueOf(false));
    }

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	 @SideOnly(Side.CLIENT)
	 public boolean shouldSideBeRendered(IBlockAccess w, BlockPos pos, EnumFacing side)
	 {
	     return side == EnumFacing.DOWN ? super.shouldSideBeRendered(w, pos, side) : true;
	 }

	public IBlockState getActualState(IBlockState def,IBlockAccess w,BlockPos pos){
		IPlugabel p = (IPlugabel) w.getTileEntity(pos);
        return def.withProperty(UP, Boolean.valueOf(p.canConnect(w,pos.up()))).withProperty(NORTH, Boolean.valueOf(p.canConnect(w,pos.north()))).withProperty(EAST, Boolean.valueOf(p.canConnect(w,pos.east()))).withProperty(SOUTH, Boolean.valueOf(p.canConnect(w,pos.south()))).withProperty(WEST, Boolean.valueOf(p.canConnect(w,pos.west()))).withProperty(DOWN, Boolean.valueOf(p.canConnect(w,pos.down())));
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
		IBlockAccess w = worldIn;
		TileEntityPipe pip = (TileEntityPipe) w.getTileEntity(pos);
		if(pip != null){
		boolean csouth = pip.canConnect(w, pos.south());
		boolean cnorth = pip.canConnect(w, pos.north());
		boolean cdown = pip.canConnect(w, pos.down());
		boolean cup = pip.canConnect(w, pos.up());
		boolean ceast = pip.canConnect(w, pos.east());
		boolean cwest = pip.canConnect(w, pos.west());
        float anfangunten = 0.3F;
        float anfnagoben = 0.7F;
        float anfangX = 0.3F;
        float endeX = 0.7F;
        float anfangZ = 0.3F;
        float endeZ = 0.7F;
        
        if(cup){
        	anfnagoben = 1;
        }
        if(cdown){
        	anfangunten = 0;
        }
        if(cwest){
        	anfangX = 0;
        }
        if(ceast){
        	endeX = 1;
        }
        if(cnorth){
        	anfangZ = 0;
        }
        if(csouth){
        	endeZ = 1;
        }
        this.setBlockBounds(anfangX, anfangunten, anfangZ, endeX, anfnagoben, endeZ);
		}
	}
	
	private EnumFaceDirection[] dir = new EnumFaceDirection[6];

}
