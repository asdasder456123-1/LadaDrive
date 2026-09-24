package com.nexusxs.ladadrive.item;

import com.nexusxs.ladadrive.LadaDriveEntities;
import com.nexusxs.ladadrive.entity.LadaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class LadaItem extends Item {

    public LadaItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(
            World world,
            PlayerEntity player,
            Hand hand
    ) {
        ItemStack stack = player.getStackInHand(hand);

        HitResult hit = player.raycast(
                5.0D,
                1.0F,
                false
        );

        if (hit.getType() != HitResult.Type.BLOCK) {
            return TypedActionResult.pass(stack);
        }

        BlockHitResult blockHit = (BlockHitResult) hit;

        if (!world.isClient) {
            Direction side = blockHit.getSide();

            double x =
                    blockHit.getPos().x
                    + side.getOffsetX() * 1.5D;

            double y =
                    blockHit.getPos().y
                    + 0.15D;

            double z =
                    blockHit.getPos().z
                    + side.getOffsetZ() * 1.5D;

            LadaEntity lada =
                    new LadaEntity(
                            LadaDriveEntities.LADA_2107,
                            world
                    );

            lada.refreshPositionAndAngles(
                    x,
                    y,
                    z,
                    player.getYaw(),
                    0.0F
            );

            lada.setCustomName(
                    net.minecraft.text.Text.translatable(
                            "entity.ladadrive.lada_2107"
                    )
            );

            lada.setCustomNameVisible(false);

            world.spawnEntity(lada);

            if (!player.getAbilities().creativeMode) {
                stack.decrement(1);
            }
        }

        return TypedActionResult.success(stack, world.isClient());
    }
}
