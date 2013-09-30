package ch.vorburger.worlds.persistence.gson;

import java.io.File;

import ch.vorburger.leviator.World;

public class WorldRepository {

	private final World world;
	private final File file;

	public WorldRepository(World world, File file) {
		super();
		this.file = file;
		this.world = world;
	}
//	
//	public World getWorld() {
//		
//	}
//	
//	public void save() {
//		
//	}
}
