package com.alejobeliz.proyectos.literatura.principal;

import com.alejobeliz.proyectos.literatura.service.MenuService;
import com.alejobeliz.proyectos.literatura.ui.Menu;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    private final MenuService menuService;

    @Autowired
    public Principal(MenuService menuService) {
        this.menuService = menuService;
    }

    public void ExecutarAplicacao() {
        Menu menu = new Menu();
        Scanner teclado = new Scanner(System.in);
        System.out.println(menu.getBemVindos());
        int opcion;
        do {
            try {
                System.out.print(menu.getMenu() + " ");
                opcion = teclado.nextInt();
                teclado.nextLine();
                switch (opcion) {
                    case 1:
                        menuService.salvarLivro();
                        break;
                    case 2:
                        menuService.listarLivrosRegistrados();
                        break;
                    case 3:
                        menuService.listarAutoresRegistrados();
                        break;
                    case 4:
                        menuService.listarAutoresVivosEmAnos();
                        break;
                    case 5:
                        menuService.listarLivrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Sainddo de literAlura...");
                        break;
                    default:
                        System.out.println("Opcao inválida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Digite um numero");
                opcion = -1;
                teclado.nextLine();
            }
        } while (opcion != 0);
        teclado.close();
    }
}


