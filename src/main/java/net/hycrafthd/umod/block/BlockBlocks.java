package net.hycrafthd.umod.block;

import java.util.List;
import java.util.Random;

import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockBlocks extends Block {

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
