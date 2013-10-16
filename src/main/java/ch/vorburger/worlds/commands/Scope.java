package ch.vorburger.worlds.commands;

import ch.vorburger.worlds.core.WClass;
import ch.vorburger.worlds.core.java.JWClass;
import ch.vorburger.worlds.naming.QualifiedName;

public interface Scope {
	// TODO double think and confirm it makes sense to return QualifiedName for all + WObject for one below
	// as opposed to the same some new type à la IEObjectDescription for both, which itself has a getName and getObject - like org.eclipse.xtext.scoping.IScope does
	
	/**
	 * @return never null
	 */
	Iterable<QualifiedName> getAll(WClass wClass);
	
	/**
	 * This MAY be the same as {@link #getAll(WClass)} with the WClass being the
	 * WParameter's type, or it may be a more restricted sub-set of that which is
	 * applicable as the respective Command Argument.
	 */
	Iterable<QualifiedName> getAllApplicable(WCommandArgument arg); 
	
	/*W?*/Object get(WClass wClass, QualifiedName objectName) throws IllegalArgumentException;
	<T> T get(JWClass<T> wClass, QualifiedName objectName) throws IllegalArgumentException;
}
