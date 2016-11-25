package Mines;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Zelle implements ActionListener{
    private JButton button;
    private Spiel spiel;
    private int value;
    private int id;
    private boolean notChecked;

    
    //Hier werden die Felder fürs Spilen generiert
    public Zelle(Spiel spiel){
        button = new JButton();
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(40,40));
        button.setBackground(Color.LIGHT_GRAY);
        button.setMargin(new Insets(0,0,0,0));
        this.spiel = spiel;
        notChecked = true;
    }

    public JButton getButton() {
        return button;
    }

    public int getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(int value) {
        this.value = value;
    }
// Wen der User auf eine Bobe Klickt wird die Angezeigt und das Feld Wird Rot
    public void displayValue(){
        if(value==-1){
            button.setText("\u2600");
            button.setBackground(Color.RED);
        }else if(value!=0){
            button.setText(String.valueOf(value));
        }
    }
// Ihr werden die Felder überprüft 
    public void checkCell(){
        button.setEnabled(false);
        displayValue();
        notChecked = false;
        if(value == 0) spiel.scanForEmptyCells();
        if(value == -1) spiel.fail();
    }

    public void incrementValue(){
        value++;
    }

    public boolean isNotChecked(){
        return notChecked;
    }

    public boolean isEmpty(){
        return isNotChecked() && value==0;
    }

    public void reveal(){
        displayValue();
        button.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkCell();
    }

}