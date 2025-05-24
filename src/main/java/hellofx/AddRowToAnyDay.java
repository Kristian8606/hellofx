package hellofx;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import static hellofx.DBConector.DBName;

public class AddRowToAnyDay {

    public DatePicker datePickeronAdd;
    public TextField onMark;
    public TextField onDogNumber;
    public TextField onInterest;
    public TextField onPrincipal;
    public TextField onGoldGr;
    public TextField onSilverGr;
    public TextField onIncome;
    public TextField onCost;
    public TextField onNumOfDog;
    public TextField onSumOfBet;
    public TextField onPurchaseGold;
    public TextField onPurchaseSilver;
    public int numberForRevision;
    public TextField onNewSilverGr;

    private String AddRowToAnyDay() {
        if (datePickeronAdd.getValue() != null) {
            return String.valueOf(datePickeronAdd.getValue());
        }
        return null;
    }

    boolean sendDbAddRow() throws SQLException, ClassNotFoundException {

        String num = onDogNumber.getText();
        String interest = onInterest.getText();
        String principal = onPrincipal.getText();

        String income = onIncome.getText();
        String cost = onCost.getText();
        String numOfBet = onNumOfDog.getText();
        String sumOfBet = onSumOfBet.getText();
        boolean isSend = false;

        boolean markSilverGold = (onMark.getText().equals("") && (onGoldGr.getText().equals("") && onSilverGr.getText().equals("")));
        boolean numInterestPrincipal = (!onDogNumber.getText().equals("") && !onInterest.getText().equals("") && !onPrincipal.getText().equals(""));
        boolean marcAndSilverOrGold = (!onDogNumber.getText().equals("") && !onInterest.getText().equals("") && !onPrincipal.getText().equals(""))
                && (!onMark.getText().equals("") && (!onGoldGr.getText().equals("") || !onSilverGr.getText().equals("")));
        /*try {
            revisionValue = Integer.parseInt(RevisionValue.revisionValue());

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);

        }

         */

        //  DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateTodaySend = AddRowToAnyDay();
        try {
            //Cashbox.newDayChange();
            // CangeNewDay.changeDay();
            Connection con = DBConector.getConections();

            if (/*markSilverGold &&*/ numInterestPrincipal || marcAndSilverOrGold) {
                String gold = onGoldGr.getText();
                String silver = onSilverGr.getText();
                String newSilverGr = onNewSilverGr.getText();
                String mark = onMark.getText();
                if (!markSilverGold) {
                    if (gold.equals("") && silver.equals("") && newSilverGr.equals("")) {
                        ExeptionDialog.alertDialog("Не е въведен грамаж!");
                    }
                    if (mark.equals("")) {
                        ExeptionDialog.alertDialog("Въведен е грамаж, но не е маркиран като продажба!");
                    }
                }
                if (mark.equals("-")) {
                    mark = "--||--";
                }
                if (gold.equals("")) {
                    gold = "null";
                }
                if (silver.equals("")) {
                    silver = "null";
                }
                if (newSilverGr.equals("")) {
                    newSilverGr = "null";
                }

                //  System.out.println(df.format(dateToSend));
                PreparedStatement posted = con.prepareStatement("INSERT into " + DBName + ".table1 ( date, mark, num, interest, principal, " +
                        "goldGr, silverGr, newSilverGr, goldText, silverText, newSilverText)" +
                        "VALUES (  " + "'" + dateTodaySend + "'" + ", " + "'" + mark + "'" + ", " + num + ", " + interest + ", " + principal + ", " + Regex.returnDigit(gold) + ", "
                        + Regex.returnDigit(silver) + ", "+Regex.returnDigit(newSilverGr)+", '" + Regex.returnText(gold) + "','" + Regex.returnText(silver) + "', '"+Regex.returnText(newSilverGr)+"');");


                posted.executeLargeUpdate();
                posted.close();

                onMark.clear();
                onDogNumber.clear();
                onInterest.clear();
                onPrincipal.clear();
                onGoldGr.clear();
                onSilverGr.clear();
                onNewSilverGr.clear();

                onSumOfBet.clear();

                isSend = true;
            }

            if (!income.equals("") || !cost.equals("")) {
                if (income.equals("")) {
                    income = null;
                }
                if (cost.equals("")) {
                    cost = null;
                }


                PreparedStatement postedIncomeAndCost = con.prepareStatement("INSERT into " + DBName + ".table2 ( date, income, cost, incomeString, costString) " +
                        "VALUES (  " + "'" + dateTodaySend + "'" + "," + Regex.returnDigit(income) + "," + Regex.returnDigit(cost) + ", '" + Regex.returnText(income) + "', '" + Regex.returnText(cost) + "' );");

                postedIncomeAndCost.executeLargeUpdate();
                postedIncomeAndCost.close();

                onIncome.clear();
                onCost.clear();

                isSend = true;
            }
            if (!numOfBet.equals("") && !sumOfBet.equals("")) {
                PreparedStatement postedSumAndNum = con.prepareStatement("INSERT into " + DBName + ".table3 ( date, numberOfBet, sumOfBet) " +
                        "VALUES (  " + "'" + dateTodaySend + "'" + "," + "'" + numOfBet + "'" + "," + "'" + sumOfBet + "'" + ");");

                postedSumAndNum.executeLargeUpdate();
                postedSumAndNum.close();
                try {

                    int numberOfBet = Integer.parseInt(onNumOfDog.getText());
                    onNumOfDog.clear();
                    numberOfBet = numberOfBet + 1;
                    onNumOfDog.setText(String.valueOf(numberOfBet));
                } catch (Exception e) {
                    onNumOfDog.clear();
                    System.out.println(e + "increst number 0f Bet");
                }
                onSumOfBet.clear();

                isSend = true;
            }

            if (!onPurchaseGold.getText().equals("") || !onPurchaseSilver.getText().equals("")) {
                String goldPrice = onPurchaseGold.getText();
                String silverPrice = onPurchaseSilver.getText();
                if (goldPrice.equals("")) {
                    goldPrice = null;
                }
                if (silverPrice.equals("")) {
                    silverPrice = null;
                }

                PreparedStatement postedGoldPrice = con.prepareStatement("INSERT into " + DBName + ".table4 ( date, goldPrice, silverPrice, goldText, silverText) " +
                        "VALUES (  " + "'" + dateTodaySend + "'" + "," + Regex.returnDigit(goldPrice) + "," + Regex.returnDigit(silverPrice) + ",'" + Regex.returnText(goldPrice) + "','" + Regex.returnText(silverPrice) + "' );");

                postedGoldPrice.executeLargeUpdate();
                postedGoldPrice.close();
                onPurchaseGold.clear();
                onPurchaseSilver.clear();
                isSend = true;
            }

            System.out.println("Sending info");
            if (isSend) {
               // Refresh(dateTodaySend);

                Controller.myController.Refresh(dateTodaySend);
                assert dateTodaySend != null;
                Controller.myController.dateBar.setValue(LocalDate.parse(dateTodaySend));
                //  UpdateAllTable.updateCashbox();
                BackupDB.Backupdbtosql();
                return true;

            }


        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public void onEditSubmit(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        //  numberForRevision = -1;
        if (sendDbAddRow()) {

        } else {

        }


    }



}
