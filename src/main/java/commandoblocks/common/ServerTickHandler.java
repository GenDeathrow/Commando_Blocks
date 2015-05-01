package commandoblocks.common;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Type;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ServerTickHandler 
{
	@SubscribeEvent
	public void tickEnd(TickEvent.WorldTickEvent tick)
	{
		//Adv_Controller.UpdateTick(tick.world);
	}

	
	@SubscribeEvent
	public void ptickEnd(TickEvent.PlayerTickEvent tick)
	{
		
	}
	// Used for to load up SaveContoler for clients side GUI settings
    //private boolean ticked = false;
    private boolean firstload = true;

    @SubscribeEvent
	@SideOnly(Side.CLIENT)
    public void RenderTickEvent(RenderTickEvent event) 
    {
        if ((event.type == Type.RENDER || event.type == Type.CLIENT) && event.phase == Phase.END) 
        {
            Minecraft mc = Minecraft.getMinecraft();
            if (firstload && mc != null) 
            {
 //               if (!SaveController.loadConfig(SaveController.UISettingsData))
 //               {
 //                   HUDRegistry.checkForResize();
 //                   HUDRegistry.resetAllDefaults();
 //                   SaveController.saveConfig(SaveController.UISettingsData);
  //              }
                firstload = false;
            }
        }
    }
}