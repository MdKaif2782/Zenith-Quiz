package inc.typhon.zenithquiz.Particapnt_info;

public class AccountHolder {
    private String name,email,password,gender,phone,age,institute, avatar,avatar_delete_url;
    private Boolean isSenior;
    private Boolean isMale;

    public AccountHolder(String name, String email, String password, String gender, String phone, String age, String institute, Boolean isSenior) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phone = phone;
        this.age = age;
        this.institute = institute;
        this.isSenior = isSenior;
    }

    public String getAvatar_delete_url() {
        return avatar_delete_url;
    }

    public void setAvatar_delete_url(String avatar_delete_url) {
        this.avatar_delete_url = avatar_delete_url;
    }

    public Boolean getMale() {
        return isMale;
    }

    public void setMale(Boolean male) {
        isMale = male;
    }

    public AccountHolder() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public Boolean getSenior() {
        return isSenior;
    }

    public void setSenior(Boolean senior) {
        isSenior = senior;
    }
}
