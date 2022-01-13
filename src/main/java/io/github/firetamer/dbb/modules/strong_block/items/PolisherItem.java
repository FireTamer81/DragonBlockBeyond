package io.github.firetamer.dbb.modules.strong_block.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import io.github.firetamer.dbb.modules.strong_block.tiles.StrongBlockTile;

public class PolisherItem extends Item
{
    public PolisherItem(Properties properties) {
        super(properties);
    }


    @Override
    public ActionResultType useOn(ItemUseContext context) {
        World worldIn = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        PlayerEntity playerEntity = context.getPlayer();


        if (!worldIn.isClientSide) {
            TileEntity te = worldIn.getBlockEntity(blockPos);

            if (te instanceof StrongBlockTile) {
                StrongBlockTile strongBlockTile = (StrongBlockTile) te;

                if (playerEntity.isCrouching()) {
                    playerEntity.displayClientMessage(new StringTextComponent("Block Health: " + ((StrongBlockTile) te).getHealth()), false);
                } else {
                    strongBlockTile.polishBlock(100);

                    context.getItemInHand().hurtAndBreak(1, context.getPlayer(), (consumer) -> {
                        consumer.broadcastBreakEvent(context.getHand());
                    });
                }

                return ActionResultType.SUCCESS;
            } else {
                return ActionResultType.FAIL;
            }
        } else {
            return ActionResultType.FAIL;
        }
    }


}
