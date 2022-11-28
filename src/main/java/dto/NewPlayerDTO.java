package dto;

public class NewPlayerDTO {

    private String name;
    private int bettingMoney;

    public NewPlayerDTO () {}

    public NewPlayerDTO (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getBettingMoney() {
        return bettingMoney;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBettingMoney(int bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    @Override
    public String toString() {
        return "이름: " + name;
    }
}
