package ch.vorburger.leviator;

import ch.vorburger.worlds.naming.Named;

public class PlantableThing extends Thing {
	
	// while it would actually be possible (it would work) to use implements Plantable above
	// instead of super(... Plantable.class) below, this is better as it make sure that
	// code doesn't (cannot) use instanceof, but Adaptable.isAdaptableTo(Class<?>). 
	
	public PlantableThing(Named parent, String name) {
		super(parent, name, Plantable.class);
	}

}
