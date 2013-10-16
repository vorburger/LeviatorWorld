package ch.vorburger.worlds.core;

/**
 * WObject intentionally doesn't, and never should, extend Named.
 * Scope is used to "find" WObject/s, given a (and list available) names.
 * Some WObjects may actually be Named themselves - others may not be.
 */
public interface WObject {
	
	WClass getWClass();
	
}
