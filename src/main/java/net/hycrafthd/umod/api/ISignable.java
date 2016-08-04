package net.hycrafthd.umod.api;

import net.minecraft.entity.player.EntityPlayer;

public interface ISignable {
	
	public void signPlayer(EntityPlayer pl);
	
	public String getSignedPlayerName();
	
}
