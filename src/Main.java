import manager.userManager;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        userManager teste  = new userManager();
        teste.register("Diogo", "gugugaga", "diogogomecardoso", "Diogo", "Cardoso", LocalDate.now());
        teste.register("Diogo", "gugugaga", "diogogomecardoso", "Diogo", "Cardoso", LocalDate.now());

    }
}