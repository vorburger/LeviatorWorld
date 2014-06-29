package ch.vorburger.meta.adaptable;

import java.util.Optional;


public abstract class StaticAdaptable extends AbstractAdaptable {

	@Override
	protected <T> Optional<T> getAdapted(Class<T> clazz) {
		return AdaptableUtil.getAdapterIfIsInstance(this, clazz);
	}
	
}
