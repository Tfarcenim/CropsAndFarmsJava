package tfar.cropsandfarmsjava.world.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import tfar.cropsandfarmsjava.CropsAndFarms;
import tfar.cropsandfarmsjava.world.CropsAndFarmsBlocks;
import tfar.cropsandfarmsjava.world.CropsAndFarmsEntityTypes;

public class CropsAndFarmsItems {
    public static final Item ANIMAL_HEATER = Items.registerBlock(new BlockItem(CropsAndFarmsBlocks.ANIMAL_HEATER,new Item.Properties()));
    public static final Item FANCY_COW_SPAWN_EGG = Items.registerItem(CropsAndFarms.id("fancy_cow_spawn_egg"),
            new SpawnEggItem(CropsAndFarmsEntityTypes.FANCY_COW,0xffffff,0xffffff,new Item.Properties()));

    public static void init() {

    }
}
