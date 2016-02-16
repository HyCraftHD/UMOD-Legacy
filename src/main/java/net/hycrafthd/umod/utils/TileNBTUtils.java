package net.hycrafthd.umod.utils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class TileNBTUtils {

	public static void saveNBT(NBTTagCompound c,String name){
		WorldServer worldserver = MinecraftServer.getServer().worldServers[0];
		try {
			TLog.warn(worldserver.getSaveHandler().getWorldDirectory().getPath() + File.separatorChar + name + ".nbt");
			CompressedStreamTools.write(c, new DataOutputStream(new FileOutputStream(new File(worldserver.getSaveHandler().getWorldDirectory().getPath() + "/" + name + ".nbt"))));
			worldserver.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
