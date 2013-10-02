package ch.vorburger.meta.adaptable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicAdaptable extends DynamicMarkerInterfaceAdaptable {

	private final List<Object> adapteds = new ArrayList<Object>(0);
	
	public DynamicAdaptable() {
	}

	protected DynamicAdaptable(Class<?>... markerInterfaces) {
		super(markerInterfaces);
	}

	public void addAdapted(Object... adapted) {
		if (adapted.length == 1)
			adapteds.add(adapted[0]);
		else
			adapteds.addAll(Arrays.asList(adapted));
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected <T> T getAdaptedOrNull(Class<T> clazz) {
		T parent = super.getAdaptedOrNull(clazz);
		if (parent != null)
			return parent;
		for (Object adapted : adapteds) {
			if (clazz.isInstance(adapted))
				return (T) adapted;
		}
		return null;
	}

}
