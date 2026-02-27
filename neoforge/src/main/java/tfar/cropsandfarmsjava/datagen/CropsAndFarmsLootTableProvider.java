package tfar.cropsandfarmsjava.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import tfar.cropsandfarmsjava.CropsAndFarms;
import tfar.cropsandfarmsjava.world.CropsAndFarmsBlocks;

import java.util.*;
import java.util.concurrent.CompletableFuture;

class CropsAndFarmsLootTableProvider extends LootTableProvider {


    public CropsAndFarmsLootTableProvider(PackOutput output, Set<ResourceKey<LootTable>> requiredTables, List<SubProviderEntry> subProviders,
                                          CompletableFuture<HolderLookup.Provider> registries) {
        super(output, requiredTables, subProviders, registries);
    }

    public static LootTableProvider create(PackOutput pOutput,CompletableFuture<HolderLookup.Provider> registries) {
        return new CropsAndFarmsLootTableProvider(pOutput, BuiltInLootTables.all(),
                List.of(
                        new SubProviderEntry(BlockLoot::new, LootContextParamSets.ENTITY)
                ),registries);
    }

    static class BlockLoot extends VanillaBlockLoot {

        public BlockLoot(HolderLookup.Provider registries) {
            super(registries);
        }

        @Override
        public void generate() {
            dropSelf(CropsAndFarmsBlocks.ANIMAL_HEATER);
        }


        @Override
        protected Iterable<Block> getKnownBlocks() {
            return BuiltInRegistries.BLOCK.stream().filter(block -> BuiltInRegistries.BLOCK.getKey(block).getNamespace().equals(CropsAndFarms.MOD_ID))
                    .toList();
        }
    }

    @Override
    protected void validate(WritableRegistry<LootTable> writableregistry, ValidationContext validationcontext, ProblemReporter.Collector problemreporter$collector) {

    }
}
