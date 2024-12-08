package me.meow.features.modules.misc;


import me.meow.features.modules.Module;
import me.meow.features.settings.Setting;

public class AntiAFK extends Module {
    public enum Mode{
        Jump, Sneak, Both
    }
    public Setting<Mode> mode = this.register(new Setting<>("Mode", Mode.Both));


    public AntiAFK(){
        super("AntiAFK","",Category.MISC,true,false,false);
    }
    @Override
    public void onUpdate() {

        if (mode.getValue() == Mode.Sneak) {
            if (nullCheck())
                return;
            mc.options.sneakKey.setPressed(true);
        }
        if (mode.getValue() == Mode.Jump) {
            if (nullCheck())
                return;
            mc.options.jumpKey.setPressed(true);
        }
        if (mode.getValue() == Mode.Both) {
            if (nullCheck())
                return;
            mc.options.sneakKey.setPressed(true);
            mc.options.jumpKey.setPressed(true);
        }
    }
}
