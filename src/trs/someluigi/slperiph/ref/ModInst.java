package trs.someluigi.slperiph.ref;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import trs.someluigi.slperiph.block.BlockMulti1;
import trs.someluigi.slperiph.ctab.SLPCreativeTab;
import trs.someluigi.slperiph.itemblock.ItemBlockMulti1;
import trs.someluigi.slperiph.tileentity.TileEntityHTTPd;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


public class ModInst {
	
	public static BlockMulti1 bMulti1;
	public static SLPCreativeTab ctab;
	
	public static void createThings() {
		ctab = new SLPCreativeTab("someluigisperiph:mainctab");
		if (ModConfig.bHttpServer != -1) {
			bMulti1 = new BlockMulti1(ModConfig.bHttpServer, Material.iron);
		}
	}
	
	public static void registerThings() {
		LanguageRegistry lr = LanguageRegistry.instance();
		
		GameRegistry.registerBlock(bMulti1, ItemBlockMulti1.class, "someluigisperiph:multi1_http");
		
		GameRegistry.registerTileEntity(TileEntityHTTPd.class, "somneluigisperiph:multi1_http");
		
		lr.addStringLocalization("itemGroup.someluigisperiph:mainctab", "SomeLuigi's Peripherals by TehSomeLuigi");
		lr.addStringLocalization("someluigisperiph:multi1_0.name", "HTTP Server Module");
	}
	
	public static void registerRecipes() {
		// HTTP Server Module crafting
		GameRegistry.addRecipe(new ItemStack(bMulti1, 1, 0),
			new Object[] { "rtr", "gbg", "gig",
				Character.valueOf('r'), Item.redstone,
				Character.valueOf('t'), Block.torchRedstoneIdle,
				Character.valueOf('i'),	Item.ingotIron,
				Character.valueOf('g'),	Item.ingotGold,
				Character.valueOf('b'),	Block.blockIron
			});
		GameRegistry.addRecipe(new ItemStack(bMulti1, 1, 0),
			new Object[] { "rtr", "gbg", "gig",
				Character.valueOf('r'), Item.redstone,
				Character.valueOf('t'), Block.torchRedstoneActive,
				Character.valueOf('i'),	Item.ingotIron,
				Character.valueOf('g'),	Item.ingotGold,
				Character.valueOf('b'),	Block.blockIron
			});
	}
	
}
