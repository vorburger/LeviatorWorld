package ch.vorburger.leviator.tests;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import ch.vorburger.leviator.Fertile;
import ch.vorburger.leviator.Place;
import ch.vorburger.leviator.PlantableThing;
import ch.vorburger.leviator.Player;
import ch.vorburger.leviator.World;

public class LeviatorWorldTest {

	World world;
	
	@Before
	public void setUp() {
		world = new World();
		world.setUI(new NoOpUI());
		Place farm = new Place("Farm");
		farm.addAdapted(Fertile.class);
		world.getPlaces().add(farm);
		Place mountain = new Place("Mountain");
		mountain.things.addThing(new PlantableThing("SomePlantableThing"), 1);
		world.getPlaces().add(mountain);
		world.getPlayers().add(new Player("EllieWhoFoundNewHome", mountain, world));
	}
	
	@Test
	public void testPlantOnlyInFertilePlaces() {
		// Player.plant should initially fail (because in Mountain)
		// go Farm
		// now plant() should work...
		fail("Commands need some refactoring work before all this is integration testable");
	}

}
