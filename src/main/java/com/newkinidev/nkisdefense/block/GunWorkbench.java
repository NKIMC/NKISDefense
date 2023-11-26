package com.newkinidev.nkisdefense.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.util.LazyOptional;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

public class GunWorkbench extends BaseEntityBlock {

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

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return null;
    }
}
