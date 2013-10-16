package ch.vorburger.worlds.commands;

import com.google.common.collect.Iterables;

import ch.vorburger.worlds.core.WClass;
import ch.vorburger.worlds.naming.QualifiedName;

public abstract class AbstractDelegatingScope extends AbstractScope {
	
	private final Scope delegate;
	
	protected AbstractDelegatingScope(Scope parent) {
		this.delegate = parent;
	}
	
	@Override public final Iterable<QualifiedName> getAll(WClass wClass) {
		Iterable<QualifiedName> mine = getAllFromLocalScope(wClass);
		Iterable<QualifiedName> parents = delegate.getAll(wClass);
		return Iterables.concat(mine, parents);
	}

	@Override public final Object get(WClass wClass, QualifiedName objectName) throws IllegalArgumentException {
		Object mine = getFromLocalScope(wClass, objectName);
		if (mine != null)
			return mine;
		Object parent = delegate.get(wClass, objectName);
		if (parent != null)
			return parent;
		throw new IllegalArgumentException("No Scope provides QN " + objectName.toString() + " (WClass: " + wClass.name() + ")");
	}

	/**
	 * Never throw exception or return null, just use return Collections.emptySet() if this (local) scope doesn't have anything for this class.
	 */
	abstract protected Iterable<QualifiedName> getAllFromLocalScope(WClass wClass);
	
	/**
	 * Never throw IllegalArgumentException, but return null if this (local) scope doesn't have this object.
	 */
	abstract protected Object getFromLocalScope(WClass wClass, QualifiedName objectName);
}
