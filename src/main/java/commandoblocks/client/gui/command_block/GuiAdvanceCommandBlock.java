package commandoblocks.client.gui.command_block;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityCommandBlock;

import org.lwjgl.input.Keyboard;

import commandoblocks.block.tileentity.TileEntityAdvanceCommand;
import commandoblocks.core.CommandoBlocks;
import commandoblocks.network.AdvCBMessage;
import cpw.mods.fml.client.config.GuiCheckBox;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAdvanceCommandBlock extends GuiScreen
{
	private TileEntityAdvanceCommand  localCommandBlock; 
    private GuiButton doneBtn;
    private GuiButton cancelBtn;
   
    //triggers
    private GuiCheckBox onwalk;
    private GuiCheckBox loop;
    
    private GuiTextField updateTick;
    
    private GuiCheckBox unbreakable;
    private GuiCheckBox globalcommand;
	
	   public GuiAdvanceCommandBlock(TileEntityAdvanceCommand AdvBlock)
	    {
	        this.localCommandBlock = AdvBlock;
	    }

	    public void updateScreen()
	    {
	        this.updateTick.updateCursorCounter();
	    }

	    /**
	     * Adds the buttons (and other controls) to the screen in question.
	     */
	    public void initGui()
	    {
	        Keyboard.enableRepeatEvents(true);
	        this.buttonList.clear();
	        
	        this.buttonList.add(this.doneBtn = new GuiButton(0, this.width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150, 20, I18n.format("gui.done", new Object[0])));
	        this.buttonList.add(this.cancelBtn = new GuiButton(1, this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20, I18n.format("gui.cancel", new Object[0])));
	        
	        this.buttonList.add(this.loop = new GuiCheckBox(20, this.width / 6, this.height / 6, "Loop x amount ticks", this.localCommandBlock.getLoop()));
	        
	        int xloop = this.loop.xPosition + this.loop.width + 4; 
	        
	        this.updateTick = new GuiTextField(fontRendererObj, xloop, this.height / 6, 30, 15);
	        this.updateTick.setMaxStringLength(4);
	        this.updateTick.setText(""+ this.localCommandBlock.getTickUpdate());
	        
	        this.buttonList.add(new GuiButton(3, this.width / 2 - 75, this.height / 4 + 100, 150, 20, "Command"));
	        		
	        //this.buttonList.add(this.onwalk = new GuiCheckBox(20, this.width / 6, this.height / 6 + 30, "Triggered when walked on", false));
	        //this.buttonList.add(this.globalcommand = new GuiCheckBox(20, this.width / 6, this.height / 6 + 60, "Loop x amount ticks", false));
	        	        
	    }

	    /**
	     * Called when the screen is unloaded. Used to disable keyboard repeat events
	     */
	    public void onGuiClosed()
	    {
	        Keyboard.enableRepeatEvents(false);
	    }

	    protected void actionPerformed(GuiButton p_146284_1_)
	    {
	        if (p_146284_1_.enabled)
	        {
	        	if (p_146284_1_.id == 0)
	            {
	        		
	        		NBTTagCompound nbt = new NBTTagCompound();
	        		nbt.setBoolean("loop", this.loop.isChecked());
	        		nbt.setInteger("updateTick", Integer.parseInt(this.updateTick.getText()));
	        		
	        		nbt.setInteger("x", this.localCommandBlock.xCoord);
	        		nbt.setInteger("y", this.localCommandBlock.yCoord);
	        		nbt.setInteger("z", this.localCommandBlock.zCoord);
	     
	        		CommandoBlocks.instance.network.sendToServer(new AdvCBMessage(nbt, mc.thePlayer.getCommandSenderName()));
	        		this.mc.displayGuiScreen((GuiScreen)null);
	            }
	            if (p_146284_1_.id == 1)
	            {
	                this.mc.displayGuiScreen((GuiScreen)null);
	            }
	            if (p_146284_1_.id == 3)
	            {
	            	this.mc.displayGuiScreen(new GuiCommandBlockExt(((TileEntityCommandBlock)this.localCommandBlock).func_145993_a(), this));
	            }	            
	            
	 
	        }
	    }

	    /**
	     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
	     */
	    protected void keyTyped(char p_73869_1_, int p_73869_2_)
	    {
	        this.updateTick.textboxKeyTyped(p_73869_1_, p_73869_2_);
	    }

	    /**
	     * Called when the mouse is clicked.
	     */
	    protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_)
	    {
	        super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
	        this.updateTick.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
	    }

	    /**
	     * Draws the screen and all the components in it.
	     */
	    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
	    {
	        this.drawDefaultBackground();
	        FontRenderer fontrenderer = this.fontRendererObj;
	        
	        
	        this.updateTick.drawTextBox();

	        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	    }
	    

}
