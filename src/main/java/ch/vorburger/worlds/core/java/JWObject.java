package ch.vorburger.worlds.core.java;

import ch.vorburger.worlds.core.WClass;
import ch.vorburger.worlds.core.WObject;

public class JWObject implements WObject {

	@Override final public WClass getWClass() {
		return JWClass.fromJavaClass(getClass());
	}

}
