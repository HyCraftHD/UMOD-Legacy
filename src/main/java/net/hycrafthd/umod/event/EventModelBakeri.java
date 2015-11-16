package net.hycrafthd.umod.event;

import net.hycrafthd.umod.UUtils;
import net.hycrafthd.umod.block.BlockPipe;
import net.hycrafthd.umod.render.PipeRender;
import net.minecraft.block.Block;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventModelBakeri {

	@SubscribeEvent
	public void registerModels(ModelBakeEvent event) {
		for (Object o : Block.blockRegistry) {
			if (o instanceof BlockPipe) {
				event.modelRegistry.putObject(new ModelResourceLocation(UUtils.getBlockName((Block) o), null), new PipeRender());
				event.modelRegistry.putObject(new ModelResourceLocation(UUtils.getBlockName((Block) o), "normal"), new PipeRender());
				for (int i = 0; i < 16; i++) {
					event.modelRegistry.putObject(new ModelResourceLocation(UUtils.getBlockName((Block) o), "data=" + i), new PipeRender());
				}
			}
		}
	}
	
}
