package ch.vorburger.leviator;


public class NewWorldFactory {
	
	public World newWorld() {
		World world = new World();
		world.season = Season.Spring;

		Place plain = new Plain();
		plain.addAdapted(Fertile.class);
		plain.things.addThing(new EdiblePlantableThing("Apple"), 50);
		plain.things.addThing(new Thing("dirt"),100);
		plain.things.addThing(new PlantableThing("rose"), 20);
		plain.things.addThing(new EdiblePlantableThing("medicalHerb"),10 );
		plain.things.addThing(new Thing("ash"), 10);
		world.places.add(plain);

		Forest forest = new Forest();
		forest.things.addThing(new PlantableThing("birchWood"),20 );
		forest.things.addThing(new PlantableThing("oakWood"),30 );
		forest.things.addThing(new EdiblePlantableThing("mango"),50);
		forest.things.addThing(new Thing("stick"),25 );
		world.places.add(forest);
		
		Cave cave = new Cave();
		cave.things.addThing(new Thing("Stone"), 10000);
		cave.things.addThing(new Thing("coal"), 1000);
		cave.things.addThing(new Thing("iron"), 150);
		cave.things.addThing(new Thing("gold"), 50);
		cave.things.addThing(new Thing("diamond"), 10);
		cave.things.addThing(new Thing("emerald"), 20);
		cave.things.addThing(new Thing("saphire"), 20);
		cave.things.addThing(new Thing("ruby"), 20);
		cave.things.addThing(new Thing("amethyst"), 15);
		world.places.add(cave);
		
		world.players.add(new Player("Dév", plain, world));
		world.players.add(new Player("Michael", forest, world));
		
		// world.players = Collections.synchronizedList(world.players);
		
		return world;
	}
}
