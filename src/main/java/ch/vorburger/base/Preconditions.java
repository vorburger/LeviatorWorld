package ch.vorburger.base;


public class Preconditions {
	public static <T> T checkNotNull(T reference) {
		return com.google.common.base.Preconditions.checkNotNull(reference);
	}
}

