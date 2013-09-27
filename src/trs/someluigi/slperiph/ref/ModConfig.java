package trs.someluigi.slperiph.ref;

import net.minecraftforge.common.Configuration;

public class ModConfig {
	
	// Block IDs
	public static int bHttpServer;
	
	
	
	// ===== HTTP Server =====
	public static boolean httpShouldRun;
	public static int httpPort;
	
	public static void handleMain(Configuration config) {
		config.load();
		
		bHttpServer = config.getBlock("block", "httpServer", 2047, "BlockID of the HTTP Server. (Multi-Block1). Future peripherals will share this ID").getInt(2047);
		
		
		httpShouldRun = config.get("httpserver", "enable", true, "Set to false and the HTTP Server will not start").getBoolean(true);
		httpPort = config.get("httpserver", "port", 8080, "Choose a port to run the server on. Using a port <= 2048 may require advanced setup.").getInt(8080);
		
		config.save();
	}
	
}
