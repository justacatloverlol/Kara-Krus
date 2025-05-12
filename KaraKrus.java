import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

public class KaraKrus extends JFrame implements ActionListener{

    Font h1 = new Font("Comic Sans MS", Font.BOLD, 80);
    Font h2 = new Font("Comic Sans MS", Font.BOLD, 60);
    Font h3 = new Font("Comic Sans MS", Font.BOLD, 40);

    JLabel title;
    JButton play;
    JLabel bet;
    JButton heads;
    JButton tails;
    JButton betbutton;
    JLabel balance;
    JButton max;
    JButton addfive;
    JLabel balancewarning;

    int currentbalance = 100;
    int currentbet = 0;
    String playerchoice = "";
    String[] flip = {"Heads", "Tails"};
    Random random = new Random();


    KaraKrus(){

        title = new JLabel("Kara Krus");
        title.setFont(h1);
        title.setBounds(100,25,400,100);
        title.setForeground(Color.YELLOW);

        play = new JButton("Maglaro");
        play.setFont(h2);
        play.setBounds(100,300,400,100);
        play.setBackground(Color.GREEN);
        play.setFocusPainted(false);
        play.setBorder(BorderFactory.createEmptyBorder());
        play.setForeground(Color.WHITE);
        play.addActionListener(this);

        bet = new JLabel(""+currentbet);
        bet.setFont(h3);
        bet.setBounds(200,500,200,100);
        bet.setBackground(Color.WHITE);
        bet.setForeground(Color.BLACK);
        bet.setOpaque(true);

        addfive = new JButton("+5");
        addfive.setFont(h3);
        addfive.setBackground(Color.WHITE);
        addfive.setForeground(Color.BLACK);
        addfive.setFocusPainted(false);
        addfive.setBorder(BorderFactory.createEmptyBorder());
        addfive.setBounds(80,500,100,100);
        addfive.addActionListener(this);

        max = new JButton("MAX");
        max.setFont(h3);
        max.setBackground(Color.WHITE);
        max.setForeground(Color.BLACK);
        max.setFocusPainted(false);
        max.setBorder(BorderFactory.createEmptyBorder());
        max.setBounds(420,500,100,100);
        max.addActionListener(this);

        balancewarning = new JLabel();
        balancewarning.setFont(h3);
        balancewarning.setForeground(Color.RED);
        balancewarning.setBounds(100,580,400,100);

        betbutton = new JButton("Bet");
        betbutton.setFont(h2);
        betbutton.setBounds(100,380,400,100);
        betbutton.setBackground(Color.GREEN);
        betbutton.setFocusPainted(false);
        betbutton.setBorder(BorderFactory.createEmptyBorder());
        betbutton.setForeground(Color.WHITE);
        betbutton.addActionListener(this);

        heads = new JButton("Heads");
        heads.setFont(h2);
        heads.setBounds(35,50,260,300);
        heads.setBackground(Color.RED);
        heads.setFocusPainted(false);
        heads.setBorder(BorderFactory.createEmptyBorder());
        heads.setForeground(Color.WHITE);
        heads.addActionListener(this);

        tails = new JButton("Tails");
        tails.setFont(h2);
        tails.setBounds(300,50,260,300);
        tails.setBackground(Color.BLACK);
        tails.setFocusPainted(false);
        tails.setBorder(BorderFactory.createEmptyBorder());
        tails.setForeground(Color.WHITE);
        tails.addActionListener(this);

        balance = new JLabel("Balance: "+currentbalance);
        balance.setFont(h3);
        balance.setForeground(Color.YELLOW);
        balance.setBounds(100,650,400,100);

        this.add(title);
        this.add(play);
        this.add(bet);
        this.add(addfive);
        this.add(max);
        this.add(balancewarning);
        this.add(betbutton);
        this.add(heads);
        this.add(tails);
        this.add(balance);

        bet.setVisible(false);
        addfive.setVisible(false);
        max.setVisible(false);
        balancewarning.setVisible(false);
        betbutton.setVisible(false);
        heads.setVisible(false);
        tails.setVisible(false);
        balance.setVisible(false);

        this.setSize(600,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(20,20,20));
        this.setTitle("Kara Krus");
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent clicked){

        if (clicked.getSource() == play){
            title.setVisible(false);
            play.setVisible(false);
            bet.setVisible(true);
            addfive.setVisible(true);
            max.setVisible(true);
            balancewarning.setVisible(true);
            betbutton.setVisible(true);
            heads.setVisible(true);
            tails.setVisible(true);
            balance.setVisible(true);
        }

        if (clicked.getSource() == addfive) {
            if (currentbet + 5 <= currentbalance) {
                currentbet += 5;
                bet.setText("" + currentbet);
            }
            else {
                currentbet += 0;
                bet.setText("" + currentbet);
                balancewarning.setText("Not Enough Balance");
            }
        }

        else if (clicked.getSource() == max) {
            currentbet = currentbalance;
            bet.setText("" + currentbet);
        }

        if (clicked.getSource() == betbutton){
            if (currentbet <= 4){
                balancewarning.setText("Minimum Bet is 5");
            }
            else if(playerchoice.equals("")){
                balancewarning.setText("Heads or Tails?");
            }
            else{
                String winningside = flip[random.nextInt(flip.length)];
                System.out.print(winningside);
                JOptionPane.showMessageDialog(this,"Coin Flip Result is "+winningside);;
                if (playerchoice.equalsIgnoreCase(winningside)){
                    balancewarning.setText("You win! +"+currentbet);
                    currentbalance += currentbet;
                    currentbet = 0;
                    bet.setText(""+currentbet);
                    balance.setText("Balance: "+currentbalance);
                }
                else {
                    balancewarning.setText("You lose! -"+currentbet);
                    currentbalance -= currentbet;
                    currentbet = 0;
                    bet.setText(""+currentbet);
                    balance.setText("Balance: "+currentbalance);
                }
                if (currentbalance == 0) {

                    int response = JOptionPane.showConfirmDialog(this, "Zero Balance, Restart?", "Game Over!", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        currentbalance = 100;
                        currentbet = 0;
                        playerchoice = "";

                        bet.setText("" + currentbet);
                        balance.setText("Balance: " + currentbalance);
                        balancewarning.setText("");

                        heads.setBackground(Color.RED);
                        tails.setBackground(Color.BLACK);
                        heads.setForeground(Color.WHITE);
                        tails.setForeground(Color.WHITE);
                        heads.setEnabled(true);
                        tails.setEnabled(true);

                        title.setVisible(true);
                        play.setVisible(true);
                        bet.setVisible(false);
                        addfive.setVisible(false);
                        max.setVisible(false);
                        balancewarning.setVisible(false);
                        betbutton.setVisible(false);
                        heads.setVisible(false);
                        tails.setVisible(false);
                        balance.setVisible(false);
                    }
                    else {
                        System.exit(0);
                    }
                    }
            }
        }

        if (clicked.getSource() == heads){

            if (playerchoice.equalsIgnoreCase("tails")){
                tails.setBackground(Color.RED);
                tails.setForeground(Color.WHITE);
                playerchoice = "heads";
                heads.setBackground(Color.WHITE);
                heads.setForeground(Color.BLACK);
                System.out.println(playerchoice);
            }
            else {
                heads.setBackground(Color.WHITE);
                heads.setForeground(Color.BLACK);
                playerchoice = "heads";
                System.out.println(playerchoice);
            }

        }


        else if (clicked.getSource() == tails){
            if (playerchoice.equalsIgnoreCase("heads")){
                heads.setBackground(Color.RED);
                heads.setForeground(Color.WHITE);
                playerchoice = "tails";
                tails.setBackground(Color.WHITE);
                tails.setForeground(Color.BLACK);
                System.out.println(playerchoice);
            }
            else {
                tails.setBackground(Color.WHITE);
                tails.setForeground(Color.BLACK);
                playerchoice = "tails";
                System.out.println(playerchoice);
            }
        }



    }

    public static void main(String[] args) {
        new KaraKrus();
    }

}
