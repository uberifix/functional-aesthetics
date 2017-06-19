package ca.uberifix.functionalaesthetics;

import ca.uberifix.functionalaesthetics.common.block.ModBlocks;
import ca.uberifix.functionalaesthetics.common.lib.LibRef;
import ca.uberifix.functionalaesthetics.proxy.CommonProxy;
import mcjty.lib.compat.CompatCreativeTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

/**
 * Created by uberifix
 */
@Mod(   modid = LibRef.MOD_ID,
        name = LibRef.MOD_NAME,
        version = LibRef.MOD_VER,
        useMetadata = true,
        acceptedMinecraftVersions = "[1.10,1.12)",
        dependencies = "required-after:compatlayer@[" + LibRef.COMPATLAYER_VER + ",);" )
public class FunctionalAesthetics {
    @SidedProxy(clientSide = LibRef.CLIENT_PROXY, serverSide = LibRef.SERVER_PROXY, modId = LibRef.MOD_ID)
    public static CommonProxy proxy;

    @Mod.Instance
    public static FunctionalAesthetics instance;

    public static Logger logger;

    public static CreativeTabs tabFunctionalAestheticsRustic = new CompatCreativeTabs("tab_functional_aesthetics_rustic") {
        @Override
        protected Item getItem() { return Item.getItemFromBlock(ModBlocks.barrelBlock); }
    };

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);

        FunctionalAesthetics.logger.info("Pre-Initializing");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
