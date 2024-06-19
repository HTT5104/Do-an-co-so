package project_do_an_co_so;

public class Player {
    private String name;
    private String position;
    private String birthDate;
    private String hometown;
    private String photoPath;

    public Player(String name, String position, String birthDate, String hometown, String photoPath) {
        this.name = name;
        this.position = position;
        this.birthDate = birthDate;
        this.hometown = hometown;
        this.photoPath = photoPath;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getHometown() {
        return hometown;
    }

    public String getPhotoPath() {
        return photoPath;
    }
}
