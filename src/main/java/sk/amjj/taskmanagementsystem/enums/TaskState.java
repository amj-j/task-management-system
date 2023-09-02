package sk.amjj.taskmanagementsystem.enums;

public enum TaskState {
    TODO(0),
    IN_PROGRESS(1),
    COMPLETED(2);

    private final int order;

    private TaskState(int order) {
        this.order = order;
    }

    public int getOrder() {
        return this.order;
    }

}
