/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ideavideojuego;

/**
 *
 * @author Michael Lofer
 */
public class Ataque {
    private int dmg, usos, probabilidadFallo;
    private String nombre, descripcion;

    public Ataque(int dmg, int usos, String nombre, String descripcion, int probabilidadFallo) {
        this.dmg = dmg;
        this.usos = usos;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.probabilidadFallo=probabilidadFallo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getProbabilidadFallo() {
        return probabilidadFallo;
    }

    public void setProbabilidadFallo(int probabilidadFallo) {
        this.probabilidadFallo = probabilidadFallo;
    }
    
    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getUsos() {
        return usos;
    }

    public void setUsos(int usos) {
        this.usos = usos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public boolean isAcertado(){  //devuelve si hacierta el ataque o no
        boolean acertado;
        int contador=0;
        for (int i = 0; i < 10; i++) {
            int valor=(int)(Math.floor(Math.random()*2));
            contador +=valor;
        }
        contador = contador*10; //lo multiplica por 10 para compararlo con la probablidad de fallo
        if(contador>=probabilidadFallo){ //si el contador es mayor que la probabilidad de fallo el ataque acierta
            acertado=true;
        }else{
            acertado=false;
        }
        return acertado;
    }

    @Override
    public String toString() {
        return "Ataque{" + "dmg=" + dmg + ", usos=" + usos + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }
}
