package lessons.tests.vladimir.todomvc.smoke.v6.helpers;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.refresh;
import static lessons.tests.vladimir.todomvc.smoke.v6.helpers.GivenHelpers.TaskStatus.ACTIVE;

/**
 * Created by vladimir on 17.08.16.
 */
public class GivenHelpers {

    public enum TaskStatus {
        ACTIVE("false"), COMPLETED("true");

        String description;

        TaskStatus(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }
    }

    public static void given(String... texts){
        given(ACTIVE, texts);
    }

    public static void given(TaskStatus status, String... texts){
        given(aTasks(status,texts));
    }

    public static class Task {
        private String text;
        private TaskStatus status;

        public Task(TaskStatus status, String text) {
            this.status = status;
            this.text = text;
        }

        public Task(String text){
            this(ACTIVE,text);
        }

        public TaskStatus getStatus(){
            return this.status;
        }

        public String getText(){
            return this.text;
        }
    }

    public static Task aTask(TaskStatus status, String text){
        return new Task(status,text);
    }

    public static Task aTask(String text){
        return new Task(text);
    }

    public static Task[] aTasks(TaskStatus status, String... texts){
        Task[] tasks = new Task[texts.length];

        for(int i=0;i<texts.length;i++){
            tasks[i]= aTask(status,texts[i]);
        }
        return tasks;
    }

    public static void given(Task... tasks){
        StringBuilder tasksList=new StringBuilder();

        for(Task task: tasks){
            if(tasksList.length()!=0){
                tasksList.append(",");
            }
            tasksList.append(String.format("{\"completed\":%s,\"title\":\"%s\"}", task.getStatus(), task.getText()));
        }

        executeJavaScript("localStorage.setItem('todos-troopjs','["+tasksList+"]')");
        refresh();
    }

}
