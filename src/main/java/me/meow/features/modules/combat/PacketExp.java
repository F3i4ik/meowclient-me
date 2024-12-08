package me.meow.features.modules.combat;


import me.meow.Meow;
import me.meow.features.modules.Module;
import me.meow.features.settings.Setting;
import me.meow.util.player.FindItemResult;
import me.meow.util.player.InventoryUtil;
import net.minecraft.item.Items;

public class PacketExp extends Module {
    public Setting<Boolean> rotate = this.register(new Setting<>("Down", false));
    public PacketExp(){
        super("PacketExp","",Category.COMBAT,true,false,false);
    }

    @Override
    public void onTick() {
        FindItemResult exp = InventoryUtil.findInHotbar(Items.EXPERIENCE_BOTTLE);
        if (!exp.found()) return;
        if (rotate.getValue()) {
            Meow.rotationManager.setPlayerRotations(mc.player.getYaw(), 90);
        }
        if (exp.getHand() != null) {
            mc.interactionManager.interactItem(mc.player, exp.getHand());
        }
        else {
            InventoryUtil.swap(exp.slot(), true);
            mc.interactionManager.interactItem(mc.player, exp.getHand());
            InventoryUtil.swapBack();
        }
    }
}
