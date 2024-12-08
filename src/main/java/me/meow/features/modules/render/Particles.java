package me.meow.features.modules.render;

import me.meow.event.eventbus.EventHandler;
import me.meow.event.impl.ParticleEvent;
import me.meow.event.impl.Render3DEvent;
import me.meow.features.modules.Module;
import me.meow.features.settings.Setting;
import net.minecraft.client.particle.*;

public class Particles extends Module {
    public Setting<Boolean> elderGuardian = this.register(new Setting<>("Guardian", false));
    public Setting<Boolean> campFire = this.register(new Setting<>("CampFire", false));
    public Setting<Boolean> fireworks = this.register(new Setting<>("FireWorks", false));
    public Setting<Boolean> effect = this.register(new Setting<>("Effects", false));
    public Setting<Boolean> portal = this.register(new Setting<>("Portal", false));
    public Setting<Boolean> totem = this.register(new Setting<>("Totem", false));
    public Particles(){
        super("Particles","",Category.RENDER,true,false,false);
    }

    @EventHandler
    public void onParticle(ParticleEvent.AddParticle event) {
            if (elderGuardian.getValue() && event.particle instanceof ElderGuardianAppearanceParticle) {
                event.setCancelled(true);
            } else if (campFire.getValue() && event.particle instanceof CampfireSmokeParticle) {
                event.setCancelled(true);
            } else if (totem.getValue() && event.particle instanceof TotemParticle) {
                event.cancel();
            } else if (fireworks.getValue() && (event.particle instanceof FireworksSparkParticle.FireworkParticle || event.particle instanceof FireworksSparkParticle.Flash)) {
                event.setCancelled(true);
            } else if (effect.getValue() && event.particle instanceof SpellParticle) {
                event.cancel();
            }
             else if (portal.getValue() && event.particle instanceof PortalParticle) {
            event.cancel();
            }
        }

}
