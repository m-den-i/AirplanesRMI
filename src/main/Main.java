package main;

import control.DataCtrl;
import logging.MyLogger;
import model.AirCompanyHolder;
import model.apbuilding.AirPlaneModel;
import parser.Parser;
import remote.DataCtrlIntf;
import serializer.AirportDrive;
import sun.plugin.javascript.navig.Array;

import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
/**
 * Created by denis on 12.12.14.
 * main.Main class that starts remote service,
 * provided by DataCtrlIntf
 */
public class Main {
    static Logger log;
    public static void main(String args[]) {
        try {
            MyLogger.setup("ServerLogger");
            log = Logger.getLogger("ServerLogger");
            DataCtrl obj = DataCtrl.getInstance();

            //DataCtrlIntf stub = (DataCtrlIntf) UnicastRemoteObject.exportObject(obj, 0);
            //Registry registry = LocateRegistry.createRegistry(8112);
            //registry.bind("Airplanes", stub);

            System.err.println("Server ready");
            int choice = -1;
            Scanner in = new Scanner(System.in);
            while (choice != 0){
                System.out.println("*****\n1 - serialize current state");
                System.out.println("2 - deserialize current state");
                System.out.println("3 - show airports");
                System.out.println("4 - set airport");
                System.out.println("5 - show planes of airport");
                System.out.println("6 - show models");
                System.out.println("7 - add model");
                System.out.println("8 - add airport");
                System.out.println("9 - add model from file");
                System.out.println("0 - quit\n>>>");
                choice = in.nextInt();
                switch (choice){
                    case 0: {break;}
                    case 1: {
                        System.out.println("Input path");
                        in.nextLine();
                        String name = in.nextLine();
                        obj.serialize(name);
                        break;
                    }
                    case 2:{
                        System.out.println("Input path");
                        in.nextLine();
                        String name = in.nextLine();
                        obj.deserialize(name);
                        break;
                    }
                    case 3:{
                        System.out.println("--------\nCURRENT:");
                        System.out.println(obj.getAirCompanyInfo());
                        System.out.println("--------\nAVAILABLE:");
                        for (String i : obj.getAirCompanyNamesList())
                            System.out.println(i);
                        break;
                    }
                    case 4:{
                        System.out.println("Input airport name");
                        String name = in.next();
                        obj.setAirport(name);
                        break;
                    }
                    case 5:{
                        for (String i : obj.getAirPlaneIDs())
                            System.out.println(i);
                        break;
                    }
                    case 6:{
                        for (String i : obj.getBuilderNames())
                            System.out.println(i);
                        break;
                    }
                    case 7:{
                        System.out.println("Input airplane name");
                        in.nextLine();
                        String name = in.nextLine();
                        obj.addAirplane(name);
                        break;
                    }
                    case 8:{
                        System.out.println("Input airport name and info");
                        String name = in.next();
                        String info = in.next();
                        obj.addNewAirport(name, info);
                        break;
                    }
                    case 9:{
                        ArrayList<AirPlaneModel> planes = Parser.parse('s');
                        for (AirPlaneModel i : planes){
                            obj.addNewBuilder(i);
                        }
                        break;
                    }
                    case -1: {choice = 0; break;}
                }
            }
        } catch (Exception e) {
            log.severe("Exception caught: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
