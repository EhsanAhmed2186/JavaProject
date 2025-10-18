package Library.model;

import java.io.Serializable;

public class Member implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String memberId;

    public Member(String name, String memberId) {
        this.name = name;
        this.memberId = memberId;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getMemberId() {
        return memberId;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "Member{name='" + name + "', memberId='" + memberId + "'}";
    }
}
