package ch.vorburger.leviator;

import ch.vorburger.meta.adaptable.DynamicAdaptable;
import ch.vorburger.worlds.naming.Named;
import ch.vorburger.worlds.naming.QualifiedName;

public class Thing extends DynamicAdaptable implements Named {

	private final QualifiedName name;

	public Thing(Named parent, String localName, Class<?>... markerInterfaces) {
		super(markerInterfaces);
		this.name = QualifiedName.create(parent.name(), localName);
	}

	@Override
	public QualifiedName name() {
		return name;
	}
	
}
