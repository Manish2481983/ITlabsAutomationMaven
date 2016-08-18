package lessons.tests.vladimir.todomvc.smoke.v6.testconfigs;

import org.junit.After;
import org.junit.Before;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by vladimir on 15.08.16.
 */
public class AtToDoMVCPageWithClearedDataAfterEachTest extends BaseTest {

    @Before
    public void testSetup() {
        open("https://todomvc4tasj.herokuapp.com/");
    }

    @After
    public void clearData() {
        executeJavaScript("localStorage.clear()");
    }

}
