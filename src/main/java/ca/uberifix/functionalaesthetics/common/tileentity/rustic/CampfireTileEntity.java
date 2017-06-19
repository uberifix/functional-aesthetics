package ca.uberifix.functionalaesthetics.common.tileentity.rustic;

import ca.uberifix.functionalaesthetics.common.tileentity.TileEntityCommon;
import mcjty.lib.tools.ItemStackTools;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.WorldServer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uberifix
 */
public class CampfireTileEntity extends TileEntityCommon implements ITickable{
    private int tick;
    private int processTime;
    @Override
    public void update () {
        tick = (tick + 1) %20;

        List<EntityItem> itemsToCook = new ArrayList<>();
        if (tick == 0 && !getWorld().isRemote) {
            double x = this.pos.getX(), y = this.pos.getY(), z = this.pos.getZ();
            AxisAlignedBB COOK_RADIUS_AABB = new AxisAlignedBB(x - 1, y, z - 1, x + 2, y + 1, z + 2);
            List<EntityItem> itemsNearby = getWorld().getEntitiesWithinAABB(EntityItem.class, COOK_RADIUS_AABB);
            for (EntityItem itemNearby : itemsNearby) {
                double itemX = itemNearby.posX, itemY = itemNearby.posY, itemZ = itemNearby.posZ;
                int itemsToCookAvailable = 4 - itemsToCook.size();
                ItemStack cookedItem = FurnaceRecipes.instance().getSmeltingResult(itemNearby.getEntityItem());
                if(!itemNearby.cannotPickup()) {
                    if (!ItemStackTools.isEmpty(cookedItem)) {
                        if (cookedItem.getItem() instanceof ItemFood) {
                            //TODO: limit items cooked to 8, add cook timer, add steam particle effects during cooking
                            //TODO: after cooked, add smoke particles, after 2 sec, add fire particles, after 2 sec turn item to charcoal
                            if (itemsToCookAvailable > 0) {
                                EntityItem newItem = new EntityItem(getWorld(), itemX, itemY+0.2, itemZ, itemNearby.getEntityItem().splitStack(1));
                                newItem.setInfinitePickupDelay();
                                getWorld().spawnEntity(newItem);
                                itemsToCook.add(newItem);
                            }
                        }
                    }
                } else { itemsToCook.add(itemNearby); }
            }
        }
        if (tick % 5 == 0 && !getWorld().isRemote) {
            if(itemsToCook.size() > 0) { processTime = (processTime + 1) % 8; }
            if (processTime == 0) {
                for (EntityItem itemToCook : itemsToCook) {
                    double itemX = itemToCook.posX, itemY = itemToCook.posY, itemZ = itemToCook.posZ;
                    ItemStack itemCooked = FurnaceRecipes.instance().getSmeltingResult(itemToCook.getEntityItem());
                    ItemStackTools.setStackSize(itemCooked, ItemStackTools.getStackSize(itemToCook.getEntityItem()));
                    itemToCook.setEntityItemStack(itemCooked.copy());
                    itemToCook.setNoPickupDelay();
                    ((WorldServer) this.getWorld()).spawnParticle(EnumParticleTypes.END_ROD, true, itemX, itemY+1, itemZ, 20, 0, 0, 0, 0.01f, new int[0]);
                }
            }
        }
        if (this.getWorld() instanceof WorldServer) {
            for (EntityItem itemToCook : itemsToCook) {
                double itemX = itemToCook.posX, itemY = itemToCook.posY, itemZ = itemToCook.posZ;
                ((WorldServer) this.getWorld()).spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, true, itemX, itemY+0.5, itemZ, 3, 0, 0, 0, 0.01f, new int[0]);
            }
        }
    }
}