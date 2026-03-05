package tfar.cropsandfarmsjava.network;

import net.minecraft.network.syncher.EntityDataSerializer;
import tfar.cropsandfarmsjava.platform.Services;
import tfar.cropsandfarmsjava.world.entity.FancyCowEntity;
import tfar.cropsandfarmsjava.world.entity.Sex;

public class CropsAndFarmsEntityDataSerializers {
    public static final EntityDataSerializer<Sex> SEX = EntityDataSerializer.forValueType(Sex.STREAM_CODEC);
    public static final EntityDataSerializer<FancyCowEntity.Variant> COW_VARIANT = EntityDataSerializer.forValueType(FancyCowEntity.Variant.STREAM_CODEC);

    static {
        Services.PLATFORM.registerDataSerializer("sex", SEX);
        Services.PLATFORM.registerDataSerializer("cow_variant", COW_VARIANT);
    }

    public static void init() {

    }
}
