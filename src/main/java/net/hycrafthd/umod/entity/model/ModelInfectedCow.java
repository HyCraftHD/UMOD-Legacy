package net.hycrafthd.umod.entity.model;

import net.minecraft.client.model.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelInfectedCow extends ModelBase {
	// fields
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer horn1;
	ModelRenderer horn2;
	ModelRenderer horn3;
	ModelRenderer horn4;
	ModelRenderer udders;
	ModelRenderer head2;

	public ModelInfectedCow() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-5F, -4F, -6F, 8, 8, 6);
		head.setRotationPoint(0F, 4F, -8F);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		body = new ModelRenderer(this, 0, 14);
		body.addBox(-6F, -10F, -7F, 12, 18, 10);
		body.setRotationPoint(0F, 5F, 2F);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 1.570796F, 0F, 0F);
		leg1 = new ModelRenderer(this, 0, 42);
		leg1.addBox(-3F, 0F, -2F, 4, 12, 4);
		leg1.setRotationPoint(-3F, 12F, 7F);
		leg1.setTextureSize(64, 64);
		leg1.mirror = true;
		setRotation(leg1, 0F, 0F, 0F);
		leg2 = new ModelRenderer(this, 16, 42);
		leg2.addBox(-1F, 0F, -2F, 4, 12, 4);
		leg2.setRotationPoint(3F, 12F, 7F);
		leg2.setTextureSize(64, 64);
		leg2.mirror = true;
		setRotation(leg2, 0F, 0F, 0F);
		leg3 = new ModelRenderer(this, 32, 42);
		leg3.addBox(-3F, 0F, -3F, 4, 12, 4);
		leg3.setRotationPoint(-3F, 12F, -5F);
		leg3.setTextureSize(64, 64);
		leg3.mirror = true;
		setRotation(leg3, 0F, 0F, 0F);
		leg4 = new ModelRenderer(this, 48, 42);
		leg4.addBox(-1F, 0F, -3F, 4, 12, 4);
		leg4.setRotationPoint(3F, 12F, -5F);
		leg4.setTextureSize(64, 64);
		leg4.mirror = true;
		setRotation(leg4, 0F, 0F, 0F);
		horn1 = new ModelRenderer(this, 50, 0);
		horn1.addBox(-5F, -5F, -4F, 1, 3, 1);
		horn1.setRotationPoint(1F, 2F, -7F);
		horn1.setTextureSize(64, 64);
		horn1.mirror = true;
		setRotation(horn1, 0.0743572F, 0.0371786F, -0.1858931F);
		horn2 = new ModelRenderer(this, 50, 0);
		horn2.addBox(2F, -5F, -4F, 1, 3, 1);
		horn2.setRotationPoint(-1F, 2F, -7F);
		horn2.setTextureSize(64, 64);
		horn2.mirror = true;
		setRotation(horn2, 0.0743572F, -0.1115358F, 0.0371786F);
		horn3 = new ModelRenderer(this, 50, 0);
		horn3.addBox(0F, 0F, 0F, 1, 3, 1);
		horn3.setRotationPoint(4F, -2F, -12F);
		horn3.setTextureSize(64, 64);
		horn3.mirror = true;
		setRotation(horn3, 0.3717861F, 0F, 0F);
		horn4 = new ModelRenderer(this, 50, 0);
		horn4.addBox(0F, 0F, 0F, 1, 3, 1);
		horn4.setRotationPoint(8F, -2F, -10F);
		horn4.setTextureSize(64, 64);
		horn4.mirror = true;
		setRotation(horn4, 0.0371786F, 0.1858931F, 0.1115358F);
		udders = new ModelRenderer(this, 44, 11);
		udders.addBox(-2F, -3F, 0F, 4, 6, 2);
		udders.setRotationPoint(0F, 14F, 6F);
		udders.setTextureSize(64, 64);
		udders.mirror = true;
		setRotation(udders, 1.570796F, 0F, 0F);
		head2 = new ModelRenderer(this, 28, 0);
		head2.addBox(0F, 0F, 0F, 6, 6, 5);
		head2.setRotationPoint(4F, 0F, -12F);
		head2.setTextureSize(64, 64);
		head2.mirror = true;
		setRotation(head2, -0.0743572F, -0.3346075F, 0.0743572F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		head.render(f5);
		body.render(f5);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
		horn1.render(f5);
		horn2.render(f5);
		horn3.render(f5);
		horn4.render(f5);
		udders.render(f5);
		head2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.head.rotateAngleY = f4 / (180F / (float) Math.PI);

		this.head2.rotateAngleX = -(f4 / (180F / (float) Math.PI));
		this.head2.rotateAngleY = -(f4 / (180F / (float) Math.PI));

		this.body.rotateAngleX = ((float) Math.PI / 2F);

		this.leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		this.leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
		this.leg1.rotateAngleY = 0.0F;
		this.leg2.rotateAngleY = 0.0F;

		this.leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		this.leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
		this.leg4.rotateAngleY = 0.0F;
		this.leg3.rotateAngleY = 0.0F;
	}

}
