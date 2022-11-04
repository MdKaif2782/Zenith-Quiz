package inc.typhon.zenithquiz;

public class Participant {
    private String name;
    private String email;
    private String institute;
    private String claas;

    public Participant(String name, String email, String institute, String claas) {
        this.name = name;
        this.email = email;
        this.institute = institute;
        this.claas = claas;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getInstitute() {
        return institute;
    }

    public String getClaas() {
        return claas;
    }
}
