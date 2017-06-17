package ca.uberifix.functionalaesthetics.common.item.block;

import ca.uberifix.functionalaesthetics.common.block.ModBlocks;
import ca.uberifix.functionalaesthetics.common.item.block.rustic.BarrelItemBlock;
import ca.uberifix.functionalaesthetics.common.item.block.rustic.StoneCampfire1ItemBlock;
import ca.uberifix.functionalaesthetics.common.item.block.rustic.StoneCampfire2ItemBlock;

import static ca.uberifix.functionalaesthetics.common.config.Config.RUSTIC_MODULE_ENABLED;

/**
 * Created by uberifix
 */
public class ModItemBlocks {
    public static BarrelItemBlock barrelItemBlock;
    public static StoneCampfire1ItemBlock stoneCampfire1ItemBlock;
    public static StoneCampfire2ItemBlock stoneCampfire2ItemBlock;

    public static void init() {
        if(RUSTIC_MODULE_ENABLED) {
            barrelItemBlock = new BarrelItemBlock(ModBlocks.barrelBlock);
            stoneCampfire1ItemBlock = new StoneCampfire1ItemBlock(ModBlocks.stoneCampfire1Block);
            stoneCampfire2ItemBlock = new StoneCampfire2ItemBlock(ModBlocks.stoneCampfire2Block);
        }
    }
}
