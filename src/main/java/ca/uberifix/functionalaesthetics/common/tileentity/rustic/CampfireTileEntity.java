package ca.uberifix.functionalaesthetics.common.tileentity.rustic;

import ca.uberifix.functionalaesthetics.common.tileentity.TileEntityCommon;
import mcjty.lib.tools.ItemStackTools;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

/**
 * Created by uberifix
 */
public class CampfireTileEntity extends TileEntityCommon implements ITickable{
    private int tick;
    @Override
    public void update () {
        tick = (tick + 1) %20;
        if (tick == 0 && !getWorld().isRemote) {
            double x = this.pos.getX(), y = this.pos.getY(), z = this.pos.getZ();
            AxisAlignedBB COOK_RADIUS_AABB = new AxisAlignedBB(x - 1, y, z - 1, x + 2, y + 1, z + 2);
            List<EntityItem> entities = getWorld().getEntitiesWithinAABB(EntityItem.class, COOK_RADIUS_AABB);
            for (EntityItem item : entities) {
                ItemStack cookedItem = FurnaceRecipes.instance().getSmeltingResult(item.getEntityItem());
                ItemStackTools.setStackSize(cookedItem, ItemStackTools.getStackSize(item.getEntityItem()));
                if(!ItemStackTools.isEmpty(cookedItem)) {
                    if (cookedItem.getItem() instanceof ItemFood) {
                        //TODO: limit items cooked to 8, add 4 sec cook timer, add steam particle effects during cooking
                        //TODO: after cooked, add smoke particles, after 2 sec, add fire particles, after 2 sec turn item to charcoal
                        item.setEntityItemStack(cookedItem.copy());
                    }
                }
            }
        }
    }
}