package com.newkinidev.nkisdefense.block;

import com.newkinidev.nkisdefense.gui.GunWorkbenchGui;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class GunWorkbench extends Block {
    private static final Component CONTAINER_TITLE = Component.translatable("container.gun_workbench");

    public GunWorkbench(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public VoxelShape getShape(BlockState p_51222_, BlockGetter p_51223_, BlockPos p_51224_, CollisionContext p_51225_) {
        VoxelShape voxel = Block.box(0.0,14.0,0.0,16.0,16.0,16.0); // 맨위
        voxel = Shapes.join(Block.box(0.0,0,0.0,4,14,4), voxel, BooleanOp.OR); //(위에서 봤을떄)오른쪽 위
        voxel = Shapes.join(Block.box(0,0,12,4,14,16), voxel, BooleanOp.OR); // 오른쪽 아래
        voxel = Shapes.join(Block.box(12,0,12,16,14,16), voxel, BooleanOp.OR); // 왼쪽 아래
        voxel = Shapes.join(Block.box(12,0,0,16,14,4), voxel, BooleanOp.OR); // 왼쪽 위
        return voxel;
    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(serverPlayer, state.getMenuProvider(level, pos));
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public MenuProvider getMenuProvider(BlockState p_52240_, Level p_52241_, BlockPos p_52242_) {
        return new SimpleMenuProvider(
                    (containerId, playerInventory, player) -> new GunWorkbenchGui(containerId, playerInventory),
                    CONTAINER_TITLE
        );
    }
}
