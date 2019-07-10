
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ArbolBinario {
    
    private Nodo root;
    private String contenido; // Para el archivo de texto.

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    
    public ArbolBinario(){
        this.root=null;
        this.contenido="Archivo de texto del adivinador de animales\n";
    }
    
    public void insertarRaiz(Nodo root){
        this.root=root;
    }

    public Nodo getRoot() {
        return root;
    }

    public void setRoot(Nodo root) {
        this.root = root;
    }
    
    
    public int getheight(Nodo root) {
		if (root == null)
			return 0;
		return Math.max(getheight(root.gethIzq()), getheight(root.gethDer())) + 1;
	}
    public boolean preguntar(Nodo actual, ArbolBinario tree){
        int resp;
        // Le pregunta al usuario si el animal que piensa cumple con la característica que se encuentra en el nodo que está
        // visitando el programa y valida que le dé una respuesta correcta.
        resp = JOptionPane.showConfirmDialog(null,"¿El animal que estás pensando " + actual.getInfo()+"?","PREGUNTA",JOptionPane.YES_NO_OPTION);
        while(resp!=JOptionPane.YES_OPTION && resp!=JOptionPane.NO_OPTION){
            JOptionPane.showMessageDialog(null, "Lo siento, tienes que responder a esta pregunta.");
            resp = JOptionPane.showConfirmDialog(null,"¿El animal que estás pensando " + actual.getInfo()+"?","PREGUNTA",JOptionPane.YES_NO_OPTION);
        }
        if(resp==JOptionPane.YES_OPTION){
            return true;
        } else {
            return false;
        }  
    }
    
    // Metodo que le permite al programa recorrer todo el árbol.
    public Nodo moverse(boolean op, Nodo actual){
        // op es un boolean que le indica al programa si se debe ir por la rama izquierda o derecha del árbol.
        if(op){
            return actual.gethIzq();
        } else{
            return actual.gethDer();
        }
    }
        
     // Le pregunta al usuario si su animal es el animal que el árbol tiene guardado en la hoja a la que se llegó.
     // Si no es, lo agrega. Si es, le deja saber que ganó.
     public void preguntarAnimal(Nodo actual, String nombre, ArbolBinario tree){
         boolean direccion=true;
         int resp=JOptionPane.showConfirmDialog(null,"¿" + nombre + ", el animal que estás pensando es " + actual.getInfo()+"?","PREGUNTA",JOptionPane.YES_NO_OPTION);
         
         // Valida que el usuario haya respondido correctamente, sino sigue preguntando hasta que lo haga.
         while(resp!=JOptionPane.YES_OPTION && resp!=JOptionPane.NO_OPTION){
             JOptionPane.showMessageDialog(null, "Lo siento, tienes que responder a esta pregunta.");
             resp=JOptionPane.showConfirmDialog(null,"¿" + nombre + ", el animal que estás pensando es " + actual.getInfo()+"?","PREGUNTA",JOptionPane.YES_NO_OPTION);
         }
         
         // Si es el animal que el programa propuso, le deja saber al usuario que lo adivinó.
         if(resp==JOptionPane.YES_OPTION){
             JOptionPane.showMessageDialog(null, nombre+", tu animal es "+actual.getInfo()+". ¡SOY EL MEJOR!");
         } else {
             // Si NO es el animal que el programa propuso, le pregunta al usuario cuál es y qué lo diferencia del que lo propuso,
             // para así agregarlo a su árbol para un próximo juego.
             
             String animal = JOptionPane.showInputDialog(null, "¡Me ganaste esta vez!\nAyúdame a mejorar, ¿en qué animal estás pensando?");
             // Valida que el usuario haya respondido correctamente, sino sigue preguntando hasta que lo haga.
             while(animal==null || animal.equals("")){
                 JOptionPane.showMessageDialog(null, "Lo siento, tienes que responder a esta pregunta.");
                 animal = JOptionPane.showInputDialog(null, "¡Me ganaste esta vez!\nAyúdame a mejorar, ¿en qué animal estás pensando?");
             }
             
             String diferencia = JOptionPane.showInputDialog(null, "¿Qué diferencia hay entre "+ actual.getInfo() +" y " + animal+"?");
             // Valida que el usuario haya respondido correctamente, sino sigue preguntando hasta que lo haga.
             while(diferencia==null || diferencia.equals("")){
                 JOptionPane.showMessageDialog(null, "Lo siento, tienes que responder a esta pregunta.");
                 diferencia = JOptionPane.showInputDialog(null, "¿Qué diferencia hay entre "+ actual.getInfo() +" y " + animal+"?");
             }
             
             int resp2= JOptionPane.showConfirmDialog(null, "¿"+actual.getInfo()+" "+diferencia+"?", "PREGUNTA", JOptionPane.YES_NO_OPTION); 
             // Valida que el usuario haya respondido correctamente, sino sigue preguntando hasta que lo haga.
             while(resp2!=JOptionPane.YES_OPTION&&resp2!=JOptionPane.NO_OPTION){
                 JOptionPane.showMessageDialog(null, "Lo siento, tienes que responder a esta pregunta.");
                 resp2= JOptionPane.showConfirmDialog(null, "¿"+actual.getInfo()+" "+diferencia+"?", "PREGUNTA", JOptionPane.YES_NO_OPTION); 
             }
             
             JOptionPane.showMessageDialog(null, "¡Gracias! He ampliado mis conocimientos.\n¡Vuelve a jugar y esta vez te ganaré!");
             
             if(resp2==JOptionPane.YES_OPTION){
                 direccion=true;
             } else {
                 direccion=false;
             }
             
             // Finalmente, lo inserta como hijo derecho o izquierdo según sea el caso.
             insertar(animal,actual,diferencia,direccion);
         }
     }
     
     // Separa dos animales por la diferencia que estableció el usuario previamente.
     public void insertar(String animal, Nodo actual, String diferencia, boolean direccion){
         
         Nodo nuevo = new Nodo(animal);
         Nodo viejo = new Nodo(actual.getInfo());
         actual.setInfo(diferencia);
         
         if(direccion){
             actual.sethIzq(viejo);
             actual.sethDer(nuevo);
         } else {
             actual.sethIzq(nuevo);
             actual.sethDer(viejo);
         }
         
     }
     
     // Se recorre el árbol en preOrden para guardar el contenido en un archivo de texto.
     public void PreOrderGuardarContenido(Nodo n, ArbolBinario tree){
		
	if (n == null)
            return;
        
        tree.setContenido(tree.getContenido()+n.getInfo()+",");
        if(n.gethIzq()!=null){
            tree.setContenido(tree.getContenido()+n.gethIzq().getInfo()+",");
        } else {
            tree.setContenido(tree.getContenido()+"null,");
        }
        if(n.gethDer()!=null){
            tree.setContenido(tree.getContenido()+n.gethDer().getInfo()+"\n");
        } else {
            tree.setContenido(tree.getContenido()+"null\n");
        }
        
	PreOrderGuardarContenido(n.gethIzq(),tree);
	PreOrderGuardarContenido(n.gethDer(),tree);
    }
     
    // Se recorre el árbol en preorden para buscar el nodo correspondiente a quien se le debe añadir los hijos.
    public Nodo PreOrderBuscarNodo(Nodo n, String info){
        Nodo aux;
        if (n == null){
            return null;
        } else {
            
            if(n.getInfo().equals(info)){
                return n;
            } else {
                aux=PreOrderBuscarNodo(n.gethIzq(),info);
                if(aux==null){
                    aux=PreOrderBuscarNodo(n.gethDer(),info);
                }
                return aux;
            }
        }
    }
    
    public int altura(Nodo root) {
	if (root == null)
		return 0;
	return Math.max(altura(root.gethIzq()), altura(root.gethDer())) + 1;
    }

    
}
