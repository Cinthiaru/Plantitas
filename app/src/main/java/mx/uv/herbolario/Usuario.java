package mx.uv.herbolario;

public class Usuario {
    int id;
    public String nombre;
    public String user;
    public String password;
    public String rol;

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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }
}
