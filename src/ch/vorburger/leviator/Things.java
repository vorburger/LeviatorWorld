package ch.vorburger.leviator;

import java.util.HashMap;
import java.util.Map;

public class Things {

	Map<AbstractThing, Integer> bag = new HashMap<AbstractThing, Integer>();	

	void addThings(AbstractThing thing, int n) {
		Integer before = bag.containsKey(thing) ? bag.get(thing) : 0;
		bag.put(thing, before + n);
	}

	private void removeThings(AbstractThing thing, int n) {
		if (!bag.containsKey(thing))
			throw new IllegalArgumentException("No " + thing.name());
		bag.put(thing, bag.get(thing) - n);
	}
	
	void transferThing(AbstractThing it, int n, Things to) {
		removeThings(it, n);
		to.addThings(it, n);
	}

	// TODO better share this code with World instead of copy/paste 
	public AbstractThing getThing(String name) throws IllegalArgumentException {
		for (AbstractThing thing : bag.keySet()) {
			if (thing.name().equalsIgnoreCase(name))
				return thing;
		}
		String availableThings = "";
		for (AbstractThing thing : bag.keySet()) {
			availableThings += thing.name() + ", ";
		}
		throw new IllegalArgumentException("No such thing: " + name + ", only: " + availableThings);
	}

}
