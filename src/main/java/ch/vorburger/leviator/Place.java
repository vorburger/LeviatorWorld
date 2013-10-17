package ch.vorburger.leviator;

import ch.vorburger.meta.adaptable.DynamicAdaptable;
import ch.vorburger.worlds.core.WClass;
import ch.vorburger.worlds.core.WObject;
import ch.vorburger.worlds.core.java.JWClass;
import ch.vorburger.worlds.naming.Named;
import ch.vorburger.worlds.naming.QualifiedName;


public class Place extends DynamicAdaptable implements WObject, Named {

	public final Things things = new Things();

	private String name;

	public Place(String name) {
		super();
		this.name = name;
	}

	public QualifiedName name() {
		return QualifiedName.create(/* world, */ name);
	}

	@Override
	public WClass getWClass() {
		return JWClass.fromJavaClass(getClass());
	}

}
