package ch.vorburger.meta.methods;

/**
 * ... could be immediately executed, or scheduled into a Q for later.. or if on
 * an interface then dispatched to several targets...
 */
public interface MethodInvoker {

	// TODO rename invokeAsync? return Future<T> ?
	<T> T invoke(T targetObject); // TODO naming.. *Dispatcher?

	<T> T invokeOnAll(Class<T> targetClass);

}
