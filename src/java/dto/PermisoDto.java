/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import xml.Empaquetable;

/**
 *
 * @author christian
 */
public class PermisoDto implements Empaquetable{
    private int id;
    private String tipo;
    private int estado;
    private Date fechaInicio;
    private Date fechaTermino;
    private Date fechaSolicitud;
    private String descripcion;
    private FuncionarioDto solicitante;
    private FuncionarioDto autorizante;

    public PermisoDto() {
        id = -1;
        tipo = null;
        estado = -1;
        fechaInicio = null;
        fechaTermino = null;
        fechaInicio = null;
        descripcion = null;
        solicitante = null;
        autorizante = null;
    }

    public FuncionarioDto getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(FuncionarioDto solicitante) {
        this.solicitante = solicitante;
    }

    public FuncionarioDto getAutorizante() {
        return autorizante;
    }

    public void setAutorizante(FuncionarioDto autorizante) {
        this.autorizante = autorizante;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    

    @Override
    public void empaquetarXML(Node nodeXML, Document doc) {
        Element permisoXML = doc.createElement("Permiso");
        nodeXML.appendChild(permisoXML);
        if(id != -1)
        {
            Element elementoId = doc.createElement("id");
            elementoId.appendChild(doc.createTextNode(String.valueOf(id)));
            permisoXML.appendChild(elementoId);
        }
        if(tipo != null)
        {
            Element elementoTipo = doc.createElement("tipo");
            elementoTipo.appendChild(doc.createTextNode(this.tipo));
            permisoXML.appendChild(elementoTipo);
        }
        if(descripcion != null)
        {
            Element elementoDescripcion = doc.createElement("descripcion");
            elementoDescripcion.appendChild(doc.createTextNode(this.descripcion));
            permisoXML.appendChild(elementoDescripcion);
        }
        if(estado != -1)
        {
            Element elementoEstado = doc.createElement("estado");
            elementoEstado.appendChild(doc.createTextNode(String.valueOf(estado)));
            permisoXML.appendChild(elementoEstado);
        }
        if(fechaInicio != null)
        {
            Element elementoFechaInicio = doc.createElement("fechaInicio");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            elementoFechaInicio.appendChild(doc.createTextNode(sdf.format(fechaInicio)));
            permisoXML.appendChild(elementoFechaInicio);
        }
        if(fechaTermino != null)
        {
            Element elementoFechaTermino = doc.createElement("fechaTermino");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            elementoFechaTermino.appendChild(doc.createTextNode(sdf.format(fechaTermino)));
            permisoXML.appendChild(elementoFechaTermino);
        }
        if(fechaSolicitud != null)
        {
            Element elementoFechaSolicitud = doc.createElement("fechaSolicitud");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            elementoFechaSolicitud.appendChild(doc.createTextNode(sdf.format(fechaSolicitud)));
            permisoXML.appendChild(elementoFechaSolicitud);
        }
        if(autorizante != null)
        {
            Element autorizanteXML = doc.createElement("Autorizante");
            permisoXML.appendChild(autorizanteXML);
            autorizante.empaquetarXML(autorizanteXML, doc);
        }
        if(solicitante != null)
        {
            Element solicitanteXML = doc.createElement("Solicitante");
            permisoXML.appendChild(solicitanteXML);
            solicitante.empaquetarXML(solicitanteXML, doc);
        }
    }

    @Override
    public String toString() {
        return "PermisoDto{" + "id=" + id + ", tipo=" + tipo + ", estado=" + estado + ", fechaInicio=" + fechaInicio + ", fechaTermino=" + fechaTermino + ", fechaSolicitud=" + fechaSolicitud + ", descripcion=" + descripcion + ", \nsolicitante=" + ((solicitante==null)?solicitante:solicitante.toString()) + ", \nautorizante=" + ((autorizante==null)?autorizante:autorizante.toString()) + '}';
    }
    
    public void empaquetarPermisoParaArchivoXml(Node nodeXML, Document doc)
    {
        Element permisoXML = doc.createElement("permiso");
        nodeXML.appendChild(permisoXML);
        Element funcionarioXML = doc.createElement("funcionario");
        permisoXML.appendChild(funcionarioXML);
        
        Element nombreXML = doc.createElement("nombre");
        nombreXML.appendChild(doc.createTextNode(this.getSolicitante().getNombre()+" "+this.getSolicitante().getApellidoPaterno()));
        funcionarioXML.appendChild(nombreXML);
        Element runXML = doc.createElement("run");
        runXML.appendChild(doc.createTextNode(String.valueOf(this.solicitante.getRun())));
        funcionarioXML.appendChild(runXML);
        String dv = (this.solicitante.getDv() == 10)?"k":String.valueOf(this.solicitante.getDv());
        Element dvXML = doc.createElement("dv");
        dvXML.appendChild(doc.createTextNode(dv));
        funcionarioXML.appendChild(dvXML);
        Element unidadXML = doc.createElement("unidad");
        unidadXML.appendChild(doc.createTextNode(this.solicitante.getUnidad().getNombre()));
        funcionarioXML.appendChild(unidadXML);
        Element detalleXML = doc.createElement("detalle");
        
        permisoXML.appendChild(detalleXML);
        Element fechaInicioXML = doc.createElement("fecha_inicio");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        fechaInicioXML.appendChild(doc.createTextNode(sdf.format(fechaInicio)));
        detalleXML.appendChild(fechaInicioXML);
        Element fechaTerminoXML = doc.createElement("fecha_termino");
        fechaTerminoXML.appendChild(doc.createTextNode(sdf.format(fechaTermino)));
        detalleXML.appendChild(fechaTerminoXML);
        int dias = (1+(int)TimeUnit.DAYS.convert(this.fechaInicio.getTime()-this.fechaTermino.getTime(),TimeUnit.MILLISECONDS));
        Element diasXML = doc.createElement("dias");
        diasXML.appendChild(doc.createTextNode(String.valueOf(dias)));
        detalleXML.appendChild(diasXML);
        Element tipoXML = doc.createElement("tipo");
        tipoXML.appendChild(doc.createTextNode(this.tipo));
        detalleXML.appendChild(tipoXML);
    }
    
    
}
