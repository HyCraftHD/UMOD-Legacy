package net.hycrafthd.umod.gui;

import java.io.IOException;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.container.ContainerBase.Mode;
import net.hycrafthd.umod.container.ContainerPulverizer;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiPulverizer extends GuiBase{
	
	public BlockPos pos;

	public GuiPulverizer(EntityPlayer pl, IInventory tile,World w,BlockPos pos) {
		super(new GuiRescources("pulver.png"),new GuiRescources("battery.png"), new GuiRescources("IOMode.png"),pl, tile, new ContainerPulverizer(tile, pl, w));
		this.pos = pos;
	}

	@Override
	public void initGui() {
		super.initGui();
		box.setOnListClicked(new Runnable() {
			
			@Override
			public void run() {
				if(box.getSelceted() != 2){
					if(box.getSelceted() == 0){
						((TileEntityPulverizer)ent).setEnumInput(hal);
					}else if(box.getSelceted() == 1){
						((TileEntityPulverizer)ent).setEnumOutput(hal);
					}
					ent.getDescriptionPacket();
					System.out.println(" " + box.getSelceted() + " " + hal.toString() + " "  + ((TileEntityPulverizer)ent).getEnumInput());
				}
			}
		});
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		if(!basecon.mode.equals(Mode.OUTPUT)){
 	    TileEntityPulverizer p = (TileEntityPulverizer) this.ent;
		fontRendererObj.drawString(((TileEntityPulverizer)this.ent).getTime() + "%", this.width/2-5, this.height/2-(this.ySize/2) + 15, 0x00000);
		}
	}

	@Override
	public void addToBox(GuiCombobox box2) {
		box2.getItems().add("Input");
		box2.getItems().add("Outputs");
		box2.setSelected(0);
	}

	@Override
	public void onIOModeSwitched() {
		if(this.getIOFaceing().equals(((TileEntityPulverizer)ent).getEnumInput())){
			box.setSelected(0);
		}else if(this.getIOFaceing().equals(((TileEntityPulverizer)ent).getEnumOutput())){
			box.setSelected(1);
		}else{
			box.setSelected(2);
		}
		
	}
	
}
