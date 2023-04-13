public class Office {
    private int id;
    private String officeName;
    private String address;

    @Override
    public String toString() {
        return "Office{" +
                "id=" + id +
                ", office_name='" + officeName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Office() {
    }

    public Office(int id, String officeName, String address) {
        this.id = id;
        this.officeName = officeName;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
