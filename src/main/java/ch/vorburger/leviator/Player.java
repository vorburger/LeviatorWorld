package ch.vorburger.leviator;

import ch.vorburger.worlds.core.java.JWObject;
import ch.vorburger.worlds.naming.Named;
import ch.vorburger.worlds.naming.QualifiedName;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;


public class Player extends JWObject implements Named {

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
				return input.name();
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
	public QualifiedName name() {
		return QualifiedName.create(/* world, */ name);
	}
	
}
