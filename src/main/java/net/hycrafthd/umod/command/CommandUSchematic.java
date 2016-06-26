package net.hycrafthd.umod.command;

import java.util.List;

import net.hycrafthd.umod.uschematic.BlockObject;
import net.hycrafthd.umod.utils.USchematicUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CommandUSchematic extends CommandBase {

	@Override
	public String getName() {
		return "uschematic";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "commands.uschematic.usage";
	}

	@Override
	public void execute(ICommandSender sender, String[] args) throws CommandException {
		if (args.length < 2 || args.length > 8) {
			throw new WrongUsageException(getCommandUsage(sender), new Object[0]);
		} else {
			World world = sender.getEntityWorld();
			if (args[0].equalsIgnoreCase("save")) {
				if (args.length == 8) {
					BlockPos pos = func_175757_a(sender, args, 2, false);
					BlockPos pos1 = func_175757_a(sender, args, 5, false);
					try {
						USchematicUtils.saveSchematic(world, args[1], pos.getX(), pos.getY(), pos.getZ(), pos1.getX(), pos1.getY(), pos1.getZ());
						notifyOperators(sender, this, "commands.uschematic.success.save", new Object[] { args[1], pos, pos1 });
					} catch (Exception e) {
						e.printStackTrace();
						throw new CommandException("commands.uschematic.failed.save", new Object[0]);
					}
				} else {
					throw new WrongUsageException(getCommandUsage(sender), new Object[1]);
				}
			} else if (args[0].equalsIgnoreCase("load")) {
				if (args.length == 2) {
					try {
						NBTTagCompound tag = USchematicUtils.getTagCompound(args[1]);
						BlockObject[] blocks = USchematicUtils.readSchematic(tag);
						BlockPos pos = sender.getPosition();
						for (BlockObject obj : blocks) {
							BlockPos position = obj.getPositionWithOffset(pos.getX(), pos.getY(), pos.getZ());
							world.setBlockState(position, obj.getState());
							if (obj.getTileEntityData() != null && world.getTileEntity(position) != null) {
								TileEntity tile = world.getTileEntity(position);
								tile.readFromNBT(obj.getTileEntityData());
								tile.setPos(position);
								world.setTileEntity(position, tile);
							}
						}
						notifyOperators(sender, this, "commands.uschematic.success.load", new Object[] { args[1], pos, pos.add(USchematicUtils.getWidth(tag), USchematicUtils.getHeight(tag), USchematicUtils.getLength(tag)) });
					} catch (Exception e) {
						throw new CommandException("commands.uschematic.failed.load", new Object[0]);
					}
				} else {
					throw new WrongUsageException(getCommandUsage(sender), new Object[1]);
				}
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		try {
			if (args.length == 1) {
				return getListOfStringsMatchingLastWord(args, new String[] { "save", "load" });
			} else {
				if (args[0].equalsIgnoreCase("load")) {
					if (args.length == 2) {
						return getListOfStringsMatchingLastWord(args, USchematicUtils.getFileNameList());
					}
				} else if (args[0].equalsIgnoreCase("save")) {
					if (args.length == 2) {
						return getListOfStringsMatchingLastWord(args, "name");
					}
					if (args.length >= 3 && args.length <= 5) {
						return func_175771_a(args, 2, pos);
					}
					if (args.length >= 5 && args.length <= 8) {
						return func_175771_a(args, 5, pos);
					}
				}
			}
		} catch (Exception ex) {
		}
		return null;
	}

}
