package ca.uberifix.functionalaesthetics.common.blocks;

import net.minecraft.util.IStringSerializable;

/**
 * Created by uberifix
 */
public class BlockVariants {
    public static enum EnumWoodVariant implements IStringSerializable {
        OAK(0, "oak"),
        SPRUCE(1, "spruce"),
        BIRCH(2, "birch"),
        JUNGLE(3, "jungle"),
        ACACIA(4, "acacia"),
        DARK_OAK(5, "dark_oak");

        private static final BlockVariants.EnumWoodVariant[] META_LOOKUP = new BlockVariants.EnumWoodVariant[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;

        private EnumWoodVariant(int metaIn, String nameIn) { this(metaIn, nameIn, nameIn); }

        private EnumWoodVariant(int metaIn, String nameIn, String unlocalizedNameIn) {
            this.meta = metaIn;
            this.name = nameIn;
            this.unlocalizedName = unlocalizedNameIn;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public String toString()
        {
            return this.name;
        }

        public static BlockVariants.EnumWoodVariant byMetadata(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName()
        {
            return this.name;
        }

        public String getUnlocalizedName()
        {
            return this.unlocalizedName;
        }

        static {
            for (BlockVariants.EnumWoodVariant blockplanks$enumtype : values())
            {
                META_LOOKUP[blockplanks$enumtype.getMetadata()] = blockplanks$enumtype;
            }
        }
    }
}
