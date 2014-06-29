package ch.vorburger.worlds.code.module;

import ch.vorburger.worlds.os.WFolder;

public class GitRepo implements ch.vorburger.worlds.os.WRootFolder {

	@Override
	public String name() {
		return "TODO";
	}

	@Override
	public WFolder getContainer() {
		throw new UnsupportedOperationException();
	}
	
	// TODO clone()
	// TODO pull()
	// TODO push()
	
}
