package tfar.cropsandfarmsjava.client;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import tfar.cropsandfarmsjava.CropsAndFarms;
import tfar.cropsandfarmsjava.world.HolsteinCowEntity;

import java.util.Locale;

public class HolsteinCowRenderer extends GeoEntityRenderer<HolsteinCowEntity> {

   /* private static final Map<HolsteinCowEntity.Variant, ResourceLocation> TEXTURE_BY_TYPE = Util.make(Maps.newHashMap(), (map) -> {
        for(HolsteinCowEntity.Variant variant : HolsteinCowEntity.Variant.values()) {
            map.put(variant, CropsAndFarmsJava.id(String.format(Locale.ROOT, "textures/entity/holstein_cow/%s.png", variant.getName())));
        }
    });*/


    public HolsteinCowRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(CropsAndFarms.id("holstein_cow")));
    }
}
