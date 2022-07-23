package com.example.backerp.Tools;

import com.example.backerp.Models.Associations;
import com.example.backerp.Models.Conventions;
import com.example.backerp.Models.Detailevenement;
import com.example.backerp.Models.Evenement;
import com.example.backerp.Repositories.AssociationsRepository;
import com.example.backerp.Repositories.ConventionsRepository;
import com.example.backerp.Repositories.DetailevenementRepository;
import com.example.backerp.Repositories.EvenementRepository;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;


@Component
public class Broadcaster {

    @Autowired
    private AssociationsRepository AR;
    @Autowired
    private DetailevenementRepository ER;
    @Autowired
    private EvenementRepository EE;
    @Autowired
    private ConventionsRepository CR;

    public boolean LoadMainsheet(MultipartFile excel) {

        boolean Res = false;
        try {
            System.out.println("Loading Excel File");
            XSSFWorkbook workbook = new XSSFWorkbook(excel.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                System.out.println("Loading Row " + i);
                XSSFRow row = sheet.getRow(i);
                String Association = row.getCell(0).toString();
                String Reference = row.getCell(1).toString();
                String referenceConvention = row.getCell(2).toString();
                String EvenementDescription = row.getCell(3).toString();
                // display Association and Reference in console
                System.out.println("Association : " + Association + " Reference : " + Reference);
                Associations A = new Associations();
                A.setLibelle(Association);
                A.setReference(Reference);
                if (AddAssociation(A)) {
                    Conventions C = new Conventions();
                    C.setIdassoc(Association);
                    C.setReferenceconv(referenceConvention);
                    try {
                        CR.save(C);
                        System.out.println("Convention Saved");
                    } catch (Exception E) {
                        System.out.println("Convention non trouvée");

                    }
                    if (Load_Event_Details_Sheet(excel, EvenementDescription, referenceConvention)) {
                        return true;
                    }
                }
            }
        } catch (IOException IO) {
            IO.getMessage();

        }
        return Res;
    }

    public boolean Load_Event_Details_Sheet(MultipartFile excel, String EvenementDescription, String Reference) {
        boolean Res = false;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(excel.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(1);
            int k = 0;
            while (k < sheet.getPhysicalNumberOfRows()) {
                XSSFRow row = sheet.getRow(k);
                if (row.getCell(0).toString().equals(EvenementDescription)) {
                    System.out.println("Evenement trouvé");
                    //TODO : Add Event Details
                    String CodeEvenement = row.getCell(1).toString();
                    int Prix = (int) row.getCell(2).getNumericCellValue();
                    int places = (int) row.getCell(3).getNumericCellValue();
                    String Localisation = row.getCell(4).toString();
                    int promotion = (int) row.getCell(5).getNumericCellValue();
                    Date DateDebut = row.getCell(6).getDateCellValue();
                    Date DateFin = row.getCell(7).getDateCellValue();
                    Detailevenement DE = new Detailevenement();
                    DE.setDatedeb((java.sql.Date) DateDebut);
                    DE.setDatefin((java.sql.Date) DateFin);
                    DE.setLocalisation(Localisation);
                    DE.setPrix(Prix);
                    DE.setPromotion(promotion);
                    DE.setPlaces(places);
                    //generate a  random number between 1 and 100000 and concatenate it with Det String to avoid duplicates
                    int Det = (int) (Math.random() * 100000);
                    DE.setIdetail(Det);
                    if (AddDetailEvent(DE)) {
                        System.out.println("Detail Evenement Ajouté");
                        Evenement E = new Evenement();
                        E.setDetails(Det);
                        E.setRefconv(Reference);
                        E.setCodevenement(CodeEvenement);
                        E.setDescription(EvenementDescription);
                        if (AddEvent(E)) {
                            System.out.println("Evenement Ajouté");
                            System.out.println("Finished");
                            k++;
                        }
                    }
                } else {
                    k++;
                }
                if (k == sheet.getPhysicalNumberOfRows()) {
                    System.out.println("Finished");
                    Res = true;
                }
            }

        } catch (IOException IO) {
            IO.getMessage();
        }
        return Res;
    }

    public boolean AddAssociation(Associations A) {
        System.out.println("Adding Association");
        // display A in console
        System.out.println("Libelle" + A.getLibelle() + " Reference" + A.getReference());
        boolean Res = false;

        try {
            this.AR.save(A);
            System.out.println("Association Saved");
            Res = true;
        } catch (Exception E) {
            System.out.println(E.getMessage());
        }
        return Res;
    }

    public boolean AddDetailEvent(Detailevenement Detail) {
        boolean Res = false;
        try {
            ER.save(Detail);
            System.out.println("Detail Evenement Saved");
            Res = true;
        } catch (Exception E) {
            System.out.println("Detail Evenement non trouvée");
        }
        return Res;
    }

    public boolean AddEvent(Evenement E) {
        boolean Res = false;
        try {
            EE.save(E);
            System.out.println("Evenement Saved");
            Res = true;
        } catch (Exception E1) {
            System.out.println("Evenement non trouvée");
        }
        return Res;
    }
}