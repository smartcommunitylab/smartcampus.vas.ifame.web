package eu.trentorise.smartcampus.vas.ifame.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.trentorise.smartcampus.aac.AACException;
import eu.trentorise.smartcampus.unidataservice.CanteenService;
import eu.trentorise.smartcampus.unidataservice.UnidataServiceException;
import eu.trentorise.smartcampus.unidataservice.model.CanteenOpening;
import eu.trentorise.smartcampus.vas.ifame.model.Mensa;
import eu.trentorise.smartcampus.vas.ifame.repository.MensaRepository;
import eu.trentorise.smartcampus.vas.ifame.utils.EasyTokenManger;

@Controller("IFrettaController")
public class IFrettaController {

	private static final Logger log = Logger.getLogger(IFrettaController.class);

	@Autowired
	MensaRepository mensaRepository;

	@Autowired
	@Value("${profile.address}")
	private String profileaddress;
	@Autowired
	@Value("${client.id.sc}")
	private String clientId;
	@Autowired
	@Value("${client.secret.sc}")
	private String clientSecret;

	private EasyTokenManger tkm;

	@Autowired
	@Value("${url.studente.service}")
	private String URLStudenteService;

	private CanteenService studentInfoService;

	@PostConstruct
	private void initStudentInfoService() {
		studentInfoService = new CanteenService(URLStudenteService);
		tkm = new EasyTokenManger(profileaddress, clientId, clientSecret);
	}

	private static final Logger logger = Logger
			.getLogger(IFrettaController.class);

	@RequestMapping(method = RequestMethod.GET, value = "/getmense")
	public @ResponseBody
	List<Mensa> getMense(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException {
		try {
			logger.info("/getmense");
			 updateMense();
			List<Mensa> reList = new ArrayList<Mensa>();
			List<CanteenOpening> mense = studentInfoService.getOpening(tkm
					.getClientSmartCampusToken());
			//Mensa m = new Mensa("1", "test", "http://www.google.com",
			//		"http://www.facebook.com");
			//reList.add(m);
			 for (CanteenOpening canteenOpening : mense) {
			 Mensa x = mensaRepository.findOne(canteenOpening.getId());
			 x.setTimes(canteenOpening.getTimes());
			 reList.add(x);
			 }

			return reList;

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return null;
	}

	@PostConstruct
	public void updateMense() throws AACException, SecurityException,
			UnidataServiceException {
		log.debug("Update mense in local");

		// *************************************************************
		// finche non ci sono aggiornamenti sui link delle webcam o nomi delle
		// mense lasciare tutto commentato
		// *************************************************************
		// /*
		// *
		// * Inizializzo le mense
		// */
		final String url_povo_mensa_offline = "http://www.operauni.tn.it/upload/cms/456_x/mensa-povo2.jpg";
		final String url_povo_mensa_veloce_offline = "http://www.operauni.tn.it/upload/cms/456_x/mensa-povo1.jpg";
		final String url_tommaso_gar_offline = "http://www.operauni.tn.it/upload/cms/456_x/gar-offline.jpg";
		final String url_zanella_offline = "http://www.operauni.tn.it/upload/cms/456_x/mensa-zanella.jpg";
		final String url_mesiano_1_offline = "http://www.operauni.tn.it/upload/cms/456_x/mesiano-offline.jpg";
		final String url_mesiano_2_offline = "http://www.operauni.tn.it/upload/cms/456_x/mesiano-web-2.jpg";

		final String url_povo_mensa_online = "http://www.operauni.tn.it/upload/Webcam/Povo02.jpg";
		final String url_povo_mensa_veloce_online = "http://www.operauni.tn.it/upload/Webcam/Povo01.jpg";
		final String url_tommaso_gar_online = "http://www.operauni.tn.it/upload/Webcam/MensaUni.jpg";
		final String url_zanella_online = "http://www.operauni.tn.it/upload/Webcam/mensa_zanella.jpg";
		final String url_mesiano_1_online = "http://www.operauni.tn.it/upload/Webcam/MensaMes01.jpg";
		final String url_mesiano_2_online = "http://www.operauni.tn.it/upload/Webcam/MensaMes02.jpg";

		//
		// final String name_povo_mensa = "Povo Mensa";
		// final String name_povo_mensa_veloce = "Povo Mensa Veloce";
		// final String name_tommaso_gar = "Tommaso Gar";
		// final String name_zanella = "Zanella";
		// final String name_mesiano_1 = "Mesiano 1";
		// final String name_mesiano_2 = "Mesiano 2";

		// mense tornate da unidata:

		// Mensa + sottotipo:
		// descr_zona descr_mensa
		// Mesiano Bar (orario 7:30-18)
		// Mesiano Pranzo
		// Povo0 Pranzo
		// Povo1 (Polo Ferrari) Pranzo (linea standard)
		// Povo1 (Polo Ferrari) Bar (orario 7.30-18)
		// Povo1 (Polo Ferrari) Pranzo (linea veloce)
		// S. Bartolameo Bar (orario 7.30-14.30 17-22)
		// T. Gar Cena (linea standard)
		// T. Gar Pranzo (pizzeria)
		// T. Gar Pranzo (linea standard)
		// T. Gar Cena (pizzeria)
		// T. Gar Pranzo (paninoteca)
		// Zanella Pranzo
		// Zanella Cena
		//
		// Solo mensa:
		// descr_zona
		// Zanella
		// Mesiano
		// S. Bartolameo
		// Povo1 (Polo Ferrari)
		// T. Gar
		// Povo0

		// List<String>
		// mense="[{\"id\":\"323031332d31312d30315f63\",\"date\":\"2013-11-01\",\"dishes\":[{\"name\":\"Zuppa di farro e fagioli\",\"cal\":\"485\"},{\"name\":\"Pasta panna e prosciutto\",\"cal\":\"619\"},{\"name\":\"Pasta cacio e pepe\",\"cal\":\"605\"},{\"name\":\"Saltimbocca alla romana\",\"cal\":\"312\"},{\"name\":\"Bruschetta rustica\",\"cal\":\"346\"},{\"name\":\"Patate al forno\",\"cal\":\"292\"},{\"name\":\"Carciofi saltati\",\"cal\":\"164\"}],\"type\":\"c\"},{\"id\":\"323031332d31312d30315f70\",\"date\":\"2013-11-01\",\"dishes\":[{\"name\":\"Zuppa di ceci\",\"cal\":\"472\"},{\"name\":\"Pasta alla marinara\",\"cal\":\"450\"},{\"name\":\"Wurstel farciti\",\"cal\":\"370\"},{\"name\":\"Merluzzo alle olive e capperi\",\"cal\":\"184\"},{\"name\":\"Crocchette di patate\",\"cal\":\"350\"},{\"name\":\"Carote prezzemolate\",\"cal\":\"140\"}],\"type\":\"p\"}]";

		List<CanteenOpening> mense = studentInfoService.getOpening(tkm
				.getClientSmartCampusToken());

		for (CanteenOpening canteenOpening : mense) {
			Mensa newMensa = new Mensa(canteenOpening);

			if (canteenOpening.getCanteen() == "Povo1 (Polo Ferrari) Pranzo (linea standard)") {
				newMensa.setMensa_link_offline(url_povo_mensa_offline);
				newMensa.setMensa_link_online(url_povo_mensa_online);
			} else if (canteenOpening.getCanteen() == "Povo1 (Polo Ferrari) Pranzo (linea veloce)") {
				newMensa.setMensa_link_offline(url_povo_mensa_veloce_offline);
				newMensa.setMensa_link_online(url_povo_mensa_veloce_online);
			} else if (canteenOpening.getCanteen() == "T. Gar") {
				newMensa.setMensa_link_offline(url_tommaso_gar_offline);
				newMensa.setMensa_link_online(url_tommaso_gar_online);
			} else if (canteenOpening.getCanteen() == "Zanella") {
				newMensa.setMensa_link_offline(url_zanella_offline);
				newMensa.setMensa_link_online(url_zanella_online);
			} else if (canteenOpening.getCanteen() == "Mesiano") {
				newMensa.setMensa_link_offline(url_mesiano_1_offline);
				newMensa.setMensa_link_online(url_mesiano_1_online);
			} else if (canteenOpening.getCanteen() == "Mesiano Bar") {
				newMensa.setMensa_link_offline(url_mesiano_2_offline);
				newMensa.setMensa_link_online(url_mesiano_2_online);
			}

			mensaRepository.save(newMensa);

		}

	}

}
