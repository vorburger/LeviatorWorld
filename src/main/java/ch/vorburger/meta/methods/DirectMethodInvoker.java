package ch.vorburger.meta.methods;

public abstract class DirectMethodInvoker implements MethodInvoker {
	// TODO just as an Illustration.. delete later?  This cannot really impl. invokeOnAll()
	
	@Override
	public <T> T invoke(T target) {
		return target;
	}

}
