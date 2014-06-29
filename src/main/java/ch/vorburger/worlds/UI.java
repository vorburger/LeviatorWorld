package ch.vorburger.worlds;

import org.eclipse.jdt.annotation.NonNull;

public interface UI {
	// TODO not sure this will be kept around for long, with my new inter-object messages idea.. isn't this just for chat() from Computer to Player?
	
	void println(String string);

	void print(String string);

	// TODO we'll have input() related stuff here as well.. instead of System.in in CommandPrompt

	class NoUI implements UI {

		@Override
		public void println(@NonNull String string) {
		}

		@Override
		public void print(@NonNull String string) {
		}
		
	}
}
