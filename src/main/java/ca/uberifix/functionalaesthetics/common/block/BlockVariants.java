package ca.uberifix.functionalaesthetics.common.block;

import net.minecraft.util.IStringSerializable;

/**
 * Created by uberifix
 */
public class BlockVariants {
    public enum EnumWoodVariantAll implements IStringSerializable {
        OAK(0, "oak"),
        SPRUCE(1, "spruce"),
        BIRCH(2, "birch"),
        JUNGLE(3, "jungle"),
        ACACIA(4, "acacia"),
        DARK_OAK(5, "dark_oak");

        private static final BlockVariants.EnumWoodVariantAll[] META_LOOKUP = new BlockVariants.EnumWoodVariantAll[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;

        EnumWoodVariantAll(int metaIn, String nameIn) { this(metaIn, nameIn, nameIn); }

        EnumWoodVariantAll(int metaIn, String nameIn, String unlocalizedNameIn) {
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

        public static BlockVariants.EnumWoodVariantAll byMetadata(int meta) {
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
            for (BlockVariants.EnumWoodVariantAll blockvariant$enumtype : values())
            {
                META_LOOKUP[blockvariant$enumtype.getMetadata()] = blockvariant$enumtype;
            }
        }
    }

    public enum EnumWoodVariantOld implements IStringSerializable {
        OAK(0, "oak"),
        SPRUCE(1, "spruce"),
        BIRCH(2, "birch"),
        JUNGLE(3, "jungle");

        private static final BlockVariants.EnumWoodVariantOld[] META_LOOKUP = new BlockVariants.EnumWoodVariantOld[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;

        EnumWoodVariantOld(int metaIn, String nameIn) { this(metaIn, nameIn, nameIn); }

        EnumWoodVariantOld(int metaIn, String nameIn, String unlocalizedNameIn) {
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

        public static BlockVariants.EnumWoodVariantOld byMetadata(int meta) {
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
            for (BlockVariants.EnumWoodVariantOld blockvariant$enumtype : values())
            {
                META_LOOKUP[blockvariant$enumtype.getMetadata()] = blockvariant$enumtype;
            }
        }
    }

    public enum EnumWoodVariantNew implements IStringSerializable {
        ACACIA(0, "acacia"),
        DARK_OAK(1, "dark_oak");

        private static final BlockVariants.EnumWoodVariantNew[] META_LOOKUP = new BlockVariants.EnumWoodVariantNew[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;

        EnumWoodVariantNew(int metaIn, String nameIn) { this(metaIn, nameIn, nameIn); }

        EnumWoodVariantNew(int metaIn, String nameIn, String unlocalizedNameIn) {
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

        public static BlockVariants.EnumWoodVariantNew byMetadata(int meta) {
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
            for (BlockVariants.EnumWoodVariantNew blockvariant$enumtype : values())
            {
                META_LOOKUP[blockvariant$enumtype.getMetadata()] = blockvariant$enumtype;
            }
        }
    }

    public enum EnumStoneVariant implements IStringSerializable {
        STONE(0, "stone"),
        GRANITE(1, "granite"),
        DIORITE(2, "diorite"),
        ANDESITE(3, "andesite");

        private static final BlockVariants.EnumStoneVariant[] META_LOOKUP = new BlockVariants.EnumStoneVariant[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;

        EnumStoneVariant(int metaIn, String nameIn) { this(metaIn, nameIn, nameIn); }

        EnumStoneVariant(int metaIn, String nameIn, String unlocalizedNameIn) {
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

        public static BlockVariants.EnumStoneVariant byMetadata(int meta) {
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
            for (BlockVariants.EnumStoneVariant blockvariant$enumtype : values())
            {
                META_LOOKUP[blockvariant$enumtype.getMetadata()] = blockvariant$enumtype;
            }
        }
    }
}
