package ch.vorburger.leviator;

import ch.vorburger.worlds.naming.Named;

public class EdiblePlantableThing extends Thing implements Edible, Plantable {
	// see comment in PlantableThing... let's use "implements" here, as both works!
	
	public EdiblePlantableThing(Named parent, String name) {
		super(parent, name);
	}

}
