package ch.vorburger.worlds.commands;

import ch.vorburger.worlds.Documented;
import ch.vorburger.worlds.core.WClass;
import ch.vorburger.worlds.naming.Named;

public interface WCommandArgument extends Named, Documented {
	
	WClass getType();
	// NOT Class<?> getType();
	
	// TODO may be instead of this it would be clearer if the WCommand WAS-ADAPTABLE-TO ScopeProvider<WCommandArgument> ?
	Scope getScope();
	
	/**
	 * Static Java method argument would typically have @Nullable.
	 * (Not only the last parameter may be optional; as with named parameters, skipping is possible.)
	 */
// TODO if this is (really?) needed, then ch.vorburger.worlds.commands.WCommand.invoke(WObject...) will need to take a Map<String/WCommandArgument, WObject> ..
//	boolean isOptional();

	/**
	 * Parameters with default are optional.
	 */
	// TODO String for now.. Object better?!
	// String getDefaultValue();
	
	/**
	 * Only the last parameter may be an EllipsisVarArg.
	 * Static Java method argument will have a "..." in this case.
	 */
	boolean isEllipsisVarArg();
	
	// TODO "constraint".. see notes in my booklet
}
