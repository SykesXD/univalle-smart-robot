/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agentesia;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author jorgeorm
 */
public class Robot {
    int x,y;
    int gifIzq,gifDer,gifItem;
    BufferedImage [] camiarDerecha,caminarIzquierda,celebrar;

    public Robot() {
        camiarDerecha= new BufferedImage[9];
        caminarIzquierda=new BufferedImage[9];
        celebrar=new BufferedImage[11];
        gifDer=0;
        gifIzq=0;
        for(int i=0;i<9;i++)
        {
            camiarDerecha[i] = cargarImagen("img/caminarDer/" +(i+1)+ ".gif");
            caminarIzquierda[i] = cargarImagen("img/caminarIzq/" +(i+1)+ ".gif");
        }
        for(int i=0;i<11;i++)
        {
            celebrar[i] = cargarImagen("img/celebrar/" +(i+1)+ ".gif");
        }
    }

    public final BufferedImage cargarImagen(String nombre)
    {
        URL url=null;
        try {
            url=getClass().getClassLoader().getResource(nombre);
            return ImageIO.read(url);
        } catch (IOException ex) {
            System.out.println("No se pudo cargar la imagen " + nombre +" de "+url);
            System.out.println("El error fue : ");
            Logger.getLogger(EscenarioGrafico.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        }
    }

    public void animarMov(boolean derecha)
    {
        if(derecha)
        {
            gifDer++;
            if(gifDer>8)gifDer=0;
        }else{
            gifIzq++;
            if(gifIzq>8)gifIzq=0;
        }
        
    }

    public void animarItem()
    {
        gifItem++;
        if(gifItem>10)gifItem=0;
    }
    public void pintarItem(Graphics graph,ImageObserver imobs,int x,int y)
    {
        graph.drawImage(celebrar[gifItem], x, y, imobs);
    }

    public void pintar(boolean Derecha,Graphics graph,ImageObserver imobs,int x,int y)
    {
        if(Derecha)graph.drawImage(camiarDerecha[gifDer], x, y, imobs);
        else graph.drawImage(caminarIzquierda[gifIzq], x, y, imobs);
    }

}
