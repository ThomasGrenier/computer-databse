package com.excilys.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.excilys.model.CompanyModel;
import com.excilys.model.ComputerModel;
import com.excilys.model.Page;
import com.excilys.service.CompanyService;
import com.excilys.service.CompanyServiceImpl;
import com.excilys.service.ComputerService;
import com.excilys.service.ComputerServiceImpl;
import com.excilys.utils.Regex;

public class CLIController {

	public static void main(String[] args) {
		ComputerService computerService = new ComputerServiceImpl();
		CompanyService companyService = new CompanyServiceImpl();
		Scanner sc = new Scanner(System.in);
		System.out.println("Bienvenue, tapez help pour la liste des commandes");
		String str = sc.nextLine().trim();
		while (!str.equals("exit")) {
			switch(str) {
			case "help":
				StringBuilder result = new StringBuilder()
				.append("liste des commandes : \n\n")
				.append("\t- computerList : affiche la liste des ordinateurs\n")
				.append("\t- companyList : affiche la liste des compagnies\n")
				.append("\t- getById : affiche l'ordinateur correspondant à l'id\n")
				.append("\t- getComp : affiche la compagnie correspondante à l'id\n")
				.append("\t- getComputerPage : affiche une page de computer\n")
				.append("\t- getCompanyPage : affiche une page de compagnie\n")
				.append("\t- delete : supprime l'ordinateur correspondant à l'id\n")
				.append("\t- create : cré un nouvel ordinateur\n")
				.append("\t- update : permet de mettre à jour les champs d'un ordinateur\n")
				.append("\t- exit : arrêt du programme\n");
				System.out.println(result.toString());
				break;
			case "computerList":
				List<ComputerModel> computerList = new LinkedList<ComputerModel>();
				computerList = computerService.listAll();
				computerList.stream().forEach(System.out::println);
				//computerList.stream().map(e -> e + 1).map(e -> e + 2).forEach(System.out::println);
				/*for (int i = 0; i < computerList.size(); i++) {
					System.out.println(computerList.get(i).toString());
				}*/
				break;
			case "companyList":
				List<CompanyModel> companyList = new LinkedList<CompanyModel>();
				companyList = companyService.listAll();
				for (int i = 0; i < companyList.size(); i++) {
					System.out.println(companyList.get(i).toString());
				}
				break;
			case "getById":
				System.out.println("quel est l'id de l'ordinateur : ");
				str = sc.nextLine().trim();
				if (Pattern.matches(Regex.DIGIT.getRegex(), str)) {
					ComputerModel c = computerService.getById(Long.parseLong(str));
					System.out.println(c.toString());
				} else {
					System.err.println("id invalid");
				}
				break;
			case "getComp":
				System.out.println("quel est l'id de la compagnie ? : ");
				str = sc.nextLine().trim();
				if (Pattern.matches(Regex.DIGIT.getRegex(), str)) {
					CompanyModel comp = companyService.getById(Long.parseLong(str));
					System.out.println(comp.toString());
				} else {
					System.err.println("is not a number");
				}
				break;
			case "getComputerPage":
				System.out.println("quelle page ? ");
				str = sc.nextLine().trim();
				while (!Pattern.matches(Regex.DIGIT.getRegex(), str)) {
					System.err.println("is not a number");
					str = sc.nextLine().trim();
				}
				int currentPage = Integer.parseInt(str);
				System.out.println("Combien de résultat par page ? ");
				str = sc.nextLine().trim();
				while (!Pattern.matches(Regex.DIGIT.getRegex(), str)) {
					System.err.println("is not a number");
					str = sc.nextLine().trim();
				}
				int nbResult = Integer.parseInt(str);
				Page<ComputerModel> p = computerService.getPage(currentPage, nbResult, "", "id", "");
				System.out.println(p.toString());
				System.out.println(" - next pour avancer d'une page");
				System.out.println(" - previous pour reculer d'une page");
				System.out.println(" - stop pour sortir");
				str = sc.nextLine().trim();
				while (!str.equals("stop")) {
					switch (str) {
					case "next":
						if (currentPage < p.getTotalPage()) {
							currentPage += 1;
						}
						p = computerService.getPage(currentPage, nbResult, "", "id", "");
						System.out.println(p.toString());
						break;
					case "previous":
						if (currentPage > 1) {
							currentPage -= 1;
						}
						p = computerService.getPage(currentPage, nbResult, "", "id", "");
						System.out.println(p.toString());
						break;
					default:
						System.out.println(" - next pour avancer d'une page");
						System.out.println(" - previous pour reculer d'une page");
						System.out.println(" - stop pour sortir");
						break;
					}
					str = sc.nextLine().trim();
				}
				System.out.println("sortie des pages");
				break;
			case "getCompanyPage":
				System.out.println("quelle page ? ");
				str = sc.nextLine().trim();
				while (!Pattern.matches(Regex.DIGIT.getRegex(), str)) {
					System.err.println("is not a number");
					str = sc.nextLine().trim();
				}
				int currentPageComp = Integer.parseInt(str);
				System.out.println("Combien de résultat par page ? ");
				str = sc.nextLine().trim();
				while (!Pattern.matches(Regex.DIGIT.getRegex(), str)) {
					System.err.println("is not a number");
					str = sc.nextLine().trim();
				}
				int nbResultComp = Integer.parseInt(str);
				Page<CompanyModel> pa = companyService.getPage(currentPageComp, nbResultComp, "", "id", "");
				System.out.println(pa.toString());
				System.out.println(" - next pour avancer d'une page");
				System.out.println(" - previous pour reculer d'une page");
				System.out.println(" - stop pour sortir");
				str = sc.nextLine().trim();
				while (!str.equals("stop")) {
					switch (str) {
					case "next":
						if (currentPageComp < pa.getTotalPage()) {
							currentPageComp += 1;
						}
						pa = companyService.getPage(currentPageComp, nbResultComp, "", "id", "");
						System.out.println(pa.toString());
						break;
					case "previous":
						if (currentPageComp > 1) {
							currentPageComp -= 1;
						}
						pa = companyService.getPage(currentPageComp, nbResultComp, "", "id", "");
						System.out.println(pa.toString());
						break;
					default:
						System.out.println(" - next pour avancer d'une page");
						System.out.println(" - previous pour reculer d'une page");
						System.out.println(" - stop pour sortir");
						break;
					}
					str = sc.nextLine().trim();
				}
				System.out.println("sortie des pages");
				break;
			case "delete":
				System.out.println("quel est l'id de l'ordinateur à supprimer : ");
				str = sc.nextLine().trim();
				if (Pattern.matches(Regex.DIGIT.getRegex(), str)) {
					computerService.delete(Long.parseLong(str));
					System.out.println("ordinateur " + Long.parseLong(str) + " supprimé");
				} else {
					System.err.println("id invalid");
				}
				break;
			case "create":
				System.out.println("quel est le nom de votre ordinateur : ");
				str = sc.nextLine().trim();
				long id = computerService.create(str, null, null, -1);
				System.out.println("ordinateur enregistré, id = " + id);
				break;
			case "update":
				System.out.println("quel est l'id de l'ordinateur à mettre à jour ?");
				str = sc.nextLine().trim();

				System.out.println("vous avez choisi l'odinateur : ");
				if (Pattern.matches(Regex.DIGIT.getRegex(), str)) {
					ComputerModel computer = computerService.getById(Long.parseLong(str));
					System.out.println(computer.toString());
				} else {
					System.err.println("id invalid");
					break;
				}

				System.out.println("nouveau nom : ");
				String name = sc.nextLine().trim();

				boolean isOk = false;
				String introduced = null;
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				System.out.println("date d'introduction (format = yyyy-MM-dd HH:mm:ss) : ");
				
				LocalDateTime intro = null;
				while (!isOk) {
					introduced = sc.nextLine().trim();

					if (!introduced.equals("")) {
						if (Pattern.matches(Regex.DATE_FORMAT.getRegex(), introduced.trim())) {
							intro = LocalDateTime.parse(introduced, formatter);
							isOk = true;
						} else {
							System.err.println("mauvais format ! (format = yyyy-MM-dd HH:mm:ss) : ");
						}
					} else {
						isOk = true;
					}
				}

				isOk = false;
				String discontinued = null;
				System.out.println("date de suppression (format = yyyy-MM-dd HH:mm:ss) : ");
				LocalDateTime discon = null;
				while (!isOk) {
					discontinued = sc.nextLine().trim();
					if (!discontinued.equals("")) {
						if (Pattern.matches(Regex.DATE_FORMAT.getRegex(), discontinued.trim())) {
							discon = LocalDateTime.parse(discontinued, formatter);
							isOk = true;
						} else {
							System.out.println("mauvais format ! (format = yyyy-MM-dd HH:mm:ss) : ");
						}
					} else {
						isOk = true;
					}
				}

				System.out.println("id de la compagnie : ");
				String idComp = sc.nextLine().trim();
				long idCompany;
				if ((idComp == null) || (idComp.equals(""))) {
					idCompany = -1;
				} else {

					if (Pattern.matches(Regex.DIGIT.getRegex(), idComp)) {
						idCompany = Long.parseLong(idComp);
					} else {
						idCompany = -1;
						System.err.println("invalid company id");
					}
				}
				computerService.update(Long.parseLong(str), name, intro, discon, idCompany);
				System.out.println("ordinateur mi à jour");
				break;
			default:
				System.out.println("help pour la liste des commandes");
				break;
			}
			str = sc.nextLine().trim();
		}
		System.out.println("arrêt de l'application");
		sc.close();
	}
}
