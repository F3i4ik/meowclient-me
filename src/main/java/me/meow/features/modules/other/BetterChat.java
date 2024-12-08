package me.meow.features.modules.other;

import me.meow.features.modules.Module;
import me.meow.features.settings.Setting;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BetterChat extends Module {
    public Setting<Boolean> timestamp = this.register(new Setting<>("Timestamp", true));

    public BetterChat(){
        super("BetterChat","",Category.MISC,true,false,false);
    }




}
