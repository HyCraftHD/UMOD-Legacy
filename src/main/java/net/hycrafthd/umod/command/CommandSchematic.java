package net.hycrafthd.umod.command;

import java.util.List;

import net.hycrafthd.umod.utils.SchematicUtils;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandFill;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.BlockPos;

public class CommandSchematic extends CommandBase {

	@Override
	public String getName() {
		return "schematic";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "commands.schematic.usage";
	}

	@Override
	public void execute(ICommandSender sender, String[] args) throws CommandException {
		if (args.length < 6) {
			throw new WrongUsageException("commands.schematic.usage", new Object[0]);
		} else {
			BlockPos pos = func_175757_a(sender, args, 1, false);
			BlockPos pos1 = func_175757_a(sender, args, 4, false);
			try {
				SchematicUtils.saveSchematic(sender.getEntityWorld(), args[0], pos.getX(), pos.getY(), pos.getZ(), pos1.getX(), pos1.getY(), pos1.getZ());
			} catch (Exception e) {
				e.printStackTrace();
			}
			notifyOperators(sender, this, "commands.schematic.success", new Object[] { args[0] });
		}

	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		return args.length > 1 && args.length <= 4 ? func_175771_a(args, 1, pos) : (args.length > 4 && args.length <= 7 ? func_175771_a(args, 4, pos) : null);
	}

}
