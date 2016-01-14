package net.hycrafthd.umod.block;

import java.util.List;
import java.util.Random;

import net.hycrafthd.umod.UItems;
import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockOres extends BlockBase {

	public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumTypeBaseStuff.class);

	public BlockOres() {
		super(Material.rock);
		this.setResistance(5.0F);
		this.setStepSound(soundTypePiston);
	}

	@Override
	public float getBlockHardness(World world, BlockPos pos) {
		EnumTypeBaseStuff type = EnumTypeBaseStuff.byMetadata(world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos)));
		return type.getOreHardness();
	}

	@Override
	public int getHarvestLevel(IBlockState state) {
		EnumTypeBaseStuff type = (EnumTypeBaseStuff) state.getValue(TYPE);
		return type.getOreHarvestLevel();
	}

	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		EnumTypeBaseStuff type = (EnumTypeBaseStuff) state.getValue(TYPE);
		if (type.getName() == "sulphur") {
			return UItems.ingots;
		}
		return super.getItemDropped(state, rand, fortune);
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		EnumTypeBaseStuff type = (EnumTypeBaseStuff) state.getValue(TYPE);
		if (type.getName() == "sulphur") {
			if (1 + fortune <= 4) {
				return MathHelper.getRandomIntegerInRange(random, 1 + fortune, 4);
			} else {
				return 4;
			}
		}
		return 1;
	}

	@Override
	public int damageDropped(IBlockState state) {
		EnumTypeBaseStuff type = (EnumTypeBaseStuff) state.getValue(TYPE);
		return type.getMetadata();
	}

	@Override
	public int getExpDrop(IBlockAccess world, BlockPos pos, int fortune) {
		Random rand = world instanceof World ? ((World) world).rand : new Random();
		EnumTypeBaseStuff type = (EnumTypeBaseStuff) world.getBlockState(pos).getValue(TYPE);
		if (type.getName() == "sulphur") {
			return MathHelper.getRandomIntegerInRange(rand, 3, 6);
		}
		return 0;
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
