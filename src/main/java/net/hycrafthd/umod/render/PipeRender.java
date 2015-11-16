package net.hycrafthd.umod.render;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.google.common.primitives.Ints;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.block.BlockPipe;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.model.ISmartBlockModel;
import net.minecraftforge.client.model.ISmartItemModel;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PipeRender implements IBakedModel, ISmartBlockModel, ISmartItemModel {
	
	private IBlockState state;
	private ItemStack stack;
	private boolean isItem;

	public PipeRender() {

	}

	private PipeRender(IBlockState state) {
		this.state = state;
		if(!(state.getBlock() instanceof BlockPipe)){
		     throw new IllegalArgumentException("Only Pipes are alowed");
		}
		isItem = false;
	}

	private PipeRender(ItemStack stack) {
		this.stack = stack;
		isItem = true;
	}

	@Override
	public IBakedModel handleBlockState(IBlockState state) {
		return new PipeRender(state);
	}

	private int[] vertexToInts(float x, float y, float z, int color, TextureAtlasSprite texture, float u, float v) {
		return new int[] {
				Float.floatToRawIntBits(x),
				Float.floatToRawIntBits(y),
				Float.floatToRawIntBits(z),
				color,
				Float.floatToRawIntBits(texture.getInterpolatedU(u)),
				Float.floatToRawIntBits(texture.getInterpolatedV(v)),
				0
		};
	}
	
	@Override
	public List getFaceQuads(EnumFacing p_177551_1_) {
		return Collections.emptyList();
	}

	private boolean[] isConnected = new boolean[6];
	private TextureAtlasSprite[] textures = new TextureAtlasSprite[7];

	private float[][][] quadVertexes = new float[][][]{
			{
					{0.35F, 0.55F, 0.35F},
					{0.35F, 0.55F, 0.35F},
					{0.35F, 1.00F, 0.35F},
					{0.35F, 1.00F, 0.35F},
			},
			{
					{0.35F, 0.55F, 0.35F},
					{0.35F, 0.55F, 0.55F},
					{0.35F, 1.00F, 0.55F},
					{0.35F, 1.00F, 0.35F},
			},
			{
					{0.35F, 0.55F, 0.55F},
					{0.55F, 0.55F,0.55F},
					{0.55F, 1.00F, 0.55F},
					{0.35F, 1.00F, 0.55F},
			},
			{
					{0.55F, 0.55F, 0.55F},
					{0.55F, 0.55F, 0.55F},
					{0.55F, 1.00F, 0.55F},
					{0.55F, 1.00F, 0.55F},
			}
	};

	private void createTwoSidedBakedQuad(List<BakedQuad> quads, float x1, float x2, float z1, float z2, float y, TextureAtlasSprite texture, EnumFacing side)
	{
		Vec3 v1 = rotate(new Vec3(x1 - .5, y - .5, z1 - .5), side).addVector(.5, .5, .5);
		Vec3 v2 = rotate(new Vec3(x1 - .5, y - .5, z2 - .5), side).addVector(.5, .5, .5);
		Vec3 v3 = rotate(new Vec3(x2 - .5, y - .5, z2 - .5), side).addVector(.5, .5, .5);
		Vec3 v4 = rotate(new Vec3(x2 - .5, y - .5, z1 - .5), side).addVector(.5, .5, .5);
		int[] data = Ints.concat(
				vertexToInts((float) v1.xCoord, (float) v1.yCoord, (float) v1.zCoord, -1, texture, x1 * 16, z1 * 16),
				vertexToInts((float) v2.xCoord, (float) v2.yCoord, (float) v2.zCoord, -1, texture, x1 * 16, z2 * 16),
				vertexToInts((float) v3.xCoord, (float) v3.yCoord, (float) v3.zCoord, -1, texture, x2 * 16, z2 * 16),
				vertexToInts((float) v4.xCoord, (float) v4.yCoord, (float) v4.zCoord, -1, texture, x2 * 16, z1 * 16)
		);
		quads.add(new BakedQuad(data, -1, side));
		quads.add(new BakedQuad(data, -1, side.getOpposite()));
	}

	private static Vec3 rotate(Vec3 vec, EnumFacing side)
	{
		switch(side)
		{
			case DOWN: return new Vec3( vec.xCoord, -vec.yCoord, -vec.zCoord);
			case UP: return new Vec3( vec.xCoord, vec.yCoord, vec.zCoord);
			case NORTH: return new Vec3( vec.xCoord, vec.zCoord, -vec.yCoord);
			case SOUTH: return new Vec3( vec.xCoord, -vec.zCoord, vec.yCoord);
			case WEST: return new Vec3(-vec.yCoord, vec.xCoord, vec.zCoord);
			case EAST: return new Vec3( vec.yCoord, -vec.xCoord, vec.zCoord);
		}
		return null;
	}

	private EnumFacing getSide(Vec3 a, Vec3 b, Vec3 c) {
		int dir = a.yCoord == b.yCoord && b.yCoord == c.yCoord ? 0 : (a.xCoord == b.xCoord && b.xCoord == c.xCoord ? 2 : 4);
		if (dir == 0) {
			dir += (c.yCoord >= 0.5) ? 1 : 0;
		} else if (dir == 2) {
			dir += (c.xCoord >= 0.5) ? 1 : 0;
		} else if (dir == 4) {
			dir += (c.zCoord >= 0.5) ? 1 : 0;
		}
		return EnumFacing.getFront(dir);
	}

	@Override
	public List getGeneralQuads() {
		LinkedList<BakedQuad> quads = new LinkedList<BakedQuad>();

		float min = UReference.PIPE_MIN_POS;
		float max = UReference.PIPE_MAX_POS;
		
		if (this.state != null) {
		         isConnected[0] = (Boolean) this.state.getValue(BlockPipe.DOWN);
		         isConnected[1] = (Boolean) this.state.getValue(BlockPipe.UP);
		         isConnected[2] = (Boolean) this.state.getValue(BlockPipe.EAST);
		         isConnected[3] = (Boolean) this.state.getValue(BlockPipe.WEST);
		         isConnected[4] = (Boolean) this.state.getValue(BlockPipe.NORTH);
		         isConnected[5] = (Boolean) this.state.getValue(BlockPipe.SOUTH);
		      for(int i = 0;i < textures.length;i++){
		    	  textures[i] = new PipeBaseTextureSprite(((BlockPipe)state.getBlock()).getSpirte());
		      }
		}

		for (EnumFacing f : EnumFacing.values()) {
			if (!isConnected[f.ordinal()]) {
				createTwoSidedBakedQuad(quads, min, max, min, max, max, textures[6], f);
			} else {
				TextureAtlasSprite sprite = textures[f.ordinal()];
				for (float[][] v : quadVertexes) {
					Vec3 v1 = rotate(new Vec3(v[0][0] - .5, v[0][1] - .5, v[0][2] - .5), f).addVector(.5, .5, .5);
					Vec3 v2 = rotate(new Vec3(v[1][0] - .5, v[1][1] - .5, v[1][2] - .5), f).addVector(.5, .5, .5);
					Vec3 v3 = rotate(new Vec3(v[2][0] - .5, v[2][1] - .5, v[2][2] - .5), f).addVector(.5, .5, .5);
					Vec3 v4 = rotate(new Vec3(v[3][0] - .5, v[3][1] - .5, v[3][2] - .5), f).addVector(.5, .5, .5);
					EnumFacing side = getSide(v1, v2, v3);
					int[] data = Ints.concat(
							vertexToInts((float) v1.xCoord, (float) v1.yCoord, (float) v1.zCoord, -1, sprite, 4, 0),
							vertexToInts((float) v2.xCoord, (float) v2.yCoord, (float) v2.zCoord, -1, sprite, 12, 0),
							vertexToInts((float) v3.xCoord, (float) v3.yCoord, (float) v3.zCoord, -1, sprite, 12, 4),
							vertexToInts((float) v4.xCoord, (float) v4.yCoord, (float) v4.zCoord, -1, sprite, 4, 4)
					);
					quads.add(new BakedQuad(data, -1, side));
					//quads.add(new BakedQuad(data, -1, side.getOpposite()));
				}
				if (isItem && f.ordinal() < 2) {
					createTwoSidedBakedQuad(quads, min, max, min, max, 1.0F, textures[f.ordinal()], f);
				}
			}
		}
		return quads;
	}

	@Override
	public boolean isAmbientOcclusion() {
		return true;
	}

	@Override
	public boolean isGui3d() {
		return true;
	}

	@Override
	public boolean isBuiltInRenderer() {
		return false;
	}

	@Override
	public TextureAtlasSprite getTexture() {
		return textures[0];
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms() {
		return ItemCameraTransforms.DEFAULT;
	}

	@Override
	public IBakedModel handleItemState(ItemStack stack) {
		return new PipeRender(stack);
	}
}