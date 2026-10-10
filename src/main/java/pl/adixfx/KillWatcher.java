package pl.adixfx;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/** Wykrywa zabicie gracza, którego uderzyłeś, i puszcza losowy utwór z playlisty. */
public final class KillWatcher {
    private static final class Hit { int tick; float health; }

    private static final Map<Integer, Hit> HITS = new HashMap<>();
    private static int tick = 0;

    private KillWatcher() {}

    public static void onAttack(Entity target) {
        if (!(target instanceof PlayerEntity p)) return;
        Hit h = HITS.computeIfAbsent(target.getId(), k -> new Hit());
        h.tick = tick;
        h.health = p.getHealth();
    }

    public static void tick(MinecraftClient mc) {
        tick++;
        if (mc.world == null || mc.player == null || !Config.d.music || !Config.d.onKill) {
            HITS.clear();
            return;
        }
        Iterator<Map.Entry<Integer, Hit>> it = HITS.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Hit> e = it.next();
            Hit h = e.getValue();
            Entity ent = mc.world.getEntityById(e.getKey());
            if (ent instanceof PlayerEntity pl && !pl.isRemoved()) {
                h.health = pl.getHealth();
                if (pl.isDead() || pl.getHealth() <= 0f) {
                    Music.playRandom(false);
                    it.remove();
                    continue;
                }
            } else if (tick - h.tick <= 40 && h.health <= 6f) {
                Music.playRandom(false);
                it.remove();
                continue;
            }
            if (tick - h.tick > 100) it.remove();
        }
    }
}
