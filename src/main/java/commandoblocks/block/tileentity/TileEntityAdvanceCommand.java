package commandoblocks.block.tileentity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.world.World;

import commandoblocks.common.commands.server.AdvanceCommandBlockLogic;

public class TileEntityAdvanceCommand extends TileEntityCommandBlock
{
	private int tickCounter;
	private int UpdateTick;
	private boolean isloop;
	
	private boolean isWalkedOn;
	private List<Entity> walkedOnList;
	private boolean onStruckByLighting;
	
	private boolean unbreakable;
	
	private int minTick = 10;
	
	private boolean globalCommand;
	
	private AdvanceCommandBlockLogic AdvCommandBlockLogic = new AdvanceCommandBlockLogic();

	
	public TileEntityAdvanceCommand()
	{
		this.tickCounter = 0;
		this.UpdateTick = 20;
		this.isloop = true;
		
		this.isWalkedOn = false;
		this.walkedOnList = new ArrayList();
		
		this.onStruckByLighting = false;
		
		this.unbreakable = false;
		this.globalCommand = true;
		
		
	}
	
	public void onWalked(Entity entity)
	{
		if((this.isWalkedOn && this.walkedOnList.size() <= 0 )||( this.isWalkedOn && this.walkedOnList.contains(entity)))
		{
			this.worldObj.scheduleBlockUpdate(this.xCoord, this.yCoord, this.zCoord, this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord), 5);			
		}
	}
	
	public void updateEntity() 
	{
		super.updateEntity();
		
		if(this.isloop)
		{
			if (!this.globalCommand)
			{
				if(!isCunkLoaded(this.worldObj, this))
				{
					return;
				}
			}		

			LoopUpdate();
		}
	}
	
	public void LoopUpdate()
	{
		if(this.isloop)
		{
			if(this.tickCounter >= this.UpdateTick)
			{
				this.worldObj.scheduleBlockUpdate(this.xCoord, this.yCoord, this.zCoord, this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord), 5);
				this.tickCounter = 0;
			}
			else
			{
				this.tickCounter++;
			}
		}
	}
		
		public void setTickUpdate(int newTick)
		{
			this.UpdateTick = newTick;
			this.markDirty();
		}
		public int getTickUpdate()
		{
			return this.UpdateTick;
		}
		
		public void setLoop(Boolean bool)
		{
			this.isloop = bool;
			this.markDirty();
		}
		
		public boolean getLoop()
		{
			return this.isloop;
		}
		
		public int getCounter()
		{
			return this.tickCounter;
		}
	
		@Override
	 	public void writeToNBT(NBTTagCompound nbt)
	    {
	        super.writeToNBT(nbt);
	        nbt.setInteger("tickUpdate", this.UpdateTick);
	        nbt.setInteger("currentTick", this.tickCounter);
	        nbt.setBoolean("isLoop", this.isloop);
	        
	        nbt.setBoolean("globalCommand", this.globalCommand);
	        
	        nbt.setBoolean("unbreakable", this.unbreakable);
	    }

		@Override
	    public void readFromNBT(NBTTagCompound nbt)
	    {
	        super.readFromNBT(nbt);
	        this.UpdateTick = nbt.getInteger("tickUpdate");
	        this.tickCounter = nbt.getInteger("currentTick");
	        this.isloop = nbt.getBoolean("isLoop");
	        
	        this.globalCommand = nbt.getBoolean("globalCommand");
	        
	        this.unbreakable = nbt.getBoolean("unbreakable");
	    }
		
		private static Boolean isCunkLoaded(World world, TileEntityAdvanceCommand AdvBlock)
		{
			
			if(world.getChunkFromBlockCoords(AdvBlock.xCoord, AdvBlock.yCoord).isChunkLoaded) return true;
			return false;
		}
}
