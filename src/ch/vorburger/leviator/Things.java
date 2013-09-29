package ch.vorburger.leviator;

import java.util.HashMap;
import java.util.Map;

public class Things {

	Map<AbstractThing, Integer> bag = new HashMap<AbstractThing, Integer>();	

	void addThing(AbstractThing thing, int n) {
		Integer before = bag.containsKey(thing) ? bag.get(thing) : 0;
		bag.put(thing, before + n);
	}

	void removeThing(AbstractThing thing, int n) {
		if (!bag.containsKey(thing))
			throw new IllegalArgumentException("No " + thing.name());
		Integer newN = bag.get(thing) - n;
		if (newN > 0)
			bag.put(thing, newN);
		else
			bag.remove(thing);
	}
	
	void transferThing(AbstractThing it, int n, Things to) {
		removeThing(it, n);
		to.addThing(it, n);
	}

	public AbstractThing getThing(String name) throws IllegalArgumentException {
		return NamedUtil.get(bag.keySet(), name);
	}

}
