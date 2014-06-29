package ch.vorburger.leviator.tests;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.Nullable;
import org.junit.Test;

import ch.vorburger.worlds.persistence.gson.GraphAdapterBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SimpleGraphPersistenceTest {

	static class World {
		List<Place> places = new ArrayList<>();
		List<User> users = new ArrayList<>();
	}

	static class Place {
		@Nullable String name;
	}
	
	static class User {
		@Nullable String name;
		@Nullable Place inPlace;
	}
	
	@Test
	public void testSaveWorld() throws Exception {
		World world = new World();
		Place place = new Place();
		place.name = "Mountain";
		world.places.add(place);
		place = new Place();
		place.name = "City";
		world.places.add(place);
		User user = new User();
		user.name = "Jack";
		user.inPlace = place;
		world.users.add(user);
		
		saveWorldWithGSON(world);
		//saveWorldWithJackson(world);
	}
/*	
	private void saveWorldWithJackson(Object world) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		om.configure(SerializationFeature.INDENT_OUTPUT, true);
		om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		System.out.println(om.writeValueAsString(world));
	}
*/
	private void saveWorldWithGSON(Object world) {
		GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
		GraphAdapterBuilder graphAdapterBuilder = new GraphAdapterBuilder();
		graphAdapterBuilder.addType(World.class);
		graphAdapterBuilder.addType(Place.class);
		graphAdapterBuilder.addType(User.class);
		graphAdapterBuilder.registerOn(gsonBuilder);
		Gson gson = gsonBuilder.create();
		System.out.println(gson.toJson(world));
	}


}
