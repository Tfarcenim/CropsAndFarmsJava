package tfar.cropsandfarmsjava.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import tfar.cropsandfarmsjava.CropsAndFarms;
import tfar.cropsandfarmsjava.world.entity.FancyCowEntity;

import java.util.EnumMap;

public class FancyCowRenderer extends GeoEntityRenderer<FancyCowEntity> {

    protected final EnumMap<FancyCowEntity.Variant,SexedModel<FancyCowEntity>> modelMap = new EnumMap<>(FancyCowEntity.Variant.class);


    public FancyCowRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager,(GeoModel<FancyCowEntity>)null);

        for (FancyCowEntity.Variant variant : FancyCowEntity.Variant.values()) {
            SexedModel<FancyCowEntity> sexedModel = new SexedModel<>(new DefaultedEntityGeoModel<>(CropsAndFarms.id("male_"+variant.getSerializedName()+"_cow")),
                    new DefaultedEntityGeoModel<>(CropsAndFarms.id("female_"+variant.getSerializedName()+"_cow")));
            modelMap.put(variant,sexedModel);
        }
    }

    @Override
    public GeoModel<FancyCowEntity> getGeoModel() {
        FancyCowEntity fancyCowEntity = getAnimatable();
        FancyCowEntity.Variant variant = fancyCowEntity.getVariant();
        return modelMap.get(variant).getModel(fancyCowEntity.getSex());
    }
}
