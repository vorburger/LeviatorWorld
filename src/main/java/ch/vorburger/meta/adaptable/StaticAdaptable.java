package ch.vorburger.meta.adaptable;

public abstract class StaticAdaptable extends AbstractAdaptable {

	@Override
	protected <T> T getAdaptedOrNull(Class<T> clazz) {
		return AdaptableUtil.getAdapterIfIsInstance(this, clazz);
	}
	
}
