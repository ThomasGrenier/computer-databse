package com.excilys.ui;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.excilys.utils.Regex;
import com.excilys.webservice.ComputerWebService;

public class CLIController {

	private final URL url;
    private final QName qname;
    private final Service service;
    private final ComputerWebService ws;
	
	public CLIController() throws MalformedURLException {
		url = new URL("http://localhost:9898/computer-database-ws/computers?wsdl");
		qname = new QName("http://webservice.excilys.com/", "ComputerWebServiceImplService");
        service = Service.create(url, qname);
        ws = service.getPort(ComputerWebService.class);
	}
	
	public ComputerWebService getWs() {
		return ws;
	}

	public static void main(String[] args) {
		CLIController cli = null;
		try {
			cli = new CLIController();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		ComputerWebService webService = cli.getWs();
		
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
				System.out.println(webService.getComputers());
				break;
			case "companyList":
				System.out.println(webService.getCompanies());
				break;
			case "getById":
				System.out.println("quel est l'id de l'ordinateur : ");
				str = sc.nextLine().trim();
				if (Pattern.matches(Regex.DIGIT.getRegex(), str)) {
					System.out.println(webService.getComputerById(Long.parseLong(str)));
				} else {
					System.err.println("id invalid");
				}
				break;
			case "getComp":
				System.out.println("quel est l'id de la compagnie ? : ");
				str = sc.nextLine().trim();
				if (Pattern.matches(Regex.DIGIT.getRegex(), str)) {
					System.out.println(webService.getCompanyById(Long.parseLong(str)));
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
				int total = webService.getNbComputer() / nbResult;
				if ((webService.getNbComputer() % nbResult) > 0) {
					total += 1;
				}
				if (currentPage > total) {
					System.out.println("redirection dernière page");
					currentPage = total;
				}
				if (currentPage < 1) {
					System.out.println("redirection page 1");
					currentPage = 1;
				}
				System.out.println(webService.getComputerPage(currentPage, nbResult, "", "id", ""));
				System.out.println(" - next pour avancer d'une page");
				System.out.println(" - previous pour reculer d'une page");
				System.out.println(" - stop pour sortir");
				str = sc.nextLine().trim();
				while (!str.equals("stop")) {
					switch (str) {
					case "next":
						if (currentPage < total) {
							currentPage += 1;
						}
						System.out.println(webService.getComputerPage(currentPage, nbResult, "", "id", ""));
						break;
					case "previous":
						if (currentPage > 1) {
							currentPage -= 1;
						}
						System.out.println(webService.getComputerPage(currentPage, nbResult, "", "id", ""));
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
				
				int totalComp = webService.getNbCompany() / nbResultComp;
				if ((webService.getNbCompany() % nbResultComp) > 0) {
					totalComp += 1;
				}
				if (currentPageComp > totalComp) {
					System.out.println("redirection dernière page");
					currentPageComp = totalComp;
				}
				if (currentPageComp < 1) {
					System.out.println("redirection page 1");
					currentPageComp = 1;
				}
				System.out.println(webService.getCompaniesByPage(currentPageComp, nbResultComp, "", "id", ""));
				System.out.println(" - next pour avancer d'une page");
				System.out.println(" - previous pour reculer d'une page");
				System.out.println(" - stop pour sortir");
				str = sc.nextLine().trim();
				while (!str.equals("stop")) {
					switch (str) {
					case "next":
						if (currentPageComp < totalComp) {
							currentPageComp += 1;
						}
						System.out.println(webService.getCompaniesByPage(currentPageComp, nbResultComp, "", "id", ""));
						break;
					case "previous":
						if (currentPageComp > 1) {
							currentPageComp -= 1;
						}
						System.out.println(webService.getCompaniesByPage(currentPageComp, nbResultComp, "", "id", ""));
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
					webService.deleteComputer(Long.parseLong(str));
					System.out.println("ordinateur " + Long.parseLong(str) + " supprimé");
				} else {
					System.err.println("id invalid");
				}
				break;
			case "create":
				System.out.println("quel est le nom de votre ordinateur : ");
				str = sc.nextLine().trim();
				//long id = computerService.create(str, null, null, -1);
				webService.createComputer(str, null, null, -1);
				System.out.println("ordinateur enregistré");
				break;
			case "update":
				System.out.println("quel est l'id de l'ordinateur à mettre à jour ?");
				str = sc.nextLine().trim();

				System.out.println("vous avez choisi l'odinateur : ");
				if (Pattern.matches(Regex.DIGIT.getRegex(), str)) {
					System.out.println(webService.getComputerById(Long.parseLong(str)));
				} else {
					System.err.println("id invalid");
					break;
				}

				System.out.println("nouveau nom : ");
				String name = sc.nextLine().trim();

				boolean isOk = false;
				String introduced = null;
				System.out.println("date d'introduction (format = yyyy-MM-dd HH:mm:ss) : ");
				
				//LocalDateTime intro = null;
				while (!isOk) {
					introduced = sc.nextLine().trim();

					if (!introduced.equals("")) {
						if (Pattern.matches(Regex.DATE_FORMAT_EN.getRegex(), introduced.trim())) {
							//intro = LocalDateTime.parse(introduced, formatter);
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
				//LocalDateTime discon = null;
				while (!isOk) {
					discontinued = sc.nextLine().trim();
					if (!discontinued.equals("")) {
						if (Pattern.matches(Regex.DATE_FORMAT_EN.getRegex(), discontinued.trim())) {
							//discon = LocalDateTime.parse(discontinued, formatter);
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
					idCompany = 0;
				} else {

					if (Pattern.matches(Regex.DIGIT.getRegex(), idComp)) {
						idCompany = Long.parseLong(idComp);
					} else {
						idCompany = 0;
						System.err.println("invalid company id");
					}
				}
				webService.updateComputer(Long.parseLong(str), name, introduced.trim(), discontinued.trim(), idCompany);
				System.out.println("ordinateur mi à jour");
				break;
			case "deleteComp":
				System.out.println("quel est l'id de la compagnie à supprimer : ");
				str = sc.nextLine().trim();
				if (Pattern.matches(Regex.DIGIT.getRegex(), str)) {
					webService.deleteCompany(Long.parseLong(str));
					System.out.println("company " + Long.parseLong(str) + " supprimé");
				} else {
					System.err.println("id invalid");
				}
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
