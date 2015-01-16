package shoppinglist.kizema.anton.testappespresso;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class LoginActivity extends Activity {

    private static final String TAG = LoginActivity.class.getName();
    private Server loginHelper;

    EditText email,password;
    Button btnUseEmailToLogin, btnLogIn;
    LinearLayout llPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.email);
        email.setVisibility(View.VISIBLE);
        password = (EditText) findViewById(R.id.password);

        llPhone = (LinearLayout) findViewById(R.id.llPhone);
        btnUseEmailToLogin = (Button) findViewById(R.id.btnUseEmailToLogin);

        btnUseEmailToLogin.setVisibility(View.GONE);
        llPhone.setVisibility(View.GONE);

        btnLogIn = (Button) findViewById(R.id.btnLogInSuka);

        initLoginHelper();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        btnLogIn.setEnabled(true);
    }

    private void initLoginHelper(){
        loginHelper = new Server() {
            @Override
            public void login(String email, String code, String phone, String password, boolean loginByPhoneNumber) {

//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        btnLogIn.setEnabled(false);
//                    }
//                });


                //ask server
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //done
                Intent intent = new Intent(LoginActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        };
    }


    public void btnLogInSuka(View v) {
        performLogin();
    }


    void performLogin() {

        final String emailParam = email.getText().toString().toLowerCase();
        final String codeParam = "";
        final String phoneParam = "";
        final String passwordParam = password.getText().toString();


        new Thread(new Runnable() {
            @Override
            public void run() {
                loginHelper.login(emailParam, codeParam, phoneParam, passwordParam, false);
            }
        }).start();
    }


    public void setUserHelper(Server helper){
        loginHelper = helper;
    }

    public Server getUserHelper(){
        return loginHelper;
    }

}
