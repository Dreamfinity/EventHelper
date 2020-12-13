package com.gamerforea.eventhelper.inject;

import org.bukkit.World;
import org.bukkit.entity.Player;

public class DummyPluginInjection implements PluginInjection {
	private static final DummyPluginInjection instance = new DummyPluginInjection();

	public static DummyPluginInjection getInstance() {
		return instance;
	}

	private DummyPluginInjection() {
		// NO-OP
	}

	@Override
	public boolean isInPrivate(World world, int x, int y, int z) {
		return false;
	}

	@Override
	public boolean isPrivateMember(Player player, int x, int y, int z) {
		return true;
	}

	@Override
	public boolean isPrivateOwner(Player player, int x, int y, int z) {
		return true;
	}

}
