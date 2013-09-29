package ch.vorburger.leviator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class CommandPrompt {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	void waitForAndProcessCommand(Player player, List<Command> commands) {
		boolean done = false;
		while (!done) {
			player.info("What would you like to do? ");
			String text = nextLine();
			boolean ranCommand = false;
			for (Command command : commands) {
				if (text.toLowerCase().startsWith(command.keyword)) {
					try {
						done = command.isDoneCommand(new CommandContext(player, text));
					} catch (Exception e) {
						player.info("Command failed, because: " + e.getMessage());
					}
					ranCommand = true;
				}
			}
			if (!ranCommand)
				player.info("biep bap boop - Computer :-( didn't understand your command: " + text);			
		}
	}
	
	String nextLine() {
		try {
			return br.readLine().trim();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	abstract static class Command {
		final String keyword;
		final String argsHelp;
		
		Command(String keyword, String argsHelp) {
			this.keyword = keyword;
			this.argsHelp = argsHelp;
		}

		boolean isDoneCommand(CommandContext ctx) throws Exception {
			doCommand(ctx);
			return true;
		}
		
		void doCommand(CommandContext ctx) throws Exception {
		}
	}
	
	static class CommandContext {
		StringTokenizer tokenizer;
		Player currentPlayer;
		
		CommandContext(Player player, String arguments) {
			this.currentPlayer = player;
			tokenizer = new StringTokenizer(arguments);
			tokenizer.nextToken(); // skip command
		}
		
		String getString() {
			return tokenizer.nextToken();
		}
		
		int getNumber() {
			return Integer.parseInt(getString());
		}

		public int getNumber(int defaultNumber) {
			if (tokenizer.hasMoreElements())
				return getNumber();
			else
				return defaultNumber;
		}

		Place getPlace() {
			return currentPlayer.world.getPlace(getString());
		}

		public AbstractThing getThing(Things things) {
			return things.getThing(getString());
		}

		public Player getPlayer() {
			return currentPlayer.world.getPlayer(getString());
		}

	}
	
}
