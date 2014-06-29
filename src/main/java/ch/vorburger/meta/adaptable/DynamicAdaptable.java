package ch.vorburger.meta.adaptable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
	protected <T> Optional<T> getAdapted(Class<T> clazz) {
		Optional<T> parent = super.getAdapted(clazz);
		if (parent.isPresent())
			return parent;
		for (Object adapted : adapteds) {
			if (clazz.isInstance(adapted))
				return Optional.of((T) adapted);
		}
		return Optional.empty();
	}

}
