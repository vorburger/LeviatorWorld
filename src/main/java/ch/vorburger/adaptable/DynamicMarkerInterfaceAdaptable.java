package ch.vorburger.adaptable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	protected <T> T getAdaptedOrNull(Class<T> clazz) throws IllegalArgumentException {
		T parent = super.getAdaptedOrNull(clazz);
		if (parent != null)
			return parent;
		if (adaptedableMarkerInterfaces.contains(clazz)) {
			return (T) this;
		}
		return null;
	}
}
