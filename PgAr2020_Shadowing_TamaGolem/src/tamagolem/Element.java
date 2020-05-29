package tamagolem;

import tamagolem.equilibrium.EquilibriumManager;

public enum Element {
    WATER, FIRE, GRASS, ROCK, STEEL;

    public int getDamage(Element versusWho) {
        return EquilibriumManager.getDamage(this, versusWho);
    }

    /** Number of element */
    public static final byte N = (byte) Element.values().length;
}