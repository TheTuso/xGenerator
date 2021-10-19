package pl.tuso.xgenerator.biome.custombiome;

import com.mojang.serialization.Lifecycle;
import net.minecraft.core.IRegistry;
import net.minecraft.core.IRegistryWritable;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.world.level.biome.BiomeBase;
import net.minecraft.world.level.biome.BiomeFog;
import net.minecraft.world.level.biome.BiomeSettingsGeneration;
import net.minecraft.world.level.biome.BiomeSettingsMobs;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;

import java.lang.reflect.Field;

public class AutumnRed {

    public void generate() throws NoSuchFieldException, IllegalAccessException {
        Server server = Bukkit.getServer();
        CraftServer craftServer = (CraftServer)server;
        DedicatedServer dedicatedServer = craftServer.getServer();
        ResourceKey<BiomeBase> newKey = ResourceKey.a(IRegistry.aO, new MinecraftKey("experimental", "autumn_red"));

        ResourceKey<BiomeBase> oldKey = ResourceKey.a(IRegistry.aO, new MinecraftKey("minecraft", "forest"));
        IRegistryWritable<BiomeBase> registryWritable = dedicatedServer.getCustomRegistry().b(IRegistry.aO);
        BiomeBase forestBiome = registryWritable.a(oldKey);

        BiomeBase.a newBiome = new BiomeBase.a();
        newBiome.a(forestBiome.t());
        newBiome.a(forestBiome.c());

        Field biomeSettingMobsField = BiomeBase.class.getDeclaredField("m");
        biomeSettingMobsField.setAccessible(true);
        BiomeSettingsMobs biomeSettingsMobs = (BiomeSettingsMobs) biomeSettingMobsField.get(forestBiome);
        newBiome.a(biomeSettingsMobs);

        Field biomeSettingGenField = BiomeBase.class.getDeclaredField("l");
        biomeSettingGenField.setAccessible(true);
        BiomeSettingsGeneration biomeSettingsGeneration = (BiomeSettingsGeneration) biomeSettingGenField.get(forestBiome);
        newBiome.a(biomeSettingsGeneration);

        newBiome.a(0.2F);
        newBiome.b(0.05F);
        newBiome.c(0.7F);
        newBiome.d(0.8F);
        newBiome.a(BiomeBase.TemperatureModifier.a);

        BiomeFog.a newFog = new BiomeFog.a();
        newFog.a(BiomeFog.GrassColor.a);

        newFog.a(Integer.parseInt("C3E3EC", 16));//fogColor
        newFog.b(Integer.parseInt("C3E3EC", 16));//waterColor
        newFog.c(Integer.parseInt("206893", 16));//waterFogColor
        newFog.d(Integer.parseInt("73BDD3", 16));//skyColor
        newFog.e(Integer.parseInt("843A27", 16));//foliageColor
        newFog.f(Integer.parseInt("7A6A27", 16));//grassColor

        newBiome.a(newFog.a());

        dedicatedServer.getCustomRegistry().b(IRegistry.aO).a(newKey, newBiome.a(), Lifecycle.stable());
    }

}
