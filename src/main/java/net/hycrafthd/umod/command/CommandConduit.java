package net.hycrafthd.umod.command;

import java.util.List;

import net.hycrafthd.umod.utils.NBTUtils;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

public class CommandConduit extends CommandBase{

	@Override
	public String getName() {
		return "conduit";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "commands.conduit.usage";
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}

	@Override
	public void execute(ICommandSender sender, String[] args) throws CommandException {
		 EntityPlayerMP pl = getPlayer(sender, args[0]);
		 int meta = 0;
		 if(args.length > 2){
			 try{
			 meta = Integer.valueOf(args[2]);
			 }catch(Exception ex){
				 throw new WrongUsageException(args[2] +  " is not a valide Number", 1);
			 }
		 }
		 if(pl != null){
			 if(pl.getCurrentEquippedItem() != null){
             Block item = getBlockByText(sender, args[1]);
             if(item != null){
	    	 NBTUtils.addStackToConduit(pl.getCurrentEquippedItem(), new ItemStack(item , 1,meta));
             }else{
            	 sender.addChatMessage(new ChatComponentText(args[1] + " is not a valid Item"));
             }
			 }else{
		    	 sender.addChatMessage(new ChatComponentText("Please Heald a Item in Hand wile performing this"));
			 }
	     }else{
	    	 sender.addChatMessage(new ChatComponentText("Sorry this command is Only able for Players"));
	     }
	}
	
	 public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos)
	    {
	        return args.length == 1 ? getListOfStringsMatchingLastWord(args, this.getPlayers()) : (args.length == 2 ? func_175762_a(args, Block.blockRegistry.getKeys()) : null);
	    }

	 protected String[] getPlayers()
	 {
	    return MinecraftServer.getServer().getAllUsernames();
	 }

}
