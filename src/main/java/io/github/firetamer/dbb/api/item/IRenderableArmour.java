package io.github.firetamer.dbb.api.item;

import io.github.firetamer.dbb.client.model.CustomArmourModel;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface IRenderableArmour {

    @OnlyIn(Dist.CLIENT)
    CustomArmourModel getModel(ItemStack stack);

}
