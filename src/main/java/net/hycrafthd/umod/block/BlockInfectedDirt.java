package net.hycrafthd.umod.block;

import net.hycrafthd.umod.UPotion;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class BlockInfectedDirt extends BlockBase {

	public BlockInfectedDirt() {
		super(Material.ground);
		this.setHarvestLevel("spade", 2);
		this.setHardness(0.6F);
		this.setStepSound(soundTypeGrass);
	}
}
