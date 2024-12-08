package me.meow.features.modules.misc;

import com.google.common.eventbus.Subscribe;
import me.meow.features.modules.Module;
import me.meow.features.settings.Setting;
import me.meow.util.math.MathUtil;
import me.meow.util.models.Timer;
import net.minecraft.entity.player.PlayerEntity;

import java.text.DecimalFormat;
import java.util.Random;


public class Announcer extends Module {
    public Setting<Boolean> move = this.register(new Setting<>("Move", true));
    public Setting<Double> delay = this.register(new Setting<>("Delay", 10d, 1d, 30d));

    private double lastPositionX;
    private double lastPositionY;
    private double lastPositionZ;

    private int eaten;

    private int broken;

    private final Timer delayTimer = new Timer();

    public Announcer() {
        super("Announcer", "", Category.MISC, true, false, false);
    }

    @Override
    public void onEnable() {
        eaten = 0;
        broken = 0;

        delayTimer.reset();
    }

    @Override
    public void onUpdate() {
        
        double traveledX = lastPositionX - mc.player.lastRenderX;
        double traveledY = lastPositionY - mc.player.lastRenderY;
        double traveledZ = lastPositionZ - mc.player.lastRenderZ;

        double traveledDistance = Math.sqrt(traveledX * traveledX + traveledY * traveledY + traveledZ * traveledZ);

        if (move.getValue()
                && traveledDistance >= 1
                && traveledDistance <= 1000
                && delayTimer.passedS(delay.getValue())) {

            mc.player.networkHandler.sendChatMessage(getWalkMessage()
                    .replace("{blocks}", new DecimalFormat("0.00").format(traveledDistance)));

            lastPositionX = mc.player.lastRenderX;
            lastPositionY = mc.player.lastRenderY;
            lastPositionZ = mc.player.lastRenderZ;

            delayTimer.reset();
        }// poprobyi zdes
    }

    @Subscribe
    public void onUseItem(PlayerEntity event) {

        int random = MathUtil.getRandom(1, 6);

        //if (eat.getValue())
        //&& event.isPlayer() = mc.player
        // && event.getActiveItem()
        //|| event.getHandItems() instanceof Items.ENCHANTED_GOLDEN_APPLE)
        {

            ++eaten;

            if (eaten >= random && delayTimer.passedS(delay.getValue())) {

                mc.player.networkHandler.sendChatMessage(getEatMessage()
                        .replace("{amount}", "" + eaten)
                        .replace("{name}", "" + event.getActiveItem().getName()));

                eaten = 0;

                delayTimer.reset();
            }
        }
    }
/*
    @Subscribe
    public void onBreakBlock(BlockBreakingInfo event) {

        int random = MathUtil.getRandom(1, 6);

        ++broken;

        if (breakBlock.getValue()
                && broken >= random
                && delayTimer.passedS(delay.getValue())) {

            mc.player.networkHandler.sendChatMessage(getBreakMessage()
                    .replace("{amount}", "" + broken)
                    .replace("{name}", "" + event.getPos()));

            broken = 0;

            delayTimer.reset();
        }
    }

 */



        private String getWalkMessage(){
        String[] walkMessage = {
                "I just flew over {blocks} blocks thanks to meowclient.me!",
                "Я только что пролетел над {blocks} блоками с помощью meowclient.me!",
                "meowclient.me sayesinde {blocks} blok u\u00E7tum!",
                "\u6211\u521A\u521A\u7528 meowclient.me \u8D70\u4E86 {blocks} \u7C73!",
                "Dank meowclient.me bin ich gerade über {blocks} Blöcke geflogen!",
                "Jag hoppade precis över {blocks} blocks tack vare meowclient.me!",
                "Właśnie przeleciałem ponad {blocks} bloki dzięki meowclient.me!",
                "Es tikko nolidoju {blocks} blokus, paldies meowclient.me!",
                "Я щойно пролетів як моль над {blocks} блоками завдяки meowclient.me!",
                "I just fwew ovew {blocks} bwoccs thanks to meowclient.me",
                "Ho appena camminato per {blocks} blocchi grazie a meowclient.me!",
                "עכשיו עפתי {blocks} הודות ל meowclient.me!",
                "Právě jsem proletěl {blocks} bloků díky meowclient.me!"
        };

        return walkMessage[new Random().nextInt(walkMessage.length)];
    }

    private String getBreakMessage() {

        String[] breakMessage = {
                "I just destroyed {amount} {name} with the power of meowclient.me!",
                "Я только что разрушил {amount} {name} с помощью meowclient.me!",
                "Az \u00F6nce {amount} tane {name} k\u0131rd\u0131m. Te\u015Eekk\u00FCrler meowclient.me!",
                "\u6211\u521A\u521A\u7528 meowclient.me \u7834\u574F\u4E86 {amount} {name}!",
                "Ich habe gerade {amount} {name} mit der Kraft von meowclient.me zerstört!",
                "Jag förstörde precis {amount} {name} tack vare meowclient.me!",
                "Właśnie zniszczyłem {amount} {name} za pomocą meowclient.me",
                "Es tikko salauzu {amount} {name} ar spēku meowclient.me!",
                "Я щойно знищив {amount} {name} за допомогою meowclient.me!",
                "I just destwoyed {amount} {name} with the powew of meowclient.me!",
                "Ho appena distrutto {amount} {name} grazie al potere di meowclient.me!",
                "בדיוק חצבתי {amount} {name} בעזרת הכוח של meowclient.me!",
                "Právě jsem zničil {amount} {name} s mocí meowclient.me!"
        };

        return breakMessage[new Random().nextInt(breakMessage.length)];
    }

    private String getEatMessage() {

        String[] eatMessage = {
                "I just ate {amount} {name} thanks to meowclient.me!",
                "Я только что съел {amount} {name} с помощью meowclient.me!",
                "Tam olarak {amount} tane {name} yedim. Te\u015Eekk\u00FCrler meowclient.me",
                "\u6211\u521A\u7528 meowclient.me \u5403\u4E86 {amount} \u4E2A {name}!",
                "Ich habe gerade {amount} {name} dank meowclient.me gegessen!",
                "Jag åt precis {amount} {name} tack vare meowclient.me",
                "Właśnie zjadłem {amount} {name} dzięki meowclient.me",
                "Es tikko apēdu {amount} {name} paldies meowclient.me",
                "Я щойно з’їв {amount} {name} завдяки meowclient.me!",
                "I just ate {amount} {name} thanks to meowclient.me! ^-^",
                "Ho appena mangiato {amount} {name} grazie a meowclient.me!",
                "כרגע אכלתי {amount} {name} הודות לmeowclient.me!" ,
                "Právě jsem snědl {amount} {name} díky meowclient.me"
        };

        return eatMessage[new Random().nextInt(eatMessage.length)];
    }
}