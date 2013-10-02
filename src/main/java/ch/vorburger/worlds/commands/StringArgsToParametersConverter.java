package ch.vorburger.worlds.commands;

import java.util.List;

public interface StringArgsToParametersConverter {
	// TODO this is probably for a given WMethod? Obtained how?
	
	// TODO remember the Ctrl-Space requirement...
	
	// TODO better just String instead of List<String>, and do the parsing inside?
	List<Object> convertStringArgumentParameters(List<String> args);

	/**
	 * Returns a descriptive hint line, something 
	 * like "<howMany(Integer)> <NewThing> <ThingCategory>*",
	 * or "<Player> <Thing> [howMany/1]".  There is "convention" that
	 * if the Parameter name equals its Type starting with lower-case,
	 * then the type hint in parenthesis is omitted. 
	 */
	String getParametersEBNFLine();

}
