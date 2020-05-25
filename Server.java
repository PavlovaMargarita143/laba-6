package server;
import com.company.*;
import com.google.gson.Gson;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    public Server() {

    }

    SocketAddress client;
    public void setClient(SocketAddress client) {
        this.client = client;
    }
    public void start(String str) throws IOException, InterruptedException {
        FileReader Fr = new FileReader(str);
        Scanner f = new Scanner(Fr);
        String line =null;
        ArrayList<City> collection = new ArrayList<City>();

        while(f.hasNextLine()){
            line = f.nextLine();
            try {City newCity= new Gson().fromJson(line,City.class);
                collection.add( newCity);
            }
            catch (com.google.gson.JsonSyntaxException e){
                System.out.println("Некоректные данные в файле!!! Программа завершена, исправьте или очистите файл и запустите программу заново");
                System.exit(0);
            }
        }
        f.close();
        A_Command.setSet(collection);
        while (true) {
            ArrayList<String> message = new ArrayList<>();
            Command command = ReceiveData();


                if(command instanceof Command_Exit) {
                    Command_Save c= new Command_Save();
                    c.execute();
                    continue;
                }
                message = executeCommand(command);
                SendData(message);
               Thread.sleep(2000);
                Save s = new Save();
                s.run();
              //  s.sleep(2000);
                if (Save.getStr().compareTo("s")==0){
                    Command_Save c= new Command_Save();
                    c.execute();
                }

                continue;

            }
        }


    private void SendData(ArrayList<String> message) throws IOException {
        try {
            //буфер для получения входящих данных
            byte[] buffer;
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            ObjectOutputStream o = new ObjectOutputStream(b);
            o.writeObject(message);
            buffer = b.toByteArray();
            DatagramSocket sock = new DatagramSocket(6666);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            System.out.println("Отправляем данные...");
            //Получаем данные
            sock.connect(this.client);
            sock.send(packet);
            System.out.println("Отправили");
            sock.disconnect();
            sock.close();

        } catch (IOException e) {
            System.err.println("IOException " + e);
        }

    }

    private Command ReceiveData() {
        try {
            Command receiveCommand;
            //буфер для получения входящих данных
            byte[] buffer = new byte[65536];
            DatagramSocket sock = new DatagramSocket(6666);
            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
            System.out.println("Ожидаем данные...");


            while (true) {
                //Получаем данные
                    sock.receive(incoming);
                    sock.disconnect();
                    ByteArrayInputStream b = new ByteArrayInputStream(incoming.getData());
                    ObjectInputStream o = new ObjectInputStream(b);

                    receiveCommand = (Command) o.readObject();
                    System.out.println(" сообщение клиента: " + receiveCommand);
                    System.out.println("Адресс: " + incoming.getAddress());
                    System.out.println("Порт: " + incoming.getPort());
                    this.setClient(new InetSocketAddress(incoming.getAddress(), incoming.getPort()));
                    sock.close();
                    return receiveCommand;

            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("IOException " + e);
            return null;
        }
    }

    private static ArrayList<String> executeCommand(Command command) throws  IOException{
        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        return invoker.executeCommand();

    }

    }

