package net.hycrafthd.umod;

import net.hycrafthd.umod.biome.BiomeInfected;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatList;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class UAchievement {

	public static AchievementPage uachievementpage;
	public static Achievement firstjoin;

	public UAchievement() {
		init();
		register();
	}

	private void init() {
		firstjoin = new Achievement("uachievement.firstjoin", "firstjoin", 0, 0, new ItemStack(Blocks.log), (Achievement) null).setIndependent();
		uachievementpage = new AchievementPage("achievementpage.upage", new Achievement[] { firstjoin });
		Logger.debug(UAchievement.class, "init()", "Init Achievements");
	}

	private void register() {
		firstjoin.registerStat();
		AchievementPage.registerAchievementPage(uachievementpage);
		Logger.debug(UAchievement.class, "register()", "Register Achievements");
	}
}
