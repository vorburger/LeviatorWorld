package ch.vorburger.worlds.commands;

import java.util.List;

import ch.vorburger.worlds.Documented;
import ch.vorburger.worlds.core.WObject;
import ch.vorburger.worlds.naming.Named;

/**
 * Method, AKA Operation.
 */
public interface WCommand extends WObject, Named, Documented {
	
	List<WCommandArgument> getParameters();
	
	// TODO should this best be here, or outside in another interface, @see e.g. WMethodInvoker? 
	void invoke(/* no Object target, */ /*W?*/Object... parameters);
	
	// TODO NOT sure we want/need return type (everything should just be async messages), or declared exceptions?
	
}
