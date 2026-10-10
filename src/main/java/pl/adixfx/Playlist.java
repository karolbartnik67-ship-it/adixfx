package pl.adixfx;

import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Lista utworów. Żeby dodać nowy: wrzuć plik .ogg do assets/adixfx/sounds/,
 * dopisz go w sounds.json i dodaj wpis poniżej.
 */
public final class Playlist {
    public record Track(String id, String name, Identifier sound) {}

    public static final List<Track> TRACKS = List.<Track>of(
            // Tu dopisuj utwory, np.:
            // new Track("moj_utwor", "Mój utwór", Identifier.of("adixfx", "moj_utwor"))
    );

    private Playlist() {}

    /** nazwa wyświetlana (własna, jeśli ją zmieniłeś) */
    public static String displayName(Track t) {
        String n = Config.d.names.get(t.id());
        return n == null || n.isBlank() ? t.name() : n;
    }

    public static void setName(Track t, String name) {
        if (name == null || name.isBlank() || name.equals(t.name())) Config.d.names.remove(t.id());
        else Config.d.names.put(t.id(), name.trim());
        Config.save();
    }

    /** głośność utworu 0..2 (domyślnie 1 = 100%) */
    public static float volume(Track t) {
        return Config.d.trackVolume.getOrDefault(t.id(), 1.0f);
    }

    public static void setVolume(Track t, float v) {
        Config.d.trackVolume.put(t.id(), Math.max(0f, Math.min(2f, v)));
    }

    public static boolean enabled(Track t) {
        return Config.d.tracks.getOrDefault(t.id(), true);
    }

    public static void setEnabled(Track t, boolean v) {
        Config.d.tracks.put(t.id(), v);
        Config.save();
    }

    public static List<Track> enabledTracks() {
        List<Track> out = new ArrayList<>();
        for (Track t : TRACKS) if (enabled(t)) out.add(t);
        return out;
    }
}
