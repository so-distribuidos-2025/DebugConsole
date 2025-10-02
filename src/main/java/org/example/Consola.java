package org.example;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import com.mycompany.SensorTemperatura.interfaces.ISensorRMI;

public class Consola {

    Scanner sc;

    ISensorRMI sensorTemperatura;

    public Consola() {
        sc = new Scanner(System.in);

        sensorTemperatura = conexionRMI(22000, "SensorTemperaturaRMI");

        while (true) {
            System.out.println("Esperando comando");
            parseCommand(sc.nextLine());
        }
    }

    public ISensorRMI conexionRMI(int port, String name) {
        try {
            String direccionRMI = String.format("rmi://localhost:%d/%s", port, name);
            ISensorRMI sensor = (ISensorRMI) Naming.lookup(direccionRMI);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            System.err.println("Fallo al conectar al sensor " + e.getMessage());
        }
        return null;
    }

    void parseCommand(String command) {
        String[] args = command.split(" ");

        ISensorRMI target;

        String head = args[0];

        switch (head) {
            case "temperatura":
                target = this.sensorTemperatura;
                int op = (args[1] == "set") ? 1 : (args[1] == "mode" ? 2 : -1);
                break;
            case "humedad":
                break;
            case "radiacion":
                break;
            case "lluvia":
                break;
        }
    }

}
}
