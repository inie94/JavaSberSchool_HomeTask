
package homework.lesson4;

import homework.lesson4.terminal.controllers.LoginController;
import homework.lesson4.terminal.controllers.OwnerController;
import homework.lesson4.terminal.models.Owner;
import homework.lesson4.terminal.models.enums.Operation;
import homework.lesson4.terminal.services.DataBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution /*extends JFrame*/ {

    private static LoginController loginController;

    public Solution(LoginController loginController) {
        Solution.loginController = loginController;
    }

//    private DataBase dataBase = new DataBase();
//    private final MyKeyListener LISTENER = new MyKeyListener();
//    private static final long serialVersionUID = 1L;
//    private static JLabel message = new JLabel();
//    private static JLabel pin = new JLabel();
//    private static String title = "Authenticate";
//    private static String pinFormat = "XXXX";
//    private List<Integer> pinCode = new ArrayList<>();
//    private static Mode mode = Mode.AUTHENTICATE;
//
//    enum Mode {
//        AUTHENTICATE,
//        AUTHENTICATE_COMPLETE
//    }
//
//    public Solution() {
//        JFrame frame = new JFrame("Authenticate");
//        frame.addKeyListener(LISTENER);
//
//        JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(new BorderLayout());
//
//        JPanel pinPanel = new JPanel(new FlowLayout());
//        pinPanel.setBorder(BorderFactory.createTitledBorder("Enter your pin: "));
//        pin.setText(pinFormat);
//        pinPanel.add(pin);
//
//        mainPanel.add(pinPanel, BorderLayout.NORTH);
//
//        JPanel log = new JPanel(new FlowLayout());
//        log.setBorder(BorderFactory.createTitledBorder("log: "));
//        log.add(message);
//        mainPanel.add(log, BorderLayout.CENTER);
//
//        frame.getContentPane().add(mainPanel);
//        frame.setPreferredSize(new Dimension(250, 160));
//        frame.pack();
//        frame.setVisible(true);
//    }

    public static void main(String[] args) throws IOException {

        DataBase dataBase = new DataBase();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            new Solution(new LoginController(reader));
            while (true) {
                Owner owner = loginController.authorize();
                if(owner != null) {
                    OwnerController ownerController = new OwnerController(reader, owner);
                    OwnerController.commandList();
                    Operation operation = ownerController.getRequest();
                    if (operation.equals(Operation.EXIT) || operation.equals(Operation.RETURN))
                        break;
                }
            }
            System.out.println("Thank you for using our service. Whe will glad to see you again!");
        }
    }

//    public static void dataInfo() {
//        JFrame frame = new JFrame("Data Info");
//
//        JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(new BorderLayout());
//
//        JPanel pinPanel = new JPanel(new FlowLayout());
//        pinPanel.setBorder(BorderFactory.createTitledBorder("User info: "));
////        pin.setText(pinFormat);
////        pinPanel.add(pin);
//
//        mainPanel.add(pinPanel, BorderLayout.NORTH);
//
//
//        frame.getContentPane().add(mainPanel);
//        frame.setPreferredSize(new Dimension(250, 160));
//        frame.pack();
//        frame.setVisible(true);
//    }
//
//    class MyKeyListener implements KeyListener {
//
//        @Override
//        public void keyTyped(KeyEvent e) {
//
//        }
//
//        @Override
//        public void keyPressed(KeyEvent e) {
//            switch (mode) {
//                case AUTHENTICATE:
//                    try {
//                        int c = Integer.parseInt(String.valueOf(e.getKeyChar()));
//                        message.setText("");
//                        pinCode.add(c);
//                        pinFormat = pinFormat.replaceFirst("\\D", String.valueOf(c));
//                        pin.setText(pinFormat);
//                        if (pinCode.size() == 4) {
//                            mode = Mode.AUTHENTICATE_COMPLETE;
//                        }
//                    } catch (Exception exception) {
//                        message.setText("Wrong symbol");
//                        System.out.println("Wrong symbol");
//                        return;
//                    }
//                    break;
//            }
//
//        }
//
//        @Override
//        public void keyReleased(KeyEvent e) {
//            if (mode == Mode.AUTHENTICATE_COMPLETE) {
//                dataInfo();
//            }
//        }
//    }

}
