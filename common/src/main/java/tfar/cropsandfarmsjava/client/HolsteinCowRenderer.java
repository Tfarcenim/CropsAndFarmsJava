package tfar.cropsandfarmsjava.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import tfar.cropsandfarmsjava.world.entity.FancyCowEntity;
import tfar.cropsandfarmsjava.world.entity.Sex;

public class HolsteinCowRenderer extends GeoEntityRenderer<FancyCowEntity> {

   /* private static final Map<FancyCowEntity.Variant, ResourceLocation> TEXTURE_BY_TYPE = Util.make(Maps.newHashMap(), (map) -> {
        for(FancyCowEntity.Variant variant : FancyCowEntity.Variant.values()) {
            map.put(variant, CropsAndFarmsJava.id(String.format(Locale.ROOT, "textures/entity/holstein_cow/%s.png", variant.getName())));
        }
    });*/

    protected final GeoModel<FancyCowEntity> femaleModel;


    public HolsteinCowRenderer(EntityRendererProvider.Context renderManager
            , GeoModel<FancyCowEntity> maleModel, GeoModel<FancyCowEntity> femaleModel) {
        super(renderManager, maleModel);
        this.femaleModel = femaleModel;
    }

    @Override
    public GeoModel<FancyCowEntity> getGeoModel() {
        FancyCowEntity fancyCowEntity = getAnimatable();
        return fancyCowEntity.getSex() == Sex.FEMALE ? femaleModel : model;
    }
}
