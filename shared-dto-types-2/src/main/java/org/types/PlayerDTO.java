package org.types;

public class PlayerDTO {

    private String id;

    private String teamId;

    private String firstName;

    private String lastName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public PlayerDTO(String id, String teamId, String firstName, String lastName, String s) {
        this.id = id;
        this.teamId = teamId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
