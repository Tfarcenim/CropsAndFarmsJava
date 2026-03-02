package tfar.cropsandfarmsjava.world;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import tfar.cropsandfarmsjava.CropsAndFarms;
import tfar.cropsandfarmsjava.world.block.AnimalHeaterBlock;

public class CropsAndFarmsBlocks {

    public static final Block ANIMAL_HEATER = register("animal_heater",new AnimalHeaterBlock(BlockBehaviour.Properties.of()));

    public static void init() {

    }

    public static Block register(String key, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, CropsAndFarms.id(key), block);
    }
}
