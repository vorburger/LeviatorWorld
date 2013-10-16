package ch.vorburger.worlds.commands;

import java.util.Collections;

import ch.vorburger.worlds.core.WClass;
import ch.vorburger.worlds.naming.QualifiedName;

public class EmptyScope extends AbstractScope {

	@Override
	public Iterable<QualifiedName> getAll(WClass wClass) {
		return Collections.emptySet();
	}

	@Override
	public Object get(WClass wClass, QualifiedName objectName) throws IllegalArgumentException {
		throw new IllegalArgumentException("You cannot get() anything from an EmptyScope");
	}

}
