package pl.tuso.xgenerator;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.tuso.xgenerator.biome.custombiome.AutumnRed;

public final class XGenerator extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        AutumnRed autumnRed = new AutumnRed();
        try {
            autumnRed.generate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public @Nullable ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, @Nullable String id) {
        return new XDevChunkGenerator();
    }
}
