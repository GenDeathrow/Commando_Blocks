package commandoblocks.handlers.keybinds;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyBinds 
{
		public static KeyBinding reloadConfig;

		public static void Init()
		{
			//reloadConfig = new KeyBinding(StatCollector.translateToLocal("keybinds.enviromine.reload"), Keyboard.KEY_K, "EnviroMine");
		
			//ClientRegistry.registerKeyBinding(reloadConfig);
		}
		
		@SubscribeEvent
	    public void onKeyInput(InputEvent.KeyInputEvent event)
		{
			if(reloadConfig.getIsKeyPressed())
			{
	
			}		
		}
	}