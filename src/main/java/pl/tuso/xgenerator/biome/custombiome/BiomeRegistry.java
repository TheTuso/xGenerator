package pl.tuso.xgenerator.biome.custombiome;

import net.minecraft.core.BlockPosition;
import net.minecraft.core.IRegistry;
import net.minecraft.core.IRegistryWritable;
import net.minecraft.network.protocol.game.PacketPlayOutMapChunk;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.world.level.World;
import net.minecraft.world.level.biome.BiomeBase;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.craftbukkit.v1_17_R1.CraftChunk;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class BiomeRegistry {
    public boolean setBiome(String newBiomeName, Location l, int x, int y, int z) {

        Server server = Bukkit.getServer();
        CraftServer craftServer = (CraftServer)server;
        DedicatedServer dedicatedServer = craftServer.getServer();

        BiomeBase base;
        IRegistryWritable<BiomeBase> registrywritable = dedicatedServer.getCustomRegistry().b(IRegistry.aO);

        ResourceKey<BiomeBase> rkey = ResourceKey.a(IRegistry.aO, new MinecraftKey(newBiomeName.toLowerCase()));
        base = registrywritable.a(rkey);
        if(base == null) {
            if(newBiomeName.contains(":")) {
                ResourceKey<BiomeBase> newrkey = ResourceKey.a(IRegistry.aO, new MinecraftKey(newBiomeName.split(":")[0].toLowerCase(), newBiomeName.split(":")[1].toLowerCase()));
                base = registrywritable.a(newrkey);
                if(base == null) {
                    return false;
                }
            } else {
                return false;
            }
        }

        setBiome(x, y, z, ((CraftWorld)l.getWorld()).getHandle(), base);
        refreshChunksForAll(l.getChunk());
        return true;
    }

    private void setBiome(int x, int y, int z, World w, BiomeBase bb) {
        BlockPosition pos = new BlockPosition(x, 0, z);

        if (w.isLoaded(pos)) {

            net.minecraft.world.level.chunk.Chunk chunk = w.getChunkAtWorldCoords(pos);
            if (chunk != null) {

                chunk.getBiomeIndex().setBiome(x >> 2, y >> 2, z >> 2, bb);
                chunk.markDirty();
            }
        }
    }

    private void refreshChunksForAll(Chunk chunk) {
        net.minecraft.world.level.chunk.Chunk c = ((CraftChunk)chunk).getHandle();
        for (Player player : chunk.getWorld().getPlayers()) {
            if (player.isOnline()) {
                if((player.getLocation().distance(chunk.getBlock(0, 0, 0).getLocation()) < (Bukkit.getServer().getViewDistance() * 16))) {
                    ((CraftPlayer) player).getHandle().b.sendPacket(new PacketPlayOutMapChunk(c));
                }
            }
        }
    }
}
