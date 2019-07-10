
import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class Archivo {
    
    static FileInputStream entrada;
    static FileOutputStream salida;
    static File archivo;
    
    public Archivo(){
        
    }
    
    public static void GuardarTexto(File archivo, String contenido){
        
        try {
            salida = new FileOutputStream(archivo);
            byte[] bytesTxt = contenido.getBytes();
            salida.write(bytesTxt);
            JOptionPane.showMessageDialog(null, "Se guardó el archivo con éxito.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public String AbrirTexto(File archivo){
        String contenido="";
        try {
            entrada = new FileInputStream(archivo);
            int ascci;
            while((ascci = entrada.read())!= -1){
                char carcater = (char)ascci;
                contenido += carcater;
            }
        } catch (Exception e) {
        }
        return contenido;
    }
    
    
    public static boolean archivoValido(File archivo){
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo.getAbsolutePath()));
            if(br.readLine().contains("Archivo de texto del adivinador de animales")){
                return true;
            } 
        } catch (Exception e) {
        }
        return false;
    }
    
    
    public static ArbolBinario crearArbolDesdeArchivo(File archivo, ArbolBinario tree){
    String linea="";
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo.getAbsolutePath()));
            while ((linea= br.readLine())!=null) {
                if(linea.contains("Archivo de texto del adivinador de animales")&&(linea= br.readLine())!=null) {
                    do{
                        String[] nodos = linea.split(",");
                        if(tree.getRoot()==null){
                            tree.setRoot(new Nodo(nodos[0]));
                            if(!nodos[1].equals("null")){
                                tree.getRoot().sethIzq(new Nodo(nodos[1]));
                            }
                            if(!nodos[2].equals("null")){
                                tree.getRoot().sethDer(new Nodo(nodos[2]));
                            }
                        } else {
                            Nodo aux;
                            aux=tree.PreOrderBuscarNodo(tree.getRoot(), nodos[0]);
                            if(!nodos[1].equals("null")){
                                aux.sethIzq(new Nodo(nodos[1]));
                            }
                            if(!nodos[2].equals("null")){
                                aux.sethDer(new Nodo(nodos[2]));
                            }
                        }
                    } while((linea=br.readLine())!=null);
                }
            }
            return tree;
        } catch(Exception e){
            return tree;
        }    
}
    
}
