package me.meow;

import me.meow.event.eventbus.EventBus;
import me.meow.event.eventbus.EventHandler;
import me.meow.manager.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Meow implements ModInitializer, ClientModInitializer {
    public static final String NAME = "Meow";
    public static final String VERSION = " beta";
    public static final EventBus EVENT_BUS = new EventBus();
    public static EventHandler EVENT_HANDLER;

    public static float TIMER = 1f;
    public static float TICK_TIMER = 1f;

    public static final Logger LOGGER = LogManager.getLogger("Meow");
    public static ServerManager serverManager;
    public static ColorManager colorManager;
    public static RotationManager rotationManager;
    public static PositionManager positionManager;
    public static HoleManager holeManager;
    public static EventManager eventManager;
    public static SpeedManager speedManager;
    public static CommandManager commandManager;
    public static FriendManager friendManager;
    public static ModuleManager moduleManager;
    public static ConfigManager configManager;


    @Override public void onInitialize() {
        eventManager = new EventManager();
        serverManager = new ServerManager();
        rotationManager = new RotationManager();
        positionManager = new PositionManager();
        friendManager = new FriendManager();
        colorManager = new ColorManager();
        commandManager = new CommandManager();
        moduleManager = new ModuleManager();
        speedManager = new SpeedManager();
        holeManager = new HoleManager();
    }


    @Override public void onInitializeClient() {
        eventManager.init();
        moduleManager.init();

        configManager = new ConfigManager();
        configManager.load();
        colorManager.init();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> configManager.save()));
    }
}
