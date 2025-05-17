package com.example.forestryaddon.Frame;

import forestry.api.apiculture.IBeeModifier;
import forestry.api.apiculture.genetics.IBeeSpecies;
import forestry.api.genetics.IGenome;
import forestry.api.genetics.IMutation;

import javax.annotation.Nullable;

public class CustomBeeModifier implements IBeeModifier {

    private final float lifespanModifier;
    private final float productionModifier;
    private final float mutationModifier;
    private final float pollinationModifier;
    private final float geneticDecayModifier;
    private final boolean sealed;
    private final boolean alwaysActive;
    private final boolean sunlightSimulated;
    private final boolean hellish;

    // Конструктор с параметрами
    public CustomBeeModifier(float lifespanModifier, float productionModifier, float mutationModifier,
                             float pollinationModifier, float geneticDecayModifier,
                             boolean sealed, boolean alwaysActive, boolean sunlightSimulated, boolean hellish) {
        this.lifespanModifier = lifespanModifier;
        this.productionModifier = productionModifier;
        this.mutationModifier = mutationModifier;
        this.pollinationModifier = pollinationModifier;
        this.geneticDecayModifier = geneticDecayModifier;
        this.sealed = sealed;
        this.alwaysActive = alwaysActive;
        this.sunlightSimulated = sunlightSimulated;
        this.hellish = hellish;
    }

    @Override
    public float modifyProductionSpeed(IGenome genome, float currentSpeed) {
        return currentSpeed * productionModifier;
    }

    @Override
    public float modifyAging(IGenome genome, @Nullable IGenome mate, float currentAging) {
        return currentAging * lifespanModifier;
    }

    @Override
    public float modifyMutationChance(IGenome genome, IGenome mate, IMutation<IBeeSpecies> mutation, float currentChance) {
        return currentChance * mutationModifier;
    }

    @Override
    public float modifyPollination(IGenome genome, float currentPollination) {
        return currentPollination * pollinationModifier;
    }

    @Override
    public float modifyGeneticDecay(IGenome genome, float currentDecay) {
        return currentDecay * geneticDecayModifier;
    }

    @Override
    public boolean isSealed() {
        return sealed;
    }

    @Override
    public boolean isAlwaysActive(IGenome genome) {
        return alwaysActive;
    }

    @Override
    public boolean isSunlightSimulated() {
        return sunlightSimulated;
    }

    @Override
    public boolean isHellish() {
        return hellish;
    }
}
