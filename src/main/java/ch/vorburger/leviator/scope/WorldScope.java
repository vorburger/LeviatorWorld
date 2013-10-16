package ch.vorburger.leviator.scope;

import java.util.Collections;

import ch.vorburger.leviator.World;
import ch.vorburger.worlds.commands.Scope;
import ch.vorburger.worlds.commands.WCommandArgument;
import ch.vorburger.worlds.core.WClass;
import ch.vorburger.worlds.core.java.JWClass;
import ch.vorburger.worlds.naming.QualifiedName;

public class WorldScope implements Scope {
	// TODO eval what this class and the PlayerScope can share..
	
	public WorldScope(World world) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Iterable<QualifiedName> getAll(WClass wClass) {
		// TODO Auto-generated method stub
		return Collections.emptySet();
	}

	@Override
	public Iterable<QualifiedName> getAllApplicable(WCommandArgument arg) {
		// TODO Auto-generated method stub
		return Collections.emptySet();
	}

	@Override
	public Object get(WClass wClass, QualifiedName objectName) {
		// TODO Auto-generated method stub
		throw new IllegalArgumentException();
	}

	@Override
	public <T> T get(JWClass<T> wClass, QualifiedName objectName) {
		// TODO Auto-generated method stub
		throw new IllegalArgumentException();
	}

}
