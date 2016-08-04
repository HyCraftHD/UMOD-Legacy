package net.hycrafthd.umod.world;

import java.util.Random;

import net.hycrafthd.umod.UBlocks;
import net.hycrafthd.umod.block.BlockInfectedSapling;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class GenInfectedTree extends WorldGenAbstractTree {
	
	private final int minTreeHeight;
	private final boolean vinesGrow;
	private final int metaWood;
	private final int metaLeaves;
	
	public GenInfectedTree(boolean blocknotify) {
		this(blocknotify, MathHelper.getRandomIntegerInRange(new Random(), 4, 6), 0, 0, false);
	}
	
	public GenInfectedTree(boolean blocknotify, int minY, int wood, int leaves, boolean vines) {
		super(blocknotify);
		this.minTreeHeight = minY;
		this.metaWood = 0;
		this.metaLeaves = 0;
		this.vinesGrow = vines;
	}
	
	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int i = rand.nextInt(3) + this.minTreeHeight;
		boolean flag = true;
		
		if (pos.getY() >= 1 && pos.getY() + i + 1 <= 256) {
			byte b0;
			int l;
			
			for (int j = pos.getY(); j <= pos.getY() + 1 + i; ++j) {
				b0 = 1;
				
				if (j == pos.getY()) {
					b0 = 0;
				}
				
				if (j >= pos.getY() + 1 + i - 2) {
					b0 = 2;
				}
				
				for (int k = pos.getX() - b0; k <= pos.getX() + b0 && flag; ++k) {
					for (l = pos.getZ() - b0; l <= pos.getZ() + b0 && flag; ++l) {
						if (j >= 0 && j < 256) {
							if (!this.isReplaceable(world, new BlockPos(k, j, l))) {
								flag = false;
							}
						} else {
							flag = false;
						}
					}
				}
			}
			
			if (!flag) {
				return false;
			} else {
				
				BlockPos down = pos.down();
				Block block1 = world.getBlockState(down).getBlock();
				boolean isSoil = block1.canSustainPlant(world, down, net.minecraft.util.EnumFacing.UP, (BlockInfectedSapling) UBlocks.infectedSapling);
				
				world.setBlockToAir(pos);
				
				if (isSoil && pos.getY() < 256 - i - 1) {
					block1.onPlantGrow(world, down, pos);
					b0 = 3;
					byte b1 = 0;
					int i1;
					int j1;
					int k1;
					int l1;
					BlockPos blockpos1;
					
					for (l = pos.getY() - b0 + i; l <= pos.getY() + i; ++l) {
						i1 = l - (pos.getY() + i);
						j1 = b1 + 1 - i1 / 2;
						
						for (k1 = pos.getX() - j1; k1 <= pos.getX() + j1; ++k1) {
							l1 = k1 - pos.getX();
							
							for (int i2 = pos.getZ() - j1; i2 <= pos.getZ() + j1; ++i2) {
								int j2 = i2 - pos.getZ();
								
								if (Math.abs(l1) != j1 || Math.abs(j2) != j1 || rand.nextInt(2) != 0 && i1 != 0) {
									blockpos1 = new BlockPos(k1, l, i2);
									Block block = world.getBlockState(blockpos1).getBlock();
									
									if (block.isAir(world, blockpos1) || block.isLeaves(world, blockpos1) || block.getMaterial() == Material.vine || block == UBlocks.infectedFruit) {
										this.func_175905_a(world, blockpos1, UBlocks.infectedLeave, this.metaLeaves);
									}
								}
							}
						}
					}
					
					for (l = 0; l < i; ++l) {
						BlockPos upN = pos.up(l);
						Block block2 = world.getBlockState(upN).getBlock();
						
						if (block2.isAir(world, upN) || block2.isLeaves(world, upN) || block2.getMaterial() == Material.vine) {
							this.func_175905_a(world, pos.up(l), UBlocks.infectedLog, this.metaWood);
							
							for (int a = -2; a <= 2; a++) {
								for (int b = -2; b <= 2; b++) {
									if (block2.isAir(world, upN.add(a, 0, b)) && world.getBlockState(upN.add(a, 0, b).up()).getBlock().equals(UBlocks.infectedLeave)) {
										if (a == 0 && b == 0) {
											continue;
										} else {
											if (new Random().nextInt(9) == 0) {
												this.func_175905_a(world, upN.add(a, 0, b), UBlocks.infectedFruit, 0);
											}
										}
									}
								}
							}
							
							if (this.vinesGrow && l > 0) {
								if (rand.nextInt(3) > 0 && world.isAirBlock(pos.add(-1, l, 0))) {
									this.func_175905_a(world, pos.add(-1, l, 0), Blocks.vine, BlockVine.EAST_FLAG);
								}
								
								if (rand.nextInt(3) > 0 && world.isAirBlock(pos.add(1, l, 0))) {
									this.func_175905_a(world, pos.add(1, l, 0), Blocks.vine, BlockVine.WEST_FLAG);
								}
								
								if (rand.nextInt(3) > 0 && world.isAirBlock(pos.add(0, l, -1))) {
									this.func_175905_a(world, pos.add(0, l, -1), Blocks.vine, BlockVine.SOUTH_FLAG);
								}
								
								if (rand.nextInt(3) > 0 && world.isAirBlock(pos.add(0, l, 1))) {
									this.func_175905_a(world, pos.add(0, l, 1), Blocks.vine, BlockVine.NORTH_FLAG);
								}
							}
						}
					}
					
					if (this.vinesGrow) {
						for (l = pos.getY() - 3 + i; l <= pos.getY() + i; ++l) {
							i1 = l - (pos.getY() + i);
							j1 = 2 - i1 / 2;
							
							for (k1 = pos.getX() - j1; k1 <= pos.getX() + j1; ++k1) {
								for (l1 = pos.getZ() - j1; l1 <= pos.getZ() + j1; ++l1) {
									BlockPos blockpos3 = new BlockPos(k1, l, l1);
									
									if (world.getBlockState(blockpos3).getBlock().isLeaves(world, blockpos3)) {
										BlockPos blockpos4 = blockpos3.west();
										blockpos1 = blockpos3.east();
										BlockPos blockpos5 = blockpos3.north();
										BlockPos blockpos2 = blockpos3.south();
										
										if (rand.nextInt(4) == 0 && world.getBlockState(blockpos4).getBlock().isAir(world, blockpos4)) {
											this.func_175923_a(world, blockpos4, BlockVine.EAST_FLAG);
										}
										
										if (rand.nextInt(4) == 0 && world.getBlockState(blockpos1).getBlock().isAir(world, blockpos1)) {
											this.func_175923_a(world, blockpos1, BlockVine.WEST_FLAG);
										}
										
										if (rand.nextInt(4) == 0 && world.getBlockState(blockpos5).getBlock().isAir(world, blockpos5)) {
											this.func_175923_a(world, blockpos5, BlockVine.SOUTH_FLAG);
										}
										
										if (rand.nextInt(4) == 0 && world.getBlockState(blockpos2).getBlock().isAir(world, blockpos2)) {
											this.func_175923_a(world, blockpos2, BlockVine.NORTH_FLAG);
										}
									}
								}
							}
						}
						
						if (rand.nextInt(5) == 0 && i > 5) {
							for (l = 0; l < 2; ++l) {
								for (i1 = 0; i1 < 4; ++i1) {
									if (rand.nextInt(4 - l) == 0) {
										j1 = rand.nextInt(3);
										EnumFacing enumfacing = EnumFacing.getHorizontal(i1).getOpposite();
										this.func_175905_a(world, pos.add(enumfacing.getFrontOffsetX(), i - 5 + l, enumfacing.getFrontOffsetZ()), Blocks.cocoa, j1 << 2 | EnumFacing.getHorizontal(i1).getHorizontalIndex());
									}
								}
							}
						}
					}
					
					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}
	
	@Override
	protected boolean func_150523_a(Block block) {
		return block.getMaterial() == Material.air || block == UBlocks.infectedLeave || block == UBlocks.infectedSapling || block == Blocks.leaves || block == Blocks.leaves2;
	}
	
	@Override
	protected void func_175921_a(World world, BlockPos p_175921_2_) {
		if (world.getBlockState(p_175921_2_).getBlock() != UBlocks.infectedDirt) {
			this.func_175903_a(world, p_175921_2_, UBlocks.infectedDirt.getDefaultState());
		}
	}
	
	private void func_175923_a(World world, BlockPos p_175923_2_, int p_175923_3_) {
		this.func_175905_a(world, p_175923_2_, Blocks.vine, p_175923_3_);
		int j = 4;
		
		for (p_175923_2_ = p_175923_2_.down(); world.getBlockState(p_175923_2_).getBlock().isAir(world, p_175923_2_) && j > 0; --j) {
			this.func_175905_a(world, p_175923_2_, Blocks.vine, p_175923_3_);
			p_175923_2_ = p_175923_2_.down();
		}
	}
	
	@Override
	public boolean isReplaceable(World world, BlockPos pos) {
		net.minecraft.block.state.IBlockState state = world.getBlockState(pos);
		if (state.getBlock().isAir(world, pos) || func_150523_a(state.getBlock()))
			return true;
		return false;
	}
	
}