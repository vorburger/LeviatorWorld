package ch.vorburger.worlds.persistence.gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.vorburger.leviator.EdiblePlantableThing;
import ch.vorburger.leviator.Place;
import ch.vorburger.leviator.PlantableThing;
import ch.vorburger.leviator.Player;
import ch.vorburger.leviator.Thing;
import ch.vorburger.leviator.World;
import ch.vorburger.worlds.persistence.WorldRepository;

import com.google.common.base.Stopwatch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GSONFileWorldRepository extends WorldRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(GSONFileWorldRepository.class);
	
	protected final File file;
	protected Gson gson;

	public static GSONFileWorldRepository newWorldIntoFile(File file, World initialWorld) throws IOException {
		GSONFileWorldRepository repo = new GSONFileWorldRepository(file);
		repo.world = initialWorld;
		repo.saveSnapshot();
		return repo;
	}
	
	public static GSONFileWorldRepository onExistingFile(File file) throws IOException {
		GSONFileWorldRepository repo = new GSONFileWorldRepository(file);
		repo.world = repo.initialLoadFromFile();
		return repo;
	}

	protected GSONFileWorldRepository(File file) {
		super();
		this.file = file;
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
		Stopwatch stopwatch = Stopwatch.createStarted();
		Gson gson = getGSON();
		Writer writer = new FileWriter(file);
		gson.toJson(world, writer);
		writer.close();
		long ms = stopwatch.elapsed(TimeUnit.MILLISECONDS);
		LOGGER.debug("saveSnapshot() in {}ms", ms);
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
