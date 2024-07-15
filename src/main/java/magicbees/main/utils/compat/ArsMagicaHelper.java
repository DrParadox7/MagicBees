package magicbees.main.utils.compat;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import cpw.mods.fml.common.Loader;
import magicbees.main.Config;
import magicbees.main.utils.BlockInterface;
import magicbees.main.utils.ItemInterface;

public class ArsMagicaHelper implements IModHelper {

    public static Block resourceBlock;
    public static Block blackOrchid;
    public static Block desertNova;
    public static Block aum;
    public static Block wakebloom;
    public static Block tarmaRoot;

    public static Item itemResource;
    public static Item essence;

    public enum ResourceType {
        VINTEUM_DUST,
        ARCANE_COMPOUND,
        ARCANE_ASH,
        PURIFIED_VINTEUM,
        CHIMERITE,
        BLUE_TOPAZ,
        SUNSTONE,
        MOONSTONE,
    }

    public enum EssenceType {
        ARCANE,
        EARTH,
        AIR,
        FIRE,
        WATER,
        PLANT,
        ICE,
        LIGHTNING,
        LIFE,
        ENDER,
        PURE,
        HIGH,
        BASE,
    }

    private static boolean isArsMagicaPresent = false;
    public static final String Name = "arsmagica2";

    public static boolean isActive() {
        return isArsMagicaPresent;
    }

    public void preInit() {
        if (Loader.isModLoaded(Name) && Config.arsMagicaActive) {
            isArsMagicaPresent = true;
        }
    }

    public void init() {
        if (isActive()) {
            getBlocks();
            getItems();
        }
    }

    public void postInit() {}

    private static void getBlocks() {
        ArsMagicaHelper.resourceBlock = BlockInterface.getBlock(Name, "AMOres");
        ArsMagicaHelper.blackOrchid = BlockInterface.getBlock(Name, "blueOrchid");
        ArsMagicaHelper.desertNova = BlockInterface.getBlock(Name, "desertNova");
        ArsMagicaHelper.aum = BlockInterface.getBlock(Name, "Aum");
        ArsMagicaHelper.wakebloom = BlockInterface.getBlock(Name, "wakebloom");
        ArsMagicaHelper.tarmaRoot = BlockInterface.getBlock(Name, "TarmaRoot");
    }

    private static void getItems() {
        ArsMagicaHelper.itemResource = ItemInterface.getItem(Name, "itemOre");
        ArsMagicaHelper.essence = ItemInterface.getItem(Name, "essence");
    }
}
