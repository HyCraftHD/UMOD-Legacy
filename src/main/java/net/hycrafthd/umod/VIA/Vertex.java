package net.hycrafthd.umod.VIA;

import net.minecraft.util.Vec3;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Vertex {

	private final Vec3 vec1,vec2,vec3,vec4;
	
	@SideOnly(Side.CLIENT)
	public Vertex(Vec3 vec1,Vec3 vec2,Vec3 vec3,Vec3 vec4) {
		this.vec1 = vec1;
		this.vec2 = vec2;
		this.vec3 = vec3;
		this.vec4 = vec4;
	}

	public Vec3 getVec1() {
		return vec1;
	}
	
	public Vec3 getVec2() {
		return vec2;
	}

	public Vec3 getVec3() {
		return vec3;
	}

	public Vec3 getVec4() {
		return vec4;
	}

	
}
