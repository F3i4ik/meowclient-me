package me.meow.features.modules.misc;



import me.meow.Meow;
import me.meow.features.commands.Command;
import me.meow.features.modules.Module;
import me.meow.features.settings.Setting;
import me.meow.util.player.EntityUtil;
import me.meow.util.traits.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import org.lwjgl.glfw.GLFW;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

import static me.meow.util.player.InventoryUtil2.doSwap;
import static me.meow.util.player.InventoryUtil2.findItem;

public class MiddleClick extends Module {
    public static MiddleClick INSTANCE;
    private boolean pressed;
    boolean click = false;

    public Setting<Boolean> friend = this.register(new Setting<>("Friend", false));
    public Setting<Boolean> pearl = this.register(new Setting<>("Pearl", false));
    public Setting<Boolean> inventory = this.register(new Setting<>("InventorySwap", true,v ->this.pearl.getValue()));
    public MiddleClick() {
        super("MiddleClick", "", Category.MISC, true, false, false);
        INSTANCE = this;
    }

    @Override public void onTick() {
        if (friend.getValue() == true) {
            if (GLFW.glfwGetMouseButton(Util.mc.getWindow().getHandle(), 2) == 1) {
                if (!pressed) {
                    Entity targetedEntity = Util.mc.targetedEntity;
                    if (!(targetedEntity instanceof PlayerEntity)) return;
                    String name = ((PlayerEntity) targetedEntity).getGameProfile().getName();

                    if (Meow.friendManager.isFriend(name)) {
                        Meow.friendManager.removeFriend(name);
                        Command.sendMessage(Formatting.RED + name + Formatting.RED + " has been unfriended.");
                    } else {
                        Meow.friendManager.addFriend(name);
                        Command.sendMessage(Formatting.AQUA + name + Formatting.AQUA + " has been friended.");
                    }

                    pressed = true;
                }
            } else {
                pressed = false;
            }
        }

            if (pearl.getValue() == true) {
            if (nullCheck()) return;
            if (mc.mouse.wasMiddleButtonClicked()) {
                if (!click) {
                    int pearl;
                    if (mc.player.getMainHandStack().getItem() == Items.ENDER_PEARL) {
                        EntityUtil.sendLook(new PlayerMoveC2SPacket.LookAndOnGround(mc.player.getYaw(), mc.player.getPitch(), mc.player.isOnGround()));
                        mc.player.networkHandler.sendPacket(new PlayerInteractItemC2SPacket(Hand.MAIN_HAND, 0,mc.player.getYaw(),mc.player.getPitch()));
                    } else if ((pearl = findItem(Items.ENDER_PEARL)) != -1) {
                        int old = mc.player.getInventory().selectedSlot;
                        doSwap(pearl);
                        EntityUtil.sendLook(new PlayerMoveC2SPacket.LookAndOnGround(mc.player.getYaw(), mc.player.getPitch(), mc.player.isOnGround()));
                        mc.player.networkHandler.sendPacket(new PlayerInteractItemC2SPacket(Hand.MAIN_HAND, 0,mc.player.getYaw(),mc.player.getPitch()));
                        if (inventory.getValue()) {
                            doSwap(pearl);
                            EntityUtil.syncInventory();
                        } else {
                            doSwap(old);
                        }

                    }
                    click = true;
                }
            } else click = false;
        }
    }
}
