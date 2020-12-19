package fun.nothaving.jest.utils.entity;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fun.nothaving.jest.engine.StateManager;

public class EntDifficulty {
    public static void setMobDifficulty(LivingEntity creature) {
		//Pre: A LivingEntity Entity
		//Post: Manipulates the mobs in different ways
		
        //If the entity is a zombie
        int difficulty = StateManager.getCustomDifficulty();

		if(creature.getType() == EntityType.ZOMBIE) {
			
			//Switch for the different weapon modes
			switch(difficulty) {
				case 1:
					//Equips zombies with wooden swords
					creature.getEquipment().setItemInMainHand(new ItemStack(Material.WOODEN_SWORD, 1));
					break;
				case 2:
					//Equips zombies with iron swords
					creature.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD, 1));
					break;
				case 3:
					//Equips zombies with diamond swords
					creature.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_SWORD, 1));
					break;
				default:
					//This should not happen
					creature.getEquipment().setItemInMainHand(new ItemStack(Material.CARROT_ON_A_STICK, 1));
					break;
			}
		}
		
		//If the entity is a Skeleton
		if(creature.getType() == EntityType.SKELETON) {
			
			//Switch for the different weapon modes
			switch(difficulty) {
				case 2:
					creature.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2, true, false));
					break;
				case 3:
					//Gives Skeletons potions
					creature.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1, true, false));
					break;
				default:
					//This should not happen
					creature.getEquipment().setItemInMainHand(new ItemStack(Material.CARROT_ON_A_STICK, 1));
					break;
			}
		}
		
		//If the entity is a Creeper
		if(creature.getType() == EntityType.CREEPER) {
			
			//Switch for the different weapon modes
			switch(difficulty) {
				case 1:
					//Gives Creeper potions
					creature.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1, true, false));
					break;
				case 2:
					//Gives Creeper potions
					creature.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, true, false));
					creature.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 2, true, false));
					break;
				case 3:
					//Gives Creeper potions
					creature.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 2, true, false));
					creature.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 3, true, false));
					creature.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 4, true, false));
					break;
				default:
					//This should not happen
					creature.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 20, true, false));
					break;
			}
		}
		
		
		//If the entity is a Spider
		if(creature.getType() == EntityType.SPIDER) {
					
			//Switch for the different weapon modes
			switch(difficulty) {
				case 1:
					//Gives Spider potions
					creature.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, true, false));
					creature.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1, true, false));
					break;
				case 2:
					//Gives Spider potions
					creature.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2, true, false));
					creature.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 2, true, false));
					break;
				case 3:
					//Gives Spider potions
					creature.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 2, true, false));
					creature.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 4, true, false));
					creature.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 4, true, false));
					break;
				default:
					//This should not happen
					creature.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 20, true, false));
					break;
			}
		}
		
	}
	
}
