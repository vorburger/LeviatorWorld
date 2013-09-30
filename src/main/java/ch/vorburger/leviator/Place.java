package ch.vorburger.leviator;

import ch.vorburger.adaptable.DynamicAdaptable;


public class Place extends DynamicAdaptable implements Named {

	Things things = new Things();

	private String name;

	Place(String name) {
		super();
		this.name = name;
	}

	public String name() {
		return name;
	}

}
