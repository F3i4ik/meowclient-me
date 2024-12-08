package me.meow.features.modules.movement;


import me.meow.features.modules.Module;

public class Sprint extends Module {
    public Sprint(){
        super("Sprint","",Category.MOVEMENT,true,false,false);
    }
    @Override
    public void onTick() {
        if(nullCheck())
            return;
        mc.player.setSprinting(true);
    }
}
