package trs.someluigi.slperiph.ctab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import trs.someluigi.slperiph.ref.ModInst;

public class SLPCreativeTab extends CreativeTabs {
	public SLPCreativeTab(String label) {
		super(label);
	}

	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(ModInst.bMulti1, 1, 0);
	}
}
