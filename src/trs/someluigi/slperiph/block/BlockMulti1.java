package trs.someluigi.slperiph.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import trs.someluigi.slperiph.itemblock.ItemBlockMulti1;
import trs.someluigi.slperiph.ref.ModInst;
import trs.someluigi.slperiph.tileentity.TileEntityHTTPd;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMulti1 extends Block {
	
	public BlockMulti1(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setTickRandomly(true);
		this.setHardness(0.5F);
		this.setLightOpacity(3);
		this.setStepSound(soundMetalFootstep);
		this.setCreativeTab(ModInst.ctab);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		//for (int j = 0; j < 16; ++j) {
		for (int j = 0; j < 1; ++j) {
			par3List.add(new ItemStack(par1, 1, j));
		}
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		world.setBlockTileEntity(x, y, z, this.createNewTileEntity(world, world.getBlockMetadata(x, y, z)));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		switch (meta) {
			case 0:
				switch (side) {
					case 0:
						return ItemBlockMulti1.icons[1]; // bottom
					case 1:
						return ItemBlockMulti1.icons[1]; // top
					default:
						return ItemBlockMulti1.icons[0];
				}
		}
		return ItemBlockMulti1.icons[0];
	}
	
	public TileEntity createNewTileEntity(World world, int meta) {
		switch (meta) {
			case 0:
				return new TileEntityHTTPd();
		}
		
		return null;
	}
	
	@Override
	public boolean hasTileEntity(int meta) {
		return true;
	}
	
	@Override
	public int damageDropped(int par1) {
		return par1;
	}
	
}
