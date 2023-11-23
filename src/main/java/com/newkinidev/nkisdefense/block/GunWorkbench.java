package com.newkinidev.nkisdefense.block;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Function;

import static com.newkinidev.nkisdefense.NKISDefense.BLOCKS;
import static com.newkinidev.nkisdefense.NKISDefense.ITEMS;

public class GunWorkbench extends Block {

    public GunWorkbench(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public VoxelShape getShape(BlockState p_51222_, BlockGetter p_51223_, BlockPos p_51224_, CollisionContext p_51225_) {
        VoxelShape voxel = Block.box(0.0,14.0,0.0,16.0,16.0,16.0); // 맨위
        voxel = Shapes.join(Block.box(0.0,0,0.0,4,14,4), voxel, BooleanOp.OR); //(위에서 봤을떄)왼쪽 위
        voxel = Shapes.join(Block.box(4,0,12,16,14,0), voxel, BooleanOp.OR); // 왼쪽 아래
        return voxel;
    }
}
