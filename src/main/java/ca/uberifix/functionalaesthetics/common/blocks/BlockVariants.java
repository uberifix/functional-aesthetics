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

        private EnumWoodVariant(int metaIn, String nameIn)
        {
            this(metaIn, nameIn, nameIn);
        }

        private EnumWoodVariant(int metaIn, String nameIn, String unlocalizedNameIn)
        {
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

        public static BlockVariants.EnumWoodVariant byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName() { return this.name; }

        public String getUnlocalizedName() { return this.unlocalizedName; }

        static
        {
            for (BlockVariants.EnumWoodVariant blockvariant$enumtype : values())
            {
                META_LOOKUP[blockvariant$enumtype.getMetadata()] = blockvariant$enumtype;
            }
        }
    }
    public static enum EnumStoneVariant implements IStringSerializable {
        STONE(0, "stone"),
        DIORITE(1, "diorite"),
        ANDESITE(2, "andesite"),
        GRANITE(3, "granite");

        private static final EnumStoneVariant[] META_LOOKUP = new EnumStoneVariant[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;

        private EnumStoneVariant(int metaIn, String nameIn)
        {
            this(metaIn, nameIn, nameIn);
        }

        private EnumStoneVariant(int metaIn, String nameIn, String unlocalizedNameIn)
        {
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

        public static EnumStoneVariant byMetadata(int meta)
        {
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

        static
        {
            for (EnumStoneVariant blockvariant$enumtype : values())
            {
                META_LOOKUP[blockvariant$enumtype.getMetadata()] = blockvariant$enumtype;
            }
        }
    }
}
