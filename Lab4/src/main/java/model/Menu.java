package model;

import operator.Commands;

import java.util.Scanner;

public class Menu {

  private Commands commands;
  private Scanner scanner;

  public Menu() {
    commands = new Commands();
    scanner = new Scanner(System.in);
  }

  public void show() {
    commands.deleteTable();
    commands.createTable();
    System.out.println("Hi, this is lab 4. Working with databases! Input n of products:");
    populate(Integer.parseInt(scanner.nextLine()));
    System.out.println("Table created!");
    menu: while (true) {
      String input = scanner.nextLine();
      String[] inputCommands = input.split(" ");

      switch (inputCommands[0]) {
        case "/add":
        {
          if (inputCommands.length < 3) {
            System.out.println("Invalid input!");
            break;
          }
          try {
            commands.addProduct(new Product(inputCommands[1], Integer.parseInt(inputCommands[2])));
          } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
          }
          break;
        }
        case "/delete":
        {
          try {
            commands.deleteProduct(inputCommands[1]);
          } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Specify product!");
          }
          break;
        }
        case "/show_all":
        {
          commands.listProducts();
          break;
        }
        case "/price":
        {
          if (inputCommands.length < 1) {
            System.out.println("Invalid input!");
            break;
          }
          commands.getPrice(inputCommands[1]);
          break;
        }
        case "/change_price":
        {
          if (inputCommands.length < 2) {
            System.out.println("Invalid input!");
            break;
          }
          try {
            commands.changePrice(inputCommands[1], Integer.parseInt(inputCommands[2]));
          } catch (NumberFormatException e) {
            System.out.println("Invlid input!");
          }
          break;
        }
        case "/filter_by_price":
        {
          try {
            commands.filterByPrice(Integer.parseInt(inputCommands[1]), Integer.parseInt(inputCommands[2]));
          } catch (NumberFormatException e) {
            System.out.println("Invlid input!");
          }
          break;
        }
        case "/help":
        {
          listCommands();
          break;
        }
        case "/exit":
        {
          break menu;
        }
        default:
        {
          System.out.println("Invalid input!");
          break;
        }
      }
    }
  }

  private void listCommands() {
    System.out.println("/add, /delete, /show_all, /price, /change_price, /filter_by_price, /exit");
  }

  private void populate(int n) {
    for (int i = 1; i <= n; i++) {
      commands.addProduct(new Product(String.format("product%d", i), i));
    }
  }
}
