package ch.vorburger.leviator;

import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;

import ch.vorburger.worlds.UI;
import ch.vorburger.worlds.persistence.WorldRepository;
import ch.vorburger.worlds.persistence.gson.GSONFileWorldRepository;

public class Main implements UI {

	CommandPrompt cmd = new CommandPrompt();

	void run() throws IOException {
		WorldRepository repo;
		File file = new File("./leviator.world.json");
		if (!file.exists())
			repo = GSONFileWorldRepository.newWorldIntoFile(file, new NewWorldFactory().newWorld());
		else
			repo = GSONFileWorldRepository.onExistingFile(file);
		World world = repo.getWorld();
		world.setUI(this);
		
		println("LeviatorWorld  Copyright (C) 2013  Michael & Dév Vorburger");
		println("This program comes with ABSOLUTELY NO WARRANTY.\n");
		println("Hi! It's " + world.season);
		long tour = 0;
		while (world.isRunning) {
			for (Player p : world.players) {
				println("Hello " + p.name() + ", your energy bar is " + p.energyBar + ".");
				print("You're in " + p.inPlace.name() + ", where there are: ");
				printThings(p.inPlace.things);
				if (!p.things.bag.isEmpty()) {
					print("You have: ");
					printThings(p.things);
				}
				cmd.waitForAndProcessCommand(p, Commands.available);
				if (tour % 3 == 0) {
					p.energyBar = p.energyBar-1;
					if( p.energyBar == 0) {
						p.things.bag.clear();
					}
				}
				repo.saveSnapshot();
				if (!world.isRunning)
					break;
			}
			++tour;
		}
	}
	
	void printThings(Things things) {
		boolean firstThing = true;
		for (Entry<Thing, Integer> entry : things.bag.entrySet()) {
			if (!firstThing)
				print(", ");
			print(entry.getValue().toString());
			print("x ");
			print(entry.getKey().name());
			print("s");
			firstThing = false;
		}
		println(".");
	}

	public void println(String string) {
		System.out.println(string);
	}

	public void print(String string) {
		System.out.print(string);
	}

	public static void main(String[] args) throws Exception {
		new Main().run();
	}

}