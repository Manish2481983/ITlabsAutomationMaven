package lessons.tests.vladimir.todomvc.smoke.v6;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Test;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by Vladimir on 20.07.2016.
 */
public class TodoMVCTest extends AtToDoMVCPageWithClearedDataAfterEachTest {

    @Title("End 2 End test TasksLifeCycle")
    @Test
    public void testTasksLifeCycle() {

        addTasks("a");
        assertTasksAre("a");
        assertItemsLeft(1);
        toggleAllTasks();
        assertTasksAre("a");

        filterActive();
        assertVisibleTasksListIsEmpty();
        addTasks("b");
        toggleTask("b");
        assertVisibleTasksListIsEmpty();

        filterCompleted();
        assertVisibleTasksAre("a","b");
        //Activate
        toggleTask("a");
        assertVisibleTasksAre("b");
        clearCompleted();
        assertVisibleTasksListIsEmpty();

        filterAll();
        assertVisibleTasksAre("a");
    }

    @Test
    public void testDeleteTaskAtActive() {
        //given
        addTasks("a","b");
        filterActive();

        deleteTask("a");
        assertTasksAre("b");
        assertItemsLeft(1);
    }

    @Test
    public void testCancelUpdateTaskAtActive(){
        //given
        addTasks("a");
        filterActive();

        startUpdateTask("a", "a edit canceled").pressEscape();
        assertTasksAre("a");
        assertItemsLeft(1);
    }

    @Test
    public void testUpdateTaskAtAll(){
        //given
        addTasks("a", "b");

        startUpdateTask("b", "b edit").pressEnter();
        assertTasksAre("a", "b edit");
        assertItemsLeft(2);
    }

    ElementsCollection tasks = $$("#todo-list>li");

    @Step
    private void addTasks(String... texts) {
        for (String text : texts) {
            $("#new-todo").setValue(text).pressEnter();
        }
    }

    @Step
    private void assertTasksAre(String... texts) {
        tasks.shouldHave(exactTexts(texts));
    }

    @Step
    private void assertVisibleTasksAre(String... texts) {
        tasks.filterBy(visible).shouldHave(exactTexts(texts));
    }

    @Step
    private void assertVisibleTasksListIsEmpty() {
        tasks.filterBy(visible).shouldBe(empty);
    }

    @Step
    private void assertTasksListIsEmpty() {
        tasks.shouldBe(empty);
    }

    @Step
    private void assertItemsLeft(Integer counter) {
        $("#todo-count").$("strong").shouldHave(exactText(counter+""));
    }

    @Step
    private void clearCompleted() {
        $("#clear-completed").click();
    }

    @Step
    private void deleteTask(String text) {
        tasks.find(exactText(text)).hover().$(".destroy").click();
    }

    @Step
    private void toggleTask(String text) {
        tasks.find(exactText(text)).$(".toggle").click();
    }

    @Step
    private void toggleAllTasks() {
        $("#toggle-all").click();
    }

    @Step
    private void filterAll() {
        $(By.linkText("All")).click();
    }

    @Step
    private void filterActive() {
        $(By.linkText("Active")).click();
    }

    @Step
    private void filterCompleted() {
        $(By.linkText("Completed")).click();
    }

    @Step
    private SelenideElement startUpdateTask(String oldText, String newText) {
        tasks.find(exactText(oldText)).doubleClick();
        return tasks.find(cssClass("editing")).find(".edit").setValue(newText);
    }
}