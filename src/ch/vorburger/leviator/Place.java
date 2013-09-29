package ch.vorburger.leviator;


public class Place implements Named {

	Things things = new Things();

	private String name;

	Place(String name) {
		super();
		this.name = name;
	}

	public String name() {
		return name;
	}

}
