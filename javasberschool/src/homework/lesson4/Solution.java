
package homework.lesson4;

import homework.lesson4.terminal.controllers.LoginController;

import java.io.IOException;

public class Solution /*extends JFrame*/ {

    private static final LoginController loginController = new LoginController();

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
//        JFrame.setDefaultLookAndFeelDecorated(true);
//        new homework.lesson4.Solution();
        loginController.authorize();
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
