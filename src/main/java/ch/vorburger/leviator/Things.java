package ch.vorburger.leviator;

import java.util.HashMap;
import java.util.Map;

public class Things {

	// TODO think again about this.. this actually seems wrong, mixing Types and instances?
	
	// TODO http://joschi.github.io/guava-slides/#37
	public Map<Thing, Integer> bag = new HashMap<Thing, Integer>();	

	public void addThing(Thing thing, int n) {
		Integer before = bag.containsKey(thing) ? bag.get(thing) : 0;
		bag.put(thing, before + n);
	}

	public void removeThing(Thing thing, int n) {
		if (!bag.containsKey(thing))
			throw new IllegalArgumentException("No " + thing.name());
		Integer newN = bag.get(thing) - n;
		if (newN > 0)
			bag.put(thing, newN);
		else
			bag.remove(thing);
	}
	
	public void transferThing(Thing it, int n, Things to) {
		removeThing(it, n);
		to.addThing(it, n);
	}

	public Thing getThing(String name) throws IllegalArgumentException {
		return NamedUtil.get(bag.keySet(), name);
	}

}
