/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import IChat.IServer;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;

/**
 *
 * @author davit
 */
public class Cliente {

    private IServer servidor;
    private String nick;
    private Registry registry;

    public Cliente(String nick) {
        this.nick = nick;
    }

    public boolean enviarmsj(String msj, String ip,String ipmia) throws RemoteException {
        try {
            registry = LocateRegistry.getRegistry(ip, 3232);
            if ((IServer) registry.lookup("server") != null) {
                servidor = (IServer) registry.lookup("server");
                return servidor.enviarMsj(msj, ipmia);
            } else {
                System.out.println("Conexion Rechazada: Error Al Conectar");
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    public HashMap<String,String> conectar(String ip,String nick, String ipmia){
        try {
            registry = LocateRegistry.getRegistry(ip, 3232);
            if ((IServer) registry.lookup("server") != null) {
                servidor = (IServer) registry.lookup("server");
                return servidor.conectar(ipmia, nick);
            } else {
                System.out.println("Conexion Rechazada: Error Al Conectar");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void agregarNuevo(String ip,String nick,String ipmia)throws RemoteException{
        try {
            registry = LocateRegistry.getRegistry(ip, 3232);
            if ((IServer) registry.lookup("server") != null) {
                servidor = (IServer) registry.lookup("server");
                servidor.agregarNuevo(ipmia, nick);
            } else {
                System.out.println("Conexion Rechazada: Error Al Conectar");
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
