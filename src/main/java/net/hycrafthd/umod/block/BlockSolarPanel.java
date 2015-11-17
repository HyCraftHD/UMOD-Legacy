package net.hycrafthd.umod.block;

import java.util.List;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.IEnergyMessage;
import net.hycrafthd.umod.enumtype.EnumTypeGui;
import net.hycrafthd.umod.tileentity.TileEntitySolarPanel;
import net.hycrafthd.umod.utils.EnergyUtils;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSolarPanel extends BlockBase implements ITileEntityProvider, IEnergyMessage {

	public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumTypeSolarPanel.class);

	public BlockSolarPanel() {
		super(Material.iron);
	}

	@Override
	public int damageDropped(IBlockState state) {
		EnumTypeSolarPanel type = (EnumTypeSolarPanel) state.getValue(TYPE);
		return type.getMetadata();
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs creativetab, List list) {
		for (int i = 0; i < EnumTypeSolarPanel.values().length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumTypeSolarPanel type = EnumTypeSolarPanel.byMetadata(meta);
		return this.getDefaultState().withProperty(TYPE, type);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumTypeSolarPanel type = (EnumTypeSolarPanel) state.getValue(TYPE);
		return type.getMetadata();
	}

	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { TYPE });
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		switch (EnumTypeSolarPanel.byMetadata(meta)) {
		case LOWVOLTAGE:
			return new TileEntitySolarPanel(2, 10000, this.getUnlocalizedName());
		case MEDIUMVOLTAGE:
			return new TileEntitySolarPanel(20, 100000, this.getUnlocalizedName());
		case HIGHVOLTAGE:
			return new TileEntitySolarPanel(200, 1000000, this.getUnlocalizedName());
		case ULTRAVOLTAGE:
			return new TileEntitySolarPanel(2000, 10000000, this.getUnlocalizedName());
		case EXTREMEVOLTAGE:
			return new TileEntitySolarPanel(20000, 100000000, this.getUnlocalizedName());
		}
		return null;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		playerIn.openGui(UReference.modid, EnumTypeGui.SOLARPANEL.getID(), world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

	@Override
	public int isProvidingStrongPower(IBlockAccess w, BlockPos pos, IBlockState state, EnumFacing side) {
		return 0;
	}

	@Override
	public boolean isOpaqueCube() {
		return true;
	}

	@Override
	public boolean isFullBlock() {
		return true;
	}

	@Override
	public boolean isSolidFullCube() {
		return true;
	}

	@Override
	public boolean isFullCube() {
		return true;
	}

	@Override
	public String getMessage(int meta) {
		switch (EnumTypeSolarPanel.byMetadata(meta)) {
		case LOWVOLTAGE:
			return "Products " + EnergyUtils.inUE(2) + " UE/t";
		case MEDIUMVOLTAGE:
			return "Products "+ EnergyUtils.inUE(20) +"UE/t";
		case HIGHVOLTAGE:
			return "Products " + EnergyUtils.inUE(200) + "UE/t";
		case ULTRAVOLTAGE:
			return "Products "+ EnergyUtils.inUE(2000) +"UE/t";
		case EXTREMEVOLTAGE:
			return "Products "+ EnergyUtils.inUE(20000) +"UE/t";
		}
		return null;
	}

	public enum EnumTypeSolarPanel implements IStringSerializable {

		LOWVOLTAGE(0, "lowvoltage"), MEDIUMVOLTAGE(1, "mediumvoltage"), HIGHVOLTAGE(2, "highvoltage"), ULTRAVOLTAGE(3, "ultravoltage"), EXTREMEVOLTAGE(4, "extremevoltage");

		public int getMetadata() {
			return this.meta;
		}

		public String getName() {
			return this.name;
		}

		public String toString() {
			return this.name;
		}

		public static EnumTypeSolarPanel byMetadata(int meta) {
			if (meta < 0 || meta >= META_LOOKUP.length) {
				meta = 0;
			}

			return META_LOOKUP[meta];
		}

		private final int meta;
		private final String name;

		private static final EnumTypeSolarPanel[] META_LOOKUP = new EnumTypeSolarPanel[values().length];

		private EnumTypeSolarPanel(int meta, String name) {
			this.meta = meta;
			this.name = name;
		}

		static {
			for (EnumTypeSolarPanel type : values()) {
				META_LOOKUP[type.getMetadata()] = type;
			}
		}

	}

}
