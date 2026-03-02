package tfar.cropsandfarmsjava.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.neoforged.neoforge.common.data.LanguageProvider;
import tfar.cropsandfarmsjava.CropsAndFarms;
import tfar.cropsandfarmsjava.world.block.entity.AnimalHeaterBlockEntity;

public class CropsAndFarmsLang extends LanguageProvider {
    public CropsAndFarmsLang(PackOutput output) {
        super(output, CropsAndFarms.MOD_ID,"en_us");
    }

    @Override
    protected void addTranslations() {
        addTextComponent(AnimalHeaterBlockEntity.DEFAULT_NAME,"Animal Heater");
    }

    protected void addTextComponent(MutableComponent component, String text) {
        ComponentContents contents = component.getContents();
        if (contents instanceof TranslatableContents translatableContents) {
            add(translatableContents.getKey(), text);
        } else {
            throw new UnsupportedOperationException(component + " is not translatable");
        }
    }
}
