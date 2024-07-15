package magicbees.bees.allele.flowerProvider;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import forestry.api.genetics.IFlowerGrowthHelper;
import forestry.api.genetics.IFlowerGrowthRule;
import forestry.api.genetics.IFlowerRegistry;
import forestry.api.genetics.IIndividual;
import forestry.api.genetics.IPollinatable;
import magicbees.main.utils.LocalizationManager;
import magicbees.main.utils.compat.ArsMagicaHelper;

public class FlowerProviderArsMagicaFlower extends FlowerProvider implements IFlowerGrowthRule {

    public FlowerProviderArsMagicaFlower() {
        registerPlantableFlower(ArsMagicaHelper.blackOrchid, 0, 1);
        registerPlantableFlower(ArsMagicaHelper.desertNova, 0, 1);
        registerPlantableFlower(ArsMagicaHelper.aum, 0, 1);
        registerPlantableFlower(ArsMagicaHelper.wakebloom, 0, 1);
        registerPlantableFlower(ArsMagicaHelper.tarmaRoot, 0, 1);
        registerGrowthRule(this);
    }

    @Override
    public String getFlowerType() {
        return "flowersArsMagica";
    }

    @Override
    public String getDescription() {
        return LocalizationManager.getLocalizedString("flowerProvider.arsmagica");
    }

    @Override
    public ItemStack[] affectProducts(World world, IIndividual genome, int x, int y, int z, ItemStack[] products) {
        return products;
    }

    @Override
    public boolean isAcceptedPollinatable(World world, IPollinatable pollinatable) {
        return pollinatable.getPlantType().size() > 1;
    }

    @Override
    @Deprecated
    public boolean growFlower(World world, IIndividual genome, int x, int y, int z) {
        return growFlower(world, x, y, z);
    }

    @Override
    public boolean growFlower(IFlowerGrowthHelper helper, String flowerType, World world, int x, int y, int z) {
        return growFlower(world, x, y, z);
    }

    @Override
    @Deprecated
    public boolean growFlower(IFlowerRegistry fr, String flowerType, World world, IIndividual individual, int x, int y,
            int z) {
        return growFlower(world, x, y, z);
    }

    private boolean growFlower(World world, int x, int y, int z) {
        boolean flag = false;
        Block blockDown = world.getBlock(x, y - 1, z);
        if (world.getBlock(x, y, z).isAir(world, x, y, z)) {
            if (blockDown == Blocks.dirt || blockDown == Blocks.grass) {
                int dart = world.rand.nextInt(100);
                Block block = ArsMagicaHelper.tarmaRoot;
                if (dart > 60) {
                    block = ArsMagicaHelper.blackOrchid;
                } else if (dart > 30) {
                    block = ArsMagicaHelper.aum;
                }
                world.setBlock(x, y, z, block);
                flag = true;
            } else if (blockDown == Blocks.sand) {
                world.setBlock(x, y, z, ArsMagicaHelper.desertNova);
                flag = true;
            } else if (blockDown == Blocks.stone) {
                world.setBlock(x, y, z, ArsMagicaHelper.tarmaRoot);
                flag = true;
            } else if (blockDown == Blocks.water || blockDown == Blocks.water) {
                world.setBlock(x, y, z, ArsMagicaHelper.wakebloom);
                flag = true;
            }
        }
        return flag;
    }
}
