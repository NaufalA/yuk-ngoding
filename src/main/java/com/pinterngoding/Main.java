package com.pinterngoding;

import com.pinterngoding.shared.utils.JPAUtility;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtility.getEntityManager();

        JPAUtility.close();
    }
}