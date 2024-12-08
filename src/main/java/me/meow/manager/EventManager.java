package me.meow.manager;

import com.google.common.eventbus.Subscribe;
import me.meow.Meow;
import me.meow.event.Stage;

import me.meow.features.Feature;
import me.meow.features.commands.Command;
import me.meow.util.models.Timer;
import me.meow.event.impl.*;
import me.meow.util.traits.Util;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import net.minecraft.util.Formatting;

public class EventManager extends Feature {
    private final Timer logoutTimer = new Timer();

    public void init() {
        Util.EVENT_BUS.register(this);
    }

    public void onUnload() {
        Util.EVENT_BUS.unregister(this);
    }

    @Subscribe
    public void onUpdate(UpdateEvent event) {
        Util.mc.getWindow().setTitle("meowclient.me");
        if (!fullNullCheck()) {
//            OyVey.inventoryManager.update();
            Meow.moduleManager.onUpdate();
            Meow.moduleManager.sortModules(true);
            onTick();
//            if ((HUD.getInstance()).renderingMode.getValue() == HUD.RenderingMode.Length) {
//                OyVey.moduleManager.sortModules(true);
//            } else {
//                OyVey.moduleManager.sortModulesABC();
//            }
        }
    }

    public void onTick() {
        if (fullNullCheck())
            return;
        Meow.moduleManager.onTick();
        for (PlayerEntity player : Util.mc.world.getPlayers()) {
            if (player == null || player.getHealth() > 0.0F)
                continue;
            Util.EVENT_BUS.post(new DeathEvent(player));
//            PopCounter.getInstance().onDeath(player);
        }
    }

    @Subscribe
    public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent event) {
        if (fullNullCheck())
            return;
        if (event.getStage() == Stage.PRE) {
            Meow.speedManager.updateValues();
            Meow.rotationManager.updateRotations();
            Meow.positionManager.updatePosition();
        }
        if (event.getStage() == Stage.POST) {
            Meow.rotationManager.restoreRotations();
            Meow.positionManager.restorePosition();
        }
    }

    @Subscribe
    public void onPacketReceive(PacketEvent.Receive event) {
        Meow.serverManager.onPacketReceived();
        if (event.getPacket() instanceof WorldTimeUpdateS2CPacket)
            Meow.serverManager.update();
    }

    @Subscribe
    public void onWorldRender(Render3DEvent event) {
        Meow.moduleManager.onRender3D(event);
    }

    @Subscribe public void onRenderGameOverlayEvent(Render2DEvent event) {
        Meow.moduleManager.onRender2D(event);
    }

    @Subscribe public void onKeyInput(KeyEvent event) {
        Meow.moduleManager.onKeyPressed(event.getKey());
    }

    @Subscribe public void onChatSent(ChatEvent event) {
        if (event.getMessage().startsWith(Command.getCommandPrefix())) {
            event.cancel();
            try {
                if (event.getMessage().length() > 1) {
                    Meow.commandManager.executeCommand(event.getMessage().substring(Command.getCommandPrefix().length() - 1));
                } else {
                    Command.sendMessage("Please enter a command.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Command.sendMessage(Formatting.RED + "An error occurred while running this command. Check the log!");
            }
        }
    }
}