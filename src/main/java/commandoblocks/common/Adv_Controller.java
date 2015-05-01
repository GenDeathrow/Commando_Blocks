package commandoblocks.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import commandoblocks.block.tileentity.TileEntityAdvanceCommand;


public class Adv_Controller 
{
	public static HashMap<ArrayList, TileEntity> CommandBlockList = new HashMap(); 
	
    public static void ifExist(World world,Block Block)
    {
    	// Loops every so often to make sure block still exist
    }
    
	public static void onBlockPlaced(World world, int x, int y, int z)
	{
		
		world.getBlock(x, y, z);

		TileEntity TileEntity = world.getTileEntity(x, y, z);
		ArrayList cords = new ArrayList();
		cords.add(x); cords.add(y); cords.add(z);
		
		CommandBlockList.put(cords, TileEntity);
		
		System.out.println("Command block placed at"+ x +","+ y +","+ z);
		
	}
	
	public static void UpdateTick(World world)
	{
		
		List list = world.loadedTileEntityList;
		
		
		Iterator it = list.iterator();
		while(it.hasNext())
		{
			TileEntity entry = (TileEntity) it.next();
			
			if(entry.equals(null)) continue;
			if(world.equals(null)) continue;

				
			 if(entry instanceof TileEntityAdvanceCommand)
			 {
					TileEntityAdvanceCommand AdvBlock = (TileEntityAdvanceCommand) entry; 

					//if(!isCunkLoaded(world, AdvBlock))
					{
						continue;
					}
					

					//AdvBlock.LoopUpdate();
				 
			 }
			 
		}

	}

	public static void removeAdvCommandBlock(int x, int y, int z) 
	{
		ArrayList cords = new ArrayList();
		cords.add(x); cords.add(y); cords.add(z);
		
		CommandBlockList.remove(cords);
		
		System.out.println("Command block Removed at"+ x +","+ y +","+ z);
	
	}
	


}
