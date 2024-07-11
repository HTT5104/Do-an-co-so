package project_do_an_co_so;

public class Player {
    private String name;
    private String hometown;
    private String birthDate;
    private String numberShirt;
    private String position;
    private String weight;
    private String height;
    private String bodyMass;
    private String password;

    // Constructor thuong
    public Player(String name, String hometown, String birthDate, String numberShirt, String position, String weight, String height, String bodyMass) {
        this.name = name;
        this.hometown = hometown;
        this.birthDate = birthDate;
        this.numberShirt = numberShirt;
        this.position = position;
        this.weight = weight;
        this.height = height;
        this.bodyMass = bodyMass;
    }
    
    //Constructor vjppro
    public Player(String name, String hometown, String birthDate, String numberShirt, String position, String weight, String height, String bodyMass, String password) {
        this.name = name;
        this.hometown = hometown;
        this.birthDate = birthDate;
        this.numberShirt = numberShirt;
        this.position = position;
        this.weight = weight;
        this.height = height;
        this.bodyMass = bodyMass;
        this.password = password;
    }
    
    //Constructor vjpnopro

    public Player(String name, String password) {
        this.name = name;
        this.password = password;
    }
    

    public void setName(String name) {
        this.name = name;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setNumberShirt(String numberShirt) {
        this.numberShirt = numberShirt;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setBodyMass(String bodyMass) {
        this.bodyMass = bodyMass;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }      

    // Getters and Setters
    public String getName() { return name; }
    public String getHometown() { return hometown; }
    public String getBirthDate() { return birthDate; }
    public String getNumberShirt() { return numberShirt; }
    public String getPosition() { return position; }
    public String getWeight() { return weight; }
    public String getHeight() { return height; }
    public String getBodyMass() { return bodyMass; }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", hometown=" + hometown + ", birthDate=" + birthDate + ", numberShirt=" + numberShirt + ", position=" + position + ", weight=" + weight + ", height=" + height + ", bodyMass=" + bodyMass + '}';
    }
    
    
}
