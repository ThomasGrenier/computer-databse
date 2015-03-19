package com.excilys.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.excilys.model.CompanyModel;
import com.excilys.model.ComputerModel;
import com.excilys.model.Page;
import com.excilys.service.CompanyService;
import com.excilys.service.CompanyServiceImpl;
import com.excilys.service.ComputerService;
import com.excilys.service.ComputerServiceImpl;

public class CLIController {

	public static void main(String[] args) {
		//JDBCQuery jdbcQuery = new JDBCQuery(ConnectionManager.INSTANCE.getConnection());
		/*ComputerDAOImpl computerDaoImpl = (ComputerDAOImpl) DAOFactory.INSTANCE.getComputerDAO();
		CompanyDAOImpl companyDaoImpl = (CompanyDAOImpl) DAOFactory.INSTANCE.getCompanyDAO();*/
		ComputerService computerService = new ComputerServiceImpl();
		CompanyService companyService = new CompanyServiceImpl();
		Scanner sc = new Scanner(System.in);
		System.out.println("Bienvenue, tapez help pour la liste des commandes");
		String str = sc.nextLine();
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
				for (int i = 0; i < computerList.size(); i++) {
					System.out.println(computerList.get(i).toString());
				}
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
				str = sc.nextLine();
				ComputerModel c = computerService.getById(Long.parseLong(str));
				System.out.println(c.toString());
				break;
			case "getComp":
				System.out.println("quel est l'id de la compagnie ? : ");
				str = sc.nextLine();
				CompanyModel comp = companyService.getById(Long.parseLong(str));
				System.out.println(comp.toString());
				break;
			case "getComputerPage":
				System.out.println("quelle page ? ");
				str = sc.nextLine();
				int currentPage = Integer.parseInt(str);
				System.out.println("Combien de résultat par page ? ");
				str = sc.nextLine();
				int nbResult = Integer.parseInt(str);
				Page<ComputerModel> p = computerService.getPage(currentPage, nbResult);
				System.out.println(p.toString());
				System.out.println(" - next pour avancer d'une page");
				System.out.println(" - previous pour reculer d'une page");
				System.out.println(" - stop pour sortir");
				str = sc.nextLine();
				while (!str.equals("stop")) {
					switch (str) {
					case "next":
						if (currentPage < p.getTotalPages()) {
							currentPage += 1;
						}
						p = computerService.getPage(currentPage, nbResult);
						System.out.println(p.toString());
						break;
					case "previous":
						if (currentPage > 1) {
							currentPage -= 1;
						}
						p = computerService.getPage(currentPage, nbResult);
						System.out.println(p.toString());
						break;
					default:
						System.out.println(" - next pour avancer d'une page");
						System.out.println(" - previous pour reculer d'une page");
						System.out.println(" - stop pour sortir");
						break;
					}
					str = sc.nextLine();
				}
				System.out.println("sortie des pages");
				break;
			case "getCompanyPage":
				System.out.println("quelle page ? ");
				str = sc.nextLine();
				int currentPageComp = Integer.parseInt(str);
				System.out.println("Combien de résultat par page ? ");
				str = sc.nextLine();
				int nbResultComp = Integer.parseInt(str);
				Page<CompanyModel> pa = companyService.getPage(currentPageComp, nbResultComp);
				System.out.println(pa.toString());
				System.out.println(" - next pour avancer d'une page");
				System.out.println(" - previous pour reculer d'une page");
				System.out.println(" - stop pour sortir");
				str = sc.nextLine();
				while (!str.equals("stop")) {
					switch (str) {
					case "next":
						if (currentPageComp < pa.getTotalPages()) {
							currentPageComp += 1;
						}
						pa = companyService.getPage(currentPageComp, nbResultComp);
						System.out.println(pa.toString());
						break;
					case "previous":
						if (currentPageComp > 1) {
							currentPageComp -= 1;
						}
						pa = companyService.getPage(currentPageComp, nbResultComp);
						System.out.println(pa.toString());
						break;
					default:
						System.out.println(" - next pour avancer d'une page");
						System.out.println(" - previous pour reculer d'une page");
						System.out.println(" - stop pour sortir");
						break;
					}
					str = sc.nextLine();
				}
				System.out.println("sortie des pages");
				break;
			case "delete":
				System.out.println("quel est l'id de l'ordinateur à supprimer : ");
				str = sc.nextLine();
				computerService.delete(Long.parseLong(str));
				System.out.println("ordinateur " + Long.parseLong(str) + " supprimé");
				break;
			case "create":
				System.out.println("quel est le nom de votre ordinateur : ");
				str = sc.nextLine();
				long id = computerService.create(str);
				System.out.println("ordinateur enregistré, id = " + id);
				break;
			case "update":
				System.out.println("quel est l'id de l'ordinateur à mettre à jour ?");
				str = sc.nextLine();

				System.out.println("vous avez choisi l'odinateur : ");
				ComputerModel computer = computerService.getById(Long.parseLong(str));
				System.out.println(computer.toString());

				System.out.println("nouveau nom : ");
				String name = sc.nextLine();

				boolean isOk = false;
				String introduced = null;
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				System.out.println("date d'introduction (format = yyyy-MM-dd HH:mm:ss) : ");
				LocalDateTime intro = null;
				while (!isOk) {
					introduced = sc.nextLine();
					if (!introduced.equals("")) {
						try {
							intro = LocalDateTime.parse(introduced, formatter);
							isOk = true;
						} catch (DateTimeParseException e) {
							System.out.println("mauvais format ! (format = yyyy-MM-dd HH:mm:ss) : ");
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
					discontinued = sc.nextLine();
					if (!discontinued.equals("")) {
						try {
							discon = LocalDateTime.parse(discontinued, formatter);
							isOk = true;
						} catch (DateTimeParseException e) {
							System.out.println("mauvais format ! (format = yyyy-MM-dd HH:mm:ss) : ");
						}
					} else {
						isOk = true;
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
				computerService.update(Long.parseLong(str), name, intro, discon, idCompany);
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
