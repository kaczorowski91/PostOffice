package pl.kaczorowski.postoffice.entity;

public enum Status {
    REGULAR(0),
    VIP(1),
    URGENT(2);

    private final int importance;

    Status(int importance) {
        this.importance = importance;
    }

    public int getImportance() {
        return importance;
    }
}
