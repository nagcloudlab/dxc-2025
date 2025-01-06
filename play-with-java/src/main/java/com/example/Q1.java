package com.example;

class MyFileReaderResource implements AutoCloseable{
    // init...
    public void init(String filePath){
        System.out.println("File initialized: "+filePath);
    }
    // use...
    public void read(){
        // read the file
        System.out.println("File read");
        boolean isRead = true;
        if(!isRead) {
            throw new RuntimeException("File not read");
        }
    }
    // close...
    public void close(){
        // close the file
        System.out.println("File closed");
    }
}

public class Q1 {
    public static void main(String[] args) {


        MyFileReaderResource myFileReaderResource = new MyFileReaderResource();
        myFileReaderResource.init("file.txt");

        // till java 1.6
        try{
            myFileReaderResource.read();
            //myFileReaderResource.close();
            return;
        }catch (RuntimeException e){
            // handle....
            System.out.println("Exception: "+e.getMessage());
            //myFileReaderResource.close();
        } finally {
            myFileReaderResource.close();
        }

        // From java 1.7, we can use try-with-resources
        // It will automatically close the resource

        try(MyFileReaderResource myFileReaderResource2 = new MyFileReaderResource()) {
            myFileReaderResource2.init("file.txt");
            myFileReaderResource2.read();
        }catch (RuntimeException e){
            // handle....
            System.out.println("Exception: "+e.getMessage());
        }



    }
}
