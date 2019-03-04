package mx.uv.herbolario;

public class Usuario {
    int id;
    public String nombre;
    public String user;

    public Usuario(String nombre, String user ){
        this.id=id;
        this.nombre=nombre;
        this.user=user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }
}
