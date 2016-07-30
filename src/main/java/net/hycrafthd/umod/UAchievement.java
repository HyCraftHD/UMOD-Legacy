package net.hycrafthd.umod;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

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
		UMod.log.debug("Init Achievements");
	}

	private void register() {
		firstjoin.registerStat();
		AchievementPage.registerAchievementPage(uachievementpage);
		UMod.log.debug("Register Achievements");
	}
}
