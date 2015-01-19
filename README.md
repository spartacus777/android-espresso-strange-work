# android-espresso-strange-work
Connected to my stack overflow question - http://stackoverflow.com/questions/28016562/android-espresso-wrong-set-up-or-work-not-stable
This project is to test espresso on android. I can not make it work without "huck"

To make it work I have to comment this piece of code:
```java
Espresso.onView(ViewMatchers.withId(R.id.email)).perform(ViewActions.typeText("some shit"));
Espresso.onView(ViewMatchers.withId(R.id.password)).perform(ViewActions.typeText("123"));
```

and replacing them with "huck":

```java
final EditText email = (EditText) act.findViewById(R.id.email);
        final EditText password = (EditText) act.findViewById(R.id.password);
        getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                email.setText("Engineer");
                password.setText("2342");
            }
        });
```

Please, I need help, i have been investigating this for more than week!
