public class HR {
    private String username;
    private String password;

    public HR (String username, String password) {
        this.username = username;
        this.password = password;


    }


    public boolean login ( String inputusername, String inputpassword) {
        return this.username.equals(inputusername) && this.password.equals(inputpassword);

    }
public String getHRInfo () {
        return "\"HR Department:\\nAddress: Berlin, Germany\\nPhone: +49 123 456789\\nEmail: hr@riecksoftware.com\"";
}

}
