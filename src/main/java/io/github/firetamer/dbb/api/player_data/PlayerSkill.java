package io.github.firetamer.dbb.api.player_data;

import com.matyrobbrt.lib.registry.annotation.RegisterCustomRegistry;
import com.matyrobbrt.lib.registry.annotation.RegistryHolder;
import io.github.firetamer.dbb.DragonBlockBeyond;
import io.github.firetamer.dbb.util.helper.CustomRegistryHelper;
import io.github.firetamer.dbb.util.objects.TargetField;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Optional;

@RegistryHolder(modid = DragonBlockBeyond.MOD_ID)
public class PlayerSkill extends ForgeRegistryEntry<PlayerSkill> {

    public static final IForgeRegistry<PlayerSkill> REGISTRY = null;

    @RegisterCustomRegistry
    public static void createRegistries(RegistryEvent.NewRegistry event) {
        CustomRegistryHelper.<PlayerSkill>registerRegistry(
                new TargetField(PlayerSkill.class, "REGISTRY"),
                PlayerSkill.class, new ResourceLocation(DragonBlockBeyond.MOD_ID, "player_skill"),
                Optional.empty(), Optional.empty());
    }

    public ITextComponent getDescription() {
        return new TranslationTextComponent("player_skill." + getRegistryName().getNamespace() + "." +
                getRegistryName().getPath() + ".description");
    }

}