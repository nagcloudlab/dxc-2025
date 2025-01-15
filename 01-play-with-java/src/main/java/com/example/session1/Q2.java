package com.example.session1;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;

// container ( infra code ) & component ( business code ) style

//---------------------------------
// auth.jar
//---------------------------------
/**
 * team 1
 */

// e.g Servlet
class WebComponent{
    @RequestMapping(url = "/dxc/login")
    public void handleLoginRequest(String userName,String password){
        //...
        System.out.println("Login request processed");
    }

    @RequestMapping(url = "/dxc/register")
    public void handleRegisterRequest(String userName,String password,String name){
        //...
        System.out.println("Register request processed");
    }
}

//---------------------------------



/**
 * third party team
 */

@Target({java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface RequestMapping{
    String url();
}

class WebContainer{
    public void processRequest(String reqUrl){
        // create / use thread pool to process the request
        // parsing req body
        // validation....
        // processing request..

        try {
            // dynamic class/jar loading
            //...
            Class<?> clazz=Class.forName("com.example.session1.WebComponent"); // dynamic class loading

            // Reflection Api
            Object obj=clazz.newInstance();

            // get all methods
            java.lang.reflect.Method[] methods=clazz.getMethods();

            for(java.lang.reflect.Method method:methods){
                RequestMapping requestMapping=method.getAnnotation(RequestMapping.class);
                if(requestMapping!=null){
                    if(requestMapping.url().equals(reqUrl)){
                        // invoke the method
                        if(requestMapping.url().equals("/dxc/login"))
                            method.invoke(obj,new Object[]{"admin","admin"});
                        else if(requestMapping.url().equals("/dxc/register"))
                        method.invoke(obj,new Object[]{"admin","admin","DXC"});
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }
}





public class Q2 {
    public static void main(String[] args) {

        WebContainer container=new WebContainer(); // e.g Tomcat
        container.processRequest("/dxc/login");
        container.processRequest("/dxc/register");

    }
}
