package io.github.firetamer.dbb.impl;

import com.matyrobbrt.lib.nbt.BaseNBTList;
import io.github.firetamer.dbb.api.extensions.ApiExtension;
import io.github.firetamer.dbb.api.extensions.dbb.PlayerData;
import io.github.firetamer.dbb.api.player_data.PlayerSkill;
import io.github.firetamer.dbb.api.player_data.PlayerStat;
import io.github.firetamer.dbb.util.nbt.NBTBuilder;
import io.github.firetamer.dbb.util.nbt.NBTManager;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.List;
import java.util.UUID;

class PlayerDataImpl implements PlayerData {

	@ApiExtension(Deserializer.class)
	private static final PlayerData.Deserializer DESERIALIZER = nbt -> {
		PlayerDataImpl data = new PlayerDataImpl(UUID.fromString(nbt.getString("PlayerUUID")));
		data.deserializeNBT(nbt);
		return data;
	};

	private final UUID playerUUID;

	private final NBTManager nbtManager = new NBTManager();

	private final BaseNBTList<PlayerSkill, StringNBT> skills = createAndTrack("Skills", new BaseNBTList<>(type -> StringNBT.valueOf(type.getRegistryName().toString()),
			nbt -> PlayerSkill.REGISTRY.getValue(new ResourceLocation(nbt.getAsString()))));

	private final BaseNBTList<PlayerStat<?>, CompoundNBT> stats = createAndTrack("Stats",
			new BaseNBTList<>(PlayerStat::serializeNBT, PlayerStat::fromNBT));

	public PlayerDataImpl(final UUID playerUUID) {
		this.playerUUID = playerUUID;
	}

	@Override
	public UUID getPlayerUUID() {
		return playerUUID;
	}

	@Override
	public List<PlayerSkill> getSkills() {
		return skills;
	}

	@Override
	public List<PlayerStat<?>> getStats() {
		return stats;
	}

	@Override
	public CompoundNBT serializeNBT() {
		return NBTBuilder.of(nbtManager.serializeNBT())
				.putStringUUID("PlayerUUID", playerUUID)
				.build();
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		nbtManager.deserializeNBT(nbt);
	}

	private <T extends INBTSerializable<?>> T createAndTrack(String keyName, T value) {
		nbtManager.track(keyName, value);
		return value;
	}
}
