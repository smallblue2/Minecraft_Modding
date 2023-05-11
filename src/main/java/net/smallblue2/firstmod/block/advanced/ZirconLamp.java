package net.smallblue2.firstmod.block.advanced;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;


public class ZirconLamp extends Block {
    public ZirconLamp(Properties properties) {
        super(properties);
        // Default lit boolean is false
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(LIT, false));
    }

    // Blockstate to light up block
    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    // Add blockstate properties to StateDefinition builder
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    // Turning on and off the lamp (Serverside & Mainhand)
    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!level.isClientSide() && interactionHand == InteractionHand.MAIN_HAND) {
            // Set the block state
            level.setBlock(blockPos, blockState.setValue(LIT, !blockState.getValue(LIT)), 3);
        }

        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }
}
