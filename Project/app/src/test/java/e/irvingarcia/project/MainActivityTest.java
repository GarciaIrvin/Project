package test.java.e.irvingarcia.project;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import e.irvingarcia.project.MainActivity;

public class MainActivityTest {



        public ActivityTestRule<MainActivity> mActivityTestRule =  new MainActivityTest<MainActivity>(Main.Activity.class);

        priavte MainActivity mActivity = null;
        pirvate
        public void setUp()throws Exception

        {
           // super.setUp();
            mActivity = mActivityTestRule.getActivity();
        }
// App start up
        public void TestLaunch()

        {
            View view = mActivity.findViewById(R.id.tvMainText);
            assertNotnull(view);
        }
        void tearDown() throws Exception
        {
            mActivity = null;
        }

        public void ButtonResponse()

        {
           onView (withId(R.id.btnGoTo)).perfrom(click));
        }

        public void checkFirst() throws Exception

        {
            assertNotNull(mActivity.findViewById(R.id.textView));
            text = mActivity.dinyViewById(R.id.Users;
            text.setText("user1");
            String Users = text.getText().toString();
            assertNotEquals("user", Users);

        }
}