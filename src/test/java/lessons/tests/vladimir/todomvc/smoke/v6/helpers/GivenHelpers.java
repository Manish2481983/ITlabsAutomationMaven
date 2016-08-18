package lessons.tests.vladimir.todomvc.smoke.v6.helpers;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.refresh;
import static lessons.tests.vladimir.todomvc.smoke.v6.helpers.GivenHelpers.Status.active;

/**
 * Created by vladimir on 17.08.16.
 */
public class GivenHelpers {

    public enum Status {
        active("false"), completed("true");

        String description;

        Status(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }
    }

    public static void given(String... texts){
        given(active, texts);
    }

    public static void given(Status status, String... texts){
        Task[] tasks = new Task[texts.length];

        for(int i=0;i<texts.length;i++){
            tasks[i]=newTask(status,texts[i]);
        }

        given(tasks);
    }

    public static class Task {
        private String text;
        private Status status;

        public Task(Status status, String text) {
            this.status = status;
            this.text = text;
        }

        public Task(String text){
            this(active,text);
        }

        public Status getStatus(){
            return this.status;
        }

        public String getText(){
            return this.text;
        }
    }

    public static Task newTask(Status status, String text){
        return new Task(status,text);
    }

    public static Task newTask(String text){
        return new Task(text);
    }

    public static void given(Task... tasks){
        StringBuilder query=new StringBuilder();

        for(Task task: tasks){
            if(query.length()!=0){
                query.append(",");
            }
            query.append(String.format("{\"completed\":%s,\"title\":\"%s\"}", task.getStatus(), task.getText()));
        }

        executeJavaScript("localStorage.setItem('todos-troopjs','["+query+"]')");
        refresh();
    }

}
