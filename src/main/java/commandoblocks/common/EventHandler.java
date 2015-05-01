package commandoblocks.common;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import org.lwjgl.input.Keyboard;

import commandoblocks.block.AdvanceCommandBlock;
import commandoblocks.block.tileentity.TileEntityAdvanceCommand;
import commandoblocks.client.gui.command_block.GuiAdvanceCommandBlock;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class EventHandler 
{
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void GuiOpenEvent(GuiOpenEvent event)
	{
		if(event.gui != null)
		{
			System.out.println(event.gui.getClass().getName().toString());
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		if(event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
		{
			Block block = event.world.getBlock(event.x, event.y, event.z);
			if(block instanceof AdvanceCommandBlock)
			{
			
			//If retexturing
			//	if(event.entityPlayer.isSneaking()) 
			//	{ 
			//			((AdvanceCommandBlock) block).onBlockClicked(event.world, event.entityPlayer, event.x, event.y, event.z); 
			//			event.setCanceled(true); 
			//			return; 
			//	}
				
				TileEntityAdvanceCommand TE = (TileEntityAdvanceCommand) event.world.getTileEntity(event.x, event.y, event.z);
				
				if(TE != null)
				{
					
					event.setCanceled(true);
					
					Minecraft.getMinecraft().displayGuiScreen(new GuiAdvanceCommandBlock(TE));
				}
			}
			
		}
	}
	

}
