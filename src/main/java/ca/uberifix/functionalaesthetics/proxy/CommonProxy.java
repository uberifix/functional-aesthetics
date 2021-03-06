package ca.uberifix.functionalaesthetics.proxy;

import ca.uberifix.functionalaesthetics.common.block.ModBlocks;
import ca.uberifix.functionalaesthetics.common.config.Config;
import ca.uberifix.functionalaesthetics.common.crafting.ModCrafting;
import ca.uberifix.functionalaesthetics.common.item.ModItems;
import ca.uberifix.functionalaesthetics.common.item.block.ModItemBlocks;
import ca.uberifix.functionalaesthetics.common.tileentity.ModTileEntities;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

/**
 * Created by uberifix
 */
public class CommonProxy {
    public static Configuration config;
    public void preInit(FMLPreInitializationEvent event) {
        File directory = event.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "functionalaesthetics.cfg"));
        Config.readConfig();

        ModBlocks.init();
        ModItems.init();
        ModItemBlocks.init();
        ModTileEntities.init();
    }

    public void init(FMLInitializationEvent event) {
        ModCrafting.init();
    }

    public void postInit(FMLPostInitializationEvent event) {
        if (config.hasChanged()) {
            config.save();
        }
    }
}
