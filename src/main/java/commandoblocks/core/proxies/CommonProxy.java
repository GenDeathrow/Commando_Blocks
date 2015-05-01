package commandoblocks.core.proxies;

import net.minecraftforge.common.MinecraftForge;
import commandoblocks.common.EventHandler;
import commandoblocks.common.ServerTickHandler;
import commandoblocks.util.UpdateNotification;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public boolean isClient()
	{
		return false;
	}
	
	public boolean isOpenToLAN()
	{
		return false;
	}
	
	public void preInit(FMLPreInitializationEvent event) 
	{
		// TODO Auto-generated method stub
		
	}

	public void init(FMLInitializationEvent event) {
		// TODO Auto-generated method stub
		
	}

	public void postInit(FMLPostInitializationEvent event) {
		// TODO Auto-generated method stub
		
	}

	public void registerTickHandlers() {
		FMLCommonHandler.instance().bus().register(new ServerTickHandler());
			
	}

	public void registerEventHandlers() 
	{
		EventHandler eventManager = new EventHandler();
		MinecraftForge.EVENT_BUS.register(eventManager);
		FMLCommonHandler.instance().bus().register(eventManager);
		
		UpdateNotification updateManager = new UpdateNotification();
		MinecraftForge.EVENT_BUS.register(updateManager);
		FMLCommonHandler.instance().bus().register(updateManager);
	}

}
