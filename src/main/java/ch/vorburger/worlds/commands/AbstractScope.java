package ch.vorburger.worlds.commands;

import ch.vorburger.worlds.core.WClass;
import ch.vorburger.worlds.core.java.JWClass;
import ch.vorburger.worlds.naming.QualifiedName;

public abstract class AbstractScope implements Scope {

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(JWClass<T> wClass, QualifiedName objectName) throws IllegalArgumentException {
		return (T) this.get((WClass) wClass, objectName);
	}

}
