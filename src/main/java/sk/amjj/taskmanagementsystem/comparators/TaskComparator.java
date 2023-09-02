package sk.amjj.taskmanagementsystem.comparators;

import java.util.Comparator;

import sk.amjj.taskmanagementsystem.enums.SortDirection;
import sk.amjj.taskmanagementsystem.enums.SortTasksBy;
import sk.amjj.taskmanagementsystem.model.entities.Task;

public class TaskComparator implements Comparator<Task> {

    private final SortTasksBy sortBy;

    private final SortDirection sortDir;

    public TaskComparator(SortTasksBy sortBy, SortDirection sortDir) {
        this.sortBy = sortBy;
        this.sortDir = sortDir;
    }
    
    @Override
    public int compare(Task a, Task b) {
        int rtrn = 0;
        switch (sortBy) {
            case NAME:
                rtrn = a.getName().compareTo(b.getName());
                break;

            case DUE_DATE:
                rtrn = a.getDueDate().compareTo(b.getDueDate());
                break;

            case STATE:
                rtrn = a.getState().getOrder() - b.getState().getOrder();
                break;

            case CATEGORY:
                rtrn = a.getCategory().getName().compareTo(b.getCategory().getName());
                break;

            default:
                break;
        }

        if (sortDir == SortDirection.DESC) {
            if (rtrn < 0) {
                rtrn = 1;
            }
            else if (rtrn > 0) {
                rtrn = -1;
            }
        }

        return rtrn;
    }

}
