package commandoblocks.core;

import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;

import org.apache.logging.log4j.Logger;

import commandoblocks.core.proxies.CommonProxy;
import commandoblocks.handlers.ObjectHandler;
import commandoblocks.network.AdvCBMessage;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = Settings.ModID, name = Settings.Name, version = Settings.Version)
public class CommandoBlocks
{

	public static Logger logger;
	
	public static CBTab CBTab;

	@Instance(Settings.ModID)
	public static CommandoBlocks instance;
	
	@SidedProxy(clientSide = Settings.Proxy + ".ClientProxy", serverSide = Settings.Proxy + ".CommonProxy")
	public static CommonProxy proxy;
	
	public SimpleNetworkWrapper network;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
		
		CBTab = new CBTab("Commando Blocks");
		
		proxy.preInit(event);
	
		this.network = NetworkRegistry.INSTANCE.newSimpleChannel(Settings.Channel);
		this.network.registerMessage(AdvCBMessage.Handler.class, AdvCBMessage.class, 0, Side.SERVER);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
		
		ObjectHandler.initBlocks();
		ObjectHandler.registerBlocks();
		ObjectHandler.registerRenderers();
		
		ObjectHandler.registerEntities();
	
		proxy.registerTickHandlers();
		proxy.registerEventHandlers();
	
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
	}
	
	@EventHandler
	public void serverStart(FMLServerStartingEvent event)
	{
		MinecraftServer server = MinecraftServer.getServer();
		ICommandManager command = server.getCommandManager();
		ServerCommandManager manager = (ServerCommandManager) command;
		
//		manager.registerCommand(new CommandPhysics());

	}
}
