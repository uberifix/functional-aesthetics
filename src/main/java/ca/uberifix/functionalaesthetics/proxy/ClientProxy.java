package ca.uberifix.functionalaesthetics.proxy;

import ca.uberifix.functionalaesthetics.common.item.block.ModItemBlocks;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by uberifix
 */
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        ModItemBlocks.initClient();
    }
}
