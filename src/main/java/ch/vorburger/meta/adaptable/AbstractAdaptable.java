package ch.vorburger.meta.adaptable;

import java.util.Optional;

public abstract class AbstractAdaptable implements Adaptable {

	protected abstract <T> Optional<T> getAdapted(Class<T> clazz);
	
	@Override
	public final <T> T adaptTo(Class<T> clazz) throws IllegalArgumentException {
		Optional<T> adapted = getAdapted(clazz);
		if (adapted.isPresent())
			return adapted.get();
		else
			throw new IllegalArgumentException("cannot adapt to: " + clazz);
	}

	@Override
	public final boolean isAdaptableTo(Class<?> clazz) {
		Optional<?> adapted = getAdapted(clazz);
		return adapted.isPresent(); 
	}

}
