package ch.vorburger.adaptable;

public abstract class StaticAdaptable extends AbstractAdaptable {

	@Override
	protected <T> T getAdaptedOrNull(Class<T> clazz) {
		return AdaptableUtil.getAdapter(this, clazz);
	}
	
}
