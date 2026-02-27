package tfar.cropsandfarmsjava.world;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import tfar.cropsandfarmsjava.CropsAndFarms;
import tfar.cropsandfarmsjava.world.block.entity.AnimalHeaterBlockEntity;

public class CropsAndFarmsBlockEntityTypes {
    public static final BlockEntityType<AnimalHeaterBlockEntity> ANIMAL_HEATER = register("animal_heater",BlockEntityType.Builder.of(AnimalHeaterBlockEntity::new,CropsAndFarmsBlocks.ANIMAL_HEATER));

    public static void init() {
    }

    private static <T extends BlockEntity> BlockEntityType<T> register(String key, BlockEntityType.Builder<T> builder) {
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, CropsAndFarms.id(key), builder.build(null));
    }
}
