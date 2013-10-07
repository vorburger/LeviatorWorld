package ch.vorburger.leviator.tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import ch.vorburger.leviator.EdiblePlantableThing;
import ch.vorburger.leviator.Fertile;
import ch.vorburger.leviator.NewWorldFactory;
import ch.vorburger.leviator.Place;
import ch.vorburger.leviator.Player;
import ch.vorburger.leviator.Season;
import ch.vorburger.leviator.Thing;
import ch.vorburger.leviator.World;
import ch.vorburger.worlds.persistence.firebase.FirebaseWorldRepository;
import ch.vorburger.worlds.persistence.gson.GSONFileWorldRepository;

public class WorldRepositoryTest {

	@Test
	public void testEmptyWorld() throws Exception {
		World world = newEmptyWorld();
		world = checkWorld(world);
		assertNotNull(world);
	}

	@Test // TODO Enum in JSON strangely fail - even though GSON should have OOB Enum support.. weird. 
	public void testSeasonEnumWorld() throws Exception {
		World world = newEmptyWorld();
		world.season = Season.Winter;
		world = checkWorld(newEmptyWorld());
		assertEquals(Season.Winter, world.season);
	}
	
	@Test
	public void testAPlace() throws Exception {
		World world = newEmptyWorld();
		Place newPlace = new Place("NewPlace");
		world.getPlaces().add(newPlace);
		world.getPlayers().add(new Player("NewPlayer", newPlace, world));
		world = checkWorld(world);
		assertEquals("NewPlace", world.getPlaces().get(0).name());
	}

	@Test
	public void testAPlayer() throws Exception {
		World world = newEmptyWorld();
		Place newPlace = new Place("NewPlace");
		world.getPlaces().add(newPlace);
		world.getPlayers().add(new Player("NewPlayer", newPlace, world));
		world = checkWorld(world);
		assertEquals("NewPlayer", world.getPlayers().get(0).name());
		assertEquals("NewPlace", world.getPlayers().get(0).inPlace.name());
	}

	@Test
	public void testPlaceThings() throws Exception {
		World world = newEmptyWorld();
		Place newPlace = new Place("NewPlace");
		newPlace.addAdapted(Fertile.class);
		world.getPlaces().add(newPlace);
		newPlace.things.addThing(new EdiblePlantableThing("SomeThing"), 7);
		world = checkWorld(world);
		Thing thing = world.getPlaces().get(0).things.getThing("SomeThing");
		assertEquals(Integer.valueOf(7), world.getPlaces().get(0).things.bag.get(thing));
		// THis last one is important - GSON had a problem with this..
		assertTrue(thing instanceof EdiblePlantableThing);
	}
	
	@Test
	public void testNewWorldFactory() throws Exception {
		World world = new NewWorldFactory().newWorld();
		world.setUI(new NoOpUI());
		world = checkWorld(world);
	}

	protected World newEmptyWorld() {
		World world = new World();
		world.setUI(new NoOpUI());
		return world;
	}
	
	protected World checkWorld(World newWorld) throws Exception {
		// return checkWorldInGSONFile(newWorld);
		return checkWorldOnFirebase(newWorld);
	}

	protected World checkWorldOnFirebase(World newWorld) throws Exception {
		//FirebaseWorldRepository repo = FirebaseWorldRepository.connectWorld("WorldRepositoryTest");
		//repo.delete();
		FirebaseWorldRepository repo = FirebaseWorldRepository.newWorld("WorldRepositoryTest", newWorld);
		repo = FirebaseWorldRepository.connectWorld("WorldRepositoryTest");
		return repo.getWorld();
	}
	
	protected World checkWorldInGSONFile(World newWorld) throws Exception {
		File file = new File("target/WorldRepositoryTest.json");
		file.delete();
		
		GSONFileWorldRepository repo = GSONFileWorldRepository.newWorldIntoFile(file, newWorld);
		assertTrue(file.exists());
		assertEquals(newWorld, repo.getWorld());
		repo.saveSnapshot();
		
		repo = GSONFileWorldRepository.onExistingFile(file);
		World reReadWorld = repo.getWorld();
		return reReadWorld;
	}
}
