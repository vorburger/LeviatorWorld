package ch.vorburger.worlds.commands;

import java.io.Serializable;

/**
 * Could be placed in a transaction log,
 * sent to other nodes in a cluster for HA replication, etc.
 * This is the only kind of "Message" (or "Event", same thing) available in Worlds, really.
 */
public class WMethodInvocationEventMessage implements Serializable {
	// TODO remove or keep this?

	// TODO will this hold Object or String refs to the 'target' and the 'arguments' ??

}
