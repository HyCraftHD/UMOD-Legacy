package net.hycrafthd.umod.block;

import java.util.List;

import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.*;
import net.minecraft.block.state.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;

public class BlockBlocks extends BlockBase {

	public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumTypeBaseStuff.class);

	public BlockBlocks() {
		super(Material.rock);
		this.setResistance(5.0F);
		this.setHardness(3.0F);
		this.setHarvestLevel("pickaxe", 1);
		this.setStepSound(soundTypePiston);
	}

	@Override
	public int damageDropped(IBlockState state) {
		EnumTypeBaseStuff type = (EnumTypeBaseStuff) state.getValue(TYPE);
		return type.getMetadata();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubBlocks(Item item, CreativeTabs creativetab, List list) {
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumTypeBaseStuff type = EnumTypeBaseStuff.byMetadata(meta);
		return this.getDefaultState().withProperty(TYPE, type);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumTypeBaseStuff type = (EnumTypeBaseStuff) state.getValue(TYPE);
		return type.getMetadata();
	}

	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { TYPE });
	}

}
