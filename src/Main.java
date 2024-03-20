import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    //Method to add items to the bill
    public static void processBill(POS pos,Bill bill,List<Bill> bills){
        // add items
        System.out.println("--------------------------------------------------------");
        System.out.println("|                 Add items to Bill                     |");
                System.out.println("--------------------------------------------------------");

        boolean isContinue = true;
        while(isContinue) {
            Scanner scan1 = new Scanner(System.in);
            GloceryItem g = pos.getItemDetails();
            if (g != null) {
                bill.addItem(g);
            }

            System.out.print("Enter any key to add items or Enter 'N' to stop adding items : ");
            String choice1 = scan1.nextLine();
            if (choice1.equalsIgnoreCase("N")) {
                System.out.print("Do you want to add the bill to pending list(Y/N) ? ");
                        String choice2 = scan1.nextLine();
                if (choice2.equalsIgnoreCase("N")) {
                    isContinue = false;
                    bill.printBill();
                    bills.remove(bill);
                    try{
                        FileOutputStream fout = new
                                FileOutputStream("pendingBill.ser",false);
                        ObjectOutputStream objout = new
                                ObjectOutputStream(fout);
                        for(Bill b: bills){
                            objout.writeObject(b);
                        }
                        objout.close();
                        fout.close();
                    } catch (IOException e) {
                        System.out.println();
                    }
                } else {
                    try{
                        FileOutputStream fout = new
                                FileOutputStream("pendingBill.ser",false);
                        ObjectOutputStream objout = new
                                ObjectOutputStream(fout);

                        for(Bill b: bills){
                            if( b.getBillNumber() ==
                                    bill.getBillNumber()){
                                bills.remove(b);
                            }
                        }
                        bills.add(bill);

                        for(Bill b: bills){
                            objout.writeObject(b);
                        }

                        objout.close();
                        fout.close();
                        System.out.println("Bill was added to the pending list.");
                        break;

                    } catch (IOException e) {
                        System.out.println("Error! Can't add the bill to the pending list.");
                    }
                }
            }
        }
    }

    public static Bill selectPendingBill(List<Bill> bills){
        System.out.println("Bill Numbers");
        int i = 1;
        int size = bills.size();
        for(Bill b : bills ){
            System.out.println("["+i+"] "+ b.getBillNumber());
            i++;
        }
        System.out.println();
        Scanner scan4 = new Scanner(System.in);
        while(true){
            System.out.print("Select the bill[1/2/3/....] ");
            int choice4 = scan4.nextInt();
            for(int j=0; j<size;j++){
                if(choice4-1 == j){
                    return bills.get(j);
                }
            }
            System.out.println("Invalid input.");
            System.out.print("Do you want to continue selecting a pending bills[Y/N].");
                    Scanner scan6 = new Scanner(System.in);
            String choice6 = scan6.nextLine();
            if(choice6.equalsIgnoreCase("N")){
                return null;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        List<Bill> pendingBills = new ArrayList<>();

        File file = new File("pendingBill.ser");
        file.createNewFile();

        Scanner scanner = new Scanner(System.in);

        System.out.println("--------------------------------------------------------");
        System.out.println("|            Super Saving Super Market                  |");
                System.out.println("--------------------------------------------------------");

        System.out.print("Enter Your Name : ");
        String cashier = scanner.nextLine();
        System.out.print("Enter branch name: ");
        String branch = scanner.nextLine();

        POS pos = new POS(cashier,branch);

        Bill bill;

        while(true){
            try{
                Scanner scan5 = new Scanner(System.in);
                System.out.println("[1] Pending Bill            [2] New Bill              [3] Exit ");
                int choice3 = scan5.nextInt();

                switch (choice3) {
                    case 1 -> {
                        try {
                            FileInputStream fin = new
                                    FileInputStream("pendingBill.ser");
                            ObjectInputStream objin = new
                                    ObjectInputStream(fin);
                            pendingBills.clear();
                            while (true) {
                                try {
                                    Bill pendingBill = (Bill)
                                            objin.readObject();
                                    pendingBills.add(pendingBill);
                                } catch (IOException |
                                         ClassNotFoundException e) {
                                    //System.out.println("You have no remaining pending bills.");
                                    System.out.println();
                                    break;
                                }
                            }
                        } catch (EOFException eof) {
                            //System.out.println("You have no remaining pending bills.");
                            System.out.println();
                        }

                        bill = selectPendingBill(pendingBills);
                        if(bill == null){
                            break;
                        }
                        System.out.println("--------------------------------------------------------");
                        System.out.println("|                     Pending Bill                      |");
                                processBill(pos,bill,pendingBills);
                    }
                    case 2 -> {
                        Scanner scan3 = new Scanner(System.in);
                        System.out.print("Is the customer a registered customer(Y/N) ? ");
                        if (scan3.nextLine().equalsIgnoreCase("Y")) {
                            System.out.print("Enter customer Name : ");
                            String customerName = scan3.nextLine();
                            bill = new Bill(cashier, branch,
                                    customerName);
                        } else {
                            bill = new Bill(cashier, branch);
                        }
                        processBill(pos,bill,pendingBills);
                    }
                    case 3 -> {
                        System.exit(0);
                    }
                    default -> {
                        System.out.println("Invalid Input.");
                        System.out.println("Please enter a valid input.");
                    }
                }

            }
            catch(InputMismatchException e){
                System.out.println("You have entered an invalid input. Please enter a valid input.");
            }

        }
    }

}