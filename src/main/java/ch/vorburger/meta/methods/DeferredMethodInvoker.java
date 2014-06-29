package ch.vorburger.meta.methods;


public class DeferredMethodInvoker implements MethodInvoker {

	protected final MethodInvokationListener methodInvokationListener;
	
	public DeferredMethodInvoker(MethodInvokationListener methodInvokationListener) {
		super();
		this.methodInvokationListener = methodInvokationListener;
	}

	@Override
	public <T> T invoke(T target) {
		// TODO think about implementation.. my idea is to return a Proxy which just "records" the invoked method, and stores it into something given to this at construction, for then later invokation
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T invokeOnAll(Class<T> targetClass) {
		// TODO impl. This in a sense will have to, via other interface/s probably, turn the invokeAll() into invididual invoke() on actual objects obtained from an Iterable<Object> (typically lazy!) util
		throw new UnsupportedOperationException();
	}

}
