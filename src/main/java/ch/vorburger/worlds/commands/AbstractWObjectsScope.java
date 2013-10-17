package ch.vorburger.worlds.commands;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ch.vorburger.meta.adaptable.AdaptableUtil;
import ch.vorburger.worlds.core.WClass;
import ch.vorburger.worlds.core.WObject;
import ch.vorburger.worlds.naming.Named;
import ch.vorburger.worlds.naming.QualifiedName;

/**
 * This Scope implementation is based on an pre-existing list of all WObjects,
 * and then obtains their names and classes. This is an implementation choice,
 * to simplify certain implementation. The Scope interface, intentionally, would
 * allow for this to be done much more lazily. -- Also, this implementation
 * assumes that all objects in the Scope are Named WObjects. The Scope interface
 * itself (at the time of writing this at least) actually does not.
 */
public abstract class AbstractWObjectsScope extends AbstractDelegatingScope {

	private Map<WClass, Map<QualifiedName, WObject>> map;
	
	protected AbstractWObjectsScope(Scope parentScope) {
		super(parentScope);
	}

	protected Map<WClass, Map<QualifiedName, WObject>> getMap() {
		if (map == null)
			map = getClassNameObjectMap();
		return map;
	}

	@Override
	protected Iterable<QualifiedName> getAllFromLocalScope(WClass wClass) {
		Map<WClass, Map<QualifiedName, WObject>> outerMap = getMap();
		// NOTE: This doesn't deal with subclassing..
		Map<QualifiedName, WObject> innerMap = outerMap.get(wClass);
		if (innerMap == null)
			 return Collections.emptySet();
		return innerMap.keySet();
	}

	@Override
	protected Object getFromLocalScope(WClass wClass, QualifiedName objectName) {
		Map<QualifiedName, WObject> innerMap = getMap().get(wClass);
		if (innerMap == null)
			return null;
		return innerMap.get(objectName);
	}

	protected Map<WClass, Map<QualifiedName, WObject>> getClassNameObjectMap() {
		Map<WClass, Map<QualifiedName, WObject>> map = new HashMap<>();
		Iterable<? extends WObject> wObjects = getWObjects();
		for (WObject wObject : wObjects) {
			WClass wClass = wObject.getWClass();
			Map<QualifiedName, WObject> innerMap = map.get(wClass);
			if (innerMap == null) {
				innerMap = new HashMap<>();
				map.put(wClass, innerMap);
			}
			Named named = AdaptableUtil.getAdapter(wObject, Named.class);
			if (named == null)
				throw new IllegalArgumentException("WObject could not be adapted to Named: " + wObject);
			innerMap.put(named.name(), wObject);
		}
		return map;
	}

	protected abstract Iterable<? extends WObject> getWObjects();

}
