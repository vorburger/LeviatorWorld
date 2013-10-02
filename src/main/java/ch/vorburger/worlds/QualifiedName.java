package ch.vorburger.worlds;

public interface QualifiedName {
	// TODO à la org.eclipse.core.runtime.QualifiedName or, better, Xtext's QualifiedName
	// TODO optimized implementation

	/**
	 * Local name is the "last part" of a QualifiedName.
	 * Its the name under which the respective Named is known within its NamingContext.
	 */
	String getLocalName();

	String toString();
}
