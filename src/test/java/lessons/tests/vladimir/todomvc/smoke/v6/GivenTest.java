package lessons.tests.vladimir.todomvc.smoke.v6;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Selenide.$$;
import static lessons.tests.vladimir.todomvc.smoke.v6.helpers.GivenHelpers.given;
import static lessons.tests.vladimir.todomvc.smoke.v6.helpers.GivenHelpers.TaskStatus.ACTIVE;
import static lessons.tests.vladimir.todomvc.smoke.v6.helpers.GivenHelpers.TaskStatus.COMPLETED;
import static lessons.tests.vladimir.todomvc.smoke.v6.helpers.GivenHelpers.aTask;

import com.codeborne.selenide.ElementsCollection;
import lessons.tests.vladimir.todomvc.smoke.v6.helpers.GivenHelpers;
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
        given(ACTIVE,"b","c","d");
        assertTasksAre("b","c","d");
    }

    @Test
    public void testTasksWithCompletedStatusForAll(){
        given(COMPLETED,"a","b","c");
        assertTasksAre("a","b","c");
    }

    @Test
    public void testTasksWithDifferentStatusForEach(){
        given(GivenHelpers.aTask(ACTIVE,"jjj"), GivenHelpers.aTask(COMPLETED,"asdsa"), aTask("jhjhg"));
        assertTasksAre("jjj","asdsa","jhjhg");
    }



    ElementsCollection tasks = $$("#todo-list>li");

    @Step
    private void assertTasksAre(String... texts) {
        tasks.shouldHave(exactTexts(texts));
    }

}


