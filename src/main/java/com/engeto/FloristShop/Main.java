package com.engeto.FloristShop;

import java.sql.*;

public class Main {
  public static void main(String[] args) throws SQLException {
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/floristshop", "flower_crud", "Fotbalek123*");

    Statement statement = connection.createStatement();

    //vložení květin do tabulky
    insertNewFlower(statement, "Bledule", "bílá", "", 1);
    insertNewFlower(statement, "Kopretina", "bílá", "", 0);

    // Aktualizace popisu "Bledule"
    statement.executeUpdate(" UPDATE flower SET description = 'Pozor na cibulku - obsahuje největší koncentraci jedu!' WHERE name = 'Bledule'");

    //Smazání všech nejedovatých rostlin
    statement.executeUpdate(" DELETE FROM flower WHERE poisonous = 0");
    //Výpis jmen všech rostlin
    printAllFlowers(statement);
  }
  // Metoda pro vložení květin do tabulky
  private static void insertNewFlower(Statement statement, String name, String color, String description, int poisonous) throws SQLException {
    statement.executeUpdate("INSERT INTO flower (name, color, description, poisonous) VALUES ('" + name + "','" + color + "', '" + description + "', '" + poisonous + "')");
  }
  //Metoda pro vypsání všech květin v tabulce
  private static void printAllFlowers(Statement statement) throws SQLException {
    ResultSet resultSet = statement.executeQuery("SELECT * FROM flower");
    while (resultSet.next()) {
      System.out.println(resultSet.getString("name"));
    }
  }
}
