package ch.vorburger.leviator.tests;

import java.io.Serializable;
import java.util.Collection;

import org.junit.Test;

import ch.vorburger.leviator.Main;
import ch.vorburger.leviator.Place;
import ch.vorburger.leviator.Player;
import ch.vorburger.leviator.World;
import ch.vorburger.worlds.persistence.gson.ClassTypeAdapter;
import ch.vorburger.worlds.persistence.gson.CollectionTypeAdapter;
import ch.vorburger.worlds.persistence.gson.GraphAdapterBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WorldPersistenceTest {

	@Test
	public void testSaveWorld() throws Exception {
		saveAndReloadWorldWithGSON(new Main().newWorld());
		//saveWorldWithJackson(new Main().newWorld());
	}
/*	
	private void saveWorldWithJackson(World world) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		om.configure(SerializationFeature.INDENT_OUTPUT, true);
		om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		System.out.println(om.writeValueAsString(world));
	}
*/
	// This is now in ch.vorburger.worlds.persistence.gson.WorldRepository
	private void saveAndReloadWorldWithGSON(final World world) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		gsonBuilder.enableComplexMapKeySerialization();
		gsonBuilder.registerTypeAdapter(Class.class, new ClassTypeAdapter());
		gsonBuilder.registerTypeHierarchyAdapter(Collection.class, new CollectionTypeAdapter());
		GraphAdapterBuilder graphAdapterBuilder = new GraphAdapterBuilder();
		graphAdapterBuilder.addType(World.class);
		graphAdapterBuilder.addType(Place.class);
		graphAdapterBuilder.addType(Player.class);
//		graphAdapterBuilder.addType(Place.class, new InstanceCreator<Place>() {
//			@Override
//			public Place createInstance(Type type) {
//				return new Place("gsonPlace");
//			}
//		});
		// TODO: TypeAdapter which writes out all Named with just their name..
//		graphAdapterBuilder.addType(World.class, new InstanceCreator<World>() {
//			@Override
//			public World createInstance(Type type) {
//				return new World(Main.this);
//			}
//		});
//		graphAdapterBuilder.addType(Player.class, new InstanceCreator<Player>() {
//			@Override
//			public Player createInstance(Type type) {
//				return new Player("gsonPlayer", world);
//			}
//		});
		
		graphAdapterBuilder.registerOn(gsonBuilder);
		Gson gson = gsonBuilder.create();
		String json = gson.toJson(world);
		System.out.println(json);
		World worldAgain = gson.fromJson(json, World.class);
		System.out.println(gson.toJson(worldAgain));
		worldAgain.getPlace("cave").addAdapted(Serializable.class);
	}


}
