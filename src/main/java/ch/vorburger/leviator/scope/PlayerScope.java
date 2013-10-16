package ch.vorburger.leviator.scope;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.vorburger.leviator.Place;
import ch.vorburger.leviator.Player;
import ch.vorburger.meta.adaptable.AdaptableUtil;
import ch.vorburger.worlds.commands.Scope;
import ch.vorburger.worlds.commands.WCommand;
import ch.vorburger.worlds.commands.WCommandArgument;
import ch.vorburger.worlds.core.WClass;
import ch.vorburger.worlds.core.WObject;
import ch.vorburger.worlds.core.java.JWClass;
import ch.vorburger.worlds.naming.Named;
import ch.vorburger.worlds.naming.QualifiedName;

import com.google.common.collect.Iterables;

public class PlayerScope implements Scope {
	// TODO probably create an ABC, which delegates up along some chain.. or several? One being a WObjectsScope.

	private final Scope parentScope;
	
	private final Map<WClass, Map<QualifiedName, WObject>> map;

	private final Player currentPlayer;

	public PlayerScope(Player p) {
		this.currentPlayer = p;
		this.parentScope = new WorldScope(currentPlayer.world);
		this.map = getClassNameObjectMap();
	}

	/**
	 * This Scope implementation eagerly pre-creates all objects, and then
	 * obtains their names and classes. This is an implementation choice, to
	 * simplify this class. The Scope interface, intentionally, would allow for
	 * this to be done much more lazily. -- Also, this implementation assumes
	 * that all objects in the Scope are Named WObjects.  The Scope interface
	 * itself (at the time of writing this at least) actually does not.
	 */
	protected Map<WClass, Map<QualifiedName, WObject>> getClassNameObjectMap() {
		Map<WClass, Map<QualifiedName, WObject>> map = new HashMap<>();
		Iterable<WObject> wObjects = getWObjects();
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
			// TODO when Named returns QualifiedName instead String: innerMap.put(named.name(), wObject);
			innerMap.put(QualifiedName.create(named.name()), wObject);
		}
		return map;
	}

	/**
	 * See doc. in {@link #getClassNameObjectMap()}.
	 */
	protected Iterable<WObject> getWObjects() {
		// TODO later use some sort of reflection util discovering annotated methods.. but, for now, just hardcode this:
		WCommand goCommand = new WCommand() {
			// TODO create a command AbstractWCommand ABC..
			
			@Override
			public String getDocumentation() {
				return "Player goes into Place";
			}
			
			@Override
			public String name() {
				// TODO QualifiedName.create(/* TODO currentPlayer.name(), */ "go");
				return "go";
			}
			
			@Override
			public void invoke(Object... parameters) {
				throw new IllegalArgumentException();
			}
			
			@Override
			public List<WCommandArgument> getParameters() {
				WCommandArgument placeArg = new WCommandArgument() {
					// TODO create a command AbstractWCommandArgument ABC..
					
					@Override
					public String name() {
						return "place";
					}

					@Override
					public String getDocumentation() {
						return "";
					}

					@Override
					public WClass getType() {
						return JWClass.fromJavaClass(Place.class);
					}

					@Override
					public boolean isEllipsisVarArg() {
						return false;
					}
				};
				return Arrays.asList(placeArg);
			}

			@Override
			public WClass getWClass() {
				return JWClass.fromJavaClass(WCommand.class);
			}
		};
		// cannot return Arrays.asList(goCommand);
		ArrayList<WObject> list = new ArrayList<WObject>(1);
		list.add(goCommand);
		return list;
	}

	@Override
	public Iterable<QualifiedName> getAll(WClass wClass) {
		Map<QualifiedName, WObject> innerMap = getInnerMap(wClass);
		Iterable<QualifiedName> mine = innerMap.keySet();
//		Iterable<QualifiedName> mine = Collections.emptySet();
//		if (isCommandClass(wClass)) {
//			mine = getAllCommands();
//		// TODO } else if (wClass.name().equals(... Thing ...))
//		}
		Iterable<QualifiedName> parents = parentScope.getAll(wClass);
		return Iterables.concat(mine, parents);
	}

//	private boolean isCommandClass(WClass wClass) {
//		// TODO huh, really String-based?
//		return wClass.name().equals(WCommand.class.getSimpleName());
//	}
//
//	protected Iterable<QualifiedName> getAllCommands() {
//		// TODO later use some sort of reflection util discovering annotated methods.. but, for now, just hardcode this:
//		QualifiedName goCommandName = QualifiedName.create(/* TODO currentPlayer.name(), */ "go");
//		return Arrays.asList(goCommandName);
//	}

	@Override
	public Object get(WClass wClass, QualifiedName objectName) {
		Map<QualifiedName, WObject> innerMap = getInnerMap(wClass);
		WObject wObject = innerMap.get(objectName);
		if (wObject == null)
			throw new IllegalArgumentException("Scope does not provide QN " + objectName.toString());
		return wObject;
//		if (isCommandClass(wClass)) {
//			return null; // TODO IMPL!
//		}
//		throw new IllegalArgumentException();
	}

	private Map<QualifiedName, WObject> getInnerMap(WClass wClass) {
		Map<QualifiedName, WObject> innerMap = map.get(wClass);
		if (innerMap == null)
			throw new IllegalArgumentException("Scope does not provide for WClass " + wClass.name());
		return innerMap;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(JWClass<T> wClass, QualifiedName objectName) {
		return (T) this.get((WClass) wClass, objectName);
	}

	@Override
	public Iterable<QualifiedName> getAllApplicable(WCommandArgument arg) {
		// TODO Auto-generated method stub
		throw new IllegalArgumentException();
	}

}
