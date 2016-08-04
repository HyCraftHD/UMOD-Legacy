package net.hycrafthd.umod.gui;

import net.hycrafthd.umod.container.ContainerBase.Mode;
import net.hycrafthd.umod.container.ContainerPulverizer;
import net.hycrafthd.umod.network.PacketHandler;
import net.hycrafthd.umod.network.message.MessageIOMode;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class GuiPulverizer extends GuiBase {

	public BlockPos pos;

	public GuiPulverizer(EntityPlayer pl, IInventory tile, World w, BlockPos pos) {
		super(new GuiRescources("pulver.png"), new GuiRescources("battery.png"), new GuiRescources("IOMode.png"), pl, tile, new ContainerPulverizer(tile, pl, w));
		this.pos = pos;
	}

	@Override
	public void initGui() {
		super.initGui();
		box.setOnListClicked(new Runnable() {

			@Override
			public void run() {
				if (box.getSelceted() != 2) {
					if (box.getSelceted() == 0) {
						PacketHandler.INSTANCE.sendToServer(new MessageIOMode(pos,hal, ((TileEntityPulverizer) ent).getEnumOutput()));
					} else if (box.getSelceted() == 1) {
						PacketHandler.INSTANCE.sendToServer(new MessageIOMode(pos,((TileEntityPulverizer) ent).getEnumInput(),hal));
					}
				}
				worldObj.markChunkDirty(ent.getPos(), ent);
				worldObj.updateComparatorOutputLevel(ent.getPos(), ent.getBlockType());
			}
		});
	}

	@Override
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		if (basecon.mode.equals(Mode.NORMAL)) {
			fontRendererObj.drawString(((TileEntityPulverizer) this.ent).getTime() + "%", this.xSize / 2 - 5, this.ySize / 2 - 62, 0x00000);
		}
	}

	@Override
	public void addToBox(GuiCombobox box2) {
		box2.getItems().add("Input");
		box2.getItems().add("Outputs");
		box2.setSelected(0);
	}

}
