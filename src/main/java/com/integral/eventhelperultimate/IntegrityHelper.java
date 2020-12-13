package com.integral.eventhelperultimate;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class IntegrityHelper {

	public static boolean isPlayerOP(EntityPlayer player) {
		return MinecraftServer.getServer() != null ? MinecraftServer.getServer().getConfigurationManager().func_152596_g(player.getGameProfile()) : false;
	}

	public static boolean isPlayerOP(UUID playerID) {
		if (MinecraftServer.getServer() != null) {
			for (Object playerObj : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
				if (playerObj instanceof EntityPlayer) {
					if (((EntityPlayer)playerObj).getUniqueID().equals(playerID))
						return isPlayerOP((EntityPlayer)playerObj);
				}
			}
		}

		return false;
	}

	public static boolean isPlayerOP(String playerName) {
		if (MinecraftServer.getServer() != null) {
			for (Object playerObj : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
				if (playerObj instanceof EntityPlayer) {
					if (((EntityPlayer)playerObj).getGameProfile().getName().equals(playerName))
						return isPlayerOP((EntityPlayer)playerObj);
				}
			}
		}

		return false;
	}

}
