package lessons.tests.vladimir.todomvc.smoke.v6;

import com.codeborne.selenide.ElementsCollection;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Selenide.$$;
import static lessons.tests.vladimir.todomvc.smoke.v6.Helper.given;
/**
 * Created by vladimir on 17.08.16.
 */
public class AtCompletedFilterTasksActivitiesTest extends AtToDoMVCPageWithClearedDataAfterEachTest{

    @Test
    public void testTasks(){
        given("a");
        assertTasksAre("a");

    }

    ElementsCollection tasks = $$("#todo-list>li");

    @Step
    private void assertTasksAre(String... texts) {
        tasks.shouldHave(exactTexts(texts));
    }

}
