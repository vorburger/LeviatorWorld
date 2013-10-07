package ch.vorburger.worlds.persistence.firebase;

import org.slf4j.Logger;

public class FirebaseLogger implements com.firebase.client.Logger {

	protected final Logger logger;

	public FirebaseLogger(org.slf4j.Logger logger) {
		this.logger = logger;
	}

	@Override
	public void onLogMessage(Level level, String tag, String message, long msTimestamp) {
		switch (level) {
		case DEBUG:
			logger.debug(message, tag, msTimestamp);
			break;
		case INFO:
			logger.info(message, tag, msTimestamp);
			break;
		case ERROR:
			logger.error(message, tag, msTimestamp);
			break;
		case NONE:
			logger.error(message, tag, msTimestamp);
			break;
		default:
			logger.error(message, tag, msTimestamp);
			break;
		}

	}

}
