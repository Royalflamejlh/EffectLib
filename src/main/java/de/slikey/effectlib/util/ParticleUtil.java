package de.slikey.effectlib.util;

import java.util.Map;
import java.util.HashMap;

import org.bukkit.Particle;

public enum ParticleUtil {

	EXPLOSION_NORMAL("explosion_normal", "poof", "explode"),
	EXPLOSION_LARGE("explosion_large", "explosion", "largeexplode"),
	EXPLOSION_HUGE("explosion_huge", "explosion_emitter", "hugeexplosion"),
	FIREWORKS_SPARK("fireworks_spark", "firework", "fireworksspark"),
	WATER_BUBBLE("water_bubble", "bubble"),
	WATER_SPLASH("water_splash", "splash"),
	WATER_WAKE("water_wake", "fishing", "wake"),
	SUSPENDED("suspended", "underwater"),
	SUSPENDED_DEPTH("suspended_depth", "underwater", "depthsuspend"),
	CRIT("crit"),
	CRIT_MAGIC("crit_magic", "enchanted_hit", "magiccrit"),
	SMOKE_NORMAL("smoke_normal", "smoke"),
	SMOKE_LARGE("smoke_large", "large_smoke", "largesmoke"),
	SPELL("spell", "effect"),
	SPELL_INSTANT("spell_instant", "instant_effect", "instantspell"),
	SPELL_MOB("spell_mob", "entity_effect", "mobspell"),
	SPELL_MOB_AMBIENT("spell_mob_ambient", "ambient_entity_effect", "mobspellambient", "entity_effect"),
	SPELL_WITCH("spell_witch", "witch", "witchmagic"),
	DRIP_WATER("drip_water", "dripping_water", "dripwater"),
	DRIP_LAVA("drip_lava", "dripping_lava", "driplava"),
	VILLAGER_ANGRY("villager_angry", "angry_villager", "angryvillager"),
	VILLAGER_HAPPY("villager_happy", "happy_villager", "happyvillager"),
	TOWN_AURA("town_aura", "mycelium", "townaura"),
	ENCHANTMENT_TABLE("enchantment_table", "enchant", "enchantmenttable"),
	REDSTONE("redstone", "dust", "reddust"),
	SNOWBALL("snowball", "item_snowball", "snowballpoof"),
	SNOW_SHOVEL("snow_shovel", "item_snowball", "snowshovel"),
	SLIME("slime", "item_slime"),
	ITEM_CRACK("item_crack", "item", "iconcrack"),
	BLOCK_CRACK("block_crack", "blockcrack", "block"),
	BLOCK_DUST("block_dust", "blockdust", "block"),
	WATER_DROP("water_drop", "rain", "droplet"),
	MOB_APPEARANCE("mob_appearance", "elder_guardian", "mobappearance"),
	TOTEM("totem", "totem_of_undying"),
	GUST_EMITTER("gust_emitter", "gust_emitter_large"),
	GUST_DUST("gust_dust", "block"),
	DRAGON_BREATH("dragon_breath", "dragonbreath"),
	END_ROD("end_rod", "endrod"),
	DAMAGE_INDICATOR("damage_indicator", "damageindicator"),
	SWEEP_ATTACK("sweep_attack", "sweepattack"),
	FALLING_DUST("falling_dust", "fallingdust");

	private static final Map<String, Particle> namesToType = new HashMap<>();
	private static boolean initialized = false;

	private final String[] names;

	ParticleUtil(String... names) {
		this.names = names;
	}

	private static void initialize() {
		if (initialized) return;

		for (ParticleUtil p : ParticleUtil.values()) {
			Particle particle = null;

			try {
				particle = Particle.valueOf(p.name());
			} catch (Exception e) {
				// ignored
			}

			if (particle == null) continue;

			// handle the names
			namesToType.put(p.name().toLowerCase(), particle);
			for (String s : p.names) {
				namesToType.put(s.toLowerCase(), particle);
			}
		}

		initialized = true;
	}

	public static Particle getParticle(String particleName) {
		initialize();

		Particle particle = namesToType.get(particleName.toLowerCase());
		if (particle != null) return particle;

		try {
			particle = Particle.valueOf(particleName.toUpperCase());
		} catch (IllegalArgumentException ignored) {}

		return particle;
	}
}
