package trs.someluigi.slperiph.itemblock;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

import trs.someluigi.slperiph.ref.ModInst;

public class ItemBlockMulti1 extends ItemBlock {
	
	public static Icon[] icons = new Icon[16];
	
	public ItemBlockMulti1(int par1) {
		super(par1);
		this.setHasSubtypes(true);
		//this.setCreativeTab(ModInst.modCtab);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack i) {
		return "someluigisperiph:multi1_" + i.getItemDamage();
	}
	
	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1) {
		return ModInst.bMulti1.getIcon(2, par1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir) {
		ItemBlockMulti1.icons[0] = ir.registerIcon("someluigisperiph:multi1_http_top");
		ItemBlockMulti1.icons[1] = ir.registerIcon("someluigisperiph:multi1_http_side");
		/*
		ItemBlockDyedIce.icons[1] = ir.registerIcon("sl_extramisc:b1_1");
		ItemBlockDyedIce.icons[2] = ir.registerIcon("sl_extramisc:b1_2");
		ItemBlockDyedIce.icons[3] = ir.registerIcon("sl_extramisc:b1_3");
		ItemBlockDyedIce.icons[4] = ir.registerIcon("sl_extramisc:b1_4");
		ItemBlockDyedIce.icons[5] = ir.registerIcon("sl_extramisc:b1_5");
		ItemBlockDyedIce.icons[6] = ir.registerIcon("sl_extramisc:b1_6");
		ItemBlockDyedIce.icons[7] = ir.registerIcon("sl_extramisc:b1_7");
		ItemBlockDyedIce.icons[8] = ir.registerIcon("sl_extramisc:b1_8");
		ItemBlockDyedIce.icons[9] = ir.registerIcon("sl_extramisc:b1_9");
		ItemBlockDyedIce.icons[10] = ir.registerIcon("sl_extramisc:b1_10");
		ItemBlockDyedIce.icons[11] = ir.registerIcon("sl_extramisc:b1_11");
		ItemBlockDyedIce.icons[12] = ir.registerIcon("sl_extramisc:b1_12");
		ItemBlockDyedIce.icons[13] = ir.registerIcon("sl_extramisc:b1_13");
		ItemBlockDyedIce.icons[14] = ir.registerIcon("sl_extramisc:b1_14");
		ItemBlockDyedIce.icons[15] = ir.registerIcon("sl_extramisc:b1_15");
		*/
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack iStack, EntityPlayer player, List list, boolean someBool) {
		switch (iStack.getItemDamage()) {
			case 0:
				// HTTP Server Module
				list.add("This block needs Computers or Turtles (and code) to be used!");
				list.add("This peripheral allows a computer/turtle to serve HTTP requests.");
			break;
		}
	}

}
