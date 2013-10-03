package ch.vorburger.worlds.commands;

/**
 * 
 * @see ch.vorburger.meta.methods.MethodInvoker for the lower-level static Java cousin of this
 */
public interface WMethodInvoker {
	
	<T> T invoke(WMethod method);
	
}
