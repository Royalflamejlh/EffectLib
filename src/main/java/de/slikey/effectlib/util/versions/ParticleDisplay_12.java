package de.slikey.effectlib.util.versions;

import java.util.List;

import com.cryptomorin.xseries.particles.XParticle;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

import de.slikey.effectlib.util.ParticleDisplay;
import de.slikey.effectlib.util.ParticleOptions;

public class ParticleDisplay_12 extends ParticleDisplay {

    @Override
    @SuppressWarnings("deprecation")
    public void display(Particle particle, ParticleOptions options, Location center, double range, List<Player> targetPlayers) {
        // Legacy colorizeable particles
        Color color = options.color;
        if (color != null && (particle == XParticle.DUST.get() || particle == XParticle.ENTITY_EFFECT.get())) {
            displayLegacyColored(particle, options, center, range, targetPlayers);
            return;
        }

        if (particle == XParticle.ITEM.get()) {
            displayItem(particle, options, center, range, targetPlayers);
            return;
        }

        if (particle == XParticle.BLOCK.get() || particle.name().equals("FALLING_DUST")) {
            Material material = options.material;
            if (material == null || material.name().contains("AIR")) return;
            options.data = new MaterialData(material, options.materialData);
        }

        spawnParticle(particle, options, center, range, targetPlayers);
    }

}
