package service;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import model.CompanyModel;
import model.ComputerModel;
import persistence.ConnectionManager;
import persistence.JDBCQuery;

public class Controller {

	public static void main(String[] args) {
		ConnectionManager connectionManager = new ConnectionManager();
		JDBCQuery jdbcQuery = new JDBCQuery(connectionManager.getConnection());
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
					
					break;
				default:
					System.out.println("help pour la liste des commandes");
					break;
			}
			str = sc.nextLine();
		}
		System.out.println("arrêt de l'application");
		connectionManager.closeConnection();
		sc.close();
	}
}
