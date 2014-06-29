package ch.vorburger.meta.adaptable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DynamicMarkerInterfaceAdaptable extends StaticAdaptable {

	private final List<Class<?>> adaptedableMarkerInterfaces = new ArrayList<Class<?>>(0);

	public DynamicMarkerInterfaceAdaptable() {
	}

	public DynamicMarkerInterfaceAdaptable(Class<?>... markerInterfaces) {
		addAdapted(markerInterfaces);
	}

	public void addAdapted(Class<?>... markerInterfaces) {
		if (markerInterfaces.length == 1)
			adaptedableMarkerInterfaces.add(markerInterfaces[0]);
		else
			adaptedableMarkerInterfaces.addAll(Arrays.asList(markerInterfaces));
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected <T> Optional<T> getAdapted(Class<T> clazz) throws IllegalArgumentException {
		Optional<T> parent = super.getAdapted(clazz);
		if (parent.isPresent())
			return parent;
		if (adaptedableMarkerInterfaces.contains(clazz)) {
			return Optional.of((T) this);
		}
		return Optional.empty();
	}
}
