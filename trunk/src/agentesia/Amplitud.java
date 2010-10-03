/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agentesia;

import java.util.ArrayList;

/**
 *
 * @author jorgeorm
 */
//TODO Clase amplitud incompleta tiene error de memoria.
public class Amplitud extends Busqueda {
    private NodoEstado nodoraiz;
    private int [][] mapa;

    public Amplitud(NodoEstado raiz, int cordXf,int cordYf , int[][] mapa)
    {
        super();
        this.mapa=new int [10][10];
        cordXSalida=cordXf;
        cordYSalida=cordYf;
        nodoraiz=raiz;
        for(int idy=0;idy<10;idy++)
        {
            for(int idx=0;idx<10;idx++)
            {
                this.mapa[idx][idy]=mapa[idx][idy];
            }
        }
    }

     public NodoEstado ejecutar()
    {
        listaNodos.add(nodoraiz);

        while(!(listaNodos.isEmpty()))
        {
            NodoEstado nodoActual=listaNodos.get(0);
            if(esMeta(nodoActual))
            {
                return nodoActual;
            }
            else
            {
                //Expando el nodo por amplitud
                listaNodos.remove(0);
                //EliminoNodos que nacen al devolverse inmediatamente
                ArrayList<NodoEstado> hijos = aplicarOperadores(nodoActual);
                boolean lugarDevolvible=mapa[nodoActual.getX()][nodoActual.getY()]==6||mapa[nodoActual.getX()][nodoActual.getY()]==5||mapa[nodoActual.getX()][nodoActual.getY()]==4;
                for(int idx=0;idx<hijos.size();idx++)
                {
                    char operadorPadre,operadorHijo;
                    operadorHijo=hijos.get(idx).ultimoOperador();
                    operadorPadre=nodoActual.ultimoOperador();
                    boolean inverso=(operadorHijo=='←' && operadorPadre=='→')||
                            (operadorHijo=='→'&& operadorPadre=='←')||
                            (operadorHijo=='↑'&& operadorPadre=='↓')||
                            (operadorHijo=='↓'&& operadorPadre=='↑');
                    if(inverso && !lugarDevolvible) hijos.remove(idx);
                }
                listaNodos.addAll(hijos);
            }
        }
        return null;
    }

    public boolean esMeta(NodoEstado nodo)
    {
        if(nodo.getX()==cordXSalida && nodo.getY()==cordYSalida && nodo.getN_items()<=0)
        {
            return true;
        }
        else return false;
    }
}