package ch.vorburger.worlds.naming;

public interface RenameableNamed extends Named {

	/**
	 * NOTE: This CHANGES the QualifiedName which will be returned by the next ch.vorburger.worlds.naming.Named.name(), of course!
	 */
	void setLocalName(String newName);
	
}
