package ch.vorburger.leviator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ch.vorburger.leviator.CommandPrompt.Command;
import ch.vorburger.leviator.CommandPrompt.CommandContext;

public class Commands {

	static List<Command> available = new ArrayList<CommandPrompt.Command>(Arrays.asList(
			new Command("help", "") {
				@Override boolean isDoneCommand(CommandContext ctx) {
					ctx.player.info("Available commands are:");
					for (Command command : available) {
						ctx.player.info("   " + command.keyword + " " + command.argsHelp);
					}
					return false;
				}
			},
			new Command("quit", "") {
				@Override void doCommand(CommandContext ctx) {
					ctx.player.world.isRunning = false;
				}
			},
			new Command("go", "<Place>") {
				@Override void doCommand(CommandContext ctx) {
					ctx.player.go(ctx.getPlace());
				}
			},
			new Command("take", "<Thing>") { // [howMany/1]
				@Override void doCommand(CommandContext ctx) {
					ctx.player.inPlace.things.transferThing(ctx.getThing(ctx.player.inPlace.things), ctx.getNumber(1), ctx.player.things);
				}
			},
			new Command("give", "<Player> <Thing> [howMany/1]") {
				@Override void doCommand(CommandContext ctx) {
					Things otherPlayerThings = ctx.getPlayer().things;
					AbstractThing thing = ctx.getThing(ctx.player.things);
					ctx.player.things.transferThing(thing, ctx.getNumber(1), otherPlayerThings);
				}
			}
		));

}
