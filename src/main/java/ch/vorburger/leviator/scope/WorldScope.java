package ch.vorburger.leviator.scope;

import ch.vorburger.leviator.World;
import ch.vorburger.worlds.commands.AbstractWObjectsScope;
import ch.vorburger.worlds.commands.EmptyScope;
import ch.vorburger.worlds.core.WObject;

import com.google.common.collect.Iterables;

/**
 * This one doesn't extend AbstractDelegatingScope because the WorldScope is the
 * root scope - it has nowhere else to delegate to.  (TODO not sure this is even true..
 * what about the contained things and places - do we want to delegate into them??)
 */
public class WorldScope extends AbstractWObjectsScope  {

	private final World world;

	public WorldScope(World world) {
		super(new EmptyScope());
		this.world = world;
	}

	@Override
	protected Iterable<? extends WObject> getWObjects() {
		return Iterables.concat(
				world.getPlaces(), 
				world.getPlayers());
	}

}
