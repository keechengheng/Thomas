package thomas.thomas;

/**
 * Created by keechengheng on 29/6/15.
 */
public class myUser {
    @com.google.gson.annotations.SerializedName("id")
    private String mID;
    @com.google.gson.annotations.SerializedName("email")
    public String mEmail;
    @com.google.gson.annotations.SerializedName("password")
    private String mPassword;

    public myUser(){

    }

    @Override
    public String toString() {
        return getText();
    }

    public myUser(String text, String id) {
        this.setText(text);
        this.setId(id);

    }

    public String getText() {
        return mEmail;
    }

    public final void setText(String text) {
        mEmail = text;
    }

    public String getId() {
        return mID;
    }

    public final void setId(String id) {

        mID = id;
    }

}
