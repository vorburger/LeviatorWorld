package ch.vorburger.leviator;

public class EdiblePlantableThing extends Thing implements Edible, Plantable {
	// see comment in PlantableThing... let's use "implements" here, as both works!
	
	public EdiblePlantableThing(String name) {
		super(name);
	}

}
