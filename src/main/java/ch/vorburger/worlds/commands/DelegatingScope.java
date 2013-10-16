package ch.vorburger.worlds.commands;

import ch.vorburger.worlds.core.WClass;
import ch.vorburger.worlds.core.java.JWClass;
import ch.vorburger.worlds.naming.QualifiedName;

public abstract class DelegatingScope implements Scope {
	// TODO most probably remove later, as this isn't actually needed (because I've ended up using composition instead of inheritance..)
	
	private final Scope delegate;
	
	protected DelegatingScope(Scope parent) {
		this.delegate = parent;
	}
	
	protected Scope getDelegate() {
		return delegate;
	}

	@Override
	public final Iterable<QualifiedName> getAll(WClass wClass) {
		return delegate.getAll(wClass);
	}

	@Override
	public final Iterable<QualifiedName> getAllApplicable(WCommandArgument arg) {
		return delegate.getAllApplicable(arg);
	}

	@Override
	public final Object get(WClass wClass, QualifiedName objectName) {
		return delegate.get(wClass, objectName);
	}

	@Override
	public final <T> T get(JWClass<T> wClass, QualifiedName objectName) {
		return delegate.get(wClass, objectName);
	}

}
