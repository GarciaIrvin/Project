package e.irvingarcia.project;
import android.support.test.annotation.UiThreadTest;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
public class ServiceProviderTest {
    @Rule
    public ActivityTestRule<ServiceProviderCompletion> mActivityTestRule= new ActivityTestRule<ServiceProviderCompletion>(ServiceProviderCompletion.class,false,false);
    private ServiceProviderCompletion mActivity=null;
    private TextView text;
    @Before
    public void setUp() throws Exception {
        mActivity=mActivityTestRule.getActivity();
    }
    @Test
    @UiThreadTest
    public void checkCompany() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textView11));
        text= mActivity.findViewById(R.id.editCompanyS);
        text.setText("user1");
        String name= text.getText().toString();
        assertNotEquals("user",name);
    }
    @Test
    @UiThreadTest
    public void checkPostal() throws Exception{
        assertNotNull(mActivity.findViewById(R.id.textView12));
        text= mActivity.findViewById(R.id.editPostalS);
        text.setText("15.1");
        String name= text.getText().toString();
        assertNotEquals("user",name);
    }

}
