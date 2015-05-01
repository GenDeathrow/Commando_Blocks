package commandoblocks.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCommandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import commandoblocks.block.tileentity.TileEntityAdvanceCommand;
import commandoblocks.common.Adv_Controller;
import commandoblocks.handlers.ObjectHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AdvanceCommandBlock extends BlockCommandBlock
{
	
	@Override
	  public void onEntityWalking(World p_149724_1_, int p_149724_2_, int p_149724_3_, int p_149724_4_, Entity p_149724_5_) 
	  {
			TileEntityAdvanceCommand tileentitycommandblock = (TileEntityAdvanceCommand)p_149724_1_.getTileEntity( p_149724_2_, p_149724_3_, p_149724_4_);

	        tileentitycommandblock.onWalked(p_149724_5_);
	     super.onEntityWalking(p_149724_1_, p_149724_2_, p_149724_3_, p_149724_4_, p_149724_5_);
	  }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
	
        return new TileEntityAdvanceCommand();
    }
	
	   /**
     * Called when the block is placed in the world.
     */
	@Override
    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
		TileEntityAdvanceCommand tileentitycommandblock = (TileEntityAdvanceCommand)p_149689_1_.getTileEntity(p_149689_2_, p_149689_3_, p_149689_4_);

        if (p_149689_6_.hasDisplayName())
        {
            tileentitycommandblock.func_145993_a().func_145754_b(p_149689_6_.getDisplayName());
        }
        
        
    }

	
	public void onBlockClicked(World p_149699_1_, int p_149699_2_, int p_149699_3_, int p_149699_4_, EntityPlayer p_149699_5_)
	{
		super.onBlockClicked(p_149699_1_, p_149699_2_, p_149699_3_, p_149699_4_, p_149699_5_);
	}
	
	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
	{
		  super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
		  Adv_Controller.removeAdvCommandBlock( p_149749_2_, p_149749_3_, p_149749_4_);
	}

    /**
     * Ticks the block if it's been scheduled
     */
	@Override
    public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
    {
    	super.updateTick(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);
    }
	
	@Override
    public int tickRate(World p_149738_1_)
    {
        return 5;
    }
	
    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
	@Override
	public void onBlockAdded(World world, int p_149726_2_, int p_149726_3_, int p_149726_4_) 
	{	
		Adv_Controller.onBlockPlaced(world, p_149726_2_, p_149726_3_,p_149726_4_);
    }
	
	@SideOnly(Side.CLIENT)
	public IIcon getTexture()
	{
		
		
		return this.blockIcon;
		
	}
	
    /**
     * Called upon block activation (right click on the block.)
     */
	
    public boolean onBlockClicked(World world, EntityPlayer player, int x, int y, int z)
    {
		System.out.println("Block Clicked");
    	Item item = player.getHeldItem().getItem();
    	
    	if(item != null)
    	{
    		Block block = Block.getBlockFromItem(item);
    		
    		if(block != Blocks.air)
    		{
    			IIcon icon = block.getIcon(0, 0);
    			
    			this.blockIcon = icon;
    			world.markBlockForUpdate(x, y, z);
    		}
    	}
        return true;
    }
    
    //@Override
    //public int getRenderType()
    //{
        // You know that render ID we talked about earlier? You need to access it here.
    //    return 500;
    //}
    
}
