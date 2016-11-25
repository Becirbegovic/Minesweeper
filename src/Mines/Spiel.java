package Mines;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Spiel {
    private Zelle[][] zelles;
    private int zellenID = 0;
    private int side = 8;
    private int limit = side-2;

//Wird der JFrame definiert 
    public void setBoard(){
        JFrame frame = new JFrame();
        frame.add(addZelle());

        plantMines();
        setCellValues();

        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

//Fügt die Zellen hinzu 
    public JPanel addZelle(){
        JPanel panel = new JPanel(new GridLayout(side,side));
        zelles = new Zelle[side][side];
        for(int i = 0; i< side; i++){
            for(int j = 0; j<side; j++){
                zelles[i][j] = new Zelle(this);
                zelles[i][j].setId(getID());
                panel.add(zelles[i][j].getButton());
            }
        }
        return panel;
    }


    public void plantMines(){
        ArrayList<Integer> loc = generateMinesLocation(10);
        for(int i : loc){
            getCell(i).setValue(-1);
        }
    }

    //Zufelige verteilung der Minen
    public ArrayList<Integer> generateMinesLocation(int q){
        ArrayList<Integer> loc = new ArrayList<Integer>();
        int random;
        for(int i = 0; i<q;){
            random = (int)(Math.random()* (side*side));
            if(!loc.contains(random)){
                loc.add(random);
                i++;
            }
        }
        return loc;
    }


    /*Diese Methode count Stellt die Anzahl Minnen, Zellen und wert*/
    public void setCellValues(){
        for(int i = 0; i<side; i++){
            for(int j = 0; j<side; j++){
                 if(zelles[i][j].getValue() != -1){
                     if(j>=1 && zelles[i][j-1].getValue() == -1) zelles[i][j].incrementValue();
                     if(j<= limit && zelles[i][j+1].getValue() == -1) zelles[i][j].incrementValue();
                     if(i>=1 && zelles[i-1][j].getValue() == -1) zelles[i][j].incrementValue();
                     if(i<= limit && zelles[i+1][j].getValue() == -1) zelles[i][j].incrementValue();
                     if(i>=1 && j>= 1 && zelles[i-1][j-1].getValue() == -1) zelles[i][j].incrementValue();
                     if(i<= limit && j<= limit && zelles[i+1][j+1].getValue() == -1) zelles[i][j].incrementValue();
                     if(i>=1 && j<= limit && zelles[i-1][j+1].getValue() == -1) zelles[i][j].incrementValue();
                     if(i<= limit && j>= 1 && zelles[i+1][j-1].getValue() == -1) zelles[i][j].incrementValue();
                 }
            }
        }
    }
    /*Diese Methode beginnt Kettenreaktion. Wenn Benutzer auf bestimmte Zelle klicken, wenn Zelle leer ist (Wert = 0) dies
    Methode suchen nach anderen leeren Zellen neben aktiviert. Wenn eine findet, rufen Sie checkCell und in der Tat,
    Starten Sie den nächsten Scan auf seinem nächsten Bereich
     */
    public void scanForEmptyCells(){
        for(int i = 0; i<side; i++){
            for(int j = 0; j<side; j++){
                if(!zelles[i][j].isNotChecked()){
                    if(j>=1 && zelles[i][j-1].isEmpty()) zelles[i][j-1].checkCell();
                    if(j<= limit && zelles[i][j+1].isEmpty()) zelles[i][j+1].checkCell();
                    if(i>=1 && zelles[i-1][j].isEmpty()) zelles[i-1][j].checkCell();
                    if(i<= limit && zelles[i+1][j].isEmpty()) zelles[i+1][j].checkCell();
                    if(i>=1 && j>= 1 && zelles[i-1][j-1].isEmpty()) zelles[i-1][j-1].checkCell();
                    if(i<= limit && j<= limit && zelles[i+1][j+1].isEmpty()) zelles[i+1][j+1].checkCell();
                    if(i>=1 && j<= limit && zelles[i-1][j+1].isEmpty()) zelles[i-1][j+1].checkCell();
                    if(i<= limit && j>= 1 && zelles[i+1][j-1].isEmpty()) zelles[i+1][j-1].checkCell();
                }
            }
        }
    }



    public int getID(){
        int id = zellenID;
        zellenID++;
        return id;
    }



    public Zelle getCell(int id){
        for(Zelle[] a : zelles){
            for(Zelle b : a){
                if(b.getId() == id) return b;

            }
        }
        return null;
    }



    public void fail(){
        for(Zelle[] a : zelles){
            for(Zelle b : a){
                b.reveal();
            }
        }
    }
}