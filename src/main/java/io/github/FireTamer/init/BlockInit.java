package io.github.FireTamer.init;

import com.matyrobbrt.lib.registry.annotation.AutoBlockItem;
import com.matyrobbrt.lib.registry.annotation.RegisterBlock;
import com.matyrobbrt.lib.registry.annotation.RegistryHolder;
import io.github.FireTamer.DBB_Main;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

@RegistryHolder(modid = DBB_Main.MOD_ID)
public class BlockInit {

    @AutoBlockItem
    @RegisterBlock("dirty_stone")
    public static final Block DIRTY_STONE = new Block(AbstractBlock.Properties.copy(Blocks.STONE));
}
