package ch.vorburger.leviator;

import ch.vorburger.worlds.core.WClass;
import ch.vorburger.worlds.core.WObject;
import ch.vorburger.worlds.core.java.JWClass;
import ch.vorburger.worlds.naming.Named;
import ch.vorburger.worlds.naming.QualifiedName;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;


public class Player implements WObject, Named {

	final private String name;
	
	// TODO use Container/Contained?
	final public World world;

	// TODO use Container/Contained?
	final Things things = new Things();

	public Place inPlace;
	
	int energyBar = 20;
	
	// TODO some kind of @Command annotation..
	public void go(Place newPlace) {
		if (newPlace == inPlace)
			info("You're already in " + newPlace.name());
		else {
			inPlace = newPlace;
			info("OK, you're now in " + newPlace.name());
		}
	}
	public Iterable<Place> go_newPlace_scope() {
		Predicate<Place> predicate = new Predicate<Place>() {
			@Override public boolean apply(Place place) {
				return place != inPlace;
			}
		};
		return Iterables.filter(world.places, predicate);
	}
	public Iterable<QualifiedName> go_newPlace_scope_names() {
		// TODO how to generics? return Names.transform(go_newPlace_scope());
		Function<? super Place, ? extends QualifiedName> function = new Function<Place, QualifiedName>() {
			@Override public QualifiedName apply(Place input) {
				// TODO when Named returns QualifiedName instead String: return input.name();
				return QualifiedName.create(/* parent?!, */ input.name());
			}
		};
		return Iterables.transform(go_newPlace_scope(), function);
	}
	
	void info(String message) {
		world.playerInfo(this, message);
	}

	public Player(String playerName, Place spawningPlace, World myWorld) {
		name = playerName;
		world = myWorld;
		inPlace = spawningPlace;
	}

	@Override
	public String name() {
		return name;
	}
	
	@Override
	public WClass getWClass() {
		return JWClass.fromJavaClass(getClass());
	}
	
}
