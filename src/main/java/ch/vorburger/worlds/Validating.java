package ch.vorburger.worlds;

public interface Validating {

	/**
	 * UIs might show some sort of red error marker overlaid if !isValid().
	 * Some Editor for a Validating {@link Text} might ask user to re-confirm he wants to quit if !isValid(). 
	 */
	boolean isValid();
	
	// TODO getProblems() - seek inspiration in respective Eclipse, EMF & Xtext APIs...

	// BEWARE: There should never ever be a validate() method here - Worlds is REACTIVE!
	
}
