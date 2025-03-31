package org.uv.dapp02practica03;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "empleados")
public class EmpleadoPojo implements Serializable {
    
    @Id
    @GeneratedValue(generator = "empleados_clave_seq",
                    strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "empleados_clave_seq",
                        sequenceName = "empleados_clave_seq", initialValue = 1,
                        allocationSize = 1)
    @Column
    private Long clave;
    
    @Column
    private String nombre;
    
    @Column
    private String direccion;
    
    @Column
    private String telefono;

    public Long getClave() {
        return clave;
    }

    public void setClave(Long clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Empleado { " +
                "ID=" + clave +
                ", Nombre='" + nombre + '\'' +
                ", Dirección='" + direccion + '\'' +
                ", Teléfono='" + telefono + '\'' +
                " }";
    }
}
