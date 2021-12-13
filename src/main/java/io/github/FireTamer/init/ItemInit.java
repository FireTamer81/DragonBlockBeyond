package io.github.FireTamer.init;

import com.matyrobbrt.lib.registry.annotation.RegisterItem;
import com.matyrobbrt.lib.registry.annotation.RegistryHolder;
import io.github.FireTamer.DBB_Main;
import net.minecraft.item.Item;

@RegistryHolder(modid = DBB_Main.MOD_ID)
public class ItemInit {

    @RegisterItem("warenai_crystal")
    public static final Item WARENAI_CRYSTAL = new Item(new Item.Properties());
}
