/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 *
 * @author davit
 */
public class Negocio {

    private String nick;
    private HashMap<String, String> contactos;
    private String ip;
    private Cliente cliente;
    private Servidor servidor;

    public Negocio(String nick) throws UnknownHostException, Exception {
        this.nick = nick;
        contactos = new HashMap<String, String>();
        ip = (InetAddress.getLocalHost()).getHostAddress();
        cliente = new Cliente(nick);
        servidor= new Servidor(this);
    }

    public HashMap<String, String> getContactos() {
        return contactos;
    }

    //server
    public boolean recivirMsj(String msj, String ip) {
        System.out.println(contactos.get(ip) + " : " + msj);
        return true;
    }

    public HashMap<String, String> conectarAServidor(String ip, String nick) throws RemoteException {
        HashMap<String, String> copia = contactos;
        for (String ips : copia.keySet()) {
            agregarNuevo(ip,nick,this.ip);
        }
        copia.put(this.ip, this.nick);
        contactos.put(ip, nick);
        return copia;
    }

    public void agregarNuevo(String ip, String nick) throws RemoteException { 
        for (String ips : contactos.keySet()) {
            agregarNuevo(ips,nick,ip);
        }
        contactos.put(ip, nick);
    }

    //cliente
    public void enviarmsj(String msj) throws RemoteException {
        contactos.remove(ip);        
        for (String ips : contactos.keySet()) {
            if (!cliente.enviarmsj(msj, ips, this.ip)) {
                System.out.println(contactos.get(ips) + " ha salidodel chat.");
                contactos.remove(ips);
            }
        }
    }

    public boolean conectar(String ip) {
        contactos = cliente.conectar(ip, nick, this.ip);
        if (contactos != null) {
            return true;
        } else {
            return false;
        }
    }
    public void agregarNuevo(String ip,String nick,String ipmia) throws RemoteException{
        cliente.agregarNuevo(ip, nick, ipmia);
    }
}
