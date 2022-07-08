package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
	private static List<Integer> x;
	private static List<Integer> o;
	private static List<List<Integer>> winCombo;
	public static String game;

    public static void main(String[] args) {
		init();
		mark();
    }

	public static void init(){
		winCombo = new ArrayList<>();
		winCombo.add(List.of(1, 2, 3));
		winCombo.add(List.of(4, 5, 6));
		winCombo.add(List.of(7, 8, 9));
		winCombo.add(List.of(1, 4, 7));
		winCombo.add(List.of(2, 5, 8));
		winCombo.add(List.of(3, 6, 9));
		winCombo.add(List.of(1, 5, 9));
		winCombo.add(List.of(3, 5, 7));
		x = new ArrayList<>();
		o = new ArrayList<>();
		game = " 1 | 2 | 3 \n" +
				"---+---+---\n" +
				" 4 | 5 | 6 \n" +
				"---+---+---\n" +
				" 7 | 8 | 9 \n";
	}

	private static boolean check(Integer check){
		if (x.contains(check) || o.contains(check)){
			System.out.println("\nЭта ячейка уже выбрана!\n");
		} else if (check <=0 || check > 9){
			System.out.println("\nВы не можете выбирать цифры меньше 1 и больше 9!\n");
		} else {
			return true;
		}
		return false;
	}

	private static boolean checkX(Integer check){
		game = game.replace(check+"", "x");
		x.add(check);
		for (List<Integer> i:winCombo){
			if (x.contains(i.get(0)) && x.contains(i.get(1)) && x.contains(i.get(2))){
				return true;
			}
		}
		return false;
	}

	private static boolean checkO(Integer check){
		game = game.replace(check + "", "o");
		o.add(check);
		for (List<Integer> i:winCombo){
			if (o.contains(i.get(0)) && o.contains(i.get(1)) && o.contains(i.get(2))){
				return true;
			}
		}
		return false;
	}

	private static boolean draw(){
		int a = 0;
		for (int i = 1; i<10; i++){
			if (x.contains(i) || o.contains(i)){
				a++;
			}
		}
		if (a==9){
			return true;
		}
		return false;
	}

	private static void mark(){
		Integer a;
		boolean player = true;
		Scanner in = new Scanner(System.in);
		while (true){
			System.out.println(game);
			System.out.println();
			if (player){
				System.out.print("Выберите куда поставить крестик: ");
				a = in.nextInt();
				if (!check(a)){
					player = !player;
				} else {
					if (checkX(a)){
						System.out.println("Крестики выиграли!");
						System.out.println();
						System.out.println(game);
						break;
					}
				}
			}
			else {
				System.out.print("Выберите куда поставить нолик: ");
				a = in.nextInt();
				if (!check(a)){
					player = !player;
				} else {
					if (checkO(a)){
						System.out.println("Нолики выиграли!");
						System.out.println();
						System.out.println(game);
						break;
					}
				}
			}
			if (draw()){
				System.out.println();
				System.out.println("Ничья!");
				System.out.println();
				System.out.println(game);
				break;
			}
			player = !player;
		}
	}
}
