package ch.vorburger.meta.adaptable;

public interface Adaptable {

	boolean isAdaptableTo(Class<?> clazz);
	
	/**
	 * Adapt.
	 * Resistance is futile.
	 * 
	 * @param adapter class (never null)
	 * @return adapter (never null)
	 * @throws IllegalArgumentException if it cannot be adapted (or null arg)
	 */
	<T> T adaptTo(Class<T> clazz) throws IllegalArgumentException;

}
