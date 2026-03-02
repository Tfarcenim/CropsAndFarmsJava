package tfar.cropsandfarmsjava.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import tfar.cropsandfarmsjava.CropsAndFarms;

import java.util.concurrent.CompletableFuture;

public class CropsAndFarmsDatagen {
    public static void gather(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        if (event.includeServer()) {
            generator.addProvider(true, CropsAndFarmsLootTableProvider.create(packOutput,lookupProvider));
        }
        if (event.includeClient()) {
            generator.addProvider(true, new CropsAndFarmsLang(packOutput));
        }
    }

}
