package pl.tuso.xgenerator.biome;

import pl.tuso.xgenerator.biome.handler.*;

public enum Biomes {
    //id 0 is Ocean soon
    PLAINS(new PlainsHandler(), 1),
    DESERT(new DesertHandler(), 2),
    TAIGA(new TaigaHandler(), 3),
    FOREST(new ForestHandler(), 4),
    SWAMP(new SwampHandler(), 5),
    RIVER(new RiverHandler(), 6),
    JUNGLE(new JungleHandler(), 7),
    SAVANNA(new SavannaHandler(), 8),
    MESSA(new MessaHandler(), 9),
    AUTUMN_FOREST(new AutumnForestHandler(), 10);

    private final BiomeHandler handler;
    private final int id;

    Biomes(BiomeHandler handler, int id) {
        this.handler = handler;
        this.id = id;
    }

    public static Biomes getBiomeById(int id) {
        for (Biomes biome : values()) {
            if (biome.getId() == id) {
                return biome;
            }
        }
        return Biomes.PLAINS;
    }

    public BiomeHandler getHandler() {
        return this.handler;
    }

    public int getId() {
        return this.id;
    }
}
