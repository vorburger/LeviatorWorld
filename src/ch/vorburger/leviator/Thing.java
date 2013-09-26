package ch.vorburger.leviator;

public class Thing extends AbstractThing {

	private final String name;

	public Thing(String name) {
		super();
		this.name = name;
	}

	@Override
	public String name() {
		return name;
	}
	
}
