
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BasicCal implements ActionListener{
    //Declaring Variables
    JFrame win = new JFrame();
    //  JPanel res_p = new JPanel();
    JButton[] numbers = new JButton[10];
    Font myfont = new Font("Courier", Font.BOLD, 50);
    JButton[] operators = new JButton[9];
    JButton del = new JButton("C");
    JButton Ac = new JButton("AC");
    JTextField res = new JTextField();
    String[] op_name = {"+","-","*" , "/" , "%" , "//", "^", ".", "="};
    //JButton[] misc = new JButton[3];
    //String[] misc_name = {"sqrt","(",")"};
    double num1 = 0, num2 = 0, result = 0;
    char operator;
    byte flag = 0;
    int iterator = 1;
    JLabel expressio = new JLabel("");


    public BasicCal(){
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setBackground(new Color(38, 31, 31));
        win.setSize(500, 420);
        win.setMaximumSize(new Dimension(500, 420));
        win.setMinimumSize(new Dimension(500, 420));
        win.setResizable(false);
        win.setTitle("BasicCal");
        res.setBounds(5,30,470,165);
        res.setEditable(false);
        res.setFont(myfont);
        expressio.setBounds(5,5, 470, 10 );


        //Placing buttons for numbers.
        try{
            for (int i = 0, x = 5, y = 200; i < 8; i++, x += 60) {
                numbers[i] = new JButton(String.valueOf(i + 1));
                numbers[i].setFocusable(false);
                operators[i] = new JButton(op_name[i]);
                operators[i].setFocusable(false);
                numbers[i].addActionListener(this);
                operators[i].addActionListener(this);
                if (x == 245) {
                    x = 5;
                    y += 60;
                }
                // System.out.println(x + "," + y);
                numbers[i].setBounds(x, y, 50, 55);
                win.add(numbers[i]);
            }
        }
        finally{
            numbers[8] = new JButton("9");
            numbers[9] = new JButton("0");
            numbers[8].setFocusable(false);
            numbers[9].setFocusable(false);
            numbers[8].addActionListener(this);
            numbers[9].addActionListener(this);
            numbers[8].setBounds(5, 320, 50, 55);
            numbers[9].setBounds(65, 320, 50, 55);
            win.add(numbers[9]);
            win.add(numbers[8]);
        }

        //Placing buttons for operators
        try {
            for (int i = 0, x = 240, y = 200; i < 7; i++, x += 60) {
                if (x == 480) {
                    x = 240;
                    y += 60;
                }
                //System.out.println(x + "," + y);
                operators[i].setBounds(x, y, 55, 55);
                win.add(operators[i]);
            }
        }
        finally {
            del.setBounds(420,260, 55, 55);
            Ac.setBounds(360, 320, 115, 55);
            del.setFocusable(false);
            Ac.setFocusable(false);
            del.addActionListener(this);
            Ac.addActionListener(this);
            operators[7] = new JButton(".");
            operators[7].setBounds(125, 320,50,55);
            operators[7].setFocusable(false);
            operators[7].addActionListener(this);

            operators[8] = new JButton("=");
            operators[8].setFocusable(false);
            operators[8].addActionListener(this);
            operators[8].setBounds(185, 320, 170, 55);
            win.add(operators[7]);
            win.add(operators[8]);
            win.add(Ac);
            win.add(del);
        }

        //Placing Buttons for some additional features.
//        try {
//            for (int i = 1, x = 125, y = 140; i < 3; i++, x += 60) {
//                misc[i] = new JButton(misc_name[i]);
//                misc[i].setBounds(x,y,50, 55);
//                misc[i].setFocusable(false);
//                misc[i].addActionListener(this);
//                win.add(misc[i]);
//            }
//        }
//        finally {
//            misc[0] = new JButton("sqrt");
//            misc[0].setFocusable(false);
//            misc[0].addActionListener(this);
//            misc[0].setBounds(5,140,110, 55);
//            win.add(misc[0]);
//        }

        //Some additional work on the box

        win.setLayout(null);
        win.add(res);
        win.add(expressio);
        win.setVisible(true);
    }

    public static void main(String[] args) {
        BasicCal cal = new BasicCal();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        for (int i = 0 ; i < 10 ; i++)
            if (e.getSource() == numbers[i]) {
                res.setText(res.getText().concat(String.valueOf(numbers[i].getText())));
                if (flag == 0) {
                    flag = 1;
                    expressio.setText(expressio.getText().concat(res.getText()));
                }
                else {
                    expressio.setText(expressio.getText().concat(res.getText().substring(iterator, res.getText().length())));
                    iterator++;
                }
            }


        for (int i = 0 ; i < 8 ; i++)
            if (e.getSource() == operators[i]) {
                double temp = num1;
                num1 = Double.parseDouble(res.getText());
                res.setText(String.valueOf(operators[i].getText()));
                expressio.setText(expressio.getText().concat(String.valueOf(operators[i].getText())));
                iterator = 1;
                if (i == 5)
                    operator = 'f';
                else
                    operator = operators[i].getText().charAt(0);
//                switch (operator) {
//                    case '+':
//                        num1 = temp + num1;
//                        break;
//                    case '-':
//                        num1 = num1 - temp;
//                        break;
//                    case '*':
//                        num1 = temp * num1;
//                        break;
//                    case '/':
//                        num1 = temp / num1;
//                        break;
//                    case '%':
//                        num1 = temp % num1;
//                        break;
//                    case 'f':
//                        double j;
//                        for (j = num1 ; j < temp ; j += num1);
//                        result = j;
//                        break;
//                    case '^':
//                        num1 = Math.pow(temp, num1);
//                        break;
//                }
//                break;
            }

        if (e.getSource() == Ac) {
            expressio.setText("");
            iterator = 1;
            flag = 0;
            num1 = 0;
            num2 = 0;
            result = 0;
            res.setText("");
        }

        if (e.getSource() == del) {
            res.setText(res.getText().substring(0, res.getText().length() - 1));
            expressio.setText(expressio.getText().substring(0, expressio.getText().length() - 1));
        }

//        for (int i = 1 ; i < 3 ; i++)
//            if (e.getSource() == misc[i])
//                res.setText(res.getText().concat(String.valueOf(misc[i].getText())));

        if (e.getSource() == operators[8]){
            if (operator == 'f')
                num2 = Double.parseDouble(res.getText().substring(2, res.getText().length()));
            else
                num2 = Double.parseDouble(res.getText().substring(1, res.getText().length()));
            expressio.setText("=");
            flag = 0;
            iterator = 1;
            switch (operator){
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
                case '%':
                    result = num1 % num2;
                    break;
                case 'f':
                    result = Double.parseDouble(String.valueOf(num1 / num2).substring(0,String.valueOf(num1 / num2).indexOf(".")));
                    break;
                case '^':
                    result = Math.pow(num1, num2);
                    break;
            }
            res.setText(String.valueOf(result));
            num1 = 0;
            num2 = 0;
            result = 0;
        }

    }

}

