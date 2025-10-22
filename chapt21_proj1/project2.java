import java.util.*;


public class project2 {
    public static void main(String[] args) {

        System.out.print("Please enter the first set of strings: ");
        Scanner input = new Scanner(System.in);

        Set<String> set1 = new HashSet<>(Arrays.asList(input.nextLine().split("\\s+")));

        System.out.print("Please enter the second set of strings: ");
        Set<String> set2 = new HashSet<>(Arrays.asList(input.nextLine().split("\\s+")));

        TreeSet<String> treeSet1 = new TreeSet<>(set1);
        TreeSet<String> treeSet2 = new TreeSet<>(set2);

        TreeSet<String> unionSet = new TreeSet<>(treeSet1);
        unionSet.addAll(treeSet2);
        System.out.println("The union is " + unionSet);

        TreeSet<String> differenceSet = new TreeSet<>(treeSet1);
        differenceSet.removeAll(treeSet2);
        System.out.println("The difference is " + differenceSet);

        TreeSet<String> intersectionSet = new TreeSet<>(treeSet1);
        intersectionSet.retainAll(treeSet2);
        System.out.println("The intersection is " + intersectionSet);

        input.close();
        

        


    }
}
