package ch.vorburger.adaptable;


public class AdaptableUtil {
	private AdaptableUtil() { }

	@SuppressWarnings("unchecked")
	public static <T> T getAdapter(Object sourceObject, Class<T> adapterType) {
        if (sourceObject == null) {
            return null;
        }
        if (adapterType.isInstance(sourceObject)) {
            return (T) sourceObject;
        }

        if (sourceObject instanceof Adaptable) {
            Adaptable adaptable = (Adaptable) sourceObject;

            T result = adaptable.adaptTo(adapterType);
            return result;
        } 
        
//        if (!(sourceObject instanceof PlatformObject)) {
//            Object result = Platform.getAdapterManager().getAdapter(sourceObject, adapterType);
//            if (result != null) {
//                return result;
//            }
//        }

        return null;
    }
	
}
