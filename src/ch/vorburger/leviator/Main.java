package ch.vorburger.leviator;

import java.util.Map.Entry;

public class Main {

	CommandPrompt cmd = new CommandPrompt();
	
	World newWorld() {
		World world = new World(this);
		world.season = Season.Spring;

		Place plain = new Plain();
		plain.things.addThings(new EdiblePlantableThing("Apple"), 50);
		plain.things.addThings(new Thing("dirt"),100);
		plain.things.addThings(new PlantableThing("rose"), 20);
		plain.things.addThings(new EdiblePlantableThing("medicalHerb"),10 );
		plain.things.addThings(new Thing("ash"), 10);
		world.places.add(plain);

		Forest forest = new Forest();
		forest.things.addThings(new PlantableThing("birchWood"),20 );
		forest.things.addThings(new PlantableThing("oakWood"),30 );
		forest.things.addThings(new EdiblePlantableThing("mango"),50);
		forest.things.addThings(new Thing("stick"),25 );
		world.places.add(forest);
		
		Cave cave = new Cave();
		cave.things.addThings(new Thing("Stone"), 10000);
		cave.things.addThings(new Thing("coal"), 1000);
		cave.things.addThings(new Thing("iron"), 150);
		cave.things.addThings(new Thing("gold"), 50);
		cave.things.addThings(new Thing("diamond"), 10);
		cave.things.addThings(new Thing("emerald"), 20);
		cave.things.addThings(new Thing("saphire"), 20);
		cave.things.addThings(new Thing("ruby"), 20);
		cave.things.addThings(new Thing("amethyst"), 15);
		world.places.add(cave);
		
		world.players.add(new Player("Dév", world));
		world.players.add(new Player("Michael", world));
		
		// world.players = Collections.synchronizedList(world.players);
		
		return world;
	}

	void run() {
		World world = newWorld();
		println("It's " + world.season);
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
			}
			++tour;
		}
	}
	
	void printThings(Things things) {
		boolean firstThing = true;
		for (Entry<AbstractThing, Integer> entry : things.bag.entrySet()) {
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

	void println(String string) {
		System.out.println(string);
	}

	void print(String string) {
		System.out.print(string);
	}

	public static void main(String[] args) {
		new Main().run();
	}

}