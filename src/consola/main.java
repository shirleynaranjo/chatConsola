/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consola;

import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.Scanner;
import negocio.Negocio;

/**
 *
 * @author davit
 */
public class main {

    public static void main(String[] args) throws UnknownHostException, RemoteException, Exception {

        Scanner sc = new Scanner(System.in);
        System.out.println("Escriba Su Nick");
        String nombre = sc.next();
        Negocio n = new Negocio(nombre);
        String ip = "";
        System.out.println("si desea conectarse a una red digite 1");
        int opcion = sc.nextInt();
        if (opcion == 1) {
            while (true) {
                System.out.println("Escriba la ip a la cual se va a conectar");
                ip = sc.next();
                if (n.conectar(ip)) {
                    System.out.println("conexion estrablecida");
                    break;
                } else {
                    System.out.println("No se pudo establecer conexion");
                }
            }
        } else {
            System.out.println("red creada");
        }
        System.out.println("Ya puedes comunicarte con tus amigos ");
        while (true) {
            n.enviarmsj(sc.nextLine());

        }

    }
}
