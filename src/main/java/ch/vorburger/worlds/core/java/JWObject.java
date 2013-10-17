package ch.vorburger.worlds.core.java;

import ch.vorburger.worlds.core.WClass;
import ch.vorburger.worlds.core.WObject;

public class JWObject implements WObject {

	// TODO Careful: E.g. AbstractWCommandImpl cannot use this, because ch.vorburger.worlds.commands.AbstractWObjectsScope.getAllFromLocalScope(WClass) doesn't deal with sub-classes 
	
	@Override final public WClass getWClass() {
		return JWClass.fromJavaClass(getClass());
	}

}
