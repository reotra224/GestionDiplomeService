package gn.traore.demo.web.controllers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
		/*List<DiplomeNonSecure> listDiplomes = new ArrayList<>();
		try {
			// On récupère l'instance InfosDiplome
			infosDiplome = infos;
			
			// On fait appel à l'API pour réccupérer les diplomes à sécuriser
			listDiplomes = recupData();
			
			// On sécurise les diplomes recupérés
			for (DiplomeNonSecure diplome : listDiplomes) {
				securiserDiplome(diplome);
			}			
			
		} catch (WriterException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}*/
		return true;
	}
	
	//	Methode permettant de recupérer les diplomes non 
	// 	securisé au niveau des etablissements.
	@GetMapping(value="/recupDATA")
	private DiplomeNonSecure recupData() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<DiplomeNonSecure> reponse = 
				restTemplate.exchange(
						"http://localhost/apiRecupDATA/recupDATA.php",
						HttpMethod.GET, 
						null,
						DiplomeNonSecure.class
					);
		return reponse.getBody();
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
				dns.getNomEtudiant(), 
				dns.getPrenomEtudiant(), 
				dns.getPhotoEtudiant(), 
				niveau
			);	
		
		//Ajout de l'empreinte dans le diplome
        ds.setEmpreinte(empreinte);
		
		//On forme le nom du qrCode
		String nomQRCode = dns.getNomEtudiant() + "-" + dns.getMatriculeEtudiant();
		
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
		String infos = d.getNomEtudiant()
				+ d.getPrenomEtudiant()
				+ d.getMatriculeEtudiant();
		
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
