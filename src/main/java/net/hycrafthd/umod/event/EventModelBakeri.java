package net.hycrafthd.umod.event;

import com.google.common.collect.ImmutableList;

import net.hycrafthd.umod.block.BlockPipe;
import net.hycrafthd.umod.render.PipeRender;
import net.hycrafthd.umod.utils.ClientRegistryUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventModelBakeri {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void registerModels(ModelBakeEvent event) {
		for (Object o : Block.blockRegistry) {
			if (o instanceof BlockPipe) {
				event.modelRegistry.putObject(new ModelResourceLocation(ClientRegistryUtils.getBlockName((Block) o), null), new PipeRender());
				event.modelRegistry.putObject(new ModelResourceLocation(ClientRegistryUtils.getBlockName((Block) o), "normal"), new PipeRender());
				ImmutableList<BlockState.StateImplementation> list = ((BlockPipe) o).getState().getValidStates();
				for (int i = 0; i < list.size(); i++) {
					event.modelRegistry.putObject(new ModelResourceLocation(ClientRegistryUtils.getBlockName((Block) o), list.get(i).toString()), new PipeRender());
				}
			}
		}
	}
	
}
