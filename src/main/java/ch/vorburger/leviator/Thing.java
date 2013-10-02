package ch.vorburger.leviator;

import ch.vorburger.meta.adaptable.DynamicAdaptable;
import ch.vorburger.worlds.Named;

public class Thing extends DynamicAdaptable implements Named {

	private final String name;

	public Thing(String name, Class<?>... markerInterfaces) {
		super(markerInterfaces);
		this.name = name;
	}

	@Override
	public String name() {
		return name;
	}
	
}
