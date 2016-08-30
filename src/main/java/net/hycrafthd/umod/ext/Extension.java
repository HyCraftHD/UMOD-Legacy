package net.hycrafthd.umod.ext;

import net.hycrafthd.umod.UMod;

public class Extension {

	private String name,id;
	private Class<? extends IUmodExtension> cls;
	private IUmodExtension ext;
	private boolean isLoaded = false;
	
	public Extension(String name,String modid,Class<? extends IUmodExtension> clz) {
		this.cls = clz;
		this.name = name;
		this.id = modid;
	}
	
	public void init(){
		try {
			this.ext = cls.newInstance();
			isLoaded = true;
		    UMod.log.info("Started extension " + this.name);
		} catch (Exception e) {
			UMod.log.error("EXTENSION: " + name + " could not be loaded");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public IUmodExtension getExtension() {
		return ext;
	}

	public boolean isLoaded() {
		return isLoaded;
	}

	public void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}
	
}
