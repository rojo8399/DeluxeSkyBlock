package me.rojo8399.SkyBlock;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.asset.Asset;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.GameReloadEvent;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;

import me.rojo8399.SkyBlock.configs.Config;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

@Plugin(id = SkyBlock.PLUGIN_ID, name = SkyBlock.PLUGIN_NAME, version = SkyBlock.PLUGIN_NAME, authors = "rojo8399")
public class SkyBlock {

	public static final String PLUGIN_ID = "deluxeskyblock";
	public static final String PLUGIN_NAME = "Deluxe SkyBlock";
	public static final String PLUGIN_VERSION = "0.0.1";
	private static SkyBlock instance;

	@Inject
	private Logger logger;

	@Inject
	private Game game;

	@Inject
	@DefaultConfig(sharedRoot = false)
	Path path;
	@Inject
	@DefaultConfig(sharedRoot = false)
	ConfigurationLoader<CommentedConfigurationNode> loader;
	Config config;

	@Listener
	public void onPreInit(GamePreInitializationEvent e) throws IOException, ObjectMappingException {
		instance = this;

		// Make default file an asset
		Asset conf = game.getAssetManager().getAsset(this, "config.conf").get();
		// If there's no config yet, copy the default over.
		if (!Files.exists(path)) {
			try {
				conf.copyToFile(path);
			} catch (IOException ex) {
				logger.error("Could not copy the config file!");
				try {
					throw ex;
				} finally {
					mapDefault();
				}

			}
		}
		// Make configurationnode by loading the config
		ConfigurationNode root;
		try {
			root = loader.load();
		} catch (IOException ex) {
			logger.error("Could not load the config file!");
			try {
				throw ex;
			} finally {
				mapDefault();
			}
		}
		// Update config
		if (root.getNode("version").getInt() < 1) {
			try {
				root.mergeValuesFrom(loadDefault());
				root.getNode("version").setValue(1);
			} catch (IOException ex) {
				logger.error("Could not update config!");
				try {
					throw ex;
				} finally {
					mapDefault();
				}
			}
			try {
				loader.save(root);
			} catch (IOException ex) {
				logger.error("Could not save config!");
				try {
					throw ex;
				} finally {
					try {
						config = root.getValue(Config.type);
					} catch (ObjectMappingException ex2) {
						logger.error("Invalid config file!");
						try {
							throw ex;
						} finally {
							mapDefault();
						}
					}
				}
			}
		}
		// Set the config variable
		try {
			config = root.getValue(Config.type);
		} catch (ObjectMappingException ex) {
			logger.error("Invalid config file!");
			try {
				throw ex;
			} finally {
				mapDefault();
			}
		}
	}

	@Listener
	public void onGameInitializationEvent(GameInitializationEvent event) {
		// TODO Commands
	}

	@Listener
	public void onReload(GameReloadEvent e) throws IOException, ObjectMappingException {
		try {
			config = loader.load().getValue(Config.type);
		} catch (IOException ex) {
			logger.error("Could not reload config!");
			throw ex;
		} catch (ObjectMappingException ex) {
			logger.error("Invalid Config!");
			throw ex;
		}
	}

	private void mapDefault() throws IOException, ObjectMappingException {
		try {
			config = loadDefault().getValue(Config.type);
		} catch (IOException | ObjectMappingException ex) {
			logger.error("Could not load the embedded default config! Disabling plugin.");
			game.getEventManager().unregisterPluginListeners(this);
			throw ex;
		}
	}

	private ConfigurationNode loadDefault() throws IOException {
		return HoconConfigurationLoader.builder()
				.setURL(game.getAssetManager().getAsset(this, "config.conf").get().getUrl()).build()
				.load(loader.getDefaultOptions());
	}

	public Logger getLogger() {
		return logger;
	}

	public Game getGame() {
		return game;
	}

	public static SkyBlock getInstance() {
		return instance;
	}

}
