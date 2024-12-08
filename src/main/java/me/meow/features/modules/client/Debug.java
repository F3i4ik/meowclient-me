package me.meow.features.modules.client;

import me.meow.event.eventbus.EventHandler;
import me.meow.event.impl.Render3DEvent;
import me.meow.features.modules.Module;
import me.meow.features.modules.movement.Sprint;
import me.meow.features.settings.Setting;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;


public class Debug extends Module {
    public static Debug INSTANCE;

    public enum ClientMode {
        Meow, Meowlegacy, Gondal, Setstantium, Burger, Off
    }
    public enum NotifyMode {
        Mio, Miolegacy, Meow, Meowlegacy, Future, SetStantium, Off
    }
    //public Setting<ClientMode> client = this.register(new Setting<>("Client", ClientMode.Meow));
    public Setting<NotifyMode> notification = this.register(new Setting<>("Notify", NotifyMode.Meow));
    public Debug(){
        super("Debug","",Category.CLIENT,true,false,false);
        INSTANCE = this;
    }
}
