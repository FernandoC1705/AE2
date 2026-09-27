public class Main {

    public static void main(String[] args) {

        Universidad universidad = new Universidad();

        Menu menu = new Menu(universidad);

        menu.mostrarMenu();
    }
}