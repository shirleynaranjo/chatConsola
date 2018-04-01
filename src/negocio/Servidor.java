/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import IChat.IServer;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 *
 * @author davit
 */
public class Servidor extends UnicastRemoteObject implements IServer {

    private int thisPort;
    private String thisAdress;
    private Registry registry;
    private String historial;
    private Negocio negocio;

    public Servidor(Negocio negocio) throws Exception {
        historial = "";
        thisAdress = (InetAddress.getLocalHost()).getHostAddress();
        System.out.println(InetAddress.getLocalHost());
        thisPort = 3232;
        System.out.println("Escuchando en " + thisAdress + " puerto " + thisPort);
        this.registry = LocateRegistry.createRegistry(thisPort);
        registry.rebind("server", (IServer) this);
        
        this.negocio = negocio;
    }

    @Override
    public boolean enviarMsj(String msj, String ip) throws RemoteException {
        return negocio.recivirMsj(msj, ip);
    }

    @Override
    public HashMap<String, String> conectar(String ip, String nick) throws RemoteException {
        return negocio.conectarAServidor(ip, nick);
    }

    @Override
    public void agregarNuevo(String ip, String nick) throws RemoteException {
        negocio.agregarNuevo(ip, nick);
    }

}
