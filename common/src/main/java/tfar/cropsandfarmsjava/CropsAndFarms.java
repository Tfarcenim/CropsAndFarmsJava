package tfar.cropsandfarmsjava;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.animal.Cow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tfar.cropsandfarmsjava.network.CropsAndFarmsEntityDataSerializers;
import tfar.cropsandfarmsjava.world.*;

import java.util.function.BiConsumer;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class CropsAndFarms {

    public static final String MOD_ID = "cropsandfarmsjava";
    public static final String MOD_NAME = "Crops And Farms";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    // The loader specific projects are able to import and use any code from the common project. This allows you to
    // write the majority of your code here and load it from your loader specific projects. This example has some
    // code that gets invoked by the entry point of the loader specific projects.
    public static void init() {
    }

    public static void initRegistries() {
        CropsAndFarmsBlocks.init();
        CropsAndFarmsItems.init();
        CropsAndFarmsBlockEntityTypes.init();
        CropsAndFarmsEntityTypes.init();
        CAFMenuTypes.init();
        CropsAndFarmsEntityDataSerializers.init();
    }

    public static void createAttributes(BiConsumer<EntityType<? extends LivingEntity>, AttributeSupplier> consumer) {
        consumer.accept(CropsAndFarmsEntityTypes.HOLSTEIN_COW, Cow.createAttributes().build());
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID,path);
    }
}