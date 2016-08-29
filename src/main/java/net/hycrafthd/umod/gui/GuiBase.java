package net.hycrafthd.umod.gui;

import java.awt.Color;
import java.io.IOException;
import java.util.*;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Sets;

import net.hycrafthd.corelib.util.*;
import net.hycrafthd.umod.UBlocks;
import net.hycrafthd.umod.api.ISignable;
import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.container.ContainerBase;
import net.hycrafthd.umod.container.ContainerBase.Mode;
import net.hycrafthd.umod.inventory.*;
import net.hycrafthd.umod.utils.StringMethod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.player.*;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.*;

@SuppressWarnings("deprecation")
@SideOnly(Side.CLIENT)
public abstract class GuiBase extends GuiScreen {
	
	public ResourceLocation loc;
	public ResourceLocation loc1;
	public ResourceLocation loc2;
	public ResourceLocation loc3;
	public static final ResourceLocation CLEAR_GUI = new GuiRescources("clear.png");
	public EntityPlayer play;
	public TileEntity ent;
	public BlockPos pos;
	public Slot hoveredSlot;
	public ContainerBase basecon;
	public World worldObj;
	public GuiCheckbox check;
	public boolean is = true;
	
	public GuiBase(ResourceLocation loc, ResourceLocation loc2, ResourceLocation loc3, EntityPlayer pl, IInventory tile, Container con) {
		super();
		this.loc = loc;
		this.loc1 = loc;
		this.loc2 = loc2;
		this.loc3 = loc3;
		this.play = pl;
		this.ent = (TileEntity) tile;
		if(tile instanceof IPowerProvieder)
		   this.pro = (IPowerProvieder) tile;
		this.pos = ent.getPos();
		this.basecon = (ContainerBase) con;
		this.worldObj = Minecraft.getMinecraft().getIntegratedServer().worldServers[0];
		if(pro != null)
		   this.eng = new GuiEnergy(worldObj, pro,false);
	}
	
	public GuiBase(ResourceLocation loc,EntityPlayer pl, IInventory tile, Container con) {
		this(loc, new GuiRescources("battery.png"), new GuiRescources("IOMode.png"),pl,tile,con);
	}
	
	public IPowerProvieder pro;
	public EntityPlayer pl;
	public int ag;
	private GuiEnergy eng;

	
	public void drawToSMScreen(int mouseX, int mouseY, float partialTicks) {
		eng.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	public int drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color, boolean shadow) {
		return fontRendererIn.drawString(text, (float) (x - fontRendererIn.getStringWidth(text) / 2), (float) y, color, shadow);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch (button.id) {
		case 0:
			if (this.pro instanceof ISignable) {
				ISignable p = (ISignable) this.pro;
				if (button.displayString.equals("Sign with Player")) {
					p.signPlayer(this.mc.thePlayer);
					button.displayString = "Unsign";
				} else {
					this.mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Unsigned Pulverizer"));
					p.signPlayer(null);
				}
				((TileEntity) this.pro).markDirty();
			}
			break;
		}
	}
	
	@Override
	public void setWorldAndResolution(Minecraft mc, int width, int height) {
		super.setWorldAndResolution(mc, width, height);
		eng.setWorldAndResolution(mc, width, height);
	}
	
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.mc.getTextureManager().bindTexture(loc);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
	
	public ModeTabs[] tabs;
	
	@Override
	public void initGui() {
		super.initGui();
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		
		if (basecon.B) {
			tabs = new ModeTabs[Mode.values().length];
			tabs[0] = new ModeTabs(new ItemStack(UBlocks.ores, 0, 5), "Normal Mode", Mode.NORMAL, 0, 0, true) {
				
				@Override
				public void onClick(Mode m) {
					for (ModeTabs ms : tabs) {
						ms.setInUse(false);
					}
					basecon.setMode(m);
					is = true;
					loc = loc1;
					for (int i = 0; i < basecon.inventorySlots.size(); i++) {
						if (basecon.inventorySlots.get(i) instanceof BaseSlot) {
							basecon.setVisisble(i, true);
						}
						if (basecon.inventorySlots.get(i) instanceof BaseBatteryInputSlot) {
							basecon.setVisisble(i, false);
						}
					}
				}
			};
			tabs[1] = new ModeTabs(new ItemStack(UBlocks.charge), "Battery Mode", Mode.BATTERY, 28, 0, false) {
				
				@Override
				public void onClick(Mode m) {
					for (ModeTabs ms : tabs) {
						ms.setInUse(false);
					}
					basecon.setMode(m);
					loc = loc2;
					is = true;
					for (int i = 0; i < basecon.inventorySlots.size(); i++) {
						if (basecon.inventorySlots.get(i) instanceof BaseBatteryInputSlot) {
							basecon.setVisisble(i, true);
						} else if (basecon.inventorySlots.get(i) instanceof BaseSlot) {
							basecon.setVisisble(i, false);
						}
					}
				}
			};
			tabs[2] = new ModeTabs(new ItemStack(Blocks.hopper), "IO Mode", Mode.OUTPUT, 28 * 2, 0, false) {
				
				@Override
				public void onClick(Mode m) {
					for (ModeTabs ms : tabs) {
						ms.setInUse(false);
					}
					basecon.setMode(m);
					is = true;
					loc = loc3;
					for (int i = 0; i < basecon.inventorySlots.size(); i++) {
						if (basecon.inventorySlots.get(i) instanceof BaseSlot) {
							basecon.setVisisble(i, false);
						}
					}
				}
			};
			tabs[3] = new ModeTabs(new ItemStack(Blocks.wool, 0, EnumDyeColor.ORANGE.getDyeDamage()), "Panel Mode", Mode.COLOR, 28 * 3, 0, false) {
				
				@Override
				public void onClick(Mode m) {
					for (ModeTabs ms : tabs) {
						ms.setInUse(false);
					}
					basecon.setMode(m);
					is = false;
					loc = CLEAR_GUI;
					for (int i = 0; i < basecon.inventorySlots.size(); i++) {
						if (basecon.inventorySlots.get(i) instanceof BaseSlot) {
							basecon.setVisisble(i, false);
						}
					}
				}
			};
			tabs[4] = new ModeTabs(new ItemStack(UBlocks.solarpanel), "Energy Mode", Mode.ENERGY, 28 * 4, 0, false) {
				
				@Override
				public void onClick(Mode m) {
					for (ModeTabs ms : tabs) {
						ms.setInUse(false);
					}
					is = false;
					basecon.setMode(m);
					loc = new GuiRescources("solar.png");
					for (int i = 0; i < basecon.inventorySlots.size(); i++) {
						if (basecon.inventorySlots.get(i) instanceof BaseSlot) {
							basecon.setVisisble(i, false);
						}
					}
				}
			};
		}
		box = new GuiCombobox(k + 8, l + 7, 80, 12);
		check = new GuiCheckbox(k + 5, l + 5, 10, 10, new RGBA(Color.white), new RGBA(Color.DARK_GRAY));
		check.setTooltip(new StringMethod() {
			
			@Override
			public String getString() {
				if (check.isSelceted()) {
					return "Item Input List is visible";
				} else {
					return "Item Input List is hidden";
				}
			}
		});
		this.addToBox(box);
		this.play.openContainer = this.basecon;
		this.guiLeft = (this.width - this.xSize) / 2;
		this.guiTop = (this.height - this.ySize) / 2;
		box.getItems().add("Choose");
		box.setSelected(box.getItems().size() - 1);
	}
	
	public boolean canColorChange() {
		return true;
	}
	
	public abstract void addToBox(GuiCombobox box2);
	
	/** The X size of the inventory window in pixels. */
	protected int xSize = 176;
	/** The Y size of the inventory window in pixels. */
	protected int ySize = 166;
	/**
	 * Starting X position for the Gui. Inconsistent use for Gui backgrounds.
	 */
	protected int guiLeft;
	/**
	 * Starting Y position for the Gui. Inconsistent use for Gui backgrounds.
	 */
	protected int guiTop;
	/** holds the slot currently hovered */
	public Slot theSlot;
	/** Used when touchscreen is enabled. */
	public Slot clickedSlot;
	/** Used when touchscreen is enabled. */
	public boolean isRightMouseClick;
	/** Used when touchscreen is enabled */
	public ItemStack draggedStack;
	public int touchUpX;
	public int touchUpY;
	public Slot returningStackDestSlot;
	public long returningStackTime;
	/** Used when touchscreen is enabled */
	public ItemStack returningStack;
	public Slot currentDragTargetSlot;
	public long dragItemDropDelay;
	@SuppressWarnings("rawtypes")
	protected final Set dragSplittingSlots = Sets.newHashSet();
	protected boolean dragSplitting;
	public int dragSplittingLimit;
	public int dragSplittingButton;
	public boolean ignoreMouseUp;
	public int dragSplittingRemnant;
	public long lastClickTime;
	public Slot lastClickSlot;
	public int lastClickButton;
	public boolean doubleClick;
	public ItemStack shiftClickedSlot;
	public static final String __OBFID = "CL_00000737";
	public EnumFacing hal = EnumFacing.NORTH;
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		int mousePX = mouseX;
		int mousePY = mouseY;
		this.drawDefaultBackground();
		int k = this.guiLeft;
		int l = this.guiTop;
		this.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		GlStateManager.disableRescaleNormal();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableLighting();
		GlStateManager.disableDepth();
		int ks;
		
		for (ks = 0; ks < this.buttonList.size(); ++ks) {
			((GuiButton) this.buttonList.get(ks)).drawButton(this.mc, mouseX, mouseY);
			if (this.buttonList.get(ks) instanceof ExtendedGuiButton && ((ExtendedGuiButton) this.buttonList.get(ks)).isMouseOver() && ((ExtendedGuiButton) this.buttonList.get(ks)).hasString()) {
				ExtendedGuiButton gui = ((ExtendedGuiButton) this.buttonList.get(ks));
				RGBA rgb = new RGBA(0, 0, 255, 150);
				LWJGLUtils.drawGradientRect(mousePX, mousePY, mousePX + gui.getWidth(), mousePY + gui.getHeight(), rgb, rgb, this.zLevel);
				if (gui.hasMoreLines()) {
					String[] str = gui.getString().split("\n");
					for (int i = 0; i < str.length; i++)
						this.fontRendererObj.drawString(str[i], mousePX + 4, mousePY + 4 + (i * 16), gui.getFontColor());
				} else {
					this.fontRendererObj.drawString(gui.getString(), mousePX + 4, mousePY + 4, gui.getFontColor());
				}
			}
		}
		
		for (ks = 0; ks < this.labelList.size(); ++ks) {
			((GuiLabel) this.labelList.get(ks)).drawLabel(this.mc, mouseX, mouseY);
		}
		
		RenderHelper.enableGUIStandardItemLighting();
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) k, (float) l, 0.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableRescaleNormal();
		this.theSlot = null;
		short short1 = 240;
		short short2 = 240;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) short1 / 1.0F, (float) short2 / 1.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		int k1;
		
		if (is) {
			for (int ig1 = 0; ig1 < this.basecon.inventorySlots.size(); ig1++) {
				Slot slot = (Slot) this.basecon.inventorySlots.get(ig1);
				if (slot instanceof BaseSlot && ((BaseSlot) slot).hasColor() && ((BaseSlot) slot).isVisible() && (!this.isMouseOverSlot(slot, mouseX, mouseY) || !slot.canBeHovered())) {
					GlStateManager.disableLighting();
					GlStateManager.disableDepth();
					int j1 = slot.xDisplayPosition;
					k1 = slot.yDisplayPosition;
					GlStateManager.colorMask(true, true, true, false);
					RGBA st = ((BaseSlot) slot).getHoverColor(0);
					RGBA en = ((BaseSlot) slot).getHoverColor(1);
					LWJGLUtils.drawGradientRect(j1, k1, j1 + 16, k1 + 16, st, en, this.zLevel);
					GlStateManager.colorMask(true, true, true, true);
					GlStateManager.enableLighting();
					GlStateManager.enableDepth();
				}
			}
		}
		
		if (basecon.mode.equals(Mode.OUTPUT)) {
			GlStateManager.enableDepth();
			this.drawIOMode();
		}
		
		RenderHelper.disableStandardItemLighting();
		this.drawGuiContainerForegroundLayer(mouseX, mouseY);
		RenderHelper.enableGUIStandardItemLighting();
		InventoryPlayer inventoryplayer = this.mc.thePlayer.inventory;
		ItemStack itemstack = this.draggedStack == null ? inventoryplayer.getItemStack() : this.draggedStack;
		
		if (itemstack != null) {
			byte b0 = 8;
			k1 = this.draggedStack == null ? 8 : 16;
			String s = null;
			
			if (this.draggedStack != null && this.isRightMouseClick) {
				itemstack = itemstack.copy();
				itemstack.stackSize = MathHelper.ceiling_float_int((float) itemstack.stackSize / 2.0F);
			} else if (this.dragSplitting && this.dragSplittingSlots.size() > 1) {
				itemstack = itemstack.copy();
				itemstack.stackSize = this.dragSplittingRemnant;
				
				if (itemstack.stackSize == 0) {
					s = "" + EnumChatFormatting.YELLOW + "0";
				}
			}
			
			this.drawItemStack(itemstack, mouseX - k - b0, mouseY - l - k1, s);
		}
		
		if (this.returningStack != null) {
			float f1 = (float) (Minecraft.getSystemTime() - this.returningStackTime) / 100.0F;
			
			if (f1 >= 1.0F) {
				f1 = 1.0F;
				this.returningStack = null;
			}
			
			k1 = this.returningStackDestSlot.xDisplayPosition - this.touchUpX;
			int j2 = this.returningStackDestSlot.yDisplayPosition - this.touchUpY;
			int l1 = this.touchUpX + (int) ((float) k1 * f1);
			int i2 = this.touchUpY + (int) ((float) j2 * f1);
			this.drawItemStack(this.returningStack, l1, i2, (String) null);
		}
		
		if (tabs != null) {
			for (ModeTabs tab : tabs) {
				tab.render();
			}
		}
		
		GlStateManager.popMatrix();
		
		if (basecon.mode.equals(Mode.ENERGY)) {
			this.drawToSMScreen(mouseX, mouseY, partialTicks);
		}
		
		if (inventoryplayer.getItemStack() == null && this.theSlot != null && this.theSlot.getHasStack()) {
			ItemStack itemstack1 = this.theSlot.getStack();
			this.renderToolTip(itemstack1, mouseX, mouseY);
		}
		if (tabs != null) {
			for (ModeTabs tab : tabs) {
				tab.renderToolTip(mouseX, mouseY, this.guiLeft, this.guiTop);
			}
		}
		if (basecon.mode.equals(Mode.COLOR)) {
			this.drawColorMode(mouseX, mouseY);
		}
		RenderHelper.enableGUIStandardItemLighting();
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) k, (float) l, 0.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableRescaleNormal();
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) short1 / 1.0F, (float) short2 / 1.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		for (Object sl : this.basecon.inventorySlots) {
			Slot slot = (Slot) sl;
			if (!(slot instanceof BaseSlot) || ((BaseSlot) slot).isVisible()) {
				if (is) {
					this.drawSlot(slot);
					if (this.isMouseOverSlot(slot, mouseX, mouseY) && slot.canBeHovered()) {
						this.theSlot = slot;
						int j1 = slot.xDisplayPosition;
						k1 = slot.yDisplayPosition;
						if (slot instanceof BaseSlot && ((BaseSlot) slot).hasColor()) {
							RGBA st = ((BaseSlot) slot).getHoverColor(2);
							RGBA en = ((BaseSlot) slot).getHoverColor(3);
							LWJGLUtils.drawGradientRect(j1, k1, j1 + 16, k1 + 16, st, en, this.zLevel);
						} else {
							this.drawGradientRect(j1, k1, j1 + 16, k1 + 16, -2130706433, -2130706433);
						}
						if (slot instanceof BaseSlot) {
							LWJGLUtils.drawFrame(j1, k1, 16, 16, new RGBA(Color.BLACK));
							GlStateManager.popMatrix();
							if (((BaseSlot) slot).hasString()) {
								Tessellator ts = Tessellator.getInstance();
								BaseSlot slt = (BaseSlot) slot;
								RGBA sl1 = slt.getHoverColor(0);
								RGBA sli = new RGBA(sl1.toAWTColor().darker().darker().darker()).setAlpha(180);
								GlStateManager.disableTexture2D();
								GlStateManager.enableBlend();
								GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
								GlStateManager.shadeModel(7425);
								WorldRenderer rend = ts.getWorldRenderer();
								rend.startDrawingQuads();
								rend.setColorRGBA(sl1.getRed(), sl1.getGreen(), sl1.getBlue(), sl1.getAlpha());
								rend.addVertex(mouseX + slt.getWidth(), mouseY, 0);
								rend.setColorRGBA(sli.getRed(), sli.getGreen(), sli.getBlue(), sli.getAlpha());
								rend.addVertex(mouseX, mouseY, 0);
								rend.addVertex(mouseX, mouseY + slt.getHeight(), 0);
								rend.setColorRGBA(sl1.getRed(), sl1.getGreen(), sl1.getBlue(), sl1.getAlpha());
								rend.addVertex(mouseX + slt.getWidth(), mouseY + slt.getHeight(), 0);
								ts.draw();
								GlStateManager.shadeModel(7424);
								GlStateManager.disableBlend();
								GlStateManager.enableAlpha();
								GlStateManager.enableTexture2D();
								if (((BaseSlot) slot).hasMoreLines()) {
									String[] str = ((BaseSlot) slot).getString().split("\n");
									for (int i = 0; i < str.length; i++)
										this.fontRendererObj.drawString(str[i], mouseX + 4, mouseY + 4 + (i * 16), ((BaseSlot) slot).getFontColor());
								} else {
									this.fontRendererObj.drawString(((BaseSlot) slot).getString(), mouseX + 4, mouseY + 4, ((BaseSlot) slot).getFontColor());
								}
							}
							GlStateManager.pushMatrix();
							GlStateManager.translate((float) k, (float) l, 0.0F);
							GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
							GlStateManager.enableRescaleNormal();
						}
					}
				}
			}
		}
		GlStateManager.popMatrix();
		GlStateManager.enableLighting();
		GlStateManager.enableDepth();
		RenderHelper.enableStandardItemLighting();
		
	}
	
	public void drawColorMode(int x, int y) {
		check.draw(x, y, mc);
	}
	
	public GuiCombobox box;
	
	public void drawIOMode() {
		int k = this.guiLeft;
		int l = this.guiTop;
		GlStateManager.popMatrix();
		this.renderItemIntoGUI(new ItemStack(ent.getWorld().getBlockState(pos).getBlock()), (width / 2), (height / 2) - (ySize / 4));
		box.draw(this.mc);
		GlStateManager.disableDepth();
		int kl = (this.width - this.xSize) / 2;
		this.fontRendererObj.drawString(hal.toString(), kl + 10, this.height / 2 - 10, 0xFFFFFF);
		RenderHelper.enableGUIStandardItemLighting();
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) k, (float) l, 0.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableRescaleNormal();
	}
	
	public EnumFacing getIOFaceing() {
		return hal;
	}
	
	private void setupGuiTransform(int xPosition, int yPosition, boolean isGui3d) {
		GlStateManager.translate((float) xPosition, (float) yPosition, 100.0F + this.zLevel);
		GlStateManager.translate(8.0F, 8.0F, 0.0F);
		GlStateManager.scale(2.0F, 2.0F, -2.0F);
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
		
		if (isGui3d) {
			GlStateManager.scale(40.0F, 40.0F, 40.0F);
			GlStateManager.rotate(sclay + 180, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(sclax, 0.0F, 1.0F, 0.0F);
			GlStateManager.enableLighting();
		} else {
			GlStateManager.scale(64.0F, 64.0F, 64.0F);
			GlStateManager.disableLighting();
		}
	}
	
	public void renderItemIntoGUI(ItemStack stack, int x, int y) {
		IBakedModel ibakedmodel = this.itemRender.getItemModelMesher().getItemModel(stack);
		GlStateManager.pushMatrix();
		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
		Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.locationBlocksTexture).setBlurMipmap(false, false);
		GlStateManager.enableRescaleNormal();
		GlStateManager.enableAlpha();
		GlStateManager.alphaFunc(516, 0.1F);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(770, 771);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.setupGuiTransform(x, y, ibakedmodel.isGui3d());
		ibakedmodel = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(ibakedmodel, ItemCameraTransforms.TransformType.GUI);
		this.renderItem(stack, ibakedmodel);
		GlStateManager.disableAlpha();
		GlStateManager.disableRescaleNormal();
		GlStateManager.disableLighting();
		GlStateManager.popMatrix();
		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
		Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.locationBlocksTexture).restoreLastBlurMipmap();
	}
	
	private void renderModel(IBakedModel model, int color) {
		this.renderModel(model, color, (ItemStack) null);
	}
	
	public void renderItem(ItemStack stack, IBakedModel model) {
		GlStateManager.pushMatrix();
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
		
		if (model.isBuiltInRenderer()) {
			GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(-0.5F, -0.5F, -0.5F);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.enableRescaleNormal();
			TileEntityItemStackRenderer.instance.renderByItem(stack);
		} else {
			GlStateManager.translate(-0.5F, -0.5F, -0.5F);
			this.renderModel(model, -1, stack);
			
			if (stack.hasEffect()) {
				this.renderEffect(model);
			}
		}
		
		GlStateManager.popMatrix();
	}
	
	private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
	
	private void renderEffect(IBakedModel model) {
		GlStateManager.depthMask(false);
		GlStateManager.depthFunc(514);
		GlStateManager.disableLighting();
		GlStateManager.blendFunc(768, 1);
		Minecraft.getMinecraft().getTextureManager().bindTexture(RES_ITEM_GLINT);
		GlStateManager.matrixMode(5890);
		GlStateManager.pushMatrix();
		GlStateManager.scale(8.0F, 8.0F, 8.0F);
		float f = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F / 8.0F;
		GlStateManager.translate(f, 0.0F, 0.0F);
		GlStateManager.rotate(-50.0F, 0.0F, 0.0F, 1.0F);
		this.renderModel(model, -8372020);
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		GlStateManager.scale(8.0F, 8.0F, 8.0F);
		float f1 = (float) (Minecraft.getSystemTime() % 4873L) / 4873.0F / 8.0F;
		GlStateManager.translate(-f1, 0.0F, 0.0F);
		GlStateManager.rotate(10.0F, 0.0F, 0.0F, 1.0F);
		this.renderModel(model, -8372020);
		GlStateManager.popMatrix();
		GlStateManager.matrixMode(5888);
		GlStateManager.blendFunc(770, 771);
		GlStateManager.enableLighting();
		GlStateManager.depthFunc(515);
		GlStateManager.depthMask(true);
		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
	}
	
	private void renderModel(IBakedModel model, int color, ItemStack stack) {
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		worldrenderer.startDrawingQuads();
		worldrenderer.setVertexFormat(DefaultVertexFormats.ITEM);
		EnumFacing[] aenumfacing = EnumFacing.values();
		int j = aenumfacing.length;
		
		for (int k = 0; k < j; ++k) {
			EnumFacing enumfacing = aenumfacing[k];
			this.renderQuads(worldrenderer, model.getFaceQuads(enumfacing), color, stack);
		}
		
		this.renderQuads(worldrenderer, model.getGeneralQuads(), color, stack);
		tessellator.draw();
	}
	
	@SuppressWarnings("rawtypes")
	private void renderQuads(WorldRenderer renderer, List quads, int color, ItemStack stack) {
		boolean flag = color == -1 && stack != null;
		BakedQuad bakedquad;
		int j;
		
		for (Iterator iterator = quads.iterator(); iterator.hasNext(); this.renderQuad(renderer, bakedquad, j)) {
			bakedquad = (BakedQuad) iterator.next();
			j = color;
			
			if (flag && bakedquad.hasTintIndex()) {
				j = stack.getItem().getColorFromItemStack(stack, bakedquad.getTintIndex());
				
				if (EntityRenderer.anaglyphEnable) {
					j = TextureUtil.anaglyphColor(j);
				}
				
				j |= -16777216;
			}
		}
	}
	
	private void renderQuad(WorldRenderer renderer, BakedQuad quad, int color) {
		renderer.addVertexData(quad.getVertexData());
		if (quad instanceof net.minecraftforge.client.model.IColoredBakedQuad)
			net.minecraftforge.client.ForgeHooksClient.putQuadColor(renderer, quad, color);
		else
			renderer.putColor4(color);
		this.putQuadNormal(renderer, quad);
	}
	
	private void putQuadNormal(WorldRenderer renderer, BakedQuad quad) {
		Vec3i vec3i = quad.getFace().getDirectionVec();
		renderer.putNormal((float) vec3i.getX(), (float) vec3i.getY(), (float) vec3i.getZ());
	}
	
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
	}
	
	/**
	 * Render an ItemStack. Args : stack, x, y, format
	 */
	public void drawItemStack(ItemStack stack, int x, int y, String altText) {
		GlStateManager.translate(0.0F, 0.0F, 32.0F);
		this.zLevel = 200.0F;
		this.itemRender.zLevel = 200.0F;
		FontRenderer font = null;
		if (stack != null)
			font = stack.getItem().getFontRenderer(stack);
		if (font == null)
			font = fontRendererObj;
		this.itemRender.renderItemAndEffectIntoGUI(stack, x, y);
		this.itemRender.renderItemOverlayIntoGUI(font, stack, x, y - (this.draggedStack == null ? 0 : 8), altText);
		this.zLevel = 0.0F;
		this.itemRender.zLevel = 0.0F;
	}
	
	public void drawSlot(Slot slotIn) {
		int i = slotIn.xDisplayPosition;
		int j = slotIn.yDisplayPosition;
		ItemStack itemstack = slotIn.getStack();
		boolean flag = false;
		boolean flag1 = slotIn == this.clickedSlot && this.draggedStack != null && !this.isRightMouseClick;
		ItemStack itemstack1 = this.mc.thePlayer.inventory.getItemStack();
		String s = null;
		
		if (slotIn == this.clickedSlot && this.draggedStack != null && this.isRightMouseClick && itemstack != null) {
			itemstack = itemstack.copy();
			itemstack.stackSize /= 2;
		} else if (this.dragSplitting && this.dragSplittingSlots.contains(slotIn) && itemstack1 != null) {
			if (this.dragSplittingSlots.size() == 1) {
				return;
			}
			
			if (Container.canAddItemToSlot(slotIn, itemstack1, true) && this.basecon.canDragIntoSlot(slotIn)) {
				itemstack = itemstack1.copy();
				flag = true;
				Container.computeStackSize(this.dragSplittingSlots, this.dragSplittingLimit, itemstack, slotIn.getStack() == null ? 0 : slotIn.getStack().stackSize);
				
				if (itemstack.stackSize > itemstack.getMaxStackSize()) {
					s = EnumChatFormatting.YELLOW + "" + itemstack.getMaxStackSize();
					itemstack.stackSize = itemstack.getMaxStackSize();
				}
				
				if (itemstack.stackSize > slotIn.getItemStackLimit(itemstack)) {
					s = EnumChatFormatting.YELLOW + "" + slotIn.getItemStackLimit(itemstack);
					itemstack.stackSize = slotIn.getItemStackLimit(itemstack);
				}
			} else {
				this.dragSplittingSlots.remove(slotIn);
				this.updateDragSplitting();
			}
		}
		
		this.zLevel = 100.0F;
		this.itemRender.zLevel = 100.0F;
		
		if (itemstack == null) {
			TextureAtlasSprite textureatlassprite = slotIn.getBackgroundSprite();
			
			if (textureatlassprite != null) {
				GlStateManager.disableLighting();
				this.mc.getTextureManager().bindTexture(slotIn.getBackgroundLocation());
				this.drawTexturedModalRect(i, j, textureatlassprite, 16, 16);
				GlStateManager.enableLighting();
				flag1 = true;
			}
		}
		
		if (!flag1) {
			if (flag) {
				drawRect(i, j, i + 16, j + 16, -2130706433);
			}
			
			GlStateManager.enableDepth();
			this.itemRender.renderItemAndEffectIntoGUI(itemstack, i, j);
			this.itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, itemstack, i, j, s);
		}
		
		this.itemRender.zLevel = 0.0F;
		this.zLevel = 0.0F;
	}
	
	@SuppressWarnings("rawtypes")
	public void updateDragSplitting() {
		ItemStack itemstack = this.mc.thePlayer.inventory.getItemStack();
		
		if (itemstack != null && this.dragSplitting) {
			this.dragSplittingRemnant = itemstack.stackSize;
			ItemStack itemstack1;
			int i;
			
			for (Iterator iterator = this.dragSplittingSlots.iterator(); iterator.hasNext(); this.dragSplittingRemnant -= itemstack1.stackSize - i) {
				Slot slot = (Slot) iterator.next();
				itemstack1 = itemstack.copy();
				i = slot.getStack() == null ? 0 : slot.getStack().stackSize;
				Container.computeStackSize(this.dragSplittingSlots, this.dragSplittingLimit, itemstack1, i);
				
				if (itemstack1.stackSize > itemstack1.getMaxStackSize()) {
					itemstack1.stackSize = itemstack1.getMaxStackSize();
				}
				
				if (itemstack1.stackSize > slot.getItemStackLimit(itemstack1)) {
					itemstack1.stackSize = slot.getItemStackLimit(itemstack1);
				}
			}
		}
	}
	
	/**
	 * Returns the slot at the given coordinates or null if there is none.
	 */
	public Slot getSlotAtPosition(int x, int y) {
		for (int k = 0; k < this.basecon.inventorySlots.size(); ++k) {
			Slot slot = (Slot) this.basecon.inventorySlots.get(k);
			
			if (this.isMouseOverSlot(slot, x, y)) {
				return slot;
			}
		}
		
		return null;
	}
	
	/**
	 * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
	 */
	private int posX;
	private int posY;
	
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		posX = mouseX;
		posY = mouseY;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		if (cal.get(Calendar.MONTH) == Calendar.APRIL && cal.get(Calendar.DAY_OF_MONTH) == 1) {
			Minecraft.getMinecraft().getIntegratedServer().worldServers[0].createExplosion(play, this.pos.getX(), this.pos.getY(), this.pos.getZ(), 2.5F, false);
			Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "" + EnumChatFormatting.OBFUSCATED + "HOLLO" + EnumChatFormatting.RESET + " " + EnumChatFormatting.RED + "You be trolled " + EnumChatFormatting.GREEN + "" + EnumChatFormatting.OBFUSCATED + "HOLLO" + EnumChatFormatting.RESET));
		}
		if (mouseButton == 0)
			this.handelMouseInput(mouseX, mouseY);
		super.mouseClicked(mouseX, mouseY, mouseButton);
		if (mouseButton == 0) {
			if (basecon.mode.equals(Mode.COLOR)) {
				check.handelMouseClick(mouseX, mouseY);
			}
			if (basecon.mode.equals(Mode.OUTPUT)) {
				box.handelClick(mouseX, mouseY);
			}
			if (tabs != null) {
				for (ModeTabs tab : tabs) {
					tab.handelMouseInput(mouseX, mouseY, this.guiLeft, this.guiTop);
				}
			}
		}
		boolean flag = mouseButton == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100;
		Slot slot = this.getSlotAtPosition(mouseX, mouseY);
		long l = Minecraft.getSystemTime();
		this.doubleClick = this.lastClickSlot == slot && l - this.lastClickTime < 250L && this.lastClickButton == mouseButton;
		this.ignoreMouseUp = false;
		
		if (mouseButton == 0 || mouseButton == 1 || flag) {
			int i1 = this.guiLeft;
			int j1 = this.guiTop;
			boolean flag1 = mouseX < i1 || mouseY < j1 || mouseX >= i1 + this.xSize || mouseY >= j1 + this.ySize;
			int k1 = -1;
			
			if (slot != null) {
				k1 = slot.slotNumber;
			}
			
			if (flag1) {
				k1 = -999;
			}
			
			if (this.mc.gameSettings.touchscreen && flag1 && this.mc.thePlayer.inventory.getItemStack() == null) {
				this.mc.displayGuiScreen((GuiScreen) null);
				return;
			}
			
			if (k1 != -1) {
				if (this.mc.gameSettings.touchscreen) {
					if (slot != null && slot.getHasStack()) {
						this.clickedSlot = slot;
						this.draggedStack = null;
						this.isRightMouseClick = mouseButton == 1;
					} else {
						this.clickedSlot = null;
					}
				} else if (!this.dragSplitting) {
					if (this.mc.thePlayer.inventory.getItemStack() == null) {
						if (mouseButton == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100) {
							this.handleMouseClick(slot, k1, mouseButton, 3);
						} else {
							boolean flag2 = k1 != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54));
							byte b0 = 0;
							
							if (flag2) {
								this.shiftClickedSlot = slot != null && slot.getHasStack() ? slot.getStack() : null;
								b0 = 1;
							} else if (k1 == -999) {
								b0 = 4;
							}
							
							this.handleMouseClick(slot, k1, mouseButton, b0);
						}
						
						this.ignoreMouseUp = true;
					} else {
						this.dragSplitting = true;
						this.dragSplittingButton = mouseButton;
						this.dragSplittingSlots.clear();
						
						if (mouseButton == 0) {
							this.dragSplittingLimit = 0;
						} else if (mouseButton == 1) {
							this.dragSplittingLimit = 1;
						} else if (mouseButton == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100) {
							this.dragSplittingLimit = 2;
						}
					}
				}
			}
		}
		
		this.lastClickSlot = slot;
		this.lastClickTime = l;
		this.lastClickButton = mouseButton;
	}
	
	public void handelMouseInput(int mouseX, int mouseY) {
	}
	
	private int sclax = 0;
	private int sclay = 0;
	
	@SuppressWarnings("unchecked")
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		if (basecon.mode.equals(Mode.OUTPUT) && clickedMouseButton == 0 && mouseX > 7 + guiLeft && mouseX < 169 + guiLeft && mouseY > guiTop + 6 && mouseY < guiTop + 82) {
			if (sclax + (mouseX - posX) <= 90 && sclax + (mouseX - posX) >= -180) {
				sclax += mouseX - posX;
			}
			if (sclay + (mouseY - posY) <= 90 && sclay + (mouseY - posY) >= -90) {
				sclay += mouseY - posY;
			}
			posX = mouseX;
			posY = mouseY;
			if (sclay >= 45 && sclay <= 135) {
				hal = EnumFacing.UP;
			}
			if (sclay <= -45 && sclay >= -215) {
				hal = EnumFacing.DOWN;
			}
			if (sclay >= -45 && sclay <= 45) {
				if (sclax >= -45 && sclax <= 45) {
					hal = EnumFacing.NORTH;
				}
				if (sclax <= 135 && sclax >= 45) {
					hal = EnumFacing.EAST;
				}
				if (sclax <= -45 && sclax >= -135) {
					hal = EnumFacing.WEST;
				}
				if (sclax <= -135 && sclax >= -210) {
					hal = EnumFacing.SOUTH;
				}
			}
			onIOModeSwitched();
		}
		this.onMouseClickMoved(mouseX, mouseY);
		Slot slot = this.getSlotAtPosition(mouseX, mouseY);
		ItemStack itemstack = this.mc.thePlayer.inventory.getItemStack();
		
		if (this.clickedSlot != null && this.mc.gameSettings.touchscreen) {
			if (clickedMouseButton == 0 || clickedMouseButton == 1) {
				if (this.draggedStack == null) {
					if (slot != this.clickedSlot) {
						this.draggedStack = this.clickedSlot.getStack().copy();
					}
				} else if (this.draggedStack.stackSize > 1 && slot != null && Container.canAddItemToSlot(slot, this.draggedStack, false)) {
					long i1 = Minecraft.getSystemTime();
					
					if (this.currentDragTargetSlot == slot) {
						if (i1 - this.dragItemDropDelay > 500L) {
							this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, 0, 0);
							this.handleMouseClick(slot, slot.slotNumber, 1, 0);
							this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, 0, 0);
							this.dragItemDropDelay = i1 + 750L;
							--this.draggedStack.stackSize;
						}
					} else {
						this.currentDragTargetSlot = slot;
						this.dragItemDropDelay = i1;
					}
				}
			}
		} else if (this.dragSplitting && slot != null && itemstack != null && itemstack.stackSize > this.dragSplittingSlots.size() && Container.canAddItemToSlot(slot, itemstack, true) && slot.isItemValid(itemstack) && this.basecon.canDragIntoSlot(slot)) {
			this.dragSplittingSlots.add(slot);
			this.updateDragSplitting();
		}
	}
	
	public void onMouseClickMoved(int mouseX, int mouseY) {
	}
	
	public EnumFacing face, face2;
	
	public void onCallBack(EnumFacing fc, int mode) {
		switch (mode) {
		case 0:
			this.face = fc;
			break;
		case 1:
			this.face2 = fc;
		default:
			break;
		}
	}
	
	public abstract void onIOModeSwitched();
	
	/**
	 * Called when a mouse button is released. Args : mouseX, mouseY, releaseButton
	 */
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		Slot slot = this.getSlotAtPosition(mouseX, mouseY);
		int l = this.guiLeft;
		int i1 = this.guiTop;
		boolean flag = mouseX < l || mouseY < i1 || mouseX >= l + this.xSize || mouseY >= i1 + this.ySize;
		int j1 = -1;
		
		if (slot != null) {
			j1 = slot.slotNumber;
		}
		
		if (flag) {
			j1 = -999;
		}
		
		Slot slot1;
		@SuppressWarnings("rawtypes")
		Iterator iterator;
		
		if (this.doubleClick && slot != null && state == 0 && this.basecon.canMergeSlot((ItemStack) null, slot)) {
			if (isShiftKeyDown()) {
				if (slot != null && slot.inventory != null && this.shiftClickedSlot != null) {
					iterator = this.basecon.inventorySlots.iterator();
					
					while (iterator.hasNext()) {
						slot1 = (Slot) iterator.next();
						
						if (slot1 != null && slot1.canTakeStack(this.mc.thePlayer) && slot1.getHasStack() && slot1.inventory == slot.inventory && Container.canAddItemToSlot(slot1, this.shiftClickedSlot, true)) {
							this.handleMouseClick(slot1, slot1.slotNumber, state, 1);
						}
					}
				}
			} else {
				this.handleMouseClick(slot, j1, state, 6);
			}
			
			this.doubleClick = false;
			this.lastClickTime = 0L;
		} else {
			if (this.dragSplitting && this.dragSplittingButton != state) {
				this.dragSplitting = false;
				this.dragSplittingSlots.clear();
				this.ignoreMouseUp = true;
				return;
			}
			
			if (this.ignoreMouseUp) {
				this.ignoreMouseUp = false;
				return;
			}
			
			boolean flag1;
			
			if (this.clickedSlot != null && this.mc.gameSettings.touchscreen) {
				if (state == 0 || state == 1) {
					if (this.draggedStack == null && slot != this.clickedSlot) {
						this.draggedStack = this.clickedSlot.getStack();
					}
					
					flag1 = Container.canAddItemToSlot(slot, this.draggedStack, false);
					
					if (j1 != -1 && this.draggedStack != null && flag1) {
						this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, state, 0);
						this.handleMouseClick(slot, j1, 0, 0);
						
						if (this.mc.thePlayer.inventory.getItemStack() != null) {
							this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, state, 0);
							this.touchUpX = mouseX - l;
							this.touchUpY = mouseY - i1;
							this.returningStackDestSlot = this.clickedSlot;
							this.returningStack = this.draggedStack;
							this.returningStackTime = Minecraft.getSystemTime();
						} else {
							this.returningStack = null;
						}
					} else if (this.draggedStack != null) {
						this.touchUpX = mouseX - l;
						this.touchUpY = mouseY - i1;
						this.returningStackDestSlot = this.clickedSlot;
						this.returningStack = this.draggedStack;
						this.returningStackTime = Minecraft.getSystemTime();
					}
					
					this.draggedStack = null;
					this.clickedSlot = null;
				}
			} else if (this.dragSplitting && !this.dragSplittingSlots.isEmpty()) {
				this.handleMouseClick((Slot) null, -999, Container.func_94534_d(0, this.dragSplittingLimit), 5);
				iterator = this.dragSplittingSlots.iterator();
				
				while (iterator.hasNext()) {
					slot1 = (Slot) iterator.next();
					this.handleMouseClick(slot1, slot1.slotNumber, Container.func_94534_d(1, this.dragSplittingLimit), 5);
				}
				
				this.handleMouseClick((Slot) null, -999, Container.func_94534_d(2, this.dragSplittingLimit), 5);
			} else if (this.mc.thePlayer.inventory.getItemStack() != null) {
				if (state == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100) {
					this.handleMouseClick(slot, j1, state, 3);
				} else {
					flag1 = j1 != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54));
					
					if (flag1) {
						this.shiftClickedSlot = slot != null && slot.getHasStack() ? slot.getStack() : null;
					}
					
					this.handleMouseClick(slot, j1, state, flag1 ? 1 : 0);
				}
			}
		}
		
		if (this.mc.thePlayer.inventory.getItemStack() == null) {
			this.lastClickTime = 0L;
		}
		
		this.dragSplitting = false;
	}
	
	/**
	 * Returns if the passed mouse position is over the specified slot. Args : slot, mouseX, mouseY
	 */
	public boolean isMouseOverSlot(Slot slotIn, int mouseX, int mouseY) {
		return this.isPointInRegion(slotIn.xDisplayPosition, slotIn.yDisplayPosition, 16, 16, mouseX, mouseY);
	}
	
	/**
	 * Test if the 2D point is in a rectangle (relative to the GUI). Args : rectX, rectY, rectWidth, rectHeight, pointX, pointY
	 */
	protected boolean isPointInRegion(int left, int top, int right, int bottom, int pointX, int pointY) {
		int k1 = this.guiLeft;
		int l1 = this.guiTop;
		pointX -= k1;
		pointY -= l1;
		return pointX >= left - 1 && pointX < left + right + 1 && pointY >= top - 1 && pointY < top + bottom + 1;
	}
	
	/**
	 * Called when the mouse is clicked over a slot or outside the gui.
	 */
	protected void handleMouseClick(Slot slotIn, int slotId, int clickedButton, int clickType) {
		if (slotIn != null) {
			slotId = slotIn.slotNumber;
		}
		
		this.mc.playerController.windowClick(this.basecon.windowId, slotId, clickedButton, clickType, this.mc.thePlayer);
	}
	
	/**
	 * Fired when a key is typed (except F11 who toggle full screen). This is the equivalent of KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
	 */
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (keyCode == 1 || keyCode == this.mc.gameSettings.keyBindInventory.getKeyCode()) {
			this.mc.thePlayer.closeScreen();
		}
		
		this.checkHotbarKeys(keyCode);
		
		if (this.theSlot != null && this.theSlot.getHasStack()) {
			if (keyCode == this.mc.gameSettings.keyBindPickBlock.getKeyCode()) {
				this.handleMouseClick(this.theSlot, this.theSlot.slotNumber, 0, 3);
			} else if (keyCode == this.mc.gameSettings.keyBindDrop.getKeyCode()) {
				this.handleMouseClick(this.theSlot, this.theSlot.slotNumber, isCtrlKeyDown() ? 1 : 0, 4);
			}
		}
	}
	
	/**
	 * This function is what controls the hotbar shortcut check when you press a number key when hovering a stack. Args : keyCode, Returns true if a Hotbar key is pressed, else false
	 */
	protected boolean checkHotbarKeys(int keyCode) {
		if (this.play.inventory.getItemStack() == null && this.theSlot != null) {
			for (int j = 0; j < 9; ++j) {
				if (keyCode == this.mc.gameSettings.keyBindsHotbar[j].getKeyCode()) {
					this.handleMouseClick(this.theSlot, this.theSlot.slotNumber, j, 2);
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void onGuiClosed() {
		if (this.mc.thePlayer != null) {
			this.basecon.onContainerClosed(this.play);
		}
	}
	
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	public void updateScreen() {
		super.updateScreen();
		
		if (!this.play.isEntityAlive() || this.play.isDead) {
			this.play.closeScreen();
		}
	}
	
}