
public class Nodo {
    
    private String info; // Guarda la información, que puede ser una pregunta o un animal.
    private Nodo hIzq, hDer;
    
    public Nodo(String info){
        this.info=info;
        this.hIzq=null;
        this.hDer=null;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Nodo gethIzq() {
        return hIzq;
    }

    public void sethIzq(Nodo hIzq) {
        this.hIzq = hIzq;
    }

    public Nodo gethDer() {
        return hDer;
    }

    public void sethDer(Nodo hDer) {
        this.hDer = hDer;
    }
    
    public boolean esHoja(){
        if(this.hDer==null && this.hIzq==null){
            return true;
        } else {
            return false;
        }
    }
    
    
   
}
