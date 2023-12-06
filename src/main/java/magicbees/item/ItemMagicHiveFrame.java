package magicbees.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestry.api.apiculture.IBee;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.apiculture.IBeeModifier;
import forestry.api.apiculture.IHiveFrame;
import magicbees.item.types.HiveFrameType;
import magicbees.main.CommonProxy;
import magicbees.main.utils.TabMagicBees;

public class ItemMagicHiveFrame extends Item implements IHiveFrame {

    private HiveFrameType type;

    public ItemMagicHiveFrame(HiveFrameType frameType) {
        super();
        this.type = frameType;
        this.setMaxDamage(this.type.maxDamage);
        this.setMaxStackSize(1);
        this.setCreativeTab(TabMagicBees.tabMagicBees);
        this.setUnlocalizedName("frame" + frameType.getName());
        GameRegistry.registerItem(this, "frame" + frameType.getName());
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon(CommonProxy.DOMAIN + ":frame" + type.getName());
    }

    // --------- IHiveFrame functions -----------------------------------------

    @Override
    public ItemStack frameUsed(IBeeHousing housing, ItemStack frame, IBee queen, int wear) {
        frame.setItemDamage(frame.getItemDamage() + wear);

        if (frame.getItemDamage() >= frame.getMaxDamage()) {
            // Break the frame.
            frame = null;
        }

        return frame;
    }

    @Override
    public IBeeModifier getBeeModifier() {
        return type;
    }

    @Override
    public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
        return false;
    }

    @Override
    public boolean isRepairable() {
        return this.type != HiveFrameType.OBLIVION;
    }
}
