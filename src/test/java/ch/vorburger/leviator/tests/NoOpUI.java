package ch.vorburger.leviator.tests;

import ch.vorburger.worlds.UI;

public class NoOpUI implements UI {

	@Override
	public void println(String string) { }

	@Override
	public void print(String string) { }

}
