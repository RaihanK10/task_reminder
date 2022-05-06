package ve.com.PROJECT.msd.viewmodel;

import android.support.annotation.NonNull;

import java.security.InvalidParameterException;

import ve.com.PROJECT.msd.enums.TaskViewModelType;
import ve.com.PROJECT.msd.model.Task;



public class TaskViewModel {

    //DATA
    private Task task = null;
    private String headerTitle = null;
    private boolean headerTitleRed;
    private TaskViewModelType viewModelType;


    public TaskViewModel(@NonNull Task task, @NonNull TaskViewModelType viewModelType) {

        if(viewModelType == TaskViewModelType.HEADER)
            throw new InvalidParameterException("TaskViewModelType cannot be of type HEADER");

        this.task = task;
        this.viewModelType = viewModelType;
    }

    public TaskViewModel(@NonNull String headerTitle, @NonNull boolean headerTitleRed) {
        this.headerTitle = headerTitle;
        this.headerTitleRed = headerTitleRed;
        viewModelType = TaskViewModelType.HEADER;
    }


    public Task getTask() {
        return task;
    }
    public String getHeaderTitle() {
        return headerTitle;
    }
    public boolean isHeaderTitleRed() {return headerTitleRed;}

    public TaskViewModelType getViewModelType() {
        return viewModelType;
    }
}
