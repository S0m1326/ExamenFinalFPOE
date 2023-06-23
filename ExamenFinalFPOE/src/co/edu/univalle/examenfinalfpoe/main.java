package co.edu.univalle.examenfinalfpoe;

import co.edu.univalle.examenfinalfpoe.controller.principalViewController;
import co.edu.univalle.examenfinalfpoe.view.principalView;

public class main {

    public static void main(String[] args) {
        principalView principalView = new principalView();
        principalView.setVisible(true);
        principalViewController principalViewController = new principalViewController(principalView);
    }
}
