package trs.someluigi.slperiph;

import net.minecraftforge.common.Configuration;
import trs.someluigi.slperiph.http.HTTPHandler;
import trs.someluigi.slperiph.ref.ModConfig;
import trs.someluigi.slperiph.ref.ModInst;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

@Mod(modid="someluigisperiph")
public class TSLPMod {
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent e) {
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		
		ModConfig.handleMain(config);
		
		ModInst.createThings();
		ModInst.registerThings();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		ModInst.registerRecipes();
	}
	
	@EventHandler
	public void postinit(FMLPostInitializationEvent e) {
	}
	
	@EventHandler
	public void serverstart(FMLServerStartingEvent e) {
		if (ModConfig.httpShouldRun) {
			HTTPHandler.start(ModConfig.httpPort);
		}
	}
	
	@EventHandler
	public void serverstop(FMLServerStoppingEvent e) {
		if (ModConfig.httpShouldRun) {
			HTTPHandler.stop();
		}
	}
	
}
