package commandoblocks.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import commandoblocks.block.tileentity.TileEntityAdvanceCommand;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class AdvCBMessage implements IMessage
{
	private String player;
	private NBTTagCompound tags;

	public AdvCBMessage(){}
	
	public AdvCBMessage(NBTTagCompound _nbt, String player)
	{
		this.tags = _nbt;
		this.tags.setString("sender", player);
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.tags = ByteBufUtils.readTag(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeTag(buf, this.tags);
	}


	public static class Handler implements IMessageHandler<AdvCBMessage, IMessage> {
	       
		public Handler(){}

		@Override
		public IMessage onMessage(AdvCBMessage message, MessageContext ctx) 
		{
			NBTTagCompound recievedData = message.tags;
			
			EntityPlayer sender = ctx.getServerHandler().playerEntity;
			
			if(sender != null) updateTileEntity(recievedData , sender);
			
			return null;
		}
		
		public void updateTileEntity(NBTTagCompound tags, EntityPlayer player)
		{	
			TileEntity TE = player.worldObj.getTileEntity(tags.getInteger("x"), tags.getInteger("y"), tags.getInteger("z"));
			
			if(TE != null && TE instanceof TileEntityAdvanceCommand)
			{
				TileEntityAdvanceCommand AdvBlock = (TileEntityAdvanceCommand) TE;
				
				AdvBlock.setLoop(tags.getBoolean("loop"));
				AdvBlock.setTickUpdate(tags.getInteger("updateTick"));
				player.worldObj.markBlockForUpdate(tags.getInteger("x"), tags.getInteger("y"), tags.getInteger("z"));
			}
			
		}
    }
}
