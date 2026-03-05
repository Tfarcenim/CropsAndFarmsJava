package tfar.cropsandfarmsjava.world;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import tfar.cropsandfarmsjava.CropsAndFarms;
import tfar.cropsandfarmsjava.world.entity.FancyCowEntity;

public class CropsAndFarmsEntityTypes {
    public static final EntityType<FancyCowEntity> FANCY_COW = register("fancy_cow",EntityType.Builder
            .of(FancyCowEntity::new, MobCategory.CREATURE)
            .sized(0.9F, 1.4F).eyeHeight(1.3F).passengerAttachments(1.36875F).clientTrackingRange(10));

    private static <E extends Entity> EntityType<E> register(String key, EntityType.Builder<E> builder) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, CropsAndFarms.id(key), builder.build(key));
    }

    public static void init() {

    }
}
