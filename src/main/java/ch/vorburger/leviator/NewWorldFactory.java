package ch.vorburger.leviator;


public class NewWorldFactory {
	
	public World newWorld() {
		World world = new World();
		world.season = Season.Spring;

		Place plain = new Plain();
		plain.addAdapted(Fertile.class);
		plain.things.addThing(new EdiblePlantableThing(plain, "Apple"), 50);
		plain.things.addThing(new Thing(plain, "dirt"),100);
		plain.things.addThing(new PlantableThing(plain, "rose"), 20);
		plain.things.addThing(new EdiblePlantableThing(plain, "medicalHerb"),10 );
		plain.things.addThing(new Thing(plain, "ash"), 10);
		world.places.add(plain);

		Forest forest = new Forest();
		forest.things.addThing(new PlantableThing(forest, "birchWood"),20 );
		forest.things.addThing(new PlantableThing(forest, "oakWood"),30 );
		forest.things.addThing(new EdiblePlantableThing(forest, "mango"),50);
		forest.things.addThing(new Thing(forest, "stick"),25 );
		world.places.add(forest);
		
		Cave cave = new Cave();
		cave.things.addThing(new Thing(cave, "Stone"), 10000);
		cave.things.addThing(new Thing(cave, "coal"), 1000);
		cave.things.addThing(new Thing(cave, "iron"), 150);
		cave.things.addThing(new Thing(cave, "gold"), 50);
		cave.things.addThing(new Thing(cave, "diamond"), 10);
		cave.things.addThing(new Thing(cave, "emerald"), 20);
		cave.things.addThing(new Thing(cave, "saphire"), 20);
		cave.things.addThing(new Thing(cave, "ruby"), 20);
		cave.things.addThing(new Thing(cave, "amethyst"), 15);
		world.places.add(cave);
		
		world.players.add(new Player("Dév", plain, world));
		world.players.add(new Player("Michael", forest, world));
		
		// world.players = Collections.synchronizedList(world.players);
		
		return world;
	}
}
