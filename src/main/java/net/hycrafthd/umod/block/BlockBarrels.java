package net.hycrafthd.umod.block;

import java.util.List;

import net.hycrafthd.umod.enumtype.EnumTypeBarrels;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockBarrels extends BlockBase {

	public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumTypeBarrels.class);

	public BlockBarrels() {
		super(Material.rock);
		this.setResistance(5.0F);
		this.setHardness(2.0F);
		this.setHarvestLevel("axe", 0);
		this.setStepSound(soundTypeWood);
	}

	@Override
	public int damageDropped(IBlockState state) {
		EnumTypeBarrels type = (EnumTypeBarrels) state.getValue(TYPE);
		return type.getID();
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs creativetab, List list) {
		for (int i = 0; i < EnumTypeBarrels.values().length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumTypeBarrels type = EnumTypeBarrels.byID(meta);
		return this.getDefaultState().withProperty(TYPE, type);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumTypeBarrels type = (EnumTypeBarrels) state.getValue(TYPE);
		return type.getID();
	}

	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { TYPE });
	}

}
