package com.github.franckyi.ibeeditor.models;

import net.minecraft.nbt.NBTTagCompound;

public class PotionEffectModel {

    private int id;
    private int amplifier;
    private int duration;
    private boolean ambient;
    private boolean showParticles;

    public PotionEffectModel() {
    }

    public PotionEffectModel(int id, int amplifier, int duration, boolean ambient, boolean showParticles) {
        this.id = id;
        this.amplifier = amplifier;
        this.duration = duration;
        this.ambient = ambient;
        this.showParticles = showParticles;
    }

    public static PotionEffectModel fromNBT(NBTTagCompound compound) {
        PotionEffectModel model = new PotionEffectModel();
        model.setId(compound.getByte("Id"));
        model.setAmplifier(compound.getByte("Amplifier"));
        model.setDuration(compound.getInteger("Duration"));
        model.setAmbient(compound.getByte("Ambient") == 1);
        model.setShowParticles(compound.getByte("ShowParticles") == 1);
        return model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public void setAmplifier(int amplifier) {
        this.amplifier = amplifier;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isAmbient() {
        return ambient;
    }

    public void setAmbient(boolean ambient) {
        this.ambient = ambient;
    }

    public boolean isShowParticles() {
        return showParticles;
    }

    public void setShowParticles(boolean showParticles) {
        this.showParticles = showParticles;
    }

    public NBTTagCompound toNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setByte("Id", (byte) getId());
        compound.setByte("Amplifier", (byte) getAmplifier());
        compound.setInteger("Duration", getDuration());
        compound.setByte("Ambient", (byte) (isAmbient() ? 1 : 0));
        compound.setByte("ShowParticles", (byte) (isShowParticles() ? 1 : 0));
        return compound;
    }
}
