package ch.vorburger.worlds.commands;

import java.util.List;

import ch.vorburger.worlds.Documented;
import ch.vorburger.worlds.Named;

/**
 * Method, AKA Operation.
 */
public interface WMethod extends Named, Documented {
	// TODO Or is this really ch.vorburger.meta.methods?
	
	List<WParameter> getParameters();
	
	// TODO should this best be here, or outside in another interface? 
	void invoke(Object target, List<Object> parameters);
	
	// TODO NOT sure we want/need return type (everything should just be async messages), or declared exceptions?
	
}
