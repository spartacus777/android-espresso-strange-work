package shoppinglist.kizema.anton.testappespresso;

/**
 * Created by Anton on 15.01.2015.
 */
public interface Server {

    public void login(String email, String code, String phone, String password, boolean loginByPhoneNumber);
}
