package ch.vorburger.worlds.commands;

/**
 * 
 * @see ch.vorburger.meta.methods.MethodInvoker for the lower-level static Java cousin of this
 */
public interface WMethodInvoker {

	// TODO why <T> T? probably just void.. 
	<T> T invoke(WCommand method);
	
}
