
public class test {

    public static void main(String[] args) {
        // Scient examples
        Scientist scientist1 = new Scientist(175, "Brown", true, "green");
        Scientist scientist2 = new Scientist(165, "Black", true, "purple");
        Scientist scientist3 = new Scientist(180, "Blonde", false, "white");

        // Doctor examples
        Doctor doctor1 = new Doctor(170, "Grey", true, 5);
        Doctor doctor2 = new Doctor(168, "Brown", true, 15);
        Doctor doctor3 = new Doctor(182, "Black", true, 25);

        // Print Scientists
        System.out.println("SCIENTISTS:");
        System.out.println("==========");
        System.out.println(scientist1.toString());
        System.out.println();
        System.out.println(scientist2.toString());
        System.out.println();
        System.out.println(scientist3.toString());
        System.out.println();

        // Print Doctors
        System.out.println("DOCTORS:");
        System.out.println("=======");
        System.out.println(doctor1.toString());
        System.out.println();
        System.out.println(doctor2.toString());
        System.out.println();
        System.out.println(doctor3.toString());
    }
}
