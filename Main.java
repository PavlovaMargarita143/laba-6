package server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        for (String str : args){
            Server server = new Server();
        server.start(str);}
        if (args.length==0){
            System.out.println("Отсутствует имя файла. Чтобы сервер работал, введите файл как аргумент командной строки");
            System.exit(0);
        }
}}
