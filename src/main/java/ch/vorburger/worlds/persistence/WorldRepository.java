package ch.vorburger.worlds.persistence;

import java.io.IOException;

import ch.vorburger.leviator.World;

public abstract class WorldRepository {

	protected World world;

	public World getWorld() {
		return world;
	}

	public abstract void saveSnapshot() throws IOException;
	
}
