package lessons.tests.vladimir.todomvc.smoke.v6;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Selenide.$$;
import static lessons.tests.vladimir.todomvc.smoke.v6.helpers.GivenHelpers.given;
import static lessons.tests.vladimir.todomvc.smoke.v6.helpers.GivenHelpers.Status.active;
import static lessons.tests.vladimir.todomvc.smoke.v6.helpers.GivenHelpers.Status.completed;
import static lessons.tests.vladimir.todomvc.smoke.v6.helpers.GivenHelpers.newTask;

import com.codeborne.selenide.ElementsCollection;
import lessons.tests.vladimir.todomvc.smoke.v6.helpers.GivenHelpers.Task;
import lessons.tests.vladimir.todomvc.smoke.v6.testconfigs.AtToDoMVCPageWithClearedDataAfterEachTest;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by vladimir on 18.08.16.
 */
public class GivenTest extends AtToDoMVCPageWithClearedDataAfterEachTest {

    @Test
    public void testTasksWithoutStatus(){
        given("c","d","h");
        assertTasksAre("c","d","h");
    }

    @Test
    public void testTasksWithActiveStatusForAll(){
        given(active,"b","c","d");
        assertTasksAre("b","c","d");
    }

    @Test
    public void testTasksWithCompletedStatusForAll(){
        given(completed,"a","b","c");
        assertTasksAre("a","b","c");
    }

    @Test
    public void testTasksWithDifferentStatusForEach(){
        given(newTask(active,"jjj"), newTask(completed,"asdsa"), newTask("jhjhg"));
        assertTasksAre("jjj","asdsa","jhjhg");
    }



    ElementsCollection tasks = $$("#todo-list>li");

    @Step
    private void assertTasksAre(String... texts) {
        tasks.shouldHave(exactTexts(texts));
    }

}


