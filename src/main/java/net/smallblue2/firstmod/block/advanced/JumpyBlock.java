package net.smallblue2.firstmod.block.advanced;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JumpyBlock extends Block {

    public JumpyBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player,
                                 InteractionHand interactionHand, BlockHitResult blockHitResult) {

        // Why is this called 4 times??
        // Gets called twice on server, and twice on client. Once for offhand & mainhand on both, therefor 4 time.
        if (level.isClientSide() && interactionHand == InteractionHand.MAIN_HAND) {
            player.sendSystemMessage(Component.literal("ClientSide && Main Hand"));
        } else if (level.isClientSide() && interactionHand == InteractionHand.OFF_HAND) {
            player.sendSystemMessage(Component.literal("ClientSide && Off Hand"));
        } else if (!level.isClientSide() && interactionHand == InteractionHand.MAIN_HAND) {
            player.sendSystemMessage(Component.literal("Server Side && Main Hand"));
        } else if (!level.isClientSide() && interactionHand == InteractionHand.OFF_HAND) {
            player.sendSystemMessage(Component.literal("Server Side && Off Hand"));
        }

        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entitiy) {
        if (entitiy instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.JUMP, 200));
        }
        super.stepOn(level, blockPos, blockState, entitiy);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable BlockGetter blockGetter, List<Component> list, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            list.add(Component.literal("A block that makes you jump high!").withStyle(ChatFormatting.YELLOW));
            list.add(Component.literal("(also for testing various mod features)").withStyle(ChatFormatting.RED));
        } else {
            list.add(Component.literal("Press SHIFT for more info").withStyle(ChatFormatting.AQUA));
        }

        super.appendHoverText(itemStack, blockGetter, list, tooltipFlag);
    }
}
