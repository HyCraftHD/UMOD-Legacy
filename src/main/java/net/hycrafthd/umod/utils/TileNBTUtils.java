package net.hycrafthd.umod.utils;

import java.io.DataOutput;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;

public class TileNBTUtils {

	public static void saveNBT(NBTTagCompound c){
		WorldServer worldserver = MinecraftServer.getServer().worldServers[0];
		try {
			CompressedStreamTools.write(c, (DataOutput) new FileOutputStream(worldserver.getSaveHandler().getWorldDirectory()));
			worldserver.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
