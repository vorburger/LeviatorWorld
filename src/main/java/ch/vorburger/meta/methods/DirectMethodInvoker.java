package ch.vorburger.meta.methods;

import org.eclipse.jdt.annotation.NonNull;

public class DirectMethodInvoker implements MethodInvoker {
	// TODO just as an Illustration.. delete later?  This cannot really impl. invokeOnAll()
	
	@Override
	public <T> T invoke(T target) {
		return target;
	}

	@Override
	public <T> @NonNull T invokeOnAll(@NonNull Class<T> targetClass) {
		throw new UnsupportedOperationException();
	}

}
