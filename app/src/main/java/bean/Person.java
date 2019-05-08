package bean;

public class Person {
    private String name;
    private int imageId;
    private  String status;

    public Person(String name, int imageId, String status) {
        this.name = name;
        this.imageId = imageId;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
