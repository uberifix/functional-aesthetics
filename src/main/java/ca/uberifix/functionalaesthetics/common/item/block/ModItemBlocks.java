package ca.uberifix.functionalaesthetics.common.item.block;

import ca.uberifix.functionalaesthetics.common.block.ModBlocks;
import ca.uberifix.functionalaesthetics.common.item.block.rustic.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static ca.uberifix.functionalaesthetics.common.config.Config.RUSTIC_MODULE_ENABLED;

/**
 * Created by uberifix
 */
public class ModItemBlocks {
    //public static BarrelItemBlock barrelItemBlock;
    public static Campfire1ItemBlock campfire1ItemBlock;
    public static Campfire2ItemBlock campfire2ItemBlock;
    public static StoneCampfire1ItemBlock stoneCampfire1ItemBlock;
    public static StoneCampfire2ItemBlock stoneCampfire2ItemBlock;

    public static void init() {
        if(RUSTIC_MODULE_ENABLED) {
            //barrelItemBlock = new BarrelItemBlock(ModBlocks.barrelBlock);
            campfire1ItemBlock = new Campfire1ItemBlock(ModBlocks.campfire1Block);
            campfire2ItemBlock = new Campfire2ItemBlock(ModBlocks.campfire2Block);
            stoneCampfire1ItemBlock = new StoneCampfire1ItemBlock(ModBlocks.stoneCampfire1Block);
            stoneCampfire2ItemBlock = new StoneCampfire2ItemBlock(ModBlocks.stoneCampfire2Block);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void initClient() {
        if(RUSTIC_MODULE_ENABLED) {
            //barrelItemBlock.initModels();
            campfire1ItemBlock.initModels();
            campfire2ItemBlock.initModels();
            stoneCampfire1ItemBlock.initModels();
            stoneCampfire2ItemBlock.initModels();
        }
    }
}
