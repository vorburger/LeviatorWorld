package ch.vorburger.worlds.commands;

/**
 * 
 * @see ch.vorburger.meta.methods.MethodInvoker for the lower-level static Java cousin of this
 */
public interface WMethodInvoker {
	// TODO remove or keep this?

	// TODO why <T> T? probably just void.. 
	<T> T invoke(WCommand method);
	
}
