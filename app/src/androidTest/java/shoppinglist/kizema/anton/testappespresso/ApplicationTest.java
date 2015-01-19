package shoppinglist.kizema.anton.testappespresso;

import android.app.Activity;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.internal.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import com.android.support.test.deps.guava.collect.Iterables;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@LargeTest
public class ApplicationTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    public ApplicationTest() {
        super(LoginActivity.class);
    }

    CountingIdlingResource idleRes;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getActivity();

        idleRes = new CountingIdlingResource("server");
        Espresso.registerIdlingResources(idleRes);
    }

    public void testSample(){
//        MyUserHelper helper = new MyUserHelper(act, aHelper);
//        act.setUserHelper(helper);
//        Espresso.registerIdlingResources(helper);

        final LoginActivity act = (LoginActivity) getCurrentActivity();
        Server aHelper = act.getUserHelper();

        MyUserHelperExternalIdleRes helper2 = new MyUserHelperExternalIdleRes(idleRes, aHelper);
        act.setUserHelper(helper2);


        //if comment it works
//        final EditText email = (EditText) act.findViewById(R.id.email);
//        final EditText password = (EditText) act.findViewById(R.id.password);
//        getInstrumentation().runOnMainSync(new Runnable() {
//            public void run() {
//                email.setText("Engineer");
//                password.setText("2342");
//            }
//        });

        Espresso.onView(ViewMatchers.withId(R.id.email)).perform(ViewActions.typeText("some shit"));
        Espresso.onView(ViewMatchers.withId(R.id.password)).perform(ViewActions.typeText("123"));

        Espresso.closeSoftKeyboard();
        Espresso.onView(ViewMatchers.withId(R.id.btnLogIn)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.btnLogIn)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.secondActivityOpened)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.pressBack();

        Espresso.closeSoftKeyboard();
        Espresso.onView(ViewMatchers.withId(R.id.btnLogIn)).perform(ViewActions.click());
    }

    Activity getCurrentActivity() {
        getInstrumentation().waitForIdleSync();
        final Activity[] activity = new Activity[1];
        try {
            runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    java.util.Collection<Activity> activites = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                    activity[0] = Iterables.getOnlyElement(activites);
                }});
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return activity[0];
    }



//    class MyUserHelper implements Server, IdlingResource {
//        private ResourceCallback callback;
//        private boolean isIdle = true;
//
//        private Server aHelper;
//
//        public MyUserHelper(Activity context, Server aHelper) {
//            this.aHelper = aHelper;
//        }
//
//        @Override
//        public String getName() {
//            return "Some Idle res";
//        }
//
//        @Override
//        public boolean isIdleNow() {
//            return isIdle;
//        }
//
//        @Override
//        public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
//            this.callback = resourceCallback;
//        }
//
//        @Override
//        public void login(String email, String code, String phone, String password, boolean loginByPhoneNumber) {
//
//            isIdle = false;
//
//            aHelper.login(email,code, phone,password,loginByPhoneNumber);
//
//            isIdle = true;
//            callback.onTransitionToIdle();
//
//        }
//    }

    class MyUserHelperExternalIdleRes implements Server {
        private Server aHelper;
        private CountingIdlingResource udleRes;

        public MyUserHelperExternalIdleRes(CountingIdlingResource udleRes, Server aHelper) {
            this.aHelper = aHelper;
            this.udleRes = udleRes;
        }

        @Override
        public void login(String email, String code, String phone, String password, boolean loginByPhoneNumber) {

            udleRes.increment();
            try {
                aHelper.login(email,code, phone,password,loginByPhoneNumber);
            } finally {
                udleRes.decrement();
            }
        }
    }
}