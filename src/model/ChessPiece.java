package model;


import java.awt.*;
import java.util.Objects;

public class ChessPiece {
    // Diamond, Circle, ...
    private String name;
    private int score=10;
    private Color color;

    static public boolean statechess=false;
    public ChessPiece(String name) {
        //String linshi=null;

        if(statechess){
            this.name=Constant.change(name);
        }else{

        this.name = name;}

        this.color = Constant.colorMap.get(name);
    }

    public String getName() {

        return name;
    }

    public Color getColor(){return color;}

    public boolean equals(ChessPiece chessPiece){return Objects.equals(this.getName(), chessPiece.getName());}

}
