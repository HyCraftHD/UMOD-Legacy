package net.hycrafthd.umod.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelInfectedCreeper extends ModelBase
{
  //fields
    ModelRenderer headleft;
    ModelRenderer headright;
    ModelRenderer body;
    ModelRenderer throatright;
    ModelRenderer throatleft;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer sideleft1;
    ModelRenderer sideleft2;
    ModelRenderer sideright1;
    ModelRenderer sideright2;
  
  public ModelInfectedCreeper()
  {
	  textureWidth = 64;
	    textureHeight = 64;
	    
	      headleft = new ModelRenderer(this, 24, 24);
	      headleft.addBox(-4F, -8F, -4F, 10, 10, 10);
	      headleft.setRotationPoint(5F, -5F, -1F);
	      headleft.setTextureSize(64, 64);
	      headleft.mirror = true;
	      setRotation(headleft, 0F, 0F, 0F);
	      headright = new ModelRenderer(this, 24, 44);
	      headright.addBox(0F, 0F, 0F, 10, 10, 10);
	      headright.setRotationPoint(-11F, -13F, -5F);
	      headright.setTextureSize(64, 64);
	      headright.mirror = true;
	      setRotation(headright, 0F, 0F, 0F);
	      body = new ModelRenderer(this, 36, 0);
	      body.addBox(-4F, 0F, -2F, 6, 11, 6);
	      body.setRotationPoint(-3F, -3.8F, -1F);
	      body.setTextureSize(64, 64);
	      body.mirror = true;
	      setRotation(body, 0F, 0F, -0.0872665F);
	      throatright = new ModelRenderer(this, 36, 0);
	      throatright.addBox(0F, 0F, 0F, 6, 11, 6);
	      throatright.setRotationPoint(1F, -4F, -3F);
	      throatright.setTextureSize(64, 64);
	      throatright.mirror = true;
	      setRotation(throatright, 0F, 0F, 0.0872665F);
	      throatleft = new ModelRenderer(this, 0, 0);
	      throatleft.addBox(0F, 0F, 0F, 12, 8, 6);
	      throatleft.setRotationPoint(-6F, 7F, -3F);
	      throatleft.setTextureSize(64, 64);
	      throatleft.mirror = true;
	      setRotation(throatleft, 0F, 0F, 0F);
	      leg3 = new ModelRenderer(this, 0, 24);
	      leg3.addBox(-2F, 0F, -2F, 6, 9, 6);
	      leg3.setRotationPoint(-4F, 15F, -7F);
	      leg3.setTextureSize(64, 64);
	      leg3.mirror = true;
	      setRotation(leg3, 0F, 0F, 0F);
	      leg4 = new ModelRenderer(this, 0, 24);
	      leg4.addBox(-2F, 0F, -2F, 6, 9, 6);
	      leg4.setRotationPoint(2F, 15F, -7F);
	      leg4.setTextureSize(64, 64);
	      leg4.mirror = true;
	      setRotation(leg4, 0F, 0F, 0F);
	      leg1 = new ModelRenderer(this, 0, 24);
	      leg1.addBox(0F, 0F, -2F, 6, 9, 6);
	      leg1.setRotationPoint(-6F, 15F, 5F);
	      leg1.setTextureSize(64, 64);
	      leg1.mirror = true;
	      setRotation(leg1, 0F, 0F, 0F);
	      leg2 = new ModelRenderer(this, 0, 24);
	      leg2.addBox(-2F, 0F, -2F, 6, 9, 6);
	      leg2.setRotationPoint(2F, 15F, 5F);
	      leg2.setTextureSize(64, 64);
	      leg2.mirror = true;
	      setRotation(leg2, 0F, 0F, 0F);
	      sideleft1 = new ModelRenderer(this, 0, 39);
	      sideleft1.addBox(0F, 0F, 0F, 2, 8, 4);
	      sideleft1.setRotationPoint(7F, -3.2F, -2F);
	      sideleft1.setTextureSize(64, 64);
	      sideleft1.mirror = true;
	      setRotation(sideleft1, 0F, 0F, 0.1047198F);
	      sideleft2 = new ModelRenderer(this, 0, 39);
	      sideleft2.addBox(0F, 0F, 0F, 2, 8, 4);
	      sideleft2.setRotationPoint(-9F, -3.2F, -2F);
	      sideleft2.setTextureSize(64, 64);
	      sideleft2.mirror = true;
	      setRotation(sideleft2, 0F, 0F, -0.0872665F);
	      sideright1 = new ModelRenderer(this, 12, 39);
	      sideright1.addBox(0F, 0F, 0F, 1, 6, 3);
	      sideright1.setRotationPoint(6F, 4.8F, -1.5F);
	      sideright1.setTextureSize(64, 64);
	      sideright1.mirror = true;
	      setRotation(sideright1, 0F, 0F, 0.0698132F);
	      sideright2 = new ModelRenderer(this, 12, 39);
	      sideright2.addBox(0F, 3F, 0F, 1, 6, 3);
	      sideright2.setRotationPoint(-7.3F, 1.6F, -1.5F);
	      sideright2.setTextureSize(64, 64);
	      sideright2.mirror = true;
	      setRotation(sideright2, 0F, 0F, -0.0698132F);
  }
  
  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
	  super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    headleft.render(f5);
	    headright.render(f5);
	    body.render(f5);
	    throatright.render(f5);
	    throatleft.render(f5);
	    leg3.render(f5);
	    leg4.render(f5);
	    leg1.render(f5);
	    leg2.render(f5);
	    sideleft1.render(f5);
	    sideleft2.render(f5);
	    sideright1.render(f5);
	    sideright2.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
  }
  
  @Override
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    this.leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    this.leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
    this.leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
    this.leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
  }
  
}

