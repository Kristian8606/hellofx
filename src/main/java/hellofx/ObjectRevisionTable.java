package hellofx;

public class ObjectRevisionTable {

    private String  date, win, soldGold, soldSilver, income, cost, goldPrice,
            silverPrice,
            idTable2,
            cashBoxPrevioursPeriod,
            soldNewSilver;



    /*
    date" prefWidth="100.0"
    win" prefWidth="100.0"
    soldGold" prefWidth="11
    soldSilver" prefWidth="
    income" prefWidth="118.
    cost" prefWidth="118.0"
    goldPrice" prefWidth="8
    silverPrice" prefWidth=
    cashBoxPrevioursPeriod"
    */
    ObjectRevisionTable( String date, String win,
           String soldGold, String soldSilver, String income,
           String cost, String goldPrice, String silverPrice,String cashBoxPrevioursPeriod, String soldNewSilver) {

        this.date = date;
        this.win = win;
        this.soldGold = soldGold;
        this.soldSilver = soldSilver;
        this.income = income;
        this.cost = cost;
        this.goldPrice = goldPrice;
        this.silverPrice = silverPrice;
        this.cashBoxPrevioursPeriod = cashBoxPrevioursPeriod;
        this.soldNewSilver = soldNewSilver;

    }

    public String getSoldNewSilver() {
        return soldNewSilver;
    }

    public void setSoldNewSilver(String soldNewSilver) {
        this.soldNewSilver = soldNewSilver;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getSoldGold() {
        return soldGold;
    }

    public void setSoldGold(String soldGold) {
        this.soldGold = soldGold;
    }

    public String getSoldSilver() {
        return soldSilver;
    }

    public void setSoldSilver(String soldSilver) {
        this.soldSilver = soldSilver;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCashBoxPrevioursPeriod() {
        return cashBoxPrevioursPeriod;
    }

    public void setCashBoxPrevioursPeriod(String cashBoxPrevioursPeriod) {
        this.cashBoxPrevioursPeriod = cashBoxPrevioursPeriod;
    }

    public String getGoldPrice() {
        return goldPrice;
    }

    public void setGoldPrice(String goldPrice) {
        this.goldPrice = goldPrice;
    }

    public String getSilverPrice() {
        return silverPrice;
    }

    public void setSilverPrice(String silverPrice) {
        this.silverPrice = silverPrice;
    }

    public String getIdTable2() {
        return idTable2;
    }

    public void setIdTable2(String idTable2) {
        this.idTable2 = idTable2;
    }
}
