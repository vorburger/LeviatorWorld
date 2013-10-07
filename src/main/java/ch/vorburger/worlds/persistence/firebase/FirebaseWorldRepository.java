package ch.vorburger.worlds.persistence.firebase;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.vorburger.leviator.World;
import ch.vorburger.worlds.persistence.WorldRepository;

import com.firebase.client.Config;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.Logger.Level;
import com.firebase.client.ValueEventListener;
import com.google.common.base.Stopwatch;

/**
 * Structure:
 * 		/server
 * 		/worlds
 * 			/{name}
 *
 */
public class FirebaseWorldRepository extends WorldRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(FirebaseWorldRepository.class);
	private static final String ROOT_URL = "https://worlds.firebaseio.com/";
	
	private final Firebase f;
	
	// TODO LATER remove this - new Worlds will just be created from a template stored on server.. 
	public static FirebaseWorldRepository newWorld(String name, World newWorld) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		FirebaseWorldRepository r = new FirebaseWorldRepository();
		Firebase fbWorld = r.f.child("worlds/" + name);
		// TODO use completion listener to wait?
		fbWorld.setValue("Hi!");
		//fbWorld.setValue(newWorld);
		r.world = newWorld;
		LOGGER.info("newWorld() in {}ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
		return r;
	}

	public static FirebaseWorldRepository connectWorld(String name) {
		final FirebaseWorldRepository r = new FirebaseWorldRepository();
		Firebase fbWorld = r.f.child("worlds/" + name);
		fbWorld.addListenerForSingleValueEvent(new ValueEventListener() {

			@Override
			public void onCancelled() {
				LOGGER.error("Oups");
			}

			@Override
			public void onDataChange(DataSnapshot ds) {
				r.world = ds.getValue(World.class); 
			}
		});
		return r;
	}

	protected FirebaseWorldRepository() {
		 Config config = Firebase.getDefaultConfig();
		 config.setLogger(new FirebaseLogger(LOGGER));
		 config.setLogLevel(Level.DEBUG);
		 Firebase.setDefaultConfig(config);
		 f = new Firebase(ROOT_URL);
	}
	
	@Override
	public void saveSnapshot() throws IOException {
		// TODO This is likely very inefficient? It should do nothing (not needed with Firebase) - if we did fine grained change notification
	}

}
