package ch.vorburger.worlds.code.module;

import ch.vorburger.worlds.naming.QualifiedName;
import ch.vorburger.worlds.os.WFolder;

public class GitRepo implements ch.vorburger.worlds.os.WRootFolder {

	@Override
	public QualifiedName name() {
		return QualifiedName.create("TODO");
	}

	@Override
	public WFolder getContainer() {
		throw new UnsupportedOperationException();
	}
	
	// TODO clone()
	// TODO pull()
	// TODO push()
	
}
