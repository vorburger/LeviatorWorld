package ch.vorburger.worlds.commands;
// TODO this will turn out to not really be related to commands, is it.. should be worlds.scope package?

import ch.vorburger.worlds.core.WClass;
import ch.vorburger.worlds.core.java.JWClass;
import ch.vorburger.worlds.naming.QualifiedName;

public interface Scope {
	// TODO double think and confirm it makes sense to return QualifiedName for all + WObject for one below
	// as opposed to the same some new type à la IEObjectDescription for both, which itself has a getName and getObject - like org.eclipse.xtext.scoping.IScope does
	
	// TODO Something doesn't feel quite right yet.. if Scope is per Object (Player, World), and has a getAll(WClass wClass) but no property name, then what if e.g. World had (just for the sake of argument).. two lists of Users?
	
	/**
	 * @return never null
	 */
	Iterable<QualifiedName> getAll(WClass wClass);
		
	/*W?*/Object get(WClass wClass, QualifiedName objectName) throws IllegalArgumentException;
	<T> T get(JWClass<T> wClass, QualifiedName objectName) throws IllegalArgumentException;
}
