package commandoblocks.handlers;

import net.minecraft.block.Block;

import commandoblocks.block.AdvanceCommandBlock;
import commandoblocks.block.tileentity.TileEntityAdvanceCommand;
import commandoblocks.client.render.block.RenderAdvCB;
import commandoblocks.core.CommandoBlocks;
import commandoblocks.core.Settings;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ObjectHandler 
{

	static Block advanceCommandBlock;
	public static  int RenderAdvCBID;
	
	public static void initBlocks()
	{
		advanceCommandBlock = new AdvanceCommandBlock().setBlockName("adv_command_block").setCreativeTab(CommandoBlocks.CBTab).setBlockTextureName(Settings.ModID +":adv_command_block");		
	}
	
	public static void registerBlocks()
	{
		GameRegistry.registerBlock(advanceCommandBlock, "adv_command_block");

	}
	
		
	public static void registerEntities()
	{
		GameRegistry.registerTileEntity(TileEntityAdvanceCommand.class, "enviromine.tile.advaceCommand");
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerRenderers()
	{
		//RenderingRegistry.registerBlockHandler(new RenderAdvCB());
	}

	/*
	public static void registerRecipes()
	{
		GameRegistry.addSmelting(badWaterBottle, new ItemStack(Items.potionitem, 1, 0), 0.0F);
		GameRegistry.addSmelting(saltWaterBottle, new ItemStack(Items.potionitem, 1, 0), 0.0F);
		GameRegistry.addSmelting(coldWaterBottle, new ItemStack(Items.potionitem, 1, 0), 0.0F);
		GameRegistry.addShapelessRecipe(new ItemStack(coldWaterBottle, 1, 0), new ItemStack(Items.potionitem, 1, 0), new ItemStack(Items.snowball, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(badWaterBottle, 1, 0), new ItemStack(Items.potionitem, 1, 0), new ItemStack(Blocks.dirt, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(saltWaterBottle, 1, 0), new ItemStack(Items.potionitem, 1, 0), new ItemStack(Blocks.sand, 1));
		
		GameRegistry.addRecipe(new ItemStack(Items.slime_ball, 4, 0), " r ", "rwr", " r ", 'w', new ItemStack(spoiledMilk, 1, 0), 'r', new ItemStack(rottenFood, 1));
		GameRegistry.addRecipe(new ItemStack(Blocks.mycelium), "xyx", "yzy", "xyx", 'z', new ItemStack(Blocks.grass), 'x', new ItemStack(Blocks.brown_mushroom), 'y', new ItemStack(rottenFood, 1));
		GameRegistry.addRecipe(new ItemStack(Blocks.mycelium), "xyx", "yzy", "xyx", 'z', new ItemStack(Blocks.grass), 'y', new ItemStack(Blocks.brown_mushroom), 'x', new ItemStack(rottenFood, 1));
		GameRegistry.addRecipe(new ItemStack(Blocks.mycelium), "xyx", "yzy", "xyx", 'z', new ItemStack(Blocks.grass), 'x', new ItemStack(Blocks.red_mushroom), 'y', new ItemStack(rottenFood, 1));
		GameRegistry.addRecipe(new ItemStack(Blocks.mycelium), "xyx", "yzy", "xyx", 'z', new ItemStack(Blocks.grass), 'y', new ItemStack(Blocks.red_mushroom), 'x', new ItemStack(rottenFood, 1));
		GameRegistry.addRecipe(new ItemStack(Blocks.dirt, 1), "xxx", "xxx", "xxx", 'x', new ItemStack(rottenFood));
		
		
		GameRegistry.addRecipe(new ItemStack(gasMask, 1), "xxx", "xzx", "yxy", 'x', new ItemStack(Items.iron_ingot), 'y', new ItemStack(airFilter), 'z', new ItemStack(Blocks.glass_pane));
		GameRegistry.addRecipe(new ItemStack(hardHat, 1), "xyx", "xzx", 'x', new ItemStack(Items.dye, 1, 11), 'y', new ItemStack(Blocks.redstone_lamp), 'z', new ItemStack(Items.iron_helmet, 1, 0));

		GameRegistry.addRecipe(new ItemStack(airFilter, 4), "xyx", "xzx", "xyx", 'x', new ItemStack(Items.iron_ingot), 'y', new ItemStack(Blocks.wool, 1, OreDictionary.WILDCARD_VALUE), 'z', new ItemStack(Items.coal, 1, 1));
		GameRegistry.addRecipe(new ItemStack(airFilter, 4), "xyx", "xzx", "xyx", 'x', new ItemStack(Items.iron_ingot), 'y', new ItemStack(Items.paper), 'z', new ItemStack(Items.coal, 1, 1));
		GameRegistry.addRecipe(new ItemStack(airFilter, 4), "xyx", "xzx", "xpx", 'x', new ItemStack(Items.iron_ingot), 'y', new ItemStack(Items.paper), 'p', new ItemStack(Blocks.wool, 1, OreDictionary.WILDCARD_VALUE),'z', new ItemStack(Items.coal, 1, 1));
		GameRegistry.addRecipe(new ItemStack(airFilter, 4), "xpx", "xzx", "xyx", 'x', new ItemStack(Items.iron_ingot), 'y', new ItemStack(Items.paper), 'p', new ItemStack(Blocks.wool, 1, OreDictionary.WILDCARD_VALUE),'z', new ItemStack(Items.coal, 1, 1));
		
		GameRegistry.addRecipe(new ItemStack(elevator, 1, 0), "xyx", "z z", "z z", 'x', new ItemStack(Blocks.iron_block), 'y', new ItemStack(Blocks.redstone_lamp), 'z', new ItemStack(Blocks.iron_bars));
		GameRegistry.addRecipe(new ItemStack(elevator, 1, 1), "z z", "xyx", "www", 'x', new ItemStack(Blocks.iron_block), 'y', new ItemStack(Blocks.furnace), 'z', new ItemStack(Blocks.iron_bars), 'w', new ItemStack(Items.diamond_pickaxe));
		
		GameRegistry.addRecipe(new ItemStack(davyLampBlock), " x ", "zyz", "xxx", 'x', new ItemStack(Items.gold_ingot), 'y', new ItemStack(Blocks.torch), 'z', new ItemStack(Blocks.glass_pane));
		GameRegistry.addShapelessRecipe(new ItemStack(davyLampBlock, 1, 1), new ItemStack(davyLampBlock, 1, 0), new ItemStack(Items.flint_and_steel, 1, OreDictionary.WILDCARD_VALUE));
		GameRegistry.addShapelessRecipe(new ItemStack(davyLampBlock, 1, 1), new ItemStack(davyLampBlock, 1, 0), new ItemStack(Blocks.torch));
		GameRegistry.addShapelessRecipe(new ItemStack(davyLampBlock, 1, 1), new ItemStack(davyLampBlock, 1, 0), new ItemStack(fireTorch));
		
		GameRegistry.addRecipe(new ItemStack(esky), "xxx", "yzy", "yyy", 'x', new ItemStack(Blocks.snow), 'y', new ItemStack(Items.dye, 1, 4), 'z', new ItemStack(Blocks.chest));
		GameRegistry.addRecipe(new ItemStack(freezer), "xyx", "yzy", "xyx", 'x', new ItemStack(Blocks.iron_block), 'y', new ItemStack(Blocks.ice), 'z', new ItemStack(esky));
		GameRegistry.addRecipe(new ItemStack(freezer), "xyx", "yzy", "xyx", 'x', new ItemStack(Blocks.iron_block), 'y', new ItemStack(Blocks.packed_ice), 'z', new ItemStack(esky));
		
		ItemStack camelStack = new ItemStack(camelPack);
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("camelPackFill", 0);
		tag.setInteger("camelPackMax", 100);
		tag.setBoolean("isCamelPack", true);
		tag.setString("camelPath", Item.itemRegistry.getNameForObject(camelPack));
		camelStack.setTagCompound(tag);
		GameRegistry.addRecipe(camelStack, "xxx", "xyx", "xxx", 'x', new ItemStack(Items.leather), 'y', new ItemStack(Items.glass_bottle));
		
		ItemStack camelStack2 = camelStack.copy();
		camelStack2.getTagCompound().setInteger("camelPackFill", 25);
		GameRegistry.addRecipe(camelStack2, "xxx", "xyx", "xxx", 'x', new ItemStack(Items.leather), 'y', new ItemStack(Items.potionitem, 1, 0));
	}
	*/
}
