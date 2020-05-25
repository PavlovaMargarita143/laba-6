package client;
import com.company.*;
import com.company.Reader;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        int chance=0;
    ByteBuffer receiveData = ByteBuffer.allocate(1000000);
        DatagramChannel channel = DatagramChannel.open();
        channel.bind(new InetSocketAddress((int) (Math.random() * 6000) +2000));
        InetAddress serverIP = readIP();
        while (true) {
            if(SendData(channel, serverIP)<0){chance++;if(chance==5){ System.exit(0);} continue;}
            Thread.sleep(1500);
            ReceiveData(receiveData, channel);
        }
    }



    private static int SendData(DatagramChannel channel, InetAddress serverIP) {
        try {
            if(serverIP.isReachable(1000)) {

            channel.configureBlocking(false);
            channel.connect(new InetSocketAddress(serverIP, 6666));
            System.out.println("Готовим запрос к отправке ");
            Reader reader =new Reader();
            Command command = reader.ReadCommand();
            if (command instanceof Command_Exit) {
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                ObjectOutputStream o = new ObjectOutputStream(b);
                o.writeObject(command);
                channel.send(ByteBuffer.wrap(b.toByteArray()), new InetSocketAddress(serverIP, 6666));
                System.exit(0);
            }
            else if (command instanceof Command_Save) {
                System.out.println( "У вас нет доступа к этой команде" );
                channel.disconnect();
                return 1;
            }
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            ObjectOutputStream o = new ObjectOutputStream(b);
            o.writeObject(command);
            channel.send(ByteBuffer.wrap(b.toByteArray()), new InetSocketAddress(serverIP, 6666));
            System.out.println("Отправили");
            channel.disconnect();
            return 1;}
            System.out.println("Сервер временно недоступен!");
            return  -1;
        } catch (IOException e) {
            System.err.println("IOException " + e);
            return -1;
        }
    }
private static int c = 0;
    private static void ReceiveData(ByteBuffer receiveData, DatagramChannel channel) {

        try {
            receiveData.clear();
            System.out.println("Ожидаем ответ...");
            SocketAddress from = channel.receive(receiveData);
            ArrayList<String> answer = new ArrayList<>();
            if (from != null) {
                receiveData.flip();
                ByteArrayInputStream b = new ByteArrayInputStream(receiveData.array());
                ObjectInputStream o = new ObjectInputStream(b);
                answer = (ArrayList<String>) o.readObject();
            }
            System.out.println("Ответ сервера: ");
            for (String str : answer) {
                System.out.println("\t" + str);
            }
            if (answer.size()==0){
                System.out.println("Сервер недоступен, отключён, либо вы подключились не к тому серверу, попробуйте заново, у вас есть три попытки, если после этого сервер не будет доступен, программа завершится");
                c++;
                if (c>3){
                System.exit(0);}
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("IOException Receive " + e);
        }
    }

    private static InetAddress readIP() throws UnknownHostException {
        InetAddress server=null;
        while (true) {
            System.out.print("Введите IP адресс сервера:");
            Scanner scanner = new Scanner(System.in);
            try {
                server = InetAddress.getByName(scanner.nextLine());
            }catch (UnknownHostException e){
                System.out.println("Неверный IP!");
                continue;
            }
            break;
        }
        return server;
    }
}
