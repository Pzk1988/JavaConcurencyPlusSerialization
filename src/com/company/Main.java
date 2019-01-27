package com.company;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        String[] pathList = new String[]{
                "C:\\Users\\PKnapik\\Documents\\Intellij_workspace\\JavaConcurencyPlusSerialization\\Resources\\File1.txt",
                "C:\\Users\\PKnapik\\Documents\\Intellij_workspace\\JavaConcurencyPlusSerialization\\Resources\\File2.txt",
                "C:\\Users\\PKnapik\\Documents\\Intellij_workspace\\JavaConcurencyPlusSerialization\\Resources\\File3.txt",
                "C:\\Users\\PKnapik\\Documents\\Intellij_workspace\\JavaConcurencyPlusSerialization\\Resources\\File4.txt"};

        ThreadMethod(pathList);

        ThreadPool(pathList);

        ThreadWithCoupling(pathList);

        //SerializationDeserialization();
        Deserialization();
    }

    static void ThreadMethod(String[] pathList)
    {
        long startLime = System.currentTimeMillis();
        Adder adder;
        int result = 0;

        for (String path : pathList ) {
            adder = new Adder(path);
            adder.doAdd();
            result += adder.GetResult();
        }

        System.out.println( "Readed lines: " + result + ", time: " + (System.currentTimeMillis() - startLime));


        {
            result = 0;
            startLime = System.currentTimeMillis();

            ArrayList<Thread> threadList = new ArrayList<>();
            ArrayList<Adder> adderList = new ArrayList<>();

            for (String path : pathList) {
                Adder adder1 = new Adder(path);
                adderList.add(adder1);

                Thread thread = new Thread(adder1);
                threadList.add(thread);
                thread.run();
            }

            for (Thread thread : threadList) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                }
            }

            for (Adder adder2 : adderList) {
                result += adder2.GetResult();
            }

            System.out.println("Readed lines: " + result + ", time: " + (System.currentTimeMillis() - startLime));
        }
    }

    static void ThreadPool(String[] pathList)
    {
        int result = 0;
        long startLime = System.currentTimeMillis();
        ExecutorService es = Executors.newFixedThreadPool(2);
        ArrayList<Adder> adderList = new ArrayList<>();

        for (String path : pathList) {
            Adder adder = new Adder(path);
            adderList.add(adder);
            es.submit(adder);
        }

        try{
            es.shutdown();
            es.awaitTermination(60, TimeUnit.SECONDS);
        }catch(Exception e){}

        for (Adder adder2 : adderList) {
            result += adder2.GetResult();
        }

        System.out.println("ThreadPool lines: " + result + ", time: " + (System.currentTimeMillis() - startLime));
    }

    static void ThreadWithCoupling(String[] pathList)
    {
        int res = 0;
        long startLime = System.currentTimeMillis();
         ExecutorService es = Executors.newFixedThreadPool(3);
         Future<Integer>[] results = new Future[pathList.length];

         for(int i = 0; i < pathList.length; i++){
             AdderCallable adder = new AdderCallable(pathList[i]);
             results[i] = es.submit(adder);
         }

         for(Future<Integer> result: results)
         {
             try
             {
                 res += result.get();
             }
             catch(ExecutionException e)
             {
                 System.out.println(e.toString());
             }
             catch(Exception e)
             {
                 System.out.println(e.toString());
             }
         }

         es.shutdown();
        System.out.println("ThreadPoolCoupled lines: " + res + ", time: " + (System.currentTimeMillis() - startLime));
    }

    static void SerializationDeserialization(){
        Address address = new Address("Karola Miarki", 43190, 23);
        School school = new School("Liceum im. Karola Miarki", address);
        school.AddStudent(new Student("Eda", "Maratonczyk", 20));
        school.AddStudent(new Student("Eda1", "Maratonczyk1", 20));
        school.AddStudent(new Student("Eda2", "Maratonczyk2", 22));
        school.AddStudent(new Student("Eda3", "Maratonczyk3", 22));
        school.AddStudent(new Student("Eda4", "Maratonczyk4", 21));

        System.out.println(school.toString());

        try{
            File file = new File("file.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(School.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(school, file);
        } catch (JAXBException e) {
                e.printStackTrace();
            }
        }

     static void Deserialization(){
        try {
            File file = new File("file.xml");
            JAXBContext jaxb = JAXBContext.newInstance(School.class);
            Unmarshaller jaxbUnmar = jaxb.createUnmarshaller();
            School school = (School) jaxbUnmar.unmarshal(file);

            System.out.println(school);
        }catch (Exception e){
            System.out.println(e.toString());
        }
     }
}
