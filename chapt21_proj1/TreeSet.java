import java.util.Scanner;

public class TreeSet {
    public static void main(String[] args) {
        TreeSet set1 = new TreeSet();
        TreeSet set2 = new TreeSet();

        System.out.print("Please enter the first set of strings: ");
        Scanner input1 = new Scanner(System.in);
        var extracted1 = input1.nextLine();

        System.out.print("Please enter the second set of strings: ");
        Scanner input2 = new Scanner(System.in);
        var extracted2 = input2.nextLine();

        System.out.println(extracted1 + " " + extracted2);

        


    }
}
