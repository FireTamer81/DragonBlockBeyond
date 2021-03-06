package io.github.firetamer.dbb.api.extensions.dbb;

import com.matyrobbrt.lib.network.BaseNetwork;
import io.github.firetamer.dbb.api.client.ClientDataHolder;
import io.github.firetamer.dbb.api.extensions.ApiExtendable;
import io.github.firetamer.dbb.api.extensions.ApiExtensions;
import io.github.firetamer.dbb.api.player_data.PlayerSkill;
import io.github.firetamer.dbb.api.player_data.PlayerStatType;
import io.github.firetamer.dbb.api.player_data.PlayerStat;
import io.github.firetamer.dbb.network.DBBNetwork;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;
import javax.annotation.WillNotClose;
import java.util.Map;
import java.util.UUID;

public interface PlayerDataManager extends INBTSerializable<CompoundNBT> {

    static PlayerDataManager getManagerForServer(@Nonnull @WillNotClose final MinecraftServer server) {
        return ApiExtensions.withExtension(Getter.class, ext -> ext.getManagerForServer(server));
    }

    /**
     * Gets the data for the player with the specified {@code playerUUID}. <br>
     * This will create the data if it doesn't exist.
     *
     * @param playerUUID the UUID to get the data for
     * @return the data of the player with the uuid
     */
    @Nonnull
    PlayerData getDataForPlayer(UUID playerUUID);

    /**
     * Gets the data for the {@code player}. <br>
     * This will create the data if it doesn't exist
     *
     * @param player the player to get the data for
     * @return the data of the player
     */
    @Nonnull
    default PlayerData getDataForPlayer(PlayerEntity player) {
        return getDataForPlayer(player.getUUID());
    }

    /**
     * Getter for all the player data currently stored.
     * <b>DO NOT MODIFY THE RETURNED DATA</b>. <br>
     * Implementations should return an immutable map.
     *
     * @return all the currently stored {@link PlayerData}
     */
    Map<UUID, PlayerData> getAllPlayerData();

    /**
     * Marks the manager dirty, which means that it should be saved
     */
    void setChanged();

    /**
     * Checks if the {@code player} has the specified {@link PlayerSkill}
     *
     * @param player the player to check
     * @param skill  the skill to check for
     * @return if the player has the skill
     */
    default boolean playerHasSkill(PlayerEntity player, PlayerSkill skill) {
        return getDataForPlayer(player).getSkills().contains(skill);
    }

    /**
     * Adds a {@link PlayerSkill} to the {@code player}
     *
     * @param player the player to add the skill to
     * @param skill  the skill to add
     */
    default void addSkillToPlayer(PlayerEntity player, PlayerSkill skill) {
        getDataForPlayer(player).getSkills().add(skill);
        setChanged();
    }

    ;

    /**
     * Removes a {@link PlayerSkill} from the {@code player}
     *
     * @param player the player to remove the skill from
     * @param skill  the skill to remove
     */
    default void removeSkillFromPlayer(PlayerEntity player, PlayerSkill skill) {
        getDataForPlayer(player).getSkills().remove(skill);
        setChanged();
    }

    /**
     * Sets the {@link PlayerStat#value} of
     * the {@link PlayerStatType} for a plyaer
     * @param player the player to set the stat value to
     * @param statType the type of the stat
     * @param newValue the new stat value
     * @param <T> the type of the stat
     */
    default <T> void setStatValue(PlayerEntity player, PlayerStatType<T> statType, T newValue) {
        getDataForPlayer(player).setStatValue(statType, newValue);
        setChanged();
    }

    /**
     * Syncs the manager with all the players in the server
     */
    default void syncWithClients() {
        BaseNetwork.sendToAll(DBBNetwork.INSTANCE.mainChannel, new ClientDataHolder.PlayerDataSyncMessage(this));
    }

    /**
     * Syncs the manager to a specific {@code player}
     * @param player the player to sync the data with
     */
    default void syncWithPlayer(ServerPlayerEntity player) {
        BaseNetwork.sendTo(DBBNetwork.INSTANCE.mainChannel, new ClientDataHolder.PlayerDataSyncMessage(this), player);
    }

    @FunctionalInterface
    interface Getter extends ApiExtendable {

        PlayerDataManager getManagerForServer(@WillNotClose @Nonnull final MinecraftServer server);

    }

    @FunctionalInterface
    interface Deserializer extends ApiExtendable {

        PlayerDataManager deserialize(CompoundNBT nbt);

    }

}
