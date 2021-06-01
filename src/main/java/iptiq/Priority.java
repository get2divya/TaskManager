package iptiq;


public enum Priority {

    LOW(0),
    MEDIUM(1),
    HIGH(2);

    public final int value;

    /**
     * @param value Integer value of Priority
     */
    Priority(int value) {
        this.value = value;
    }

    /**
     * @return The Integer value of Priority
     */
    public int Value() {
        return this.value;
    }

}
