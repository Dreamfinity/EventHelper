package com.gamerforea.eventhelper.inject;

public interface PluginInjection {

	boolean isInPrivate(Object world, int x, int y, int z);

	boolean isPrivateMember(Object player, int x, int y, int z);

	boolean isPrivateOwner(Object player, int x, int y, int z);

}
