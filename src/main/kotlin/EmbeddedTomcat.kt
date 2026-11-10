package org.example

import org.apache.catalina.startup.Tomcat
import org.example.config.AopConfig
import org.example.config.DataInitializer
import org.example.config.JpaConfig
import org.example.config.WebConfig
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

fun main() {

    val tomcat = Tomcat()
    tomcat.setPort(8080)
    tomcat.connector

    val context = tomcat.addContext("",
        System.getProperty("java.io.tmpdir"))
    context.parentClassLoader = Thread.currentThread().contextClassLoader

    val appContext =
        AnnotationConfigWebApplicationContext()

    appContext.register(
        JpaConfig::class.java,
        WebConfig::class.java,
        AopConfig::class.java,
        DataInitializer::class.java)

    val dispatcherServlet =
        DispatcherServlet(appContext)

    val servlet = Tomcat.addServlet(
        context,
        "dispatcher",
        dispatcherServlet
    )

    servlet.loadOnStartup = 1
    servlet.addMapping("/")

    tomcat.start()

    println("Server started")

    tomcat.server.await()
}