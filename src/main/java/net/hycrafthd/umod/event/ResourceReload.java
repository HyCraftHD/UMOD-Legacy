package net.hycrafthd.umod.event;

import net.hycrafthd.umod.VIARegister;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;

public class ResourceReload implements IResourceManagerReloadListener{

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {
		VIARegister.registerVIA();
	}

}
