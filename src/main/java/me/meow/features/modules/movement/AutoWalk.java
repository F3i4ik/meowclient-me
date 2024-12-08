package me.meow.features.modules.movement;

import me.meow.features.modules.Module;

public class AutoWalk extends Module {
    public AutoWalk(){
        super("AutoWalk","",Category.MOVEMENT,true,false,false);
    }
    public void onTick() {
        if (nullCheck())
            return;
        mc.options.forwardKey.setPressed(true);
    }
}
