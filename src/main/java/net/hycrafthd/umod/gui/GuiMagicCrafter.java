package net.hycrafthd.umod.gui;

import net.hycrafthd.umod.tileentity.TileEntityMagicCrafter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;

public class GuiMagicCrafter extends GuiBase{
	
	public GuiMagicCrafter(EntityPlayer pl,IInventory tile, Container con) {
		super(new GuiRescources("magic_crafter.png"),pl, tile, con);
	}
	
    @Override
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    	super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    	if(this.ent instanceof TileEntityMagicCrafter){
    	TileEntityMagicCrafter mg = (TileEntityMagicCrafter) this.ent;
    	drawTexturedModalRect(this.width/2 ,this.height/2, 177, 1, 7, (mg.getCount()/4));
    	}
    }
    
	@Override
	public void addToBox(GuiCombobox box2) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onIOModeSwitched() {
		// TODO Auto-generated method stub
	}
	
}
