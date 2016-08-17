package lessons.tests.vladimir.todomvc.smoke.v6;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.refresh;

/**
 * Created by vladimir on 17.08.16.
 */
public class Helper {

    public static void given(String text){
        executeJavaScript("localStorage.setItem('todos-troopjs','[{\"completed\":false,\"title\":\""+text+"\"}]')");
        refresh();
//localStorage.setItem("todos-troopjs",'[{"completed":false,"title":"aaaaaaa"},{"completed":false,"title":"aaaaaaa"}]')
    }

}
