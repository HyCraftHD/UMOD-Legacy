package net.hycrafthd.umod.entity.model;

import net.minecraft.client.model.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelInfectedZombie extends ModelBase {
	// fields
	ModelRenderer headright;
	ModelRenderer headleft;
	ModelRenderer brain;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;

	public ModelInfectedZombie() {
		textureWidth = 64;
		textureHeight = 32;

		headright = new ModelRenderer(this, 24, 0);
		headright.addBox(0F, -8F, -4F, 4, 8, 8);
		headright.setRotationPoint(0F, 0F, 0F);
		headright.setTextureSize(64, 32);
		headright.mirror = true;
		setRotation(headright, 0F, 0F, 0.3141593F);
		headleft = new ModelRenderer(this, 0, 0);
		headleft.addBox(-4F, -8F, -4F, 4, 8, 8);
		headleft.setRotationPoint(0F, 0F, 0F);
		headleft.setTextureSize(64, 32);
		headleft.mirror = true;
		setRotation(headleft, 0F, 0F, -0.1745329F);
		brain = new ModelRenderer(this, 48, 0);
		brain.addBox(-2F, -6F, -2F, 4, 6, 4);
		brain.setRotationPoint(0F, 0F, 0F);
		brain.setTextureSize(64, 32);
		brain.mirror = true;
		setRotation(brain, 0F, 0F, 0F);

		body = new ModelRenderer(this, 16, 16);
		body.addBox(-4F, 0F, -2F, 8, 12, 4);
		body.setRotationPoint(0F, 0F, 0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		rightarm = new ModelRenderer(this, 40, 16);
		rightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
		rightarm.setRotationPoint(-5F, 2F, 0F);
		rightarm.setTextureSize(64, 32);
		rightarm.mirror = true;
		setRotation(rightarm, 0F, 0F, 0F);
		leftarm = new ModelRenderer(this, 40, 16);
		leftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
		leftarm.setRotationPoint(5F, 2F, 0F);
		leftarm.setTextureSize(64, 32);
		leftarm.mirror = true;
		setRotation(leftarm, 0F, 0F, 0F);
		rightleg = new ModelRenderer(this, 0, 16);
		rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
		rightleg.setRotationPoint(-2F, 12F, 0F);
		rightleg.setTextureSize(64, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0F, 0F, 0F);
		leftleg = new ModelRenderer(this, 0, 16);
		leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
		leftleg.setRotationPoint(2F, 12F, 0F);
		leftleg.setTextureSize(64, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		headright.render(f5);
		headleft.render(f5);
		brain.render(f5);
		body.render(f5);
		rightarm.render(f5);
		leftarm.render(f5);
		rightleg.render(f5);
		leftleg.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
        float f6 = MathHelper.sin(this.swingProgress * (float)Math.PI);
        float f7 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
        this.rightarm.rotateAngleZ = 0.0F;
        this.leftarm.rotateAngleZ = 0.0F;
        this.rightarm.rotateAngleY = -(0.1F - f6 * 0.6F);
        this.leftarm.rotateAngleY = 0.1F - f6 * 0.6F;
        this.rightarm.rotateAngleX = -((float)Math.PI / 2F);
        this.leftarm.rotateAngleX = -((float)Math.PI / 2F);
        this.rightarm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
        this.leftarm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
        this.rightarm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        this.leftarm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        this.rightarm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
        this.leftarm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
        
        this.rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        this.rightleg.rotateAngleY = 0.0F;
        this.leftleg.rotateAngleY = 0.0F;
	}

}
