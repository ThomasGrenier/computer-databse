package com.excilys.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.excilys.model.CompanyModel;
import com.excilys.model.ComputerModel;
import com.excilys.persistence.ConnectionManager;
import com.excilys.persistence.JDBCQuery;

public class Controller {

	public static void main(String[] args) {
		JDBCQuery jdbcQuery = new JDBCQuery(ConnectionManager.INSTANCE.getConnection());
		Scanner sc = new Scanner(System.in);
		System.out.println("Bienvenue, tapez help pour la liste des commandes");
		String str = sc.nextLine();
		while (!str.equals("exit")) {
			switch(str) {
			case "help":
				StringBuilder result = new StringBuilder()
				.append("liste des commandes : \n\n")
				.append("\t-computerList : affiche la liste des ordinateurs\n")
				.append("\t-companyList : affiche la liste des compagnies\n")
				.append("\t-getById : affiche l'ordinateur correspondant à l'id\n")
				.append("\t-delete : supprime l'ordinateur correspondant à l'id\n")
				.append("\t-create : cré un nouvel ordinateur\n")
				.append("\t-update : permet de mettre à jour les champs d'un ordinateur\n")
				.append("\t-exit : arrêt du programme\n");
				System.out.println(result.toString());
				break;
			case "computerList":
				List<ComputerModel> computerList = new LinkedList<ComputerModel>();
				computerList = jdbcQuery.listComputers();
				for (int i = 0; i < computerList.size(); i++) {
					System.out.println(computerList.get(i).toString());
				}
				break;
			case "companyList":
				List<CompanyModel> companyList = new LinkedList<CompanyModel>();
				companyList = jdbcQuery.listCompanies();
				for (int i = 0; i < companyList.size(); i++) {
					System.out.println(companyList.get(i).toString());
				}
				break;
			case "getById":
				System.out.println("quel est l'id de l'ordinateur : ");
				str = sc.nextLine();
				ComputerModel c = jdbcQuery.showComputer(Long.parseLong(str));
				System.out.println(c.toString());
				break;
			case "delete":
				System.out.println("quel est l'id de l'ordinateur à supprimer : ");
				str = sc.nextLine();
				jdbcQuery.deleteComputer(Long.parseLong(str));
				System.out.println("ordinateur " + Long.parseLong(str) + " supprimé");
				break;
			case "create":
				System.out.println("quel est le nom de votre ordinateur : ");
				str = sc.nextLine();
				long id = jdbcQuery.createComputer(str);
				System.out.println("ordinateur enregistré, id = " + id);
				break;
			case "update":
				System.out.println("quel est l'id de l'ordinateur à mettre à jour ?");
				str = sc.nextLine();

				System.out.println("vous avez choisi l'odinateur : ");
				ComputerModel computer = jdbcQuery.showComputer(Long.parseLong(str));
				System.out.println(computer.toString());

				System.out.println("nouveau nom : ");
				String name = sc.nextLine();

				boolean isOk = false;
				String introduced = null;
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				System.out.println("date d'introduction (format = yyyy-MM-dd HH:mm:ss) : ");
				while (!isOk) {
					introduced = sc.nextLine();
					try {
						LocalDateTime.parse(introduced, formatter);
						isOk = true;
					} catch (DateTimeParseException e) {
						System.out.println("mauvais format ! (format = yyyy-MM-dd HH:mm:ss) : ");
					}
				}

				isOk = false;
				String discontinued = null;
				System.out.println("date de suppression (format = yyyy-MM-dd HH:mm:ss) : ");
				while (!isOk) {
					discontinued = sc.nextLine();
					try {
						LocalDateTime.parse(discontinued, formatter);
						isOk = true;
					} catch (DateTimeParseException e) {
						System.out.println("mauvais format ! (format = yyyy-MM-dd HH:mm:ss) : ");
					}
				}

				System.out.println("id de la compagnie : ");
				String idComp = sc.nextLine();
				long idCompany;
				if ((idComp == null) || (idComp.equals(""))) {
					idCompany = -1;
				} else {
					idCompany = Long.parseLong(idComp);
				}
				jdbcQuery.updateComputer(Long.parseLong(str), name, introduced, discontinued, idCompany);
				System.out.println("ordinateur mi à jour");
				break;
			default:
				System.out.println("help pour la liste des commandes");
				break;
			}
			str = sc.nextLine();
		}
		System.out.println("arrêt de l'application");
		sc.close();
	}
}
