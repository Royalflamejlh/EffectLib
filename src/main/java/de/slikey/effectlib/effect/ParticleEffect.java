package de.slikey.effectlib.effect;

import com.cryptomorin.xseries.particles.XParticle;
import org.bukkit.Particle;

import de.slikey.effectlib.Effect;
import de.slikey.effectlib.EffectType;
import de.slikey.effectlib.EffectManager;

public class ParticleEffect extends Effect {

    public ParticleEffect(EffectManager effectManager) {
        super(effectManager);
        type = EffectType.REPEATING;
        particle = XParticle.ANGRY_VILLAGER.get();
        period = 1;
        iterations = 1;
    }

    @Override
    public void onRun() {
        display(particle, getLocation());
    }
}
