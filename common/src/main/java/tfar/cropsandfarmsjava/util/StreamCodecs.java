package tfar.cropsandfarmsjava.util;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public class StreamCodecs {

    public static <E extends Enum<E>> StreamCodec<FriendlyByteBuf,E> enumCodec(Class<E> eClass) {
        return StreamCodec.of(FriendlyByteBuf::writeEnum, buffer -> buffer.readEnum(eClass));
    }

}
