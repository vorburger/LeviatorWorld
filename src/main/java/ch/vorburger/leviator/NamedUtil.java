package ch.vorburger.leviator;

import java.util.Collection;

import ch.vorburger.worlds.naming.Named;

public class NamedUtil {
	private NamedUtil() { }

	static <T extends Named> T get(Collection<T> stuff, String name) {
		for (T named : stuff) {
			if (named.name().equalsIgnoreCase(name))
				return named;
		}
		
		String msg = "No: " + name;
		if (!stuff.isEmpty()) {
			boolean firstThing = true;
			// TODO use com.google.common.base.Joiner.class
			String availableStuff = "";
			for (Named named : stuff) {
				if (!firstThing) {
					availableStuff += ", ";
				}
				availableStuff += named.name();
				firstThing = false;
			}
			msg += ", only: " + availableStuff;
		}
		msg += ".";
		throw new IllegalArgumentException(msg);
	}
	
}
