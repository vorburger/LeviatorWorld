package ch.vorburger.worlds.commands;

import ch.vorburger.worlds.Documented;
import ch.vorburger.worlds.naming.Named;

public interface WParameter extends Named, Documented {

	Class<?> getType();
	
	/**
	 * Static Java method argument would typically have @Nullable.
	 * (Not only the last parameter may be optional; as with named parameters, skipping is possible.)
	 */
	boolean isOptional();

	/**
	 * Parameters with default are optional.
	 */
	// TODO String for now.. Object better?!
	String getDefaultValue();
	
	/**
	 * Only the last parameter may be an EllipsisVarArg.
	 * Static Java method argument will have a "..." in this case.
	 */
	boolean isEllipsisVarArg();
}
