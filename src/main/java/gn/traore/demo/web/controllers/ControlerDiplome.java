package gn.traore.demo.web.controllers;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import gn.traore.demo.dao_service.*;
import gn.traore.demo.entities.*;

@RestController
public class ControlerDiplome {
	
	@Autowired
	private DiplomeRepository serviceDaoDiplome;
	
	@Autowired
	private NiveauRepository serviceNiveau;
	
	private InfosDiplome infosDiplome;
	
	@PostMapping(value="/secureUnDiplome")
	public DiplomeSecure securiserUnDiplome(@RequestBody DiplomeNonSecure d) {
		DiplomeSecure dip = new DiplomeSecure();
		try {
			dip = securiserDiplome(d);
		} catch (WriterException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return dip;
	}
	
	@PostMapping(value="/secureListeDiplomes")
	public boolean securiserListeDiplome(@RequestBody InfosDiplome infos) {
		DiplomeNonSecure[] listDiplomes = null;
		try {
			// On récupère l'instance InfosDiplome
			infosDiplome = infos;
			
			// On fait appel à l'API pour réccupérer les diplomes à sécuriser
			listDiplomes = recupData(infos);
			
			// On sécurise les diplomes recupérés
			for (DiplomeNonSecure diplome : listDiplomes) {
				securiserDiplome(diplome);
			}
			
			return true;
			
		} catch (WriterException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	//	Methode permettant de recupérer les diplomes non 
	// 	securisé au niveau des etablissements.
	@PostMapping(value="/recupDATA")
	private DiplomeNonSecure[] recupData(@RequestBody InfosDiplome infos) {
		String url = "http://localhost/apiRecupDATA/recupDATA.php";
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders requestHeaders = new HttpHeaders();		
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		//requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        //requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        
        HttpEntity<InfosDiplome> requestEntity = new HttpEntity<>(infos, requestHeaders);    
        
      //adding the query params to the URL
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("filiere", infos.getFiliere())
                .queryParam("niveau", infos.getNiveau())
        		.queryParam("promotion", infos.getPromotion());
        
		ResponseEntity<DiplomeNonSecure[]> reponse = 
				restTemplate.exchange(
						uriBuilder.toUriString(),
						HttpMethod.GET, 
						requestEntity,
						DiplomeNonSecure[].class
					);
		if (reponse.getStatusCode() == HttpStatus.OK) {
            System.out.println("response received");
            System.out.println(reponse.getBody().toString() + " ==> " + reponse.getHeaders().getContentType());
            
            return reponse.getBody();
        } else {
            System.out.println("error occurred");
            System.out.println(reponse.getStatusCode());
            return null;
        }
		
	}
	
	/*@GetMapping(value="/Alldiplomes")
	public Collection<DiplomeNonSecure> allDiplomes() {
		return recupData();
	}*/
	
	private DiplomeSecure securiserDiplome(DiplomeNonSecure dns) throws WriterException, IOException {
		// On génère une empreinte pour le diplome
		String empreinte = genereEmpreinte(dns);
		
		//On récupère le niveau
		Niveau niveau = serviceNiveau.findNiveau(infosDiplome.getNiveau());
		
		//On cré le diplome sécurisé
		DiplomeSecure ds = new DiplomeSecure(
				infosDiplome.getFiliere(), 
				infosDiplome.getPromotion(), 
				dns.getNom(), 
				dns.getPrenom(), 
				dns.getPhoto(), 
				niveau
			);	
		
		//Ajout de l'empreinte dans le diplome
        ds.setEmpreinte(empreinte);
		
		//On forme le nom du qrCode
		String nomQRCode = dns.getNom() + "-" + dns.getMatricule();
		
		//On génère le qrCode
		String qrCode = genererQRCode(empreinte, nomQRCode);
		
		//Ajout du qrCode dans le diplome
        ds.setCodeQR(qrCode);
        
        //On enregistre le diplome sécurisé
        serviceDaoDiplome.save(ds);
        
        // On retourne le diplome sécurisé
		return ds;
	}
	
	private String genereEmpreinte(DiplomeNonSecure d) {
		// Préparation des informations, pour la 
		// génération de l'empreinte.
		String infos = d.getNom()
				+ d.getPrenom()
				+ d.getMatricule();
		
		// Préparation de l'empreinte
        MessageDigest empreinte = null;
        try {
        	// On recupère une instance de l'algorithme de hashage SHA-256
            empreinte = MessageDigest.getInstance("SHA-256");
        } catch(NoSuchAlgorithmException e) {
            System.err.println("Problème avec SHA : " + e);
            System.exit(-1);
        }

        // Calcul de l'empreinte
        byte[] bytes = empreinte.digest(infos.getBytes());
        String resultat = new String(bytes);
        
        return resultat;
		
	}
	
	private String genererQRCode(String qrCodeText, String qrCodeName)
			throws WriterException, IOException{
		
		String filePath = qrCodeName + ".png";
		int size = 250;
		String fileType = "png";
		File qrFile = new File(filePath);
		
		/*******************************/
		// Create the ByteMatrix for the QR-Code that encodes the given String
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// Paint and save the image using the ByteMatrix
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		ImageIO.write(image, fileType, qrFile);
		
		return filePath;		
	}
	
}
