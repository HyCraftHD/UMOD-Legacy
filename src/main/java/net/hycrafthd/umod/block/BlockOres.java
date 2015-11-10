package net.hycrafthd.umod.block;

import java.util.List;
import java.util.Random;

import net.hycrafthd.umod.UItems;
import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockOres extends Block {

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
	public void onLanded(World world, Entity entity) {
//		EnumTypeBaseStuff type = EnumTypeBaseStuff.byMetadata(world.getBlockState(entity.getPosition().add(0, -1, 0)).getBlock().getMetaFromState(world.getBlockState(entity.getPosition().add(0, -1, 0))));
//		if (type.getName() == "uran") {
//			if (entity instanceof EntityLivingBase && !world.isRemote) {
//				EntityLivingBase base = (EntityLivingBase) entity;
//				// TODO Strahlung
//				base.addPotionEffect(new PotionEffect(Potion.poison.getId(), 120, 2, false, false));
//				base.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 120, 2, false, false));
//			}
//		}
		super.onLanded(world, entity);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		EnumTypeBaseStuff type = (EnumTypeBaseStuff) state.getValue(TYPE);
		// Sulphur -> Chunk
		if (type.getName() == "sulphur") {
			return UItems.ingots;
		}
		// Normal this block
		return super.getItemDropped(state, rand, fortune);
	}

	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		EnumTypeBaseStuff type = (EnumTypeBaseStuff) state.getValue(TYPE);
		// Quantity of Sulphur
		if (type.getName() == "sulphur") {
			if (1 + fortune <= 4) {
				return MathHelper.getRandomIntegerInRange(random, 1 + fortune, 4);
			} else {
				return 4;
			}
		}
		// Normal 1
		return 1;
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
