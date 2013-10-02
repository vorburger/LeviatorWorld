package ch.vorburger.meta.adaptable;

public abstract class AbstractAdaptable implements Adaptable {

	protected abstract <T> T getAdaptedOrNull(Class<T> clazz);
	
	@Override
	public final <T> T adaptTo(Class<T> clazz) throws IllegalArgumentException {
		T adapted = getAdaptedOrNull(clazz);
		if (adapted != null)
			return adapted;
		else
			throw new IllegalArgumentException("cannot adapt to: " + clazz);
	}

	@Override
	public final boolean isAdaptableTo(Class<?> clazz) {
		Object adapted = getAdaptedOrNull(clazz);
		return adapted != null; 
	}

}
