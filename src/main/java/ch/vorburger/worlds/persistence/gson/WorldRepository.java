package ch.vorburger.worlds.persistence.gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import ch.vorburger.leviator.EdiblePlantableThing;
import ch.vorburger.leviator.Place;
import ch.vorburger.leviator.PlantableThing;
import ch.vorburger.leviator.Player;
import ch.vorburger.leviator.Thing;
import ch.vorburger.leviator.World;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WorldRepository {

	protected final File file;
	protected World world;
	protected Gson gson;

	public static WorldRepository newWorldIntoFile(File file, World initialWorld) throws IOException {
		WorldRepository repo = new WorldRepository(file);
		repo.world = initialWorld;
		repo.saveSnapshot();
		return repo;
	}
	
	public static WorldRepository onExistingFile(File file) throws IOException {
		WorldRepository repo = new WorldRepository(file);
		repo.world = repo.initialLoadFromFile();
		return repo;
	}

	protected WorldRepository(File file) {
		super();
		this.file = file;
	}
	
	public World getWorld() {
		return world;
	}

	protected World initialLoadFromFile() throws IOException {
		Gson gson = getGSON();
		Reader fileReader = new FileReader(file);
		World world = gson.fromJson(fileReader, World.class);
		fileReader.close();
		return world;
	}
	
	/**
	 * Caller must ensure that the {@link #getWorld()} is not concurrently modified.
	 */
	public void saveSnapshot() throws IOException {
		Gson gson = getGSON();
		Writer writer = new FileWriter(file);
		gson.toJson(world, writer);
		writer.close();
	}
	
	protected Gson getGSON() {
		if (gson == null) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting(); // TODO remove this, later..
			gsonBuilder.enableComplexMapKeySerialization();
			gsonBuilder.registerTypeAdapter(Class.class, new ClassTypeAdapter());
			// NOT gsonBuilder.registerTypeHierarchyAdapter(List.class, new CollectionTypeAdapter());

			// TODO We'd have to register all Classses with possible cycles :( This isn't dynamic enough for my purposes.
			GraphAdapterBuilder graphAdapterBuilder = new GraphAdapterBuilder();
			graphAdapterBuilder.addType(World.class);
			graphAdapterBuilder.addType(Place.class);
			graphAdapterBuilder.addType(Player.class);
			graphAdapterBuilder.registerOn(gsonBuilder);

	//		graphAdapterBuilder.addType(Place.class, new InstanceCreator<Place>() {
	//			@Override
	//			public Place createInstance(Type type) {
	//				return new Place("gsonPlace");
	//			}
	//		});
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

			// TODO We'd have to register all Classses with subtypes :( This isn't dynamic enough for my purposes.
			RuntimeTypeAdapterFactory<Thing> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory.of(Thing.class)
					.registerSubtype(Thing.class)
					.registerSubtype(PlantableThing.class)
					.registerSubtype(EdiblePlantableThing.class);
			gsonBuilder.registerTypeAdapterFactory(runtimeTypeAdapterFactory);
			
			gson = gsonBuilder.create();
		}
		return gson;
	}

/*	private void saveWorldWithJackson(World world) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		om.configure(SerializationFeature.INDENT_OUTPUT, true);
		om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		System.out.println(om.writeValueAsString(world));
	} */
	
	
//	public static void main(String[] args) throws Exception {
//		File file = new File(args[0]);
//		WorldRepository repo = onExistingFile(file);
	
//		gsonBuilder.setPrettyPrinting();
//		boolean prettyPrinting;
//		repo.saveSnapshotWithPrettyPrinting();
//	}

}
