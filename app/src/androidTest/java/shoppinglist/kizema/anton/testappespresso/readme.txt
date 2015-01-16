Еспресо гавно.
1. ТестоваЯ апликуха - 

ЮЗЕР стори
заполняем 2 поля
клацаем кнопку
переходим на другое активити

кнопка работает синхронно (нету емуляции сервера) так и асинхроннно (ждет 3 сек в другом потоке)

пока мы не стерли строчки 
//        Espresso.onView(ViewMatchers.withId(R.id.email)).perform(ViewActions.typeText("some shit"));
//        Espresso.onView(ViewMatchers.withId(R.id.password)).perform(ViewActions.typeText("123"));

Выдает PerformException (не видит кнопку)

Когда мы сетим 

final EditText email = (EditText) act.findViewById(R.id.email);
        final EditText password = (EditText) act.findViewById(R.id.password);
        getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                email.setText("Engineer");
                password.setText("2342");
            }
        });
		
		все работает и синхронно и асинхронно.
		
		
2. Наша апп  (реальный вызов серверу)

С этим -  мы не доходим до запуска меинАктивити 
final EditText email = (EditText) act.findViewById(R.id.email);
        final EditText password = (EditText) act.findViewById(R.id.password);
        getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                email.setText("Engineer");
                password.setText("2342");
            }
        });
		
Так:
Espresso.onView(ViewMatchers.withId(R.id.email)).perform(ViewActions.typeText("mrfuskoff1@mail.ru"));
Espresso.onView(ViewMatchers.withId(R.id.password)).perform(ViewActions.typeText("anton111"));

МеинАктивити запускаеться, видно что в логах отработал Idling res
В обоих случаях Perform ексепшен (Не нашли кнопку)

