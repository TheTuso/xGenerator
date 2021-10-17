package pl.tuso.xgenerator.biome.util;

public class BlockPos {
    private static final int SIZE_BITS_X;
    private static final int SIZE_BITS_Z;
    public static final int SIZE_BITS_Y;
    private static final long BITS_X;
    private static final long BITS_Y;
    private static final long BITS_Z;
    private static final int BIT_SHIFT_Z;
    private static final int BIT_SHIFT_X;

    public static long asLong(int x, int y, int z) {
        long l = 0L;
        l |= ((long)x & BITS_X) << BIT_SHIFT_X;
        l |= ((long)y & BITS_Y) << 0;
        l |= ((long)z & BITS_Z) << BIT_SHIFT_Z;
        return l;
    }

    static {
        SIZE_BITS_X = 1 + MathHelper.log2(MathHelper.smallestEncompassingPowerOfTwo(30000000));
        SIZE_BITS_Z = SIZE_BITS_X;
        SIZE_BITS_Y = 64 - SIZE_BITS_X - SIZE_BITS_Z;
        BITS_X = (1L << SIZE_BITS_X) - 1L;
        BITS_Y = (1L << SIZE_BITS_Y) - 1L;
        BITS_Z = (1L << SIZE_BITS_Z) - 1L;
        BIT_SHIFT_Z = SIZE_BITS_Y;
        BIT_SHIFT_X = SIZE_BITS_Y + SIZE_BITS_Z;
    }
}
