package model;

import java.text.MessageFormat;

public class Data {

    private int cheie;
    private String fisier;
    private int suma;

    public int getId() {
        return cheie;
    }

    public void setId(int cheie) {
        this.cheie = cheie;
    }

    public String getFile() {
        return fisier;
    }

    public void setFile(String fisier) {
        this.fisier = fisier;
    }

    public int getSum() {
        return suma;
    }

    public void setSum(int suma) {
        this.suma = suma;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Data: id= {0} - File: {1} - Sum: {2}", cheie, fisier, suma);
    }
}
