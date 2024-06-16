package de.slikey.effectlib.util.versions;

import java.util.List;

import com.cryptomorin.xseries.particles.XParticle;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Vibration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import de.slikey.effectlib.util.ParticleOptions;

public class ParticleDisplay_17 extends ParticleDisplay_13 {

    @Override
    public void display(Particle particle, ParticleOptions options, Location center, double range, List<Player> targetPlayers) {
        // Legacy colorizeable particles
        if (options.color != null && (particle == XParticle.ENTITY_EFFECT.get())) {
            displayLegacyColored(particle, options, center, range, targetPlayers);
            return;
        }

        if (particle == XParticle.ITEM.get()) {
            displayItem(particle, options, center, range, targetPlayers);
            return;
        }

        if (particle == XParticle.BLOCK.get() || particle == XParticle.FALLING_DUST.get()) {
            Material material = options.material;
            if (material == null || material.name().contains("AIR")) return;
            try {
                options.data = material.createBlockData();
            } catch (Exception ex) {
                manager.onError("Error creating block data for " + material, ex);
            }
            if (options.data == null) return;
        }

        if (particle == XParticle.DUST.get()) {
            // color is required
            if (options.color == null) options.color = Color.RED;
            options.data = new Particle.DustOptions(options.color, options.size);
        }

        if (particle == XParticle.DUST_COLOR_TRANSITION.get()) {
            if (options.color == null) options.color = Color.RED;
            if (options.toColor == null) options.toColor = options.color;
            options.data = new Particle.DustTransition(options.color, options.toColor, options.size);
        }

        if (particle == XParticle.VIBRATION.get()) {
            if (options.target == null) return;

            Vibration.Destination destination;
            Entity targetEntity = options.target.getEntity();
            if (targetEntity != null) destination = new Vibration.Destination.EntityDestination(targetEntity);
            else {
                Location targetLocation = options.target.getLocation();
                if (targetLocation == null) return;

                destination = new Vibration.Destination.BlockDestination(targetLocation);
            }

            options.data = new Vibration(center, destination, options.arrivalTime);
        }

        spawnParticle(particle, options, center, range, targetPlayers);
    }

}
