package com.gamerforea.eventhelper.inject.gpp;

import com.gamerforea.eventhelper.inject.DummyPluginInjection;
import com.gamerforea.eventhelper.inject.PluginInjection;
import com.gamerforea.eventhelper.util.InjectionUtils;

import net.kaikk.mc.gpp.Claim;
import net.kaikk.mc.gpp.GriefPreventionPlus;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import static com.integral.eventhelperultimate.BuildController.*;

import javax.annotation.Nullable;

public final class GPPInjection {

	@Nullable
	public static PluginInjection getInjection() {
		if (isDummyBuild)
			return null;

		Class<?> clazz = InjectionUtils.injectClass("GriefPreventionPlus", GPPInjection.class);
		if (clazz != null) {
			try {
				return (PluginInjection) clazz.newInstance();
			} catch (Throwable throwable) {
				throwable.printStackTrace();
			}
		}
		return null;
	}

	public static final class Inj implements PluginInjection {
		@Override
		public boolean isInPrivate(Object world, int x, int y, int z) {
			if (isDummyBuild)
				return false;

			GriefPreventionPlus plugin = GriefPreventionPlus.getInstance();
			Claim claim = plugin.getDataStore().getClaimAt(new Location((World) world, x, y, z), false);
			return claim != null;
		}

		@Override
		public boolean isPrivateMember(Object player, int x, int y, int z) {
			if (isDummyBuild)
				return true;

			return this.isPrivateOwner(player, x, y, z);
		}

		@Override
		public boolean isPrivateOwner(Object player, int x, int y, int z) {
			if (isDummyBuild)
				return true;

			GriefPreventionPlus plugin = GriefPreventionPlus.getInstance();
			Claim claim = plugin.getDataStore().getClaimAt(new Location(((Player)player).getWorld(), x, y, z), false);
			return claim != null && ((Player)player).getUniqueId().equals(claim.getOwnerID());
		}
	}
}
