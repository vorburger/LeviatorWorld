package ch.vorburger.leviator;

public class Thing implements Named {

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
