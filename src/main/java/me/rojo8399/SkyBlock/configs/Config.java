package me.rojo8399.SkyBlock.configs;

import java.util.List;

import com.google.common.reflect.TypeToken;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class Config {
	public static TypeToken<Config> type = TypeToken.of(Config.class);
	@Setting public Island island;
	@ConfigSerializable
	public static class Island {
		@Setting public int distance;
		@Setting public int protectionRange;
		@Setting public boolean overridelimit;
		@Setting public boolean cleanupblocks;
		@Setting public int startx;
		@Setting public int startz;
		@Setting public int maxteamsize;
		@Setting public String chestItems;
		@Setting public String companion;
		@Setting public List<String> companionnames;
		@Setting public int minnamelength;
		@Setting public int maxnamelength;
		// Default Settings
		@Setting public boolean allowanviluse;
		@Setting public boolean allowarmorstanduse;
		@Setting public boolean allowbeaconaccess;
		@Setting public boolean allowbeduse;
		@Setting public boolean allowbreakblocks;
		@Setting public boolean allowbreeding;
		@Setting public boolean allowbrewing;
		@Setting public boolean allowbucketuse;
		@Setting public boolean allowchestaccess;
		@Setting public boolean allowcrafting;
		@Setting public boolean allowcroptrample;
		@Setting public boolean allowdooruse;
		@Setting public boolean allowenchanting;
		@Setting public boolean allowenderpearls;
		@Setting public boolean allowfurnaceuse;
		@Setting public boolean allowgateuse;
		@Setting public boolean allowhorseriding;
		@Setting public boolean allowhorseinventoryaccess;
		@Setting public boolean allowhurtmobs;
		@Setting public boolean allowleashuse;
		@Setting public boolean allowleverbuttonuse;
		@Setting public boolean allowmusic;
		@Setting public boolean allowplaceblocks;
		@Setting public boolean allowportaluse;
		@Setting public boolean allowpressureplates;
		@Setting public boolean allowPvP;
		@Setting public boolean allowNetherPvP;
		@Setting public boolean pvpcooldown;
		@Setting public boolean allowredstone;
		@Setting public boolean allowshearing;
		@Setting public boolean allowvillagertrading;
		@Setting public boolean allowchorusfruit;
		@Setting public boolean enablejoinandleaveislandmessages;
		@Setting public boolean allowmobspawning;
		// System Settings
		@Setting public boolean allowhurtmonsters;
		@Setting public boolean allowendermangriefing;
		@Setting public boolean endermandeathdrop;
		@Setting public boolean allowcreeperdamage;
		@Setting public boolean allowcreepergriefing;
		@Setting public boolean allowtntdamage;
		@Setting public boolean allowspawneggs;
		@Setting public boolean allowfire;
		@Setting public boolean allowfirespread;
		@Setting public boolean allowfireextinguish;
		@Setting public boolean allowchestdamage;
		@Setting public boolean allowvisitoritemdrop;
		@Setting public boolean allowvisitoritempickup;
		@Setting public boolean allowvisitorkeepinvondeath;
		@Setting public boolean allowpistonpush;
		@Setting public boolean allowitemframedamage;
	}
}
